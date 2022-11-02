<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="../common/head.jsp" %>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
            <input type="text" class="form-control" id="phoneNumber" placeholder="휴대폰번호를 입력해 주세요">
        </div>

        <div class="form-group text-center">
            <input type="button" id="registerBtn" class="btn btn-primary"
                   value="회원가입">
            <button type="submit" class="btn btn-warning">
                가입취소<i class="fa fa-times spaceLeft"></i>
            </button>
        </div>
    </div>

</article>
<script>
document.querySelector("#registerBtn").addEventListener("click", async () => {
    let name = document.querySelector("#name").value;
    let position = document.querySelector("#position").value;
    let id = document.querySelector("#id").value;
    let pw = document.querySelector("#pw").value;
    let email = document.querySelector("#email").value;
    let phoneNumber = document.querySelector("#phoneNumber").value;

    let data = {
        method: "POST",
        body: JSON.stringify({name, position, id, pw, email, phoneNumber}),
        headers: {"Content-Type": "application/json"},
    };
    
    

    data = await fetch("register", data);
    data = await data.text();
    alert(data);
    
    
});



document.querySelector("#checkIdBtn").addEventListener("click", async () => {
    let id = document.querySelector("#id").value;
 
    let data = JSON.stringify({id});
 
	 
    
    data = await fetch("idchck", {
        method: "POST",
        body: data,
        headers: {"Content-Type": "application/json"},
    });
    
    data = await data.text();
    data = JSON.parse(data);
    if (data.errMsg) {
        alert(data.errMsg+"s");
    } else {
    	alert(data.Msg);
    }
});

   
</script>
	    

	 
	 
	 
	 

</body>
</html>