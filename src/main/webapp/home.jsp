<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini Todo App</title>
<style>
* {
	padding: 0;
	margin: 0;
}
.head {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	background: red;
	color: white;
	padding: 1rem;
}
</style>
</head>
<body>

	<c:if test="${user==null }">
		<c:redirect url="register.jsp"/>
	</c:if>

	<section class="head">
		<header>
			<h2>ToDos</h2>
		</header>
		<div>
			<a href="logout" >Log out</a>
		</div>
	</section>
	<section>
		<h5 style="font-size: 1.2em; text-align:center">List of Todos</h5>
		<hr/>
		<button style="border: 1px solid black; padding: 0.2em; margin: 0.5em">
			<a href="add-todo.jsp" style="display: block; text-decoration:none">Add Todo</a>
		</button>
	</section>
	<c:if test="${todos != null && todos.size() > 0}">
		<%@include file="todo-list.jsp" %>
	</c:if>
	
</body>
</html>