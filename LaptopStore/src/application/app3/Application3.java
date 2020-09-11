package application.app3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import services.laptop.LaptopService;

public class Application3 {

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
           System.out.println("SQL Connection to database established!");
           LaptopService laptopService = new LaptopService(connection);
//           Assignment 31
           searchByPrice(laptopService);
           searchByHardDriveAndMaker(laptopService);
//           Assignment 32
           LaptopService.displayLaptop(laptopService.searchMultipleCriteria(-1, -1, "", 14, "4 gb", "", "vivobook", "price", "asc", ""));
           LaptopService.displayLaptop(laptopService.searchMultipleCriteria(-1, -1, "", 14, "4 gb", "", "", "price", "asc", ""));
//           Assignment 33
           LaptopService.displayLaptop(laptopService.searchMostSold());
           
           
	   }
	   catch(SQLException e) {
           System.out.println("Connection Failed! Check output console"  + e);
           return;
	   }
	}
	public static void searchByPrice(LaptopService laptopService) {
		try (Scanner keyboard = new Scanner(System.in)) {
			System.out.println("---Search product in price range from x to y---");
			System.out.println("Enter x (enter -1 for non-specified):");
			long x = keyboard.nextLong();
			System.out.println("Enter y (enter -1 for non-specified):");
			long y = keyboard.nextLong();
//		System.out.println("x: "+x+", y: "+y);
			LaptopService.displayLaptop(laptopService.searchProductByPrice(x, y));
		}
	}
	public static void searchByHardDriveAndMaker(LaptopService laptopService) {
		try (Scanner keyboard = new Scanner(System.in)) {
			System.out.println("---Search product by hard drive and maker---");
			System.out.println("Enter hard drive (e.g. hdd):");
			String hardDrive = keyboard.nextLine();
			System.out.println("Enter maker (e.g. asus):");
			String maker = keyboard.nextLine();
			LaptopService.displayLaptop(laptopService.searchProductByHardDriveAndMaker(hardDrive, maker));
		}
	}
    
   
}
