<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	
<%@page import="main.*"%>
	
<html>
<head>
<title>DisplayOne</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<form action = "DisplayOne.jsp" >
Primary Key: <input type="text" name="primaryKey" size="20"><br>
<br>
<input type="submit" name="submitPrimaryKey" value="SUBMIT">
<br></form>
<br>
<p><a href="index.html">Back to Home</a><br></p>

<%

String input = request.getParameter("primaryKey");
String output = "";

//out.print("input now = " + input + "<br /> <br />");
if ( (input != null)&& !input.equals("") ) //user validation
{  
	ProductDB productDB = new ProductDB(); //create a ProductDB object so that we can dynamically call methods in it
	
	//test getProduct( ) method
    Product product = productDB.getProduct(input); //call getProduct() method in productDB object & pass it the primary key
    
    if(product == null) //test to see if record is found (or SQL error)- i.e. a null was returned
    { 
    	 output = "Product " + input + " NOT Found";  
	}
	else
	{ //here if a valid product object was returned by getProduct( )
	   
	    output = "<h3>OUTPUT FROM getProduct(" + input + ")</h3>"
    			+ product.toString()
    			+ "<br><br><br>"; 
	}
 }
  else{
     output = "Please enter a Product Code";
     }	    			
	
    out.print(output);
    
 %>   

</body>
</html>