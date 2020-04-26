<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="main.*"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Display All Records using ArrayList</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR"
	content="IBM Websphere Development Studio Client Advanced Edition for iSeries">
</head>
<body>
	<%
		String output = ""; //create String object to place output information in
		
		ProductDB productDB = new ProductDB(); //create a new ProductDB object by calling its default constructor method
		
		//get an ArrayList with all the product objects - records in it by calling the getProducts() method in the productDB object
		ArrayList<Product> products = productDB.getProducts();
		
		if (products == null) {
			output += "Error! Unable to get products."; //here if getProducts() method returned a null 
		} else { //here if prouducts object not a null - i.e., an ArrayList<> was returned rather than a null
		    output = "<h3>Number of records in ArrayList = " + products.size() + "</h3>"; //display number of objects in ArrayList<> object
			Product p = null; //declare a null Product object to be used in for loop for temporarily storing Product objects in ArrayList<> object
			for (int i = 0; i < products.size(); i++) {
				p = (Product) products.get(i); //get index i Product object from ArrayList<> (note "i" will be between 0 and products.size())
				output += p.toString() + "<br />";	//concatenate current output String with properties (database record) from p object			
			}
		}
		
		out.print(output); //display either Error report from above or concatenated String with all the records from the ArrayList<>
	%>

	<p>
		<a href="index.html">Back to Home</a><br>
	</p>

</body>
</html>
