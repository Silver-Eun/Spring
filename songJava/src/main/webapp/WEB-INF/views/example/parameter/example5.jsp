<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="kr">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<h1>ids : ${ids}</h1>
<c:forEach var="id" items="${ids}">
    <p>id : ${id}</p>
</c:forEach>
</body>
</html>