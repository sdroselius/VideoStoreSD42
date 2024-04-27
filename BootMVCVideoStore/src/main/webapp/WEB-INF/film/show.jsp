<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">

</head>
<body>

	<div class="container">
	<h6><a href="home.do">Home</a></h6>
	
		<h1>Film Details</h1>

		<div>
			<h5>${film.title}(${film.releaseYear})</h5>
			<h6>Rated ${film.rating }</h6>
			<h6>${film.length }minutes</h6>
			<blockquote>${film.description }</blockquote>
		</div>

	</div>
</body>
</html>