<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <title>Welcome</title>        
<style type="text/css">

body{
background-color:  #F8F8F8;
font-family: sans-serif,serif,Georgia;
text-align: center;
background-blend-mode:screen;
}

#menu {
    width: 700px;
    height: 50px;
    font-size: 16px;
    font-family: Tahoma, Geneva, sans-serif;
    font-weight: bold;
    text-align: center;
    /*text-shadow: 3px 2px 3px #333333;*/
    background-color: #8AD9FF;
        border-radius: 8px;
       
}

#menu ul {
    height: auto;
    padding: 8px 0px;
    margin: 0px;
}

#menu li { 
display: inline; 
padding: 5px 10px;
}

#menu a {
    text-decoration: none;
    color: #00F;
    padding: 8px 8px 8px 8px;
}

#menu a:hover {
    color: #F90;
    background-color: #FFF;
}
#updateMe {
 display: none;
}

#data_table{
 font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
}

#data_table td, #data_table th {
    border: 1px solid #ddd;
    padding: 8px;
}

#data_table tr:hover {background-color: #A9A9A9;}

#data_table th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #228B22;
    color: white;
    font-weight: bold;
}


</style>   

<script type="text/javascript">
$(document).ready(function() {
	$("#listItems").click(function() {
	  //  $("#myhide").css({'display':'block'});
		 
	  $.ajax({
url : 'list',
type : 'GET',
beforeSend: function(xhr) {
xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");
},

success : function(response) {
/*alert( response );*/
console.log("SUCCESS: ", response);
var obj = response;
if(!response || response.length <=0){
	alert("No items in the inventory. Please add them before listing");
	return false;
}
$('#heading').append("<h3 style='display:block;background-color:#FF7F50;padding:.4em 10px'>Product Inventory List</h3>");
$('#data_table thead').append("<tr> <th>Product ID</th> <th>Product Name</th> <th>Product Description</th> <th>Image</th> </tr>");
$.each(obj, function(key,value){
	
	$('#data_table tbody').append("<tr> <td>"+value.id+"</td> <td>"+value.name+
			               "</td> <td>"+value.desc+"</td><td></td> </tr>");
	console.log(key+":"+value);
});	

//console.log(""+JSON.parse(response));
/* var obj = JSON.parse(response);*/
/*var resp = "<table> <tr> <th>ProductID</th> <th>Product Name</th> <th>Product Description</th></tr> </tr> <td>"+response.id+"</td> <td>"+response.name+"</td> <td>"+response.desc+"</td> </tr> </table>";*/
	//console.log(resp); 


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
        <h2 style="color:maroon;">Welcome ${firstname}!</h2>
        
          <img alt="AT&T logo"  width="400" height="200"  style="background-blend-mode:screen;" align="middle" src='<c:url value="/resources/images/ATT3.png" />' />      
        <p style="font-family:sans-serif;color:olive;font-size:medium;">Please select your operation to continue<p> <br><br>

<div id="menu">
<ul>
<li><a href="addItem">AddItem</a></li>
<li><a href="updateItem">UpdateInventory</a></li>
<li><a href="#!" id="listItems">ListInv</a></li> 
<li><a href="deleteItem">DeleteItem</a></li>

</ul>
</div>

<br> <br>
<div id="heading"></div>
<table align='center' id="data_table">

 <thead>
 </thead>

<tbody>

</tbody>
</table>

<br> <br>
<div id="updateMe" hidden>
<form  id="updateProd">
     Enter the current Product ID to be updated:
    <input type="text" id="old_id" required/> <br> 
    <p>Enter the details to be updated: </p><br>
     ProductID: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<input type="text" id="new_id" required/> <br> <br>
     Product Name: &nbsp; &nbsp; &nbsp; &nbsp;<input type="text" id="name" required/> <br> <br>
     Product Description: <input type="text" id="desc" required/> <br> <br>
        
     <input type="submit" value="Update"/> &nbsp; &nbsp; 
     <input type="reset"  name="Reset">
 </form>
</div>



     <p>${message}</p> 
     <br> <br>
     
<a href="logout">Logout</a>


<!-- <script type="text/javascript">
$(document).ready(function() {
$("#update").click(function() {
  //  $("#myhide").css({'display':'block'});
	 $("#myhide").show();
  
});

var old_id = document.getElementById("old_id").value;
var id = document.getElementById("new_id").value;
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
alert("Product updated");
},
error : function(xhr, status, error) {
alert(xhr.responseText);
}
});	 



});

</script>  -->  
    </body>
    </html>