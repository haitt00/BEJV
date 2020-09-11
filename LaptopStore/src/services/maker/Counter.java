package services.maker;

import java.util.ArrayList;

public class Counter {
	private String maker;
	private int quantity;
	public Counter(String maker, int quantity) {
		super();
		this.maker = maker;
		this.quantity = quantity;
	}
//	public String getMaker() {
//		return maker;
//	}
//	public int getQuantity() {
//		return quantity;
//	}
	public static void displayCounter(ArrayList<Counter> counters) {
		for (Counter counter : counters) {
			System.out.println(counter.maker+": "+counter.quantity);
		}
	}
	
}
