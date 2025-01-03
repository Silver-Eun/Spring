<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/file/save" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile">
    <button type="submit">파일 업로드</button>
</form>
</body>
</html>