package moteur_recherche;
import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.*;
public class BD {
    
    private static String user="root";
    private static String url="jdbc:mysql://localhost:3306/databaseRI";
    private static String pwd="rabaa";
    private static Connection con;
    private static  String driver="com.mysql.jdbc.Driver";
    public static Connection openConnection(){
	try {
    Class.forName(driver);
    con=DriverManager.getConnection(url,user,pwd);
    //System.out.println("on ouvre une connexion");
   
	} catch(Exception e){ 
    System.out.println(e+"Exception de l'etablissement de connexion");
	} 
        return con;
}
public static void closeConnection(){
	try{
		con.close();
	}catch(Exception e){System.out.println("Fermeture de la connexion");}
}
 

	 public String getUrlBd(){ 
		 return url;}
	 public String getUser(){ return user;
	 }
	 public String getPassword(){ 
		 return pwd;
		 }
	 //Les setteurs
	 
	 public void setUrlBd(String urlBd)
	 {
		 this.url=urlBd;
	 }
	 public void setUser(String user)
	 {
		 this.user=user;
	 }
	 public void setPassword(String password)
	 {
       this.pwd=password;
	 }

}
