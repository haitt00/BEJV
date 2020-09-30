import java.util.ArrayList;

public class NhaCungCap {
	
	private String MaNhaCC;
	private String TenNhaCC;
	private String DiaChi;
	private String SoDT;
	private String MaSoThue;
	public NhaCungCap(String maNhaCC, String tenNhaCC, String diaChi, String soDT, String maSoThue) {
		super();
		MaNhaCC = maNhaCC;
		TenNhaCC = tenNhaCC;
		DiaChi = diaChi;
		SoDT = soDT;
		MaSoThue = maSoThue;
	}
	public static void displayAllInfo(ArrayList<NhaCungCap> list) {
		System.out.format("%8s %50s %30s %15s %15s\n", "Mã NCC", "Tên", "Địa chỉ", "Số ĐT", "Mã số thuế");
		for (NhaCungCap nhaCungCap : list) {
			System.out.format("%8s %50s %30s %15s %15s\n", nhaCungCap.MaNhaCC, nhaCungCap.TenNhaCC, nhaCungCap.DiaChi, nhaCungCap.SoDT, nhaCungCap.MaSoThue);
		}
	}
	public static void display(ArrayList<NhaCungCap> list) {
		for (NhaCungCap nhaCungCap : list) {
			System.out.println(nhaCungCap.TenNhaCC+"(MS:"+nhaCungCap.MaNhaCC+")");
		}
	}
}
