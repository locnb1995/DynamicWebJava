<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Manager</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Employee Table</h2>
		<p></p>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>AGE</th>
					<th>HOMETOWN</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${listEmp}">
					<tr>
						<td><c:out value="${emp.id}" /></td>
						<td><c:out value="${emp.name}" /></td>
						<td><c:out value="${emp.age}" /></td>
						<td><c:out value="${emp.hometown}" /></td>
						<td><a class="btn btn-success" href="editForm?id=<c:out value='${emp.id}' />">Edit</a></td>
						<td><a class="btn btn-danger" href="deleteEmp?id=<c:out value='${emp.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="addEmp" class="btn btn-primary">Add Employee</a>
	</div>


</body>
</html>