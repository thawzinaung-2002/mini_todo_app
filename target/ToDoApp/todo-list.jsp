<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
	table,th, td{
		font-size:1.2rem;
		border:1px solid black;
	}
</style>
</head>
<body>

	<c:if test="${user==null }">
		<c:redirect url="register.jsp"/>
	</c:if>

	<table style="border-collapse: collapse; width:50%">
		<tr>
			<th>Title</th>
			<th>Target Date</th>
			<th>Todo Status</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="todo" items="${todos }">
			<tr>
				<td>
					<c:out value="${todo.getTitle() }" />
				</td>
				<td>
					<c:out value="${todo.getTarget() }" />
				</td>
				<td>
					<c:out value="${todo.getStatus() }" />
				</td>
				<td>
					<a href="EditServlet?id=${todo.getId() }">Edit</a> | 
					<a href="DeleteTodoServlet?id=${todo.getId()}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>