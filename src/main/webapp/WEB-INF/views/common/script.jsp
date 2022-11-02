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

        data = await fetch("login", data);
        data = await data.text();
        data = JSON.parse(data);
        if (data.errMsg) {
            alert(data.errMsg);
        } else {
            opener.parent.location.reload();
            window.close();
        }
    });
    
    
</script>