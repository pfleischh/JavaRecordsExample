package main;

import java.text.NumberFormat;


public class Product {

	private String id;
	private String name;
	private double listPrice;


	public Product() {
		this("", "", 0);
	}

	public Product(String code) {
		this(code, "", 0.0);
	}

	public Product(String id, String name, double listPrice) {
		this.id = id;
		this.name = name;
		this.listPrice = listPrice;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getListPrice() {
		return listPrice;
	}


	public String getFormattedPrice() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(listPrice);
	}


	public boolean equals(Object object) {
		if (object instanceof Product) {
			Product product2 = (Product) object;
			if (id.equals(product2.getId())
					&& name.equals(product2.getName())
					&& listPrice == product2.getListPrice())
				return true;
		}
		return false;
	}

	String toString(String newLine) {
		// Using StringBuffer is generally faster than using a + to concatenate
		// strings.
		// The improvement in this case would likely be very minor but it's a
		// good habit to establish.
		StringBuffer buffer = new StringBuffer();
		buffer.append("id: ").append(id).append(newLine);
		buffer.append("product_name: ").append(name).append(newLine);
		buffer.append("list_price: ").append(getFormattedPrice()).append(newLine);
		return buffer.toString();
	}


	
	public String toString() {
		return toString("<br />");
	}


	public String toStringConsole() {
		return toString("\n");
	}

}