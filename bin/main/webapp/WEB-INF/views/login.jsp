<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
body{
background-color:  #F5F5F5;
font-family: sans-serif,serif,Georgia;
text-align: center;
background-blend-mode:screen;
}

</style>
</head>
<body>

<img alt="AT&T logo"  style="background-blend-mode:screen;" align="middle" src='<c:url value="/resources/images/ATT5.png" />' />

<br> <br>

<h3 style="color:navy;">Please login to continue</h3>  <br>

<form:form  modelAttribute="login" action="loginProcess" method="POST">
  <form:label path="username">Username: </form:label> 
   <form:input path="username" name="username" id="username" /> <br> <br>
   
   <form:label path="password">Password:</form:label>
    <form:password path="password" name="password" id="password" /> <br> <br>

 <form:button id="login" name="login">Login</form:button> &nbsp; &nbsp; 
<input type="reset"  name="Reset" >
 </form:form>
 <strong style="font-style: italic; color: red;">${message}</strong>
 
<!-- <table align="left">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>-->

</body>
</html>