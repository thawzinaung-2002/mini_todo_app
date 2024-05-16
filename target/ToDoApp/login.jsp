<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
</head>
<body>

	<div style="color:red">
		<c:out value="${msg }" />
	</div>

	<form action="login" method="post">
		<input type="email" name="email" placeholder="Enter your email"/><br/><br/>
		<input type="password" name="password" placeholder="Enter your password" /><br/><br/>
		<input type="submit" value="Log in" />
	</form>
	<a href="register.jsp">register?</a>

</body>
</html>