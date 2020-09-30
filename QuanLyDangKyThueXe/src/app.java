import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class app {
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
           service.lietKeDongXeTren5Cho();
//           2
           service.LietKeNCCDieuKienHangXeMucPhi();
//           3
           System.out.println("Thông tin các nhà cung cấp được sắp xếp tăng dần theo tên nhà cung" + 
           		" cấp: ");
           service.LietKeNCC("TenNhaCC", true);
           System.out.println("Thông tin các nhà cung cấp được sắp xếp giảm dần theo mã số thuế: ");
           service.LietKeNCC("MaSoThue", false);
//           4
           service.DemSoLanDangKyTheoNCCDieuKienNgayBatDau();
//           5
           service.LietKeHangXe();
//           6
           service.LietKeDangKyToanBoNCC();
//           7
           service.LietKeNCCDongXe();
//           8
           service.LietKeNCCChuaDangKy();
           
	   }
	   catch(SQLException e) {
           System.out.println("Connection Failed! Check output console"  + e);
           return;
	   }
	}
}
