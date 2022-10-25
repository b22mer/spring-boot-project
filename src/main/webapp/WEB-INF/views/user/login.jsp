<%--
  Created by IntelliJ IDEA.
  User: nowgnas
  Date: 2022/10/25
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <title>Title</title>
</head>
<body>
<article class="container">
    <div class="page-header">
        <div class="col-md-6 col-md-offset-3">
            <h3>회원가입 Form</h3>
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
<script type="text/javascript" src="/js/member.js"></script>
</html>
