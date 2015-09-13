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
public class Collection {
    private  int ref_col=0;
    private String chemin_col;
    private int nbre_doc;
    public Collection( int r,String c)
    {
        ref_col=r;
        chemin_col=c;
        nbre_doc=0;
    }
    public int getRefCol()
    {
        return ref_col;
    }
     public int getnbreDoc()
    {
        return nbre_doc;
    }
    public String getCheminCol()
    {
        return chemin_col;
    }
    public void setnbredoc(int n)
    {
        nbre_doc=n;
    }
    public void save()
    {
        try{
        PreparedStatement  stm;
        Connection con=null;
        con=BD.openConnection();
        //insrtion dans la table document
        stm = con.prepareStatement("insert into collection (ref_col,chemin_col,nbre_doc) values( ?, ? , ?)");
        stm.setInt(1, getRefCol());
        stm.setString(2, getCheminCol());
         stm.setInt(3, getnbreDoc());
        stm.executeUpdate();
        con.close();
        con.close();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
}
