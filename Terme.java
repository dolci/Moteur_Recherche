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
import java.util.ArrayList;
public class Terme {
    private int ref_terme;
    private String terme;
    private int ref_elem;
    private int nbre_rep;
    public Terme(int r,String t,int ref_elem)
    {
        ref_terme=r;
        terme=t;
        this.ref_elem=ref_elem;
        nbre_rep=1;
    }
    public Terme(int r,String t,int ref_elem,int n)
    {
        ref_terme=r;
        terme=t;
        this.ref_elem=ref_elem;
        nbre_rep=n;
    }
    public int getRefTerme()
    {
        return ref_terme;
    }
    public String getTerme()
    {
        return terme;
    }
    public int getRefElem()
    {
        return ref_elem;
    }
      public int getNbre()
    {
        return nbre_rep;
    }
       public void setNbre(int n)
    {
        nbre_rep=n;
    }
    public void save()
    {
        try{
        PreparedStatement  stm;
        Connection con=BD.openConnection();
        //insrtion dans la table document
        stm=con.prepareStatement("insert into terme (ref_terme,terme,ref_elem) values(?, ?,?)");
        stm.setInt(1, getRefTerme());
        stm.setString(2, getTerme());
         stm.setInt(3, getRefElem());
        System.out.println("<"+getTerme()+">");
        stm.executeUpdate();
        con.close();
        }catch(Exception e){System.out.println(e.getMessage());}
    }
    public static ArrayList affiche()
    {
       ArrayList term=new ArrayList<Terme>();
       try{
          PreparedStatement  stm;
          Connection con=BD.openConnection();
          stm=con.prepareStatement("select * from terme  ");
          ResultSet rs=stm.executeQuery();
          while( rs.next())
          {  term.add(new Terme(rs.getInt(1),rs.getString(2),rs.getInt(3)));
      //     System.out.println("req"+rs.getInt(1));
          }
       }

    catch(Exception e){System.out.println(e.getMessage());}
       return term;
    }
    
}
