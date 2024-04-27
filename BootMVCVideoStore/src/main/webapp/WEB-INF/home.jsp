<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
</head>
<body>

<div class="container">
	<h1>Boot MVC Film Site</h1>

	<form action="getFilm.do" method="GET">
		Film ID: <input type="text" name="filmId" /> 
		<input class="btn btn-outline-primary" type="submit"
			value="Show Film" />
	</form>

	<hr>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Year</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="film" items="${filmList}">
				<tr>
					<td> <a href="getFilm.do?filmId=${film.id}"> ${film.title } </a> </td>
					<td>${film.releaseYear }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>