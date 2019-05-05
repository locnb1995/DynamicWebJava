<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employee</title>
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
		<form action="editEmp" method="post">
			<div class="form-group">
				<label>ID</label> 
				<input type="text" class="form-control" name="empid" readonly value="<c:out value='${emp.id}' />">
			</div>
			<div class="form-group">
				<label>Name</label> 
				<input type="text" class="form-control" name="empname" value="<c:out value='${emp.name}' />">
			</div>
			<div class="form-group">
				<label>Age</label> 
				<input type="number" class="form-control" name="empage" value="<c:out value='${emp.age}' />">
			</div>
			<div class="form-group">
				<label>Home</label> 
				<input type="text" class="form-control" name="emphome" value="<c:out value='${emp.hometown}' />">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>