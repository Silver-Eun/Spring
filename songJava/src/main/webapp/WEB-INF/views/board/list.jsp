<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="kr">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form id="form" method="get" action="/list">
        <div class="row mb-3">
            <label for="keyword" class="col-sm-2 col-form-label"><spring:message code="search.keyword"/></label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="keyword" id="keyword" value="${parameter.keyword}"
                       placeholder="<spring:message code="placeholder.replace"/>">
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><spring:message code="button.search"/></button>
    </form>
</div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col"><spring:message code="board.title"/></th>
        <th scope="col">Last</th>
        <th scope="col">Handle</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${boardList}" varStatus="status">
        <tr>
            <th scope="row">${status.count}</th>
            <td>${board.title}</td>
            <td>${board.viewcount}</td>
            <td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/></td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td colspan="2">Larry the Bird</td>
            <td>@twitter</td>
        </tr>
    </c:forEach>
    <c:if test="${fn:length(boardList) == 0}">
        <tr>
            <td colspan="4"><spring:message code="msg.board.empty"/></td>
        </tr>
    </c:if>
    </tbody>
</table>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script>
    $(function () {

        const $form = $('#form');
        $form.bind('submit', function () {

            $.ajax({
                url: '/board/save',
                type: 'post',
                data: $form.serialize(),
                dataType: 'json',
                success: function (data) {
                    if (data.code === 'SUCCESS') {
                        alert("저장되었습니다");
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
</body>
</html>