import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
	private Connection con;

	public Service(Connection con) {
		super();
		this.con = con;
	}
	ResultSet executeQuery(String query) {
		ResultSet data = null;
		try {
			data = con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	void displayResultOfQueryGetString(String query){
		ResultSet result = executeQuery(query);
		try {
			while(result.next()) {
				System.out.println(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	ArrayList<NhaCungCap> queryGetNCC(String query){
		ArrayList<NhaCungCap> danhSachNCC = new  ArrayList<NhaCungCap>();
		ResultSet data = executeQuery(query);
		try {
			while(data.next()) {
				danhSachNCC.add(new NhaCungCap(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachNCC;
	}
//	1. Liệt kê những dòng xe có số chỗ ngồi trên 5 chỗ
	public void lietKeDongXeTren5Cho() {
		String query = "SELECT DongXe FROM thue_xe_cms.DONGXE WHERE SoChoNgoi > 5;";
		System.out.println("Những dòng xe có số chỗ ngồi trên 5 chỗ: ");
		displayResultOfQueryGetString(query);
	}
//	2. Liệt kê thông tin của các nhà cung cấp ĐÃ TỪNG đăng ký cung cấp những dòng xe
//	thuộc hãng xe “Toyota” với mức phí có đơn giá là 15.000 VNĐ/km hoặc những dòng xe
//	thuộc hãng xe “KIA” với mức phí có đơn giá là 20.000 VNĐ/km
	public void LietKeNCCDieuKienHangXeMucPhi() {
		String query = "SELECT * FROM thue_xe_cms.NHACUNGCAP WHERE MaNhaCC IN "
						+ "(SELECT MaNhaCC FROM thue_xe_cms.DANGKYCUNGCAP as dk" + 
						"						JOIN thue_xe_cms.DONGXE as dx ON dx.DongXe = dk.DongXe" + 
						"                       JOIN thue_xe_cms.MUCPHI as mp ON mp.MaMP = dk.MaMP" + 
						"                       WHERE (HangXe = \"Toyota\" AND DonGia = \"15000\")" + 
						"                       OR (HangXe = \"KIA\" AND DonGia = \"20000\"))";
		System.out.println("Các nhà cung cấp ĐÃ TỪNG đăng ký cung cấp những dòng xe" + 
				"thuộc hãng xe “Toyota” với mức phí có đơn giá là 15.000 VNĐ/km hoặc những dòng xe" + 
				"thuộc hãng xe “KIA” với mức phí có đơn giá là 20.000 VNĐ/km: ");
		NhaCungCap.displayAllInfo(queryGetNCC(query));
	}
//	3. Liệt kê thông tin toàn bộ nhà cung cấp được sắp xếp tăng dần theo tên nhà cung
//	cấp và giảm dần theo mã số thuế
	public void LietKeNCC(String tenTruong, boolean tangDan) {
		String order = tangDan ? "ASC" : "DESC";
		String query = "SELECT * FROM thue_xe_cms.NHACUNGCAP ORDER BY " + tenTruong +" "+ order;
//		System.out.println(query);
		NhaCungCap.displayAllInfo(queryGetNCC(query));
	}
//	4. Đếm số lần đăng ký cung cấp phương tiện tương ứng cho từng nhà cung cấp với
//	yêu cầu chỉ đếm cho những nhà cung cấp thực hiện đăng ký cung cấp có ngày bắt đầu
//	cung cấp là “20/11/2015”
	public void DemSoLanDangKyTheoNCCDieuKienNgayBatDau() {
		String query = "SELECT MaNhaCC, COUNT(*) FROM thue_xe_cms.DANGKYCUNGCAP WHERE NgayBatDauCungCap = '2015-11-20' GROUP BY MaNhaCC";
		ResultSet data = executeQuery(query);
		System.out.println("Số lần đăng ký cung cấp phương tiện tương ứng cho từng nhà cung cấp (" + 
				"ngày bắt đầu là 20/11/2015): ");
		try {
			while(data.next()) {
				System.out.println("NCC "+data.getString(1)+": "+data.getInt(2)+" lần đăng ký");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	5. Liệt kê tên của toàn bộ các hãng xe có trong cơ sở dữ liệu với yêu cầu mỗi hãng xe
//	chỉ được liệt kê một lần
	public void LietKeHangXe() {
		String query = "SELECT DISTINCT(HangXe) FROM thue_xe_cms.DONGXE";
		System.out.println("Các hãng xe có trong cơ sở dữ liệu: ");
		displayResultOfQueryGetString(query);
		
	}
//	6. Liệt kê MaDKCC, MaNhaCC, TenNhaCC, DiaChi, MaSoThue, TenLoaiDV, DonGia,
//	HangXe, NgayBatDauCC, NgayKetThucCC của tất cả các lần đăng ký cung cấp phương
//	tiện với yêu cầu những nhà cung cấp nào chưa từng thực hiện đăng ký cung cấp phương
//	tiện thì cũng liệt kê thông tin những nhà cung cấp đó ra
	public void LietKeDangKyToanBoNCC() {
		String query = "SELECT MaDKCC, ncc.MaNhaCC, TenNhaCC, DiaChi, MaSoThue, TenLoaiDV, DonGia," + 
				"HangXe, NgayBatDauCungCap, NgayKetThucCungCap FROM thue_xe_cms.NHACUNGCAP as ncc " + 
				"LEFT JOIN (SELECT MaNhaCC, MaDKCC, TenLoaiDV, DonGia, HangXe, NgayBatDauCungCap, NgayKetThucCungCap FROM thue_xe_cms.DANGKYCUNGCAP as dk " + 
				"JOIN thue_xe_cms.LOAIDICHVU as ldv ON dk.MaLoaiDV = ldv.MaLoaiDV " + 
				"JOIN thue_xe_cms.MUCPHI as mp ON dk.MaMP = mp.MaMP " + 
				"JOIN thue_xe_cms.DONGXE as dx ON dk.DongXe = dx.DongXe) as q ON q.MaNhaCC = ncc.MaNhaCC";
//		System.out.println(query);
		ResultSet data = executeQuery(query);
		System.out.println("Chi tiết tất cả các lần đăng ký (include những nhà cung cấp nào chưa từng thực hiện đăng ký): ");
		System.out.format("%-7s%-8s %-30s%14s%10s\n%-45s%15s%10s\n%-15s %-15s\n", "MaDKCC","MaNhaCC", "TenNhaCC", "DiaChi", "MaSoThue", "TenLoaiDV", "DonGia", "HangXe", "NgayBatDauCC", "NgayKetThucCC");
		System.out.println("--------------------------------------------------------------");
		try {
			while(data.next()) {
				System.out.format("%-7s%-8s %-30s%14s%10s\n%-45s%15s%10s\n%-15s %-15s\n\n", data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8),data.getDate(9),data.getDate(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	7. Liệt kê thông tin của các nhà cung cấp đã từng đăng ký cung cấp phương tiện
//	thuộc dòng xe “Hiace” hoặc từng đăng ký cung cấp phương tiện thuộc dòng xe “Cerato”
	public void LietKeNCCDongXe() {
		System.out.println("Các nhà cung cấp đã từng đăng ký cung cấp phương tiện " + 
				"thuộc dòng xe “Hiace” hoặc từng đăng ký cung cấp phương tiện thuộc dòng xe “Cerato”: ");
		String query = "SELECT * FROM thue_xe_cms.NHACUNGCAP WHERE MaNhaCC IN" + 
				"		( SELECT MaNhaCC FROM thue_xe_cms.DANGKYCUNGCAP WHERE DongXe IN (\"Hiace\", \"Cerato\"))";
		NhaCungCap.displayAllInfo(queryGetNCC(query));
	}
//	8. Liệt kê thông tin của các nhà cung cấp chưa từng thực hiện đăng ký cung cấp
//	phương tiện lần nào cả.
	public void LietKeNCCChuaDangKy() {
		System.out.println("Các nhà cung cấp chưa từng thực hiện đăng ký cung cấp " + 
				"phương tiện: ");
		String query = "SELECT * FROM thue_xe_cms.NHACUNGCAP WHERE MaNhaCC NOT IN" + 
				"		( SELECT MaNhaCC FROM thue_xe_cms.DANGKYCUNGCAP)";
		NhaCungCap.displayAllInfo(queryGetNCC(query));
		
	}

}
