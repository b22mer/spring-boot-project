document.querySelector("#loginBtn").addEventListener("click", async () => {
    let id = document.querySelector("#loginId").value;
    let pw = document.querySelector("#loginPw").value;

    let data = JSON.stringify({id, pw});
    console.log(data);
    data = await fetch("login", {
        method: "POST",
        body: data,
        headers: {"Content-Type": "application/json"},
    });


    data = await data.text();

    // login 창 닫기

    alert(data);
    
});