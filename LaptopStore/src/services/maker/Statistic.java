package services.maker;

import java.util.ArrayList;

public class Statistic {
	private String maker;
	private int sold;
	private long totalMoney;
	public Statistic(String maker, int sold, long totalMoney) {
		super();
		this.maker = maker;
		this.sold = sold;
		this.totalMoney = totalMoney;
	}
	public static void displayStatistics(ArrayList<Statistic> statistics) {
		for (Statistic statistic : statistics) {
			System.out.println(statistic.maker+" sold "+statistic.sold+" making "+statistic.totalMoney+" vnd");
		} 
	}
}
