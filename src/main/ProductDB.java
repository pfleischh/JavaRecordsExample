package main;


import java.util.*;
import java.sql.*;

public class ProductDB
{
  
	private Connection connection = null;
	
	public ProductDB()
	{
		String rValue = connect(); //call the connect method and set up the connection for all the methods in this class to use 
		System.out.print(rValue);  //display message returned by connect( ) method on Server Console for debug purposes
	}

	// initializes connection - separate connect( ) call out for debugging purposes since constructors cannot return a value
	public String connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/northwind?useSSL=false",
					"root", "");
			return "\nConnection successfully made to northwind"; //if here, then connection was successful
			
			}
		catch(Exception e) //this "catches" an Exception object from either the Class.forName( ) call or the getConnection( ) call
		{
			System.out.println("\n\nError connecting to the database: " + e.toString()); //report error on Server Console
			return "Error connecting to the database: " + e.toString(); //return Exception error to method that called connection( ) method
		}
	}

	//this method tries to get all the product records in the database table and return them in an ArrayList< > of Product objects 
	public ArrayList<Product> getProducts()
	{
		System.out.print("\nMessage from just inside getProducts( ) method"); //report on Server Console if this method is called
		try
		{
			ArrayList<Product> products = new ArrayList<Product>(); //create an empty ArrayList<> to store Product objects
			String query = "SELECT id, product_name, list_price FROM products ORDER BY id ASC"; //Create SQL query String
			

			PreparedStatement ps = connection.prepareStatement(query); 
			ResultSet rs = ps.executeQuery(); //query the database management system (DBMS) and store the ResultSet object returned

			while(rs.next()) //step into ResultSet object - if nothing in the ResultSet object, then while skips over this code block
			{
				String productId = rs.getString("id"); //get "id" column from northwind.products
				String productName = rs.getString("product_name"); //get "product_name" column from northwind.products
				double productListPrice = rs.getDouble("list_price"); //get "list_price" column from northwind.products

				Product p = new Product(productId, productName, productListPrice); //use parameterized constructor method to stuff record fields into object p
				System.out.print("\n" + p.toStringConsole()); //display this DB record that is stored in the Product object p
				products.add(p); //add this p object to the ArrayList<> of Product objects
			}
			rs.close(); //close ResultSet
			ps.close(); //close PreparedStatement
			System.out.print("\n\nAll products added to ArrayList object. Number of records in ArrayList object  = " + products.size());
			return products; //return ArrayList< > of Product p objects (note this will be empty in no ResultSet records returned (table empty)
		}
		catch(Exception err)
		{
			System.out.print("\n\nError: " + err.toString()); //report Exception on Server Console
			return null; //Return null rather than an ArrayList< > if there's an error in SQL code or could not locate JDBC-ODBC bridge driver
		}
	}

	public Product getProduct(String productId) 
	{
		try
		{
			System.out.print("\nJust inside getProduct() method with code = " + productId);
			
			String selectProduct =
				"SELECT id, product_name, list_price FROM products WHERE id = ?"; //create SQL statement except for ProductCode value to be inserted by ps.setString( ) call below
			

			PreparedStatement ps = connection.prepareStatement(selectProduct); //create Prepared Statement object from connection( ) method
			ps.setString(1, productId); //replace ? above with the code parameter passed to getProduct( ) method
			ResultSet rs = ps.executeQuery(); //send SQL statement to DBMS and store the ResultSet object returned

			if (rs.next()) //step into ResultSet object - if nothing in the ResultSet object, then while skips over this code block
			{
				System.out.print("\nInside rs.next() method - a ResultSet object/record must have been returned"); //report that a record was found 
				String productName = rs.getString("product_name"); //get product_name from ResultSet object
				double productListPrice = rs.getDouble("list_price"); //get list_price from ResultSet object
				Product p = new Product(productId, productName, productListPrice); //create new Product object using the code passed to this method + above fields
				rs.close();
				ps.close();
				System.out.print("\n" + p.toStringConsole()); //display record stored in p object on the Server's console for debugging
				return p; //return object p with the record which has primary key equal to the code passed to this getProduct( ) method
			}
			else
			{
				System.out.print("\nRecord " + productId + " Not Found"); //here if nothing in ResultSet object (i.e., "Record Not Found"
				return null; //return null instead of Product object if Record Not Found
			}
		}
		catch(Exception err)
		{
			System.out.print("\n\nError: " + err.toString()); //report Exception on Server Console
			return null; //Return null rather than a Product object if there's an error in SQL code
		}
	}
// Assignment 4x1
	public boolean addProduct(Product p)
	{
		String sql = "INSERT INTO products (id, product_name, list_price) " + "VALUES(?, ?, ?)";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, p.getId());
			ps.setString(2, p.getName());
			ps.setDouble(3, p.getListPrice());
			ps.executeUpdate();
		}
		catch (SQLException e) {
		//	throw new DBException (e);
		}
	
		return false;
	}

// Assignment 4x3
	public boolean deleteProduct(Product p)
	{
		String sql = "DELETE FROM product WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, p.getId());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			// throw new DBException(e);
		}
		return false;
	}
	
// Assignment 4x2
	public boolean updateProduct(Product p)
	{
		String sql = "UPDATE product SET"
				+ "list_price = ? WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setDouble(1, p.getListPrice());
			ps.setString(2, p.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			// throw new DBException(e);
		}
		
		return false;
	}

}






