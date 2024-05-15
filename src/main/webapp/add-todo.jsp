<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo Form</title>
</head>
<body>

	<c:if test="${user==null }">
		<c:redirect url="register.jsp"/>
	</c:if>
	<div style="color:red">
		<c:out value="${msg }" />
	</div>

	<form action="addTodo" >
		Title : <input type="text" name="title"/><br/><br/>
		Target Date : <input type="date" name="target"/><br/><br/>
		Status : <input type="radio" name="status" value="True" selected>True 
		<input type="radio" name="status" value="False">False<br/><br/>
		<input type="submit" value="Submit" />
	</form>
	<a href="home.jsp" >Back</a>
</body>
</html>