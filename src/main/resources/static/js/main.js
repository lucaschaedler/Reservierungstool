const loginForm = document.querySelector("#login");
const requestAccountForm = document.querySelector("#requestAccount");
var active_user;

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
  formElement.reset(); //Felder leeren
}
//Wenn Login erfolgreich wird die Kalendersicht angezeigen
function showCalendar(response) {
  active_user = response;
  console.log("modul main.js: " + active_user);
  if (active_user != -1) {
    setFormMessage(loginForm, "Login erfolgreich", true);
    window.location.href = "calendar.html";
  } else {
    setFormMessage(loginForm, "Login fehlgeschlagen", false);
  }
}

document.addEventListener("DOMContentLoaded", () => {
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
  });

  //AccountRequest-Prozess
  requestAccountForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let a_name = document.querySelector("#a_name").value;
    let a_email = document.querySelector("#a_email").value;
    let a_mobile = document.querySelector("#a_mobile").value;
    let a_password = document.querySelector("#a_password").value;

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
      success: function (response) {
        setFormMessage(
          requestAccountForm,
          "Accountanfrage erfolgreich",
          response
        );
      },
      error: function (response) {
        setFormMessage(
          requestAccountForm,
          "Accountanfrage fehlgeschlagen",
          response
        );
      },
      dataType: "json",
      contentType: "application/json",
    });
  });
});
