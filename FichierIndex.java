/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moteur_recherche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rabaa
 */
public class FichierIndex {
    
    Connection con;
    int nbre_doc;
public TreeMap getIdDocTerm()
{
        
     TreeMap<String,ArrayList>tree=new TreeMap<String,ArrayList>();
     ArrayList<String> liste_terme=new ArrayList<String>();
     con=BD.openConnection();
     try {
            
         String r0="select terme from terme";
         ResultSet rs0=con.createStatement().executeQuery(r0);
         while(rs0.next())
         {
          liste_terme.add(rs0.getString(1));
          System.out.println(rs0.getString(1));
         }
         
         for(int i=0;i<liste_terme.size();i++)
         {
             String r1 ="SELECT ref_terme from terme where terme=?";             
             PreparedStatement stm1;
             stm1 = con.prepareStatement(r1);
             stm1.setString(1,liste_terme.get(i));
             ResultSet rs1 = stm1.executeQuery();  
             rs1.next();
             int ref_term=rs1.getInt(1);
             String r2 ="SELECT ref_elem from elem_terme where ref_terme=?";             
             PreparedStatement stm2=con.prepareStatement(r2);
             stm2.setInt(1,ref_term);
             ResultSet rs2 = stm2.executeQuery();  
            ArrayList<Integer> liste_idoc=new ArrayList<Integer>();
            while(rs2.next()){
                String r3 ="SELECT distinct ref_doc,ref_elem from doc_elem where ref_elem=?";             
                PreparedStatement stm3=con.prepareStatement(r3);
                stm3.setInt(1,rs2.getInt(1));
                ResultSet rs3 = stm3.executeQuery();
                while(rs3.next()){
                System.out.println(rs3.getInt(1));
            	liste_idoc.add(rs3.getInt(1));
            }}
            System.out.println("//");
            tree.put(liste_terme.get(i),liste_idoc);
        } 
         
         }catch (SQLException e) {
            System.out.println("Erreur getOccurencesOfTerme"+e.toString());
        }
    
    return tree;
 }
 
public TreeMap listerNoeudTerme()
{
  TreeMap<String,ArrayList<Integer>> terme_idDoc;
  TreeMap<Integer,Vector<ElementPertinent>>doc_elmP=null;
  TreeMap<String,TreeMap<Integer,Vector<ElementPertinent>>>index=new TreeMap<String,TreeMap<Integer,Vector<ElementPertinent>>>();  
 
  terme_idDoc=getIdDocTerm();
  for(String key: terme_idDoc.keySet())
  {      try {
         
          for(int i=0;i<terme_idDoc.get(key).size();i++)
          {Vector<ElementPertinent> ElemPertinents=new Vector<ElementPertinent>();
             String r1 ="SELECT ref_terme from terme where terme=?";             
             PreparedStatement stm;
             stm = con.prepareStatement(r1);
             stm.setString(1,key);
             ResultSet rs1 = stm.executeQuery();  
             rs1.next();
             int ref_term=rs1.getInt(1);
             String r2 ="SELECT ref_elem,nbretermEl,COUNT(*) AS frq  from elem_terme where ref_terme=? group by ref_elem";             
             PreparedStatement stm1=con.prepareStatement(r2);
             stm1.setInt(1,ref_term);
             ResultSet rs2 = stm1.executeQuery();  
            while(rs2.next())
           {
               int ref_elem=rs2.getInt(1);
               int frq=rs2.getInt(2);
               int ef=rs2.getInt(3);
               String R3="select chemin_elem,id_parent from element where ref_elem="+ref_elem;
               ResultSet chem=con.createStatement().executeQuery(R3);
               chem.next();
               String R4="select ref_doc from doc_elem where ref_elem="+ref_elem;
               ResultSet ref_doc=con.createStatement().executeQuery(R4);
               ref_doc.next();
               int rd=ref_doc.getInt(1);
               String R5="select chemin_doc,nbre_termeT from document where ref_doc="+rd;
               ResultSet chemin_doc=con.createStatement().executeQuery(R5);
               chemin_doc.next();
               String cd=chemin_doc.getString(1);
               int longDoc=chemin_doc.getInt(2);
               String R6="select count(distinct ref_doc)  frq,sum(nbre_termeT) as nbre from document ";
               ResultSet longDoc_moy=con.createStatement().executeQuery(R6);
               longDoc_moy.next();
               nbre_doc=longDoc_moy.getInt(1);
               double logmoy=longDoc_moy.getInt(2)/longDoc_moy.getInt(1);
               ElementPertinent ep=new ElementPertinent(ref_elem,chem.getString(1),chem.getInt(2),cd,terme_idDoc.get(key).get(i));
               if(!ElemPertinents.contains(ep))
               {
                  
                   ep.setTf((double)frq/(frq+0.5+1.5*(longDoc/logmoy)));
                   ElemPertinents.add(ep);
               }
               else
               { 
                   ElementPertinent dd=((ElementPertinent)ElemPertinents.elementAt(ElemPertinents.indexOf(ep)));
                   dd.setScore(dd.getScore()+(double)frq/(double)ef);
               }
               
            } 
           // Collections.sort(ElemPertinents);
            doc_elmP=new TreeMap<Integer,Vector<ElementPertinent>>();
            doc_elmP.put(terme_idDoc.get(key).get(i), ElemPertinents);
            }
         index.put(key,doc_elmP);
          } 
      catch (SQLException ex) {
                  Logger.getLogger(FichierIndex.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
  
return index;
}

public TreeMap calculPoid()
{
  
  TreeMap<String,TreeMap<Integer,Vector<ElementPertinent>>>index=listerNoeudTerme();
            
          
  for(String key: index.keySet())
  {
      TreeMap<Integer,Vector<ElementPertinent>> tt=index.get(key);
       
           for(Integer key1: tt.keySet())
           {
               
              for(int i=0;i<tt.get(key1).size();i++)
              {
                 tt.get(key1).get(i).setIdf(index.get(key).size()/nbre_doc);
                 tt.get(key1).get(i).setScore(tt.get(key1).get(i).getIdf()*tt.get(key1).get(i).getTf());
              }
              Collections.sort(tt.get(key1));
           }
  
  
  }     
  
 return index;
}

public TreeMap PropagationTerme()
{
    TreeMap<String,TreeMap<Integer,Vector<ElementPertinent>>>index=calculPoid();
    
     for(String key: index.keySet())
    {
      TreeMap<Integer,Vector<ElementPertinent>> tt=index.get(key);
       
           for(Integer key1: tt.keySet())
           {
               
              for(int i=0;i<tt.get(key1).size();i++)
              {
                  if(tt.get(key1).get(i).getIdparent()!=0)
              
              }
              
           }
    }
    
    
return index;
}
public static void main(String[] args) {
        // TODO code application logic here
        TreeMap<String,ArrayList<Integer>> t;
        TreeMap<String,TreeMap<Integer,Vector<ElementPertinent>>>index;
       FichierIndex fi=new FichierIndex();
       t=fi.getIdDocTerm();
        for(String key2: t.keySet())
       {System.out.println(key2);for(int i=0;i<t.get(key2).size();i++)
           {
            System.out.print(" :::  "+t.get(key2).get(i) );
           }}
       index=fi.calculPoid();
       for(String key: index.keySet())
       {
          System.out.println(key );
       
         TreeMap<Integer,Vector<ElementPertinent>> tt=index.get(key);
       
           for(Integer key1: tt.keySet())
           {
              System.out.println(" "+key1  );
              for(int i=0;i<tt.get(key1).size();i++)
              {
               System.out.println(" :: "+ tt.get(key1).get(i).getCheminElem());
              }
           }
    
       }
}}
