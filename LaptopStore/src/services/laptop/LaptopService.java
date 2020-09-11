package services.laptop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LaptopService {
	private Connection con;

	public LaptopService(Connection con) {
		super();
		this.con = con;
	}
	
	public ArrayList<LaptopEntity> executeQuery(String query) {
		ArrayList<LaptopEntity> laptopList = new ArrayList<LaptopEntity>();
		try {
		Statement statement = con.createStatement();
		ResultSet data = statement.executeQuery(query);
		while(data.next()) {
			laptopList.add(new LaptopEntity(data.getString("card"), data.getString("cpu"), data.getTimestamp("created_timestamp"), data.getString("hdd"), data.getInt("id"), data.getTimestamp("last_updated_timestamp"), data.getString("maker"), data.getString("name"), data.getLong("price"), data.getString("ram"), data.getString("screen_resolution"), data.getFloat("screen_size"), data.getInt("sold"), data.getString("ssd"), data.getString("type"), data.getString("url")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return laptopList;
	}
	
	public String getPriceQuery(long x, long y) {
		if(x==-1&&y==-1) {
			return "SELECT * FROM laptop WHERE 1 = 1";
		}
		else {
			if(x==-1) {
				return "SELECT * FROM laptop WHERE price <= "+y;
			}
			if(y==-1) {
				return "SELECT * FROM laptop WHERE price >= "+x;
			}
			return "SELECT * FROM laptop WHERE price BETWEEN "+x+" AND "+y;
		}
	}
	
	public ArrayList<LaptopEntity> searchProductByPrice(long x, long y) {
		String query = getPriceQuery(x, y);
		return executeQuery(query);
	}

	public ArrayList<LaptopEntity> searchProductByHardDriveAndMaker(String hardDrive, String maker) {		
		hardDrive = hardDrive.toLowerCase();
		maker = maker.toUpperCase();
		String query = "SELECT * from laptop WHERE "+hardDrive+" IS NOT NULL AND maker = '"+maker+"'";
//		System.out.println(query);
		return executeQuery(query);
	}
	
	public ArrayList<LaptopEntity> searchMultipleCriteria(long priceFrom, long priceTo, String maker, float screenSize, String ram, String cpu, String type, String orderCriteria, String order, String card){
		String query = getPriceQuery(priceFrom, priceTo);
		if (!maker.isEmpty()) {
			query += " AND maker = '" + maker.toUpperCase() + "'";
		}
		if (screenSize!=-1) {
			query += " AND screen_size = " + screenSize;
		}
		if (!ram.isEmpty()) {
			query += " AND ram = '" + ram.replace(" ", "").toUpperCase() + "'";
		}
		if (!cpu.isEmpty()) {
			query += " AND UPPER(cpu) LIKE UPPER('%"+cpu+"%')";
		}
		if (!type.isEmpty()) {
			query += " AND UPPER(type) LIKE UPPER('%"+type+"%')";
		}
		if (!card.isEmpty()) {
			query += " AND UPPER(card) LIKE UPPER('%"+card+"%')";
		}
		if (!orderCriteria.isEmpty()) {
			query += " ORDER BY " + orderCriteria.toLowerCase();
		}
		switch (order) {
		case "ascending":
			query += " ASC";
			break;
		default:
			query += " DESC";
			break;
		}
//		System.out.println(query);
		return executeQuery(query);
	}
	public ArrayList<LaptopEntity> searchMostSold(){
		String query = "SELECT * FROM laptop ORDER BY sold DESC LIMIT 10";
		return executeQuery(query);
	}
	public int countRecords() {
		String query = "SELECT COUNT(*) FROM store_cms_plusplus.laptop";
		int count = 0;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet data = statement.executeQuery(query);
			data.next();
			count = data.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return count;
	}
	public ArrayList<LaptopEntity> searchByName(String name){
		String query = "SELECT * FROM laptop WHERE name = \""+name+"\"";
		return executeQuery(query);
	}
	public static String buildStringField(String fieldValue) {
		if(fieldValue.isEmpty()) {
			return "null";
		}
		else {
			return "\""+fieldValue+"\"";
		}
	}
	public void insertLaptop(String card, String cpu, String hdd, String maker, 
			String name, long price, String ram, String screenResolution,
			float screenSize, int sold, String ssd, String type, String url) {
		String query = "INSERT INTO store_cms_plusplus.laptop(id, name, url, maker, type, ram, cpu, ssd, hdd, card, screen_resolution, price, screen_size, sold) values";
		query+="("+this.countRecords()+","
				+buildStringField(name)+","
				+buildStringField(url)+","
				+buildStringField(maker)+","
				+buildStringField(type)+","
				+buildStringField(ram)+","
				+buildStringField(cpu)+","
				+buildStringField(ssd)+","
				+buildStringField(hdd)+","
				+buildStringField(card)+","
				+buildStringField(screenResolution)+",";
		if(price==-1) {
			query+="null,";
		}
		else {
			query+=price+",";
		}
		if(screenSize==-1) {
			query+="null,";
		}
		else {
			query+=screenSize+",";
		}
		if(sold==-1) {
			query+="null)";
		}
		else {
			query+=sold+")";
		}
		try {
			con.createStatement().execute(query);
//			System.out.println("Laptop inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateSold(int id, int extra) {
		String query = "UPDATE store_cms_plusplus.laptop SET sold = sold + "+extra+", last_updated_timestamp=CURRENT_TIMESTAMP(6) WHERE id = "+id;
		try {
			con.createStatement().execute(query);
			System.out.println("Sold number updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void displayLaptop(ArrayList<LaptopEntity> laptops) {
//		System.out.println("---RESULTS---");
		for (LaptopEntity laptop: laptops) {
			System.out.println("id: "+laptop.getId()+" name: "+laptop.getName());
		}
	}
//	public static void main(String[] args) {
//		String test = "";
//		System.out.println(buildStringField(test));
//		test = "asus";
//		System.out.println(buildStringField(test));
//	}
}
