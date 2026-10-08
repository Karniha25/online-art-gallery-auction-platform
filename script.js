function register(event) {
    event.preventDefault();

    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;
    let role = document.getElementById("role").value;

    if (password !== confirmPassword) {
        document.getElementById("message").innerText =
            "Passwords do not match";
        return;
    }

    let user = {
        name: name,
        email: email,
        password: password,
        role: role
    };

    fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
    .then(response => response.text())
    .then(data => {

        document.getElementById("message").innerText = data;

        document.querySelector("form").reset();

        setTimeout(() => {
            window.location.href = "login.html";
        }, 1000);
    })
    .catch(error => {

        console.error(error);

        document.getElementById("message").innerText =
            "Registration failed.";
    });
}
async function login(event) {
    event.preventDefault();

    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;
    const message = document.getElementById("loginMessage");

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        if (response.ok) {

            const user = await response.json();

            if (user) {
                message.innerText = "Login successful!";

                // Store logged-in user
                localStorage.setItem("user", JSON.stringify(user));

                setTimeout(() => {

                    if (user.role === "ARTIST") {
                        window.location.href = "artist-dashboard.html";
                    }
                    else if (user.role === "ADMIN") {
                        window.location.href = "admin-dashboard.html";
                    }
                    else {
                        window.location.href = "index.html";
                    }

                }, 1000);
            } 
            else {
                message.innerText = "Invalid email or password";
            }

        } else {
            message.innerText = "Invalid email or password";
        }

    } catch (error) {
        console.error(error);
        message.innerText = "Unable to connect to server";
    }
}