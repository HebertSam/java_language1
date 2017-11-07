<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	</head>

	<body>
		<div>
			<table>
				<tr>
					<th>Name</th>
					<th>Creator</th>
					<th>Version</th>
					<th>action</th>
				</tr>
				<c:forEach items="${languages}" var="language" varStatus="loop">
						<tr>
							<td>${language.name}</td>
							<td>${language.creator}</td>
							<td>${language.version}</td>
							<td><a href="/update/${loop.index}">update</a> <a href="/delete/${loop.index}">delete</a></td>
						</tr>
				</c:forEach>

			</table>
		</div>
		<c:forEach items="${errors}" var="error">
			<h4>${error.defaultMessage}</h4>
		</c:forEach>

		<form:form method="post" action="/language/new" modelAttribute="Language">
			<form:label path="name">name
				<form:errors path="name"/>
				<form:input path="name"/>
			</form:label>
			<form:label path="creator">creator
				<form:errors path="creator"/>
				<form:input path="creator"/>
			</form:label>
			<form:label path="version">version
				<form:errors path="version"/>
				<form:input path="version"/>
			</form:label>
			<input type="submit" value="Submit">
		</form:form>
	</body>
</html>