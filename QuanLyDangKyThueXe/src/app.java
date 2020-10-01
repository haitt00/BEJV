import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class app {
	@SuppressWarnings("deprecation")
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
//           1
           service.lietKeDongXeSoChoNgoiTren(5);
//           2
           service.LietKeNCCDieuKienHangXeMucPhi("Toyota", "15000");
           service.LietKeNCCDieuKienHangXeMucPhi("KIA", "20000");
//           3
           service.LietKeNCC("TenNhaCC", true);
           service.LietKeNCC("MaSoThue", false);
//           4
           service.DemSoLanDangKyTheoNCCDieuKienNgayBatDau(new Date(2015, 11, 20));
//           5
           service.LietKeHangXe();
//           6
           service.LietKeDangKyToanBoNCC();
//           7
           service.LietKeNCCDongXe("Hiace", "Cerato");
//           8
           service.LietKeNCCChuaDangKy();
           
	   }
	   catch(SQLException e) {
           System.out.println("Connection Failed! Check output console"  + e);
           return;
	   }
	}
}
