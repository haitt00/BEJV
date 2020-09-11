package application.app4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import services.maker.Counter;
import services.maker.MakerService;
import services.maker.Statistic;

public class Application4 {

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
		   MakerService makerService = new MakerService(connection);
//           Assignment 41
           System.out.println("Activity 41: Count laptop types by maker:");
           Counter.displayCounter(makerService.getCounterByMaker());
//			Assignment 42
           System.out.println("Activity 42: Count laptop quantity & revenue by maker:");
           Statistic.displayStatistics(makerService.getStatisticByMaker());
           
	   }
	   catch(SQLException e) {
           System.out.println("Connection Failed! Check output console"  + e);
           return;
	   }

	}

}
