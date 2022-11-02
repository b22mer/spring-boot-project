<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<article class="container">
    <div class="page-header">
        <div class="col-md-6 col-md-offset-3">
            <h3>로그인</h3>
        </div>
    </div>
    <div class="col-sm-6 col-md-offset-3">
        <div class="form-group">
            <label for="InputEmail">ID</label>
            <input type="text" class="form-control" id="loginId" placeholder="ID를 입력해주세요">
        </div>
        <div class="form-group">
            <label for="inputPassword">비밀번호</label>
            <input type="password" class="form-control" id="loginPw" placeholder="비밀번호를 입력해주세요">
        </div>
        <div class="form-group text-center">
            <input type="button" id="loginBtn" class="btn btn-primary"
                   value="로그인">
        </div>
    </div>
</article>

</body>
<%@include file="../common/script.jsp" %>
</html>
