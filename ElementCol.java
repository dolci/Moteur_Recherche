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
public class ElementCol {
    private int ref_elem;
    private String chemin_elem;
    private int id_parent;
   
    public ElementCol(int r,String t,int i)
    {
        ref_elem=r;
        chemin_elem=t;
        id_parent=i;
        
    }
    public int getRefElem()
    {
        return ref_elem;
    }
   
    public int getIdparent()
    {
        return id_parent;
    }
    public void setIdparent(int id)
    {
         id_parent=id;
    }
    public String getCheminElem()
    {
        return chemin_elem;
    }
    public int getRefParent()
    {
        return id_parent;
    }
    public void save()
    {
        try{
        PreparedStatement  stm;
        Connection con=BD.openConnection();
        //insrtion dans la table document
        stm=con.prepareStatement("insert into element (ref_elem,chemin_elem, id_parent) values(?, ?, ?)");
        stm.setInt(1, getRefElem());
        stm.setString(2, getCheminElem());
        stm.setInt(3, getRefParent());
         
        System.out.println(stm.executeUpdate());
        con.close();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
    public void update()
    {
        try{
        PreparedStatement  stm;
        Connection con=BD.openConnection();
        //insertion dans la table document
       
        stm=con.prepareStatement("update element set id_parent=? where ref_elem=?");
         stm.setInt(1, getIdparent());
        stm.setInt(2, getRefElem());
        
         
        stm.executeUpdate();
        con.close();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
}
