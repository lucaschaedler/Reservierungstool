function setFormMessage(formElement, message, response) {
  const messageElement = formElement.querySelector(".form__message");
  messageElement.textContent = message;
  messageElement.classList.remove(
    "form__message--success",
    "form__message--error"
  );
  if (response == true) {
    messageElement.classList.add("form__message--success");
  } else {
    messageElement.classList.add("form__message--error");
  }
}

//Wenn Login erfolgreich wird die Kalendersicht angezeigt
function showCalendar(response) {
  console.log(response);
  if (response != -1) {
    //-1 --> login failed
    window.location.href = "html/calendar.html";
  }
}

document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.querySelector("#login");
  const requestAccountForm = document.querySelector("#requestAccount");

  document
    .querySelector("#linkRequestAccount")
    .addEventListener("click", (e) => {
      e.preventDefault();
      loginForm.classList.add("form--hidden");
      requestAccountForm.classList.remove("form--hidden");
    });

  document.querySelector("#linkLogin").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.remove("form--hidden");
    requestAccountForm.classList.add("form--hidden");
  });
  //Login-Prozess
  loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let u_email = document.querySelector("#u_email").value;
    let u_password = document.querySelector("#u_password").value;
    // Ajax Prozess --> Rest-Service Aufruf
    $.ajax({
      type: "POST",
      url: "/api/login",
      data: JSON.stringify({
        userEmail: u_email,
        userPassword: u_password,
      }),
      success: showCalendar,
      dataType: "json",
      contentType: "application/json",
    });
    //je nach Rückgabewert von VerificationClass
    var isLoggedIn = true;
    if (isLoggedIn) {
      setFormMessage(loginForm, "success", "Sie sind angemeldet!");
    } else {
      setFormMessage(
        loginForm,
        "error",
        "Email/Passwort Kombination stimmt nicht überein!"
      );
    }
  });

  //AccountRequest-Prozess
  requestAccountForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let a_name = document.querySelector("#a_name").value;
    let a_email = document.querySelector("#a_email").value;
    let a_mobile = document.querySelector("#a_mobile").value;
    let a_password = document.querySelector("#a_password").value;
    // erste verifikation der daten

    // Ajax Prozess --> Rest-Service Aufruf
    $.ajax({
      type: "POST",
      url: "/api/account_request",
      data: JSON.stringify({
        accountRequestName: a_name,
        accountRequestEmail: a_email,
        accountRequestMobile: a_mobile,
        accountRequestPassword: a_password,
      }),
      success: setFormMessage(requestAccountForm, "erfolgreich"),
      error: setFormMessage(requestAccountForm, "fehlgeschlagen"),
      dataType: "json",
      contentType: "application/json",
    });
  });
});
