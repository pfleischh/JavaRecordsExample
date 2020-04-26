<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Price for an Item</title>
</head>
<body>
<h2>Enter Existing Product ID and New List Price</h2>
<form action= "UpdateRecord.jsp">
Product ID: <input type="text" name="id" size="20">
<br>
New List Price: <input type = "text" name = "listPrice" size="20">
<br>
<input type = "submit" value="Submit">
<br>
</form>
<p><a href="index.html">Back to Home</a><br></p>


<%
String id = request.getParameter("id");
double listPrice = 0.0d;
String stringListPrice = request.getParameter("listPrice");
if(stringListPrice != null){
	listPrice = Double.parseDouble(stringListPrice);
}

//double listPrice = Double.parseDouble(request.getParameter("listPrice");
//double listPrice = 19.99;
String output = "";

if( (id != null )&& !id.equals(""))
{
	ProductDB productDB = new ProductDB(); // new ProductDB object
	
	Product product = new Product(id, "", listPrice);
		if(product != null){
			if(productDB.updateProduct(product) == false){
			output = "Product's List Price Was Updated Successfully";
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