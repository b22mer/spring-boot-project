<%--
  Created by IntelliJ IDEA.
  User: nowgnas
  Date: 2022/11/01
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="../common/head.jsp" %>
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <title>board list</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="container w-75">
    <div id="boardListDiv" class="container mt-3"></div>

    <div class="text-end"><a class="btn btn-primary" href="${root}/board/upload">글 작성하기</a></div>
</div>


</body>
<script>
    function selectAllBoard(pageNo) {
        $.post("list", {pageNum: pageNo, pageSize: 10}, function (data) {
            console.log(data);
            let board = `<center><table class="table table-hover"><tr><th>index</th><th>제목</th><th>작성자</th><th>생성 날짜</th></tr>`;
            data.list.forEach(function (item, index) {
                board += `<tr><td>\${++index}</td><td><a href="/board/detail/\${item.code}">\${item.title}</a></td><td>\${item.writer}</td><td>\${item.registerTime.split("T")[0]}</td></tr>`;
            });
            board += `</table><br>`;
            if (data.hasPreviousPage) board += `<a href="#" onclick="selectAllBoard(\${data.prePage})"> << </a>`;
            board += `\${data.pageNum}`;
            if (data.hasNextPage) board += `<a href="#" onclick="selectAllBoard(\${data.nextPage})"> >> </a>`;
            board += `</center>`;
            console.log(board);
            $("#boardListDiv").html(board);
        });
    }

    $(document).ready(function () {
        selectAllBoard(1);
    });
</script>
</html>