<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
body{
background-color:  #F8F8F8;
font-family: sans-serif,serif,Georgia;
background-blend-mode:screen;
}

</style>

<script type="text/javascript">

$(document).ready(function() {
	 $("#updateProd").submit(function(event) {
		 
		 console.log('enter submit');
	      //Prevent default submission of form
	      event.preventDefault();
	      
	var old_id = document.getElementById("old_id").value;
	if(!old_id){
		alert("please enter a valid productID");
		return false;
	}
	
	var id = document.getElementById("id").value;
	var name = document.getElementById("name").value;
	var desc = document.getElementById("desc").value;
//Call ajax to update the record
	var data = {};
	data["id"] = id;
	console.log("newID:"+id);
	data["name"]=name;
	data["desc"]= desc;
	console.log(data);
	console.log(JSON.stringify(data));
	var updateObj = {product:data, id:old_id};
	console.log(JSON.stringify(updateObj));
	$.ajax({
	url : 'updateProcess',
	data : JSON.stringify(updateObj),
	type : 'POST',
	beforeSend: function(xhr) {
	xhr.setRequestHeader("Accept", "application/json");
	xhr.setRequestHeader("Content-Type", "application/json");
	},

	success : function(response) {
/*alert( response );*/
	console.log("SUCCESS: ", response);
/* var obj = JSON.parse(response);*/
/*var resp = "<table> <tr> <th>ProductID</th> <th>Product Name</th> <th>Product Description</th></tr> </tr> <td>"+response.id+"</td> <td>"+response.name+"</td> <td>"+response.desc+"</td> </tr> </table>";*/
	//console.log(resp); 
	var len = $('#data_table tr').length-1;
	console.log(len);
	alert("response:"+response);
	},
	error : function(xhr, status, error) {
	alert(xhr.responseText);
	}
	});	 

   });	 

 });

</script>    

</head>
<body>

 <h3>Please enter the details to update the product:</h3> <br>
 
<form:form  id="updateProd"   modelAttribute="product">
    Enter the current Product ID to be updated:
    <input type="text" id="old_id" required/> <br> 
    <p>Enter the details to be updated: </p><br>
  <form:label path="id">Product ID: </form:label> 
   <form:input path="id" name="id" id="id" required="required"/> <br> <br>
   
    <form:label path="name">Product name:</form:label>
    <form:input path="name" name="name" id="name" required="required"/> <br> <br>
    
    <form:label path="desc">Product Desc:</form:label>
    <form:input path="desc" name="desc" id="desc" required="required"/> <br> <br>

 <input type="submit" value="Update"/> &nbsp; &nbsp; 
<input type="reset"  name="Reset">
 </form:form>
<br> <br> <br> <br>


<a href="welcome">Home</a>
<a href="logout">Logout</a>
<!-- Script starts from here ****************************************** -->




</body>
</html>    