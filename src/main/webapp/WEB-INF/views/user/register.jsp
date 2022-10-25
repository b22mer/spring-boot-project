<%--
  Created by IntelliJ IDEA.
  User: nowgnas
  Date: 2022/10/25
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%@include file="../common/head.jsp" %>
    <title>회원가입</title>
</head>
<body>
<article class="container">
    <div class="page-header">
        <div class="col-md-6 col-md-offset-3">
            <h3>회원가입 Form</h3>
        </div>
    </div>
    <div class="col-sm-6 col-md-offset-3">
        <form role="form">
            <div class="form-group">
                <label for="inputName">성명</label>
                <input type="text" class="form-control" id="name" placeholder="이름을 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="inputName">포지션</label>
                <input type="text" class="form-control" id="position" placeholder="포지션을 입력해 주세요">
            </div>
            <div class="form-group">
                <label for="InputEmail">ID</label>
                <input type="text" class="form-control" id="id" placeholder="ID를 입력해주세요">
                <input type="button" id="checkIdBtn" class="btn btn-info" style="margin-top: 10px;" value="ID중복검사">
            </div>
            <div class="form-group">
                <label for="inputPassword">비밀번호</label>
                <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력해주세요">
            </div>
            <div>
                <p id="pwCheck" style="color:red;"></p>
            </div>
            <div class="form-group">
                <label for="inputPasswordCheck">이메일</label>
                <input type="email" class="form-control" id="email" placeholder="이메일을 입력 해 주세요">
            </div>
            <div class="form-group">
                <label for="inputMobile">휴대폰 번호</label>
                <input type="text" class="form-control" id="phone" placeholder="휴대폰번호를 입력해 주세요">
            </div>

            <div class="form-group text-center">
                <input type="button" id="memberInsertBtn" class="btn btn-primary"
                       value="회원가입">
                <button type="submit" class="btn btn-warning">
                    가입취소<i class="fa fa-times spaceLeft"></i>
                </button>
            </div>
        </form>
    </div>

</article>

</body>
</html>