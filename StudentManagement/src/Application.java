import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
	public static void main(String[] args) {
		try
	    {
	        Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch (ClassNotFoundException e) {
	        System.out.println("MySQL JDBC Driver not found !!");
	        return;
	    }
	    
	   Connection connection = null;
	   try {
		   connection = DriverManager
                   .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus?characterEncoding=utf8", "root", "382563phonepư");
//           System.out.println("SQL Connection to database established!");
           Service service = new Service(connection);
           service.updateNumberOfStudent();
           service.updateAverageScore();
           
	   }
	   catch(SQLException e) {
           System.out.println("Connection Failed! Check output console"  + e);
           return;
	   }
	}
}
