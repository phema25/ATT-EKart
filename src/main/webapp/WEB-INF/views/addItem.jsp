<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD Inv</title>
<style type="text/css">
body{
background-color:  #F8F8F8;
font-family: sans-serif,serif,Georgia;
background-blend-mode:screen;
}

#data_table{
 font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
}

#data_table td, #data_table th {
    border: 1px solid #ddd;
    padding: 8px;
}

#data_table tr:hover {background-color: #ddd;}

#data_table th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #0066ff;
    color: white;
    font-weight: bold;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<c:url value="/resources/js/tablescript.js" />"></script>

<script type="text/javascript">

   $(document).ready(function() {	
		   /*  Submit form using Ajax */
		 $("#addProd").submit(function(event) {
			 console.log('enter submit');
		      //Prevent default submission of form
		      event.preventDefault();
		      
		      //Remove all errors
		      //$('input').next().remove();
		      var data = {};
		      data["id"] = $("#id").val();
		      console.log($("#id").val());
		      data["name"]=$("#name").val();
		      data["desc"]=$("#desc").val();
		      console.log(data);
		      console.log(JSON.stringify(data));
	 $.ajax({
         url : 'addProcess',
         data : JSON.stringify(data),
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
            	$('#data_table tbody').append("<tr id='row"+len+"'><td> <input type='text' style='border-style:ridge' readonly='readonly' id='id"+len+"' value='" +response.id+"' /> </td><td> <input type='text' style='border-style:ridge' readonly='readonly' id='name"+
            			                        len+"' value='"+response.name+
            			                         "' /></td> <td>  <input type='text' style='border-style:ridge' readonly='readonly' id='desc"+len+"' value='"+response.desc+"' /></td> <td></td>"+
            			                        "<td><input type='button' id='edit_button"+len+
            			                        "' value='Edit' class='edit' onclick='edit_row("+len+
            			                        ")'> </td> <td><input type='button' id='save_button"+len+
            			                        "' value='Save' class='save' onclick='save_row("+len+
            			                        ")'></td> <td> <input type='button' value='Delete' class='delete' onclick='delete_row("+len+")'></td></tr>"); 
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
<!--<form  id="addProd" method="post">
  Product ID:  
   <input type="text" name="id" id="id" /> <br> <br>
   
    Product name:
    <input type="text" name="name" id="name" /> <br> <br>
    
    Product Desc:
    <input type="text" name="desc" id="desc" /> <br> <br>

 <input type="submit" id="submit" name="Add" /> &nbsp; &nbsp; 
<input type="reset"  name="Reset" >
 </form> -->
 <h3 style="display:block;background-color:powderblue;padding:.4em 15px">Please enter the details to add the product:</h3> <br>
 
<form:form  id="addProd"   modelAttribute="product">
  <form:label path="id">Product ID: </form:label> 
   <form:input path="id" name="id"  id="id" required="required"/> <br> <br>
   
    <form:label path="name">Product name:</form:label>
    <form:input path="name" name="name" id="name" required="required"/> <br> <br>
    
    <form:label path="desc">Product Desc:</form:label>
    <form:input path="desc" name="desc" id="desc" required="required"/> <br> <br>

 <input type="submit" value="Add"/> &nbsp; &nbsp; 
<input type="reset"  name="Reset">
 </form:form>
<br> <br> <br> <br>

<h2 style="display:block;background-color:powderblue;padding:.4em 15px">Current Inventory Items</h2>


<table align='center' cellspacing=4 cellpadding=5 id="data_table" border=1 style="display:block">

 <thead> 
<tr>
<th>Product ID</th>
<th>Product Name</th>
<th>Product Description</th>
<th>Image</th>
</tr>
</thead>

<tbody>
<tr>
<td>999</td>
<td>dummy</td>
<td>Sample product</td>
<td><img src="<c:url value="/resources/images/mac1.jpg" />" alt="Laptop" width="200" height="200" style="float:right;" />  </td>
</tr>

</tbody>
</table>

<br> <br> <br>

<a href="welcome">Home</a>
<a href="logout">Logout</a>


<!-- Script starts from here ****************************************** -->




</body>
</html>    