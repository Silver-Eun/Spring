<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="kr">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <sitemesh:write property="head"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/community"><spring:message code="menu.community"/></a>
                </li>
                <li>
                    <a class="nav-link" href="/notice"><spring:message code="menu.notice"/></a>
                </li>
                <li>
                    <a class="nav-link" href="/faq"><spring:message code="menu.faq"/></a>
                </li>
                <li>
                    <a class="nav-link" href="/inquiry"><spring:message code="menu.inquiry"/></a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<sitemesh:write property="body"/>
</body>
<script>
    $(function () {

        const $form = $('#form');
        $form.bind('submit', function () {

            $.ajax({
                url: '/${menuType}/save',
                type: 'post',
                data: $form.serialize(),
                dataType: 'json',
                success: function (response) {
                    if (response.code === 'SUCCESS') {
                        alert("저장되었습니다");
                        location.href = '/${menuType}/' + response.data;
                    } else {
                        alert(data.message);
                    }
                    console.log(data)
                }
            })
            return false;
        })
    })
</script>
</html>