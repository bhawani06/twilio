<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
	<script type="text/javascript">
function validateform(){  
var phone=document.my.guestbookName.value;  
var delay=document.my.delay.value;  

var special_list = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";

    for (var i = 0; i < phone.length; i++) {
       if (special_list.indexOf(phone.charAt(i)) != -1) {
           alert ("Phone number can't have special characters among -~,;\\\`!#$%^&*+=-[]'/{}|\":<>?.\n Please correct the input \n");
           return false;
       }
    }

	for (var i = 0; i < delay.length; i++) {
       if (special_list.indexOf(delay.charAt(i)) != -1) {
           alert ("Delay can't have special characters among -~,;\\\`!#$%^&*+=-[]'/{}|\":<>?.\n Please correct the input \n");
           return false;
       }
    }
	
if(!(/^\d+$/.test(phone)))
{
alert("Phone number should be only digits");  
  return false;
}
if(phone.length!=10)
{
alert("Phone number should have 10 digits");  
  return false;
}

if(delay.length>3)

{
alert("Too long delay. Please enter a smaller delay time");  
  return false;
}

if (phone==null || phone==""){  
  alert("Phone can't be blank");  
  return false;  
}
}  
	
</script>  
</head>

<body>

<p>Welcome to PhoneBuzz!!!! Please enter your phone number and delay(enter 0 if no delay is required).</p>
<hr>
<form action="/twiml" name="my" method="post" onsubmit="return validateform()">
	<div><label>Phone Number(only digits without country code e.g - 4791232156):</label></div> 
    <div><input type="text" name="guestbookName" /></div>
	<br>
    <div><input type="text" name="delay" style="visibility:hidden"/></div>
	<br>
    <div><input type="submit" value="PhoneBuzz It"/></div>
</form>

</body>
</html>