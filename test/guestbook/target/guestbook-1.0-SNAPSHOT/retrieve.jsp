<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>
<p> Please enter your phone number and submit to see your previously played game details. </p>

<form action="/retrieve" method="post">
	<div><label>Phone Number(only digits without country code e.g - 4791232156):</label></div> 
    <div><input type="text" name="phone" /></div>
	<br>
    <div><input type="submit" value="Retrieve It"/></div>
</form>

</body>
</html>