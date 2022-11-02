<%@ page import="com.ssafy.home.board.dto.BoardDto" %><%--
  Created by IntelliJ IDEA.
  User: nowgnas
  Date: 2022/11/02
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% BoardDto detail = (BoardDto) request.getAttribute("detail"); %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Title</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="container text-center w-50 mt-4">
    <h2>글 작성하기</h2>
    <div>
        <form method="put" action="update" enctype="multipart/form-data">
            <div class="m-lg-5 text-start">
                <h3 class="align-content-start ">Title</h3>
                <input class="w-100" type="text" name="title" value="<%=detail.getTitle()%>">
            </div>
            <div class="m-lg-5 text-start">
                <h3 class="align-content-start">Content</h3>
                <textarea class="w-100 h-25" type="" name="content"><%=detail.getContent()%></textarea>
            </div>
            <input type="file" name="files" multiple="multiple">
            <input type="submit">
        </form>
    </div>

</div>


</body>
</html>
