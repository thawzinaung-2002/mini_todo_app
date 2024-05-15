<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>

	<c:if test="${user==null }">
		<c:redirect url="register.jsp"/>
	</c:if>

	<div style="color:red">
		<c:out value="${msg }" />
	</div>
	
	<form action="updateTodo" >
		Title : <input type="text" name="title" value="${ todo.getTitle()}"/><br/><br/>
		Target Date : <input type="date" name="target" value=" ${todo.getTarget()} "/><br/><br/>
		Status : <input type="radio" name="status" value="${todo.getStatus()} ">True 
		<input type="radio" name="status" value="${todo.getStatus() }">False<br/><br/>
		<input type="hidden" name="id" value="${todo.getId()}" />
		<input type="submit" value="Update" />
	</form>
	<a href="home.jsp" >Back</a>
	

</body>
</html>