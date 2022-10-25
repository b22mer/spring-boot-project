document.querySelector("#loginBtn").addEventListener("click", async () => {
    let id = document.querySelector("#loginId").value;
    let pw = document.querySelector("#loginPw").value;

    let data = JSON.stringify({ id, pw });
    console.log(data);
    data = await fetch("login", {
        method: "POST",
        body: data,
        headers: { "Content-Type": "application/json" },
    });
    data = await data.text();

    // login 창 닫기

    alert(data);
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
        body: JSON.stringify({ name, position, id, pw, email, phoneNumber }),
        headers: { "Content-Type": "application/json" },
    };

    data = await fetch("register", data);
    data = await data.text();
    alert(data);
});
