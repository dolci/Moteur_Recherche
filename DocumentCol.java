package moteur_recherche;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Med
 */
import java.sql.*;
public class DocumentCol {
    protected  int ref_doc;
    protected String chemin_doc;
    private int nbre_termeT;
    public DocumentCol( int r,String c)
    {
        ref_doc=r;
        chemin_doc=c;
        this.nbre_termeT=0;
    }
    public int getRefDoc()
    {
        return ref_doc;
    }
    public int getNbreTerme()
    {
        return nbre_termeT;
    }
    public void setNbreTerme(int n)
    {
     nbre_termeT=n;
    }
    public String getCheminDoc()
    {
        return chemin_doc;
    }
    public void save()
    {
        try{
        PreparedStatement  stm;
        Connection con=BD.openConnection();
        //insertion dans la table document
        stm=con.prepareStatement("insert into document (ref_doc,chemin_doc,nbre_termeT) values(?, ?, ?)");
        stm.setInt(1, getRefDoc());
        stm.setString(2, getCheminDoc());
          stm.setInt(3, getNbreTerme());
        stm.executeUpdate();
        con.close();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
    public void update()
    {
        try{
        PreparedStatement  stm;
        Connection con=BD.openConnection();
        //insertion dans la table document
       
        stm=con.prepareStatement("update document set nbre_termeT=? where ref_doc=?");
         stm.setInt(1, getNbreTerme());
         stm.setInt(2, getRefDoc());  
        stm.executeUpdate();
        con.close();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
    
   }
