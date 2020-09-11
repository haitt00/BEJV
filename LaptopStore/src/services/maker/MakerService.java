package services.maker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MakerService {
	private Connection con;

	public MakerService(Connection con) {
		super();
		this.con = con;
	}
	
	public ArrayList<Counter> getCounterByMaker() {
		String query = "SELECT maker, COUNT(DISTINCT(type)) as number_of_types FROM store_cms_plusplus.laptop GROUP BY maker ORDER BY number_of_types DESC";
		ArrayList<Counter> counters = new ArrayList<Counter>();
		try {
			Statement statement = con.createStatement();
			ResultSet data = statement.executeQuery(query);
			while(data.next()) {
				counters.add(new Counter(data.getString(1), data.getInt(2)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return counters;
		
	}
	public ArrayList<Statistic> getStatisticByMaker(){
		String query = "SELECT maker, sum(sold), sum(sold*price) FROM store_cms_plusplus.laptop GROUP BY maker";
		ArrayList<Statistic> statistics = new ArrayList<Statistic>();
		try {
			Statement statement = con.createStatement();
			ResultSet data = statement.executeQuery(query);
			while(data.next()) {
				statistics.add(new Statistic(data.getString(1), data.getInt(2), data.getLong(3)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return statistics;
	}
}
