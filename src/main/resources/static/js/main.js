function setFormMessage(formElement, type, message) {
  const messageElement = formElement.querySelector(".form__message");

  messageElement.textContent = message;
  messageElement.classList.remove(
    "form__message--success",
    "form__message--error"
  );
  messageElement.classList.add("form__message--" + type);
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
  loginForm.addEventListener("submit", (e) => {
    e.preventDefault();

    // Ajax Prozess --> Rest-Service Aufruf

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
});
