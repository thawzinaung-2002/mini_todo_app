<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>

	<form action="register">
		<input type="text" name="name" placeholder="Enter your name" /><br/>
		<span style="color:red">
			<c:out value="${nameError }" />
		</span><br/>
		<input type="email" name="email" placeholder="Enter your email" /><br/>
		<span style="color:red">
			<c:out value="${emailError}" />
		</span><br/>
		<input type="password" name="password" placeholder="Enter your password" /><br/>
		<span style="color:red">
			<c:out value="${passwordError}" />
		</span><br/>
		<input type="submit" value="Register" />
	</form>
	<a href="login.jsp">Already have an account?</a>

</body>
</html>