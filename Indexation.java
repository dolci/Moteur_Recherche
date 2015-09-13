/* 
 * To change this template, choose Tools | Templates 
 * and open the template in the editor. 
 */
package moteur_recherche;
import com.sun.org.apache.xerces.internal.parsers.DOMParser; 
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
  
/** 
 * 
 * @author Med 
 */
  
  
public class Indexation { 
  
     int ref_elem=0; 
     int ref_doc=0;
     int ref_col=0;
      int ref_terme=0;
      int id_parent=0;
      String chemin_doc;
  
    PreparedStatement  stm =null;
    private Connection con=null;
    private ArrayList<ArrayList> liste;
    private ArrayList<ElementCol> listelem;
    private ArrayList listeterm;
    private ArrayList<Terme> lisTerme;
   
   int som=0;
    
    public void indexer(String chemin_col)
    {
    try{
    //ouverture de la connexion 
    con=BD.openConnection();
    //suppression des enregistrements ds tables
    stm = con.prepareStatement("delete  from elem_terme");
    stm.executeUpdate();
    stm = con.prepareStatement("delete from doc_elem");
    stm.executeUpdate();
    stm = con.prepareStatement("delete from col_doc");
    stm.executeUpdate();
    stm = con.prepareStatement("delete from collection");
    stm.executeUpdate();
    stm = con.prepareStatement("delete from document");
    stm.executeUpdate();
    stm = con.prepareStatement("delete from element");
    stm.executeUpdate();
    stm = con.prepareStatement("delete from terme");
    stm.executeUpdate();
    System.out.println("fin de suppression des enregistrements");
   
    Collection col=new Collection(++ref_col,chemin_col);
    
    //parcours des documents 
    File rep   = new File(chemin_col);
    File f1[] = rep.listFiles(); 
    col.setnbredoc(f1.length);
    //insertion dans la table collection
    col.save();
   // System.out.println(f1.length);
    con.close();
    listelem= new ArrayList<ElementCol>();
    listeterm=new ArrayList();
    for(int  p=0;p<f1.length;p++)
    {
        chemin_doc=f1[p].getPath();
        con=BD.openConnection(); 
        DocumentCol doc=new DocumentCol(++ref_doc,chemin_doc);
         doc.save();
        System.out.println("i "+p+" f1.length "+f1.length+chemin_doc);
        //insertion dans la table col_doc
        con=BD.openConnection(); 
            stm=con.prepareStatement("insert into col_doc (ref_col,ref_doc) values(?, ?)");
            stm.setInt(1, ref_col);
            stm.setInt(2, ref_doc);
            stm.executeUpdate();
            
       con.close();
      DOMParser parserDocument = new DOMParser();      
      parserDocument.parse(chemin_doc); 
      Document document = parserDocument.getDocument(); 
      Element racineDoc=document.getDocumentElement(); 
      //enregistrement du noeud racine
      ElementCol elemCol=new ElementCol(++ref_elem,XPathUtils.getXPath((Node)racineDoc),0);
      listelem.add(elemCol);
      System.out.println(XPathUtils.getXPath((Node)racineDoc));
      //elemCol.save();
       con=BD.openConnection();
     parcourirXML(racineDoc);
     con.close();
      con=BD.openConnection();
    //  System.out.println("aaa"+listeterm.size()); 
      doc.setNbreTerme(listeterm.size());
      doc.update();
     con.close();
     //id parent
    int j=listelem.size()-1,l;
      //  System.out.println("j"+j); 
        l=j-1;
        while(j>0)
        { 
          
            //System.out.println("term 1"+listelem.get(j).getCheminElem());
            // System.out.println("term 2"+listelem.get(l).getCheminElem());
            
                 
            if(listelem.get(j).getCheminElem().split("\\/").length==listelem.get(l).getCheminElem().split("\\/").length)
            { l--;}
            else if(listelem.get(j).getCheminElem().split("\\/").length==listelem.get(l).getCheminElem().split("\\/").length+1)
            {
                for(int m=l+1;m<=j;m++)
                {
                   listelem.get(m).setIdparent(listelem.get(l).getRefElem());
                   listelem.get(m).update();
                  // System.out.println("term m=="+m+"ref "+listelem.get(m).getRefElem()+" kkk "+listelem.get(l).getRefElem());
                }
              j=l;
            }
            else if(listelem.get(j).getCheminElem().split("\\/").length==listelem.get(0).getCheminElem().split("\\/").length+1 &&
                   listelem.get(j).getCheminElem().split("\\/").length<listelem.get(l).getCheminElem().split("\\/").length )
            {
               listelem.get(j).setIdparent(listelem.get(0).getRefElem());
               j=j-1;
            }
                
        }
        con=BD.openConnection();
     for(int a=0;a<listelem.size();a++) 
     {
         listelem.get(a).save(); 
         stm=con.prepareStatement("insert into doc_elem (ref_elem,ref_doc) values(?, ?)");
                 stm.setInt(1, listelem.get(a).getRefElem());
                 stm.setInt(2,doc.getRefDoc()); 
                 stm.executeUpdate();
     }
     
     con.close();
         
            System.out.println("i "+p+" f1.length "+f1.length+chemin_doc); 
            listeterm.clear();
            listelem.clear();
         }
//      doc.setNbreTerme(listeterm.size());
      //doc.update();
      ArrayList<Terme> listTo=Terme.affiche();
         ArrayList<Integer> listelmT=new ArrayList<Integer>();
        for(int s=0;s<listTo.size();s++)
             listelmT.add(listTo.get(s).getRefElem());
        ArrayList<Terme> listTo1=new ArrayList<Terme>();
         ArrayList couple=new ArrayList();
       /*System.out.println("refterm             terme         ref elem");
         for(int c=0;c<listTo.size();c++){System.out.println(" "+listTo.get(c).getRefTerme()+" "+listTo.get(c).getTerme()+"  "+listTo.get(c).getRefElem());}
         System.out.println("size = "+listTo.size());*/
         for(int c=0;c<listTo.size()-1;c++)
            {int nbre=1;
             //System.out.println(listTo.get(c).getTerme()+" " );
                for(int d=c+1;d<listTo.size();d++)
                {
                    
                  if(listTo.get(c).getTerme().equals(listTo.get(d).getTerme())&&listTo.get(c).getRefElem()==listTo.get(d).getRefElem()) 
                     {  //System.out.print( "kkkk"+listTo.get(d).getTerme()+" " );
                      /* System.out.println("t1= "+listTo.get(c).getTerme()+" te2"+listTo.get(d).getTerme());
                         if(listTo.get(c).getRefElem()!=listTo.get(d).getRefElem())
                         {couple.add(listTo.get(d).getRefElem());couple.add(listTo.get(c).getRefTerme());couple.add(nbre);}
                         else { nbre++;}*/
                         nbre++;listTo.remove(d);}
                  else if(listTo.get(c).getTerme().equals(listTo.get(d).getTerme())&&listTo.get(c).getRefElem()!=listTo.get(d).getRefElem())
                  { //System.out.print( "ppp "+listTo.get(d).getTerme()+" " );
                      couple.add(listTo.get(d).getRefElem());couple.add(listTo.get(c).getRefTerme());couple.add(nbre);
                         listTo.remove(d);}//listelmT.remove(d);  }  
                }
                //listTo1.add(listTo.get(c));
                
                couple.add(listTo.get(c).getRefElem());couple.add(listTo.get(c).getRefTerme());couple.add(nbre);
            }
        for(int i=0;i<listTo.size();i++){System.out.println(" "+listTo.get(i).getRefTerme()+" "+listTo.get(i).getTerme()+"  "+listTo.get(i).getRefElem());}
         System.out.println("size = "+listTo.size());
            con=BD.openConnection();
           
            stm = con.prepareStatement("delete  from terme");
           stm.executeUpdate();
            stm = con.prepareStatement("delete  from elem_terme");
           stm.executeUpdate();
           con.close();
            System.out.println("size = "+listTo.size());
             con=BD.openConnection();
            for(int i=0;i<listTo.size();i++)
              listTo.get(i).save();
            con.close();
            con=BD.openConnection();
           for( int i=0;i<couple.size();i=i+3)
           {
                System.out.println("i="+i+" : "+couple.get(i) +" "+couple.get(i+1)+" "+couple.get(i+2));
                 stm=con.prepareStatement("insert into elem_terme (ref_elem,ref_terme,nbretermEl) values(?, ?, ?)");
                 stm.setInt(1,Integer.parseInt(couple.get(i).toString()));
                 stm.setInt(2,Integer.parseInt(couple.get(i+1).toString())); 
                 stm.setInt(3,Integer.parseInt(couple.get(i+2).toString()));
                 stm.executeUpdate();
                
              
             } con.close();
    
   // con.close();
    System.out.println("fermeture connexion");
  }catch(Exception e){System.out.println(e.getMessage());}
        
  }//fin indexer 
   
     void parcourirXML(Element elem) 
    { 
     // int som1,som=0; 
      int re=0;
         try{ 
                
       NodeList children = elem.getChildNodes(); 
       
        for (int i = 0; i < children.getLength(); i++) 
        {
            re=0;
             Node n = children.item(i); 
             
        if(n.getNodeType()==Node.TEXT_NODE )
             {
                 
         StringTokenizer st = new StringTokenizer(n.getNodeValue()," \n \t \"; :'{}[]$&^| , \\ / * + - !?><().=  ");
       
         while(st.hasMoreTokens()){
             String s=st.nextToken().toLowerCase();
             listeterm.add(s);
             //test pas un mot vide
             if(!StopList.motVide(s)){
             //Porter pour la racinisation
            s=new FrenchStemmer().stem(s);
            Terme t=new Terme(++ref_terme, s,ref_elem);
                        //insertion dans la table terme
            t.save();
            
           
            }//fin if
        }//fin while
                
  }//fin if node text     
         if (n.getNodeType() == Node.ELEMENT_NODE) { 
             ElementCol elemCol=new ElementCol(++ref_elem,XPathUtils.getXPath(n),0);
             listelem.add(elemCol);
            
           parcourirXML((Element)n);    
            }//fin node type 
        }//fin parcours children */
        }catch(Exception e){}         
    } //fin parcours
      
    
    
   /* public static void main(String []args)
    {
        System.out.println("debut prog");
       new Indexation().indexer("collection1");
        System.out.println("fin prog");

    }//fin main
     */ 
} 