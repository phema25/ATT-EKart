<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Inv</title>
<style type="text/css">
body{
background-color:  #F8F8F8;
font-family: sans-serif,serif,Georgia;
background-blend-mode:screen;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

   $(document).ready(function() {	
		   /*  Submit form using Ajax */
		 $("#delProd").submit(function(event) {
			 console.log('enter submit');
		      //Prevent default submission of form
		      event.preventDefault();
		      
		      //Remove all errors
		      //$('input').next().remove();
		      if(!id){
		    	  alert("Please enter the ID value");
		    	  return false;
		      }
		      
		      var data = $("#id").val();
		       
		      console.log(data);		     
		      console.log(JSON.stringify(data));
	 $.ajax({
         url : 'delete/'+data,
         data : JSON.stringify(data),
         type : 'GET',
         beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
         },
        
         success : function(response) {
             /*alert( response );*/
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
  <h3>Please enter the productID to be deleted:</h3>
  <form id="delProd">
  <input type="text" id="id">
  <input type="submit" value="delete" id="sub">  
  </form>
<br> <br> <br>

<a href="welcome">Home</a> &nbsp; &nbsp;
<a href="logout">Logout</a>

</body>
</html>