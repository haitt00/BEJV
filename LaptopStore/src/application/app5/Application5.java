package application.app5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import services.laptop.LaptopEntity;
import services.laptop.LaptopService;

public class Application5 {

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
           LaptopService laptopService = new LaptopService(connection);
//	           Assignment 51
           String laptopName = "MacBook Pro 2018";
           laptopService.insertLaptop("Intel UHD Graphics 605", "", "", "apple", laptopName, -1, "8GB", "hd", 13, 10, "", "", "abvakfak");
           ArrayList<LaptopEntity> searchResult = laptopService.searchByName(laptopName);
           if(searchResult.isEmpty()) {
        	   System.out.println("No laptop with same name found. Insertion failed!");
           }
           else {
        	   System.out.println("Laptop(s) with name found: ");
        	   LaptopService.displayLaptop(searchResult);
        	   System.out.println("Insertion successful!");
           }
//           Assignment 52
           laptopService.updateSold(1, 1);
           
	   }
	   catch(SQLException e) {
           System.out.println("Connection Failed! Check output console"  + e);
           return;
	   }
	}
}
