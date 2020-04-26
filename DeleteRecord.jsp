<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete A Product Record</title>
</head>
<body>
<h2>Enter A Product ID To Delete That Product From Database</h2>
<h3>WARNING: Once a product record is deleted it cannot be restored</h3>
<form action= "DeleteRecord.jsp">
Product ID: <input type="text" name="id" size="20">
<br>
<input type = "submit" value="Submit">
<br>
</form>
<p><a href="index.html">Back to Home</a><br></p>

<%
String id = request.getParameter("id");
String output = "";

if( (id != null )&& !id.equals(""))
{
	ProductDB productDB = new ProductDB(); // new ProductDB object
	
	Product product = new Product(id);
		if(product != null){
			if(productDB.deleteProduct(product) == false){
			output = "That Product Was Successfully Deleted From The Database";
		} else {
			output= "Something Went Wrong";
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