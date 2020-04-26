<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="main.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add A Product</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h2>Enter Product ID, Product Name, And List Price To Add New Product</h2>
<form action = "AddRecord.jsp" >
Product ID: <input type="text" name="id" size="20">
<br>
Product Name: <input type="text" name="name" size="20">
<br>
List Price: <input type="text" name="listPrice" size="20">
<br>
<input type="submit" name="submitPrimaryKey" value="SUBMIT">
<br>
</form>
<br>
<p><a href="index.html">Back to Home</a><br></p>

<%
String id = request.getParameter("id");
String name = request.getParameter("name");
double listPrice = 0.0d;
String stringListPrice = request.getParameter("listPrice");
if(stringListPrice != null){
	listPrice = Double.parseDouble(stringListPrice);
}
String output = "";

if( (id != null )&& !id.equals("")&& 
		(name != null)&& !name.equals(""))
{
	ProductDB productDB = new ProductDB(); // new ProductDB object
	
	Product product = new Product(id, name, listPrice);
		if(product != null){
			if(productDB.addProduct(product)== false){
				output = "Product was entered successfully";
			}
			else{
				output = "Something Went Wrong";
			}
		}

}
else{
	output = "";
}

out.print(output);

%>
</body>
</html>