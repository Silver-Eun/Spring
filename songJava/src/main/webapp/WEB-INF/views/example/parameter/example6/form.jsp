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
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script>
    $(function () {
        var json = {
            user: {
                name: 'kim',
                age: '27',
                address: 'Korea'
            }
        };

        $.ajax({
            url: '/example/parameter/example6/saveData',
            type: 'post',
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                console.log(data)
            }
        })
    });
</script>
</body>
</html>