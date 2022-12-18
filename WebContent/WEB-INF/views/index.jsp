<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<div class="row">
	<form action="${action}" method="post">
	<table cellpadding="5px" cellspacing="5px">
		<tr>
			<td>Enter Name</td>
			<td>
			<input type="text" name="name" class="form-control" value="${nameValue}"/>
			<input type="hidden" name="pid" value="${idValue}"/>
			</td>
		</tr>
		
		<tr>
			<td>Enter Age</td>
			<td><input type="text" name="age" class="form-control" value="${ageValue}"/></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit" value="${button}" class="btn btn-info"/></td>
		</tr>
	</table> 
	</form>
	</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="display-6" style="color: red">${error}</div>
			<div class="display-6" style="color: green">${success}</div>
<%-- 			<div class="display-6" style="color:green;">${PersonList}</div> for comment= ctrl + shift + C --%>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-1">ID</div>
		<div class="col-md-3">NAME</div>
		<div class="col-md-1">AGE</div>
		<div class="col-md-2">PHONE</div>
		<div class="col-md-2">ACTION</div>
	</div>
	
	<c:forEach items="${PersonList}" var="person">
		<div class="row">
			<div class="col-md-1">${person.id}</div>
			<div class="col-md-3">${person.name}</div>
			<div class="col-md-1">${person.age}</div>
			<div class="col-md-2">${person.phone}</div>
			<div class="col-md-2"><a href="./getPersonUpdate?id=${person.id}">UPDATE</a></div>
		</div>
	</c:forEach>
</body>
</html>