/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moteur_recherche;
import java.sql.*;
import java.util.*;
public class Recherche {
//Vector ref=new Vector();
public  Vector ElemPertinents=new Vector();
Connection con;

void calculScore(String req)
{
   // docsPertinents.removeAllElements();
    try{
           
         StringTokenizer st = new StringTokenizer(req," \n \t \"; :'{}[]$&^| , \\ / * + - !?><().=  ");
         while(st.hasMoreTokens()){
             String s=st.nextToken().toLowerCase();
             // n'est pas un mot vide
             if(!StopList.motVide(s)){
                 //ouverture connexion
            s=new FrenchStemmer().stem(s);
            con=BD.openConnection();
             //Porter pour la racinisation
            
          
            String R1="select terme, count(distinct ref_elem) as ef from elem_terme where terme= \""+s+"\" group by terme";
            
            ResultSet rs1=con.createStatement().executeQuery(R1);
            rs1.next();
            int ef=rs1.getInt(2);
           
           String R2="SELECT terme,  ref_elem, COUNT(*) AS frq FROM  elem_terme where terme=\""+s+"\" GROUP BY terme, ref_elem";
           ResultSet rs2=con.createStatement().executeQuery(R2);
          
           while(rs2.next())
           {
               
               int ref_elem=rs2.getInt(2);
               int frq=rs2.getInt(3);
               String R3="select chemin_elem from element where ref_elem="+ref_elem;
               ResultSet chem=con.createStatement().executeQuery(R3);
               chem.next();
               
               String R4="select ref_doc from doc_elem where ref_elem="+ref_elem;
               ResultSet ref_doc=con.createStatement().executeQuery(R4);
               ref_doc.next();
               int rd=ref_doc.getInt(1);
               String R5="select chemin_doc from document where ref_doc="+rd;
               ResultSet chemin_doc=con.createStatement().executeQuery(R5);
               chemin_doc.next();
               String cd=chemin_doc.getString(1);
               
               ElementPertinent ep=new ElementPertinent(ref_elem,chem.getString(1), cd," ");
               if(!ElemPertinents.contains(ep))
               {
                  
                   ep.setScore((double)frq/ef);
                   ElemPertinents.add(ep);
               }
               else
               { 
                   ElementPertinent dd=((ElementPertinent)ElemPertinents.elementAt(ElemPertinents.indexOf(ep)));
                   dd.setScore(dd.getScore()+(double)frq/(double)ef);
               }
               
            }//fin while next
           con.close();
           
           }//fin if
    }
         Collections.sort(ElemPertinents);
         afficheChemin();

         
    }catch(Exception e){System.out.println(e.getMessage());}
}
public void afficheChemin()
{
    try{
    for(int i=0;i<ElemPertinents.size();i++)
         { 
             ElementPertinent ep=(ElementPertinent)ElemPertinents.elementAt(i);
          System.out.println(ep.getCheminDoc()+"   "+ep.getCheminElem()+"    score="+ep.getScore());

         }//fin for
    con.close();
}//fin try
    catch(Exception e){System.out.println(e.getMessage());}
}//fin afficheChemin
}
