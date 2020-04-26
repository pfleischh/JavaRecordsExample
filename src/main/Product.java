package main;

import java.text.NumberFormat;

/**
 * The Product class is essentially a data structure class used to store database
 * fields (object properties) for the textbook applications. The objects created
 * from this class can than be passed back and forth between methods whether the
 * method is in a JSP or in another Java class such as the ProductDB class.
 **/
public class Product {
	/*
	 * The properties of the class (sometimes called attributes or fields, as in
	 * the textbook) are typically all private in OOP programming so that the
	 * only way to access and/or change their values is through "accessor"
	 * methods. This feature is  called encapsulation since the private
	 * date essentially has a shell around it to protect it from outside access.
	 * This also facilitates future change by hiding the implementation details
	 * from external callers
	 */
	private String id;
	private String name;
	private double listPrice;

	/**
	 * This is called a default constructor method since you can call it without
	 * passing it any parameters. A default constructor method is required for a
	 * java class to be considered a java "bean" and the default constructor
	 * method generally sets default values into the private properties. NOTE
	 * that ALL constructor methods have the same name as the class they are in
	 * and NONE of them return a value. The JVM (Java Virtual Machine) knows
	 * which method to call based on the number & types of parameters passed to
	 * it
	 **/
	public Product() {
		this("", "", 0);
	}

	// this is called a parameterized constructor method since you must pass it
	// parameters when you call it
	// always using setter methods to set property values is considered a better
	// OOP approach than the textbook's approach
	public Product(String code) {
		this(code, "", 0.0);
	}

	// this is another parameterized constructor method, since you must pass it
	// parameters when you call it
	public Product(String id, String name, double listPrice) {
		this.id = id;
		this.name = name;
		this.listPrice = listPrice;
	}

	/**
	 * Setter methods typically use a combination of set + the capitalized name
	 * of the property/field for their method names. Getters never return a
	 * value since they are just used to set information into a property /
	 * field. Typically accessor methods contain security code such as checking
	 * login status, IP addresses, etc. before allowing the value to be changed
	 **/
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter methods use a combination of get + the capitalized name of the
	 * property/field for their method names. Getters always return a value
	 * since they are used to get private property / field information from an
	 * object. Typically accessor methods contain security code such as checking
	 * login status, IP addresses, etc. before allowing the value to be changed
	 **/
	public String getName() {
		return name;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getListPrice() {
		return listPrice;
	}

	// This getFormattedPrice() method is a "custom" method ("custom" if not in
	// all objects like getters/setter methods typically are).
	// This method returns a String formatted as the value of the currency for
	// the system that it's running on (i.e., U.S., French, etc.)
	public String getFormattedPrice() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(listPrice);
	}

	// See page 296-297 in your textbook for an explanation - this method is not
	// used by our current web application and only sometimes used in
	// applications
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

	// The following methods simply delegate to an overloaded version of
	// 'toString()'. Doing this allows us to isolate just
	// the parts of the methods that vary and makes it much easier to read in
	// the future.

	// This method was added to simplify displaying ALL of a Product object's
	// properties/fields with just a single method call.
	// It is meant to be called from a JSP since it uses "<br />" for new lines.
	// It can be very useful for debugging 3 tier applications since it can
	// report errors and/or progress in the client's browser
	
	public String toString() {
		return toString("<br />");
	}

	// It is meant to be called from a Server Console Java class since it uses
	// "\n" for new lines.
	// It can be very useful for debugging 3 tier applications since it can
	// report errors and/or progress on the Sever's console
	public String toStringConsole() {
		return toString("\n");
	}

}