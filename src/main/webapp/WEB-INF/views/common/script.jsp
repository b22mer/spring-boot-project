<%--
  Created by IntelliJ IDEA.
  User: nowgnas
  Date: 2022/10/27
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript" charset="utf-8">
    document.querySelector("#loginBtn").addEventListener("click", async () => {
        let id = document.querySelector("#loginId").value;
        let pw = document.querySelector("#loginPw").value;

        let data = {
            method: "POST",
            body: JSON.stringify({id, pw}),
            headers: {"Content-Type": "application/json"},
        }

        console.log(data);
        data = await fetch("login", data);
        data = await data.text();
        data = JSON.parse(data);
        if (data.msg) {
            alert(data.msg);
        } else {
            alert(data.name + " 님 안녕하세요!!");
            window.close();
        }
    });

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
</script>