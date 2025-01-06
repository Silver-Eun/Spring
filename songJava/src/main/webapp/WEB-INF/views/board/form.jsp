<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="kr">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form id="form" method="post" action="/board/save">
        <input type="hidden" name="boardType" id="boardType" value="COMMUNITY">
        <div class="row mb-3">
            <label for="title" class="col-sm-2 col-form-label"><spring:message code="board.title"/></label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="title" id="title" value="${board.title}"
                       placeholder="<spring:message code="placeholder.replace"/>">
            </div>
        </div>
        <div class="row mb-3">
            <label for="contents" class="col-sm-2 col-form-label"><spring:message code="board.contents"/></label>
            <div class="col-sm-10">
                <textarea class="form-control" name="contents" id="contents"
                          placeholder="<spring:message code="placeholder.replace"/>">${board.contents}</textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><spring:message code="button.save"/></button>
    </form>
</div>
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