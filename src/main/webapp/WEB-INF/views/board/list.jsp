<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Board list</title>
</head>
<%@include file="../common/head.jsp" %>
<body>
<%@include file="../common/header.jsp" %>
<div id="boardListDiv" class="container mt-3"></div>
</body>
<script>
    window.addEventListener("load", async () => {
        let data = {
            method: "GET"
        }

        data = await fetch("list", data);
        data = await data.text();
        data = JSON.parse(data);

        let list = `<table class="table table-hover"><tr><th>index</th><th>글번호</th><th>제목</th></tr>`;
        data.forEach((item, index) => {
            index++;
            list += "<tr><td>" + index + "</td><td>" + item.code + "</td><td>" + item.title + "</td></tr>";
            <%--list += `<tr><td>${index}</td><td>${item["code"]}</td><td>${item["title"]}</td></tr>`;--%>
        })
        list += `</table>`;
        $("#boardListDiv").html(list);
    })
</script>
</html>