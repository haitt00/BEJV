package services.laptop;
import java.sql.Timestamp;

public class LaptopEntity {
	private String card;
	private String cpu;
	private Timestamp createdTimestamp;
	private String hdd;
	private int id;
	private Timestamp lastUpdatedTimestamp;
	private String maker;
	private String name;
	private long price;
	private String ram;
	private String screenResolution;
	private float screenSize;
	private int sold;
	private String ssd;
	private String type;
	private String url;
	public LaptopEntity(String card, String cpu, Timestamp createdTimestamp, String hdd, int id,
			Timestamp lastUpdatedTimestamp, String maker, String name, long price, String ram, String screenResolution,
			float screenSize, int sold, String ssd, String type, String url) {
		super();
		this.card = card;
		this.cpu = cpu;
		this.createdTimestamp = createdTimestamp;
		this.hdd = hdd;
		this.id = id;
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
		this.maker = maker;
		this.name = name;
		this.price = price;
		this.ram = ram;
		this.screenResolution = screenResolution;
		this.screenSize = screenSize;
		this.sold = sold;
		this.ssd = ssd;
		this.type = type;
		this.url = url;
	}
	protected String getCard() {
		return card;
	}
	protected String getCpu() {
		return cpu;
	}
	protected Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	protected String getHdd() {
		return hdd;
	}
	protected int getId() {
		return id;
	}
	protected Timestamp getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	protected String getMaker() {
		return maker;
	}
	protected String getName() {
		return name;
	}
	protected long getPrice() {
		return price;
	}
	protected String getRam() {
		return ram;
	}
	protected String getScreenResolution() {
		return screenResolution;
	}
	protected float getScreenSize() {
		return screenSize;
	}
	protected int getSold() {
		return sold;
	}
	protected String getSsd() {
		return ssd;
	}
	protected String getType() {
		return type;
	}
	protected String getUrl() {
		return url;
	}
//	public static void main(String[] args) {
//		LaptopEntity lap = new LaptopEntity(null, null, null, null, 0, null, null, null, 0, null, null, 0, 0, null, null, null);
//	}
	
}
