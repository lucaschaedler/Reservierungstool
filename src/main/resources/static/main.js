//LOGIN
const loginForm = document.querySelector("#login");

//pages
const home = document.querySelector("#home");
const calendar = document.querySelector("#calendar");
const userlist = document.querySelector("#userlist");
const accreqlist = document.querySelector("#accreqlist");

const button_userlist = document.querySelector("#button_userlist");
const button_accreqlist = document.querySelector("#button_accreqlist");

//current user object
let user = {
  userid: null,
  authorization: "",
};

//Wenn Login erfolgreich wird die Kalendersicht angezeigen
function showCalendar(response) {
  if (response == null) {
    alert("Login inkorrekt");
    return;
  } else {
    user.userid = response.userId;
    user.authorization = response.authorization;
    home.hidden = true;
    calendar.hidden = false;

    if (user.authorization.localeCompare("administrator") == 0) {
      button_userlist.hidden = false;
      button_accreqlist.hidden = false;
    }
  }

  /*console.log("modul main.js: " + active_user);
  if (active_user != -1) {
    setFormMessage(loginForm, "Login erfolgreich", true);
    window.location.href = "calendar.html";
  } else {
    setFormMessage(loginForm, "Login fehlgeschlagen", false);
  }*/
}

//Eventlistener
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
    let a_password_repeat = document.querySelector("#a_password_repeat").value;

    if (
      matchPassword(a_password, a_password_repeat) &&
      verifyPassword(a_password)
    ) {
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
          let message = "Accountanfrage erfolgreich";
          if (!response) {
            message = "Accountanfrage fehlgeschlagen";
          }
          setFormMessage(requestAccountForm, message, response);
        },
        dataType: "json",
        contentType: "application/json",
      });
    }
  });
});

//ACCOUNT REQUEST
const requestAccountForm = document.querySelector("#requestAccount");

//verify-methoden
function verifyPassword(password) {
  isCorrect = false;
  //minimum password length validation
  if (password.length < 8) {
    window.alert("Passwort muss zwischen 8-15 Zeichen enthalten");
    return false;
  }

  //maximum length of password validation
  if (password.length > 15) {
    window.alert("Passwort muss zwischen 8-15 Zeichen enthalten");
    return false;
  } else {
    return true;
  }
}
function matchPassword(password, password_repeat) {
  isIdentical = false;
  if (password == password_repeat) {
    isIdentical = true;
  } else {
    window.alert("Passwörter stimmen nicht überein");
  }
  return isIdentical;
}

function setFormMessage(formElement, message, response) {
  const messageElement = formElement.querySelector(".form__message");
  messageElement.textContent = message;
  messageElement.classList.remove(
    "form__message--success",
    "form__message--error"
  );
  if (response) {
    messageElement.classList.add("form__message--success");
  } else {
    messageElement.classList.add("form__message--error");
  }
  formElement.reset(); //Felder leeren
}

//CALENDAR SHIZZZLE

/*import { active_user } from "./main.js";
const id = active_user;
console.log("modul calendar_script.js: " + id);*/

let court1Btn = document.querySelector("#button_show_court1");
let court2Btn = document.querySelector("#button_show_court2");
let court1Tbl = document.querySelector("#table_court_1");
let court2Tbl = document.querySelector("#table_court_2");
let datePicker = document.querySelector("#datepicker");
let dateObject;

//überprüft die Berechtigung ob man die listen a^nschsuen darf

button_userlist.addEventListener("click", () => {
  calendar.hidden = true;
  userlist.hidden = false;
  userlistclicked();
});

button_accreqlist.addEventListener("click", () => {
  calendar.hidden = true;
  accreqlist.hidden = false;
  accountRequestlistclicked();
});

//buttons on click methoden
court1Btn.addEventListener("click", () => {
  court1Tbl.hidden = false;
  court2Tbl.hidden = true;
  court2Btn.disabled = false;
  court1Btn.disabled = true;
  console.log("Tabelle 1 wird angezeigt");
});

//Wenn Platz 1 angezeigt wird, ist der Button für Platz 1 disabled + Button für Platz 2 enabled
court1Btn.addEventListener("click", () => {
  court1Tbl.hidden = false;
  court2Tbl.hidden = true;
  court2Btn.disabled = false;
  court1Btn.disabled = true;
  console.log("Tabelle 1 wird angezeigt");
});

//Wenn Platz 2 angezeigt wird, ist der Button für Platz 2 disabled + Button für Platz 1 enabled
court2Btn.addEventListener("click", () => {
  court1Tbl.hidden = true;
  court2Tbl.hidden = false;
  court2Btn.disabled = true;
  court1Btn.disabled = false;
  console.log("Tabelle 2 wird angezeigt");
});

/*
//Datepicker-Funktion
$(function () {
  $("#datepicker").datepicker();
});

//Selektiertes Datum bei jeder Änderung abspeichern
dateObject = $("#datepicker").datepicker({
  onSelect: function () {
    dateObject = $(this).datepicker("getDate");
    console.log("Neues Datum ausgewählt: " + dateObject);
    updateTable();
  },
});

// Tabelle wird auf den Tag angepasst
function updateTable() {
  console.log("table updated");
}

//JSON-File wird bearbeitet
/*
const url = "reservations.json";
const api_url = "https://api.wheretheiss.at/v1/satellites/25544";

async function getData() {
  const response = await fetch(url);
  const data = await response.json();
  for (i = 0; i < data.reservations.length; i++) {
    console.log(data.reservations[i].playernames);
  }
}
getData();
*/

//reservation-ajax-api
/*
$(document).ready(function () {
  $.getJSON("../test/reservations.json", function (data) {
    var reservation_data = "";
    var i = 0;
    $.each(data, function (key, value) {
      while (i < value.length) {
        reservation_data += "<tr>";
        reservation_data += "<td>" + value[i].start + "</td>";
        reservation_data += "<td>" + value[i].playernames + "</td>";
        reservation_data += "</tr>";
        i++;
      }
    });
    $("#table_court_1").append(reservation_data);
  });
});

*/
//userlist js sachen
//abrufen von userdaten
function userlistclicked() {
  $.getJSON("api/users").done(handleUserlistReply);
}

//abrufen von account anfragen daten
function accountRequestlistclicked() {
  $.getJSON("/api/accountRequests").done(handleAccountRequestlistReply);
}
//filter muss noch angeschaut werden
/*
function applyFilter() {
  var filter = $("#inpUserList").val();
  $.getJSON("api/users", { filter: filter }).done(handleUsersReply);
}

//filter muss noch angeschaut werden
function applyFilter() {
  var filter = $("#inpAcc").val();
  $.getJSON("/api/accountRequests", { filter: filter }).done(
    handleAccountRequestlistReply
  );
}
*/
//fügt daten hinzu zu userlist
function handleUserlistReply(users) {
  $("#tblUserList tbody").empty();

  for (let user of users) {
    addUserToList(user);
  }
}

function addUserToList(user) {
  let id = user["userId"];
  console.log(id);

  var newRow = "<tr>";
  newRow += "<td>" + user["userId"] + "<td>";
  newRow += "<td>" + user["userName"] + "<td>";
  newRow += "<td>" + user["userEmail"] + "<td>";
  newRow += "<td>" + user["userMobile"] + "<td>";
  newRow += "<td>" + user["authorization"] + "<td>";
  newRow +=
    "<td> <button id = 'u" +
    user["userId"] +
    "' onClick='deleteUser(" +
    user["userId"] +
    ")'> Delete </button> </td>";
  newRow += "</tr>";

  $("#tblUserList tbody").append(newRow);
}
//daten hinzufügen zu accreq
function handleAccountRequestlistReply(reqs) {
  $("#tblAccList tbody").empty();

  for (let req of reqs) {
    addReqToList(req);
  }
}

function addReqToList(req) {
  let id = req["accountRequestId"];
  console.log(id);

  var newRow = "<tr>";
  newRow += "<td>" + req["accountRequestId"] + "<td>";
  newRow += "<td>" + req["accountRequestName"] + "<td>";
  newRow += "<td>" + req["accountRequestEmail"] + "<td>";
  newRow += "<td>" + req["accountRequestMobile"] + "<td>";
  newRow +=
    "<td> <button id = 'u" +
    req["accountRequestId"] +
    "' onClick='deleteAccRequest(" +
    req["accountRequestId"] +
    ")'> Delete </button> </td>";
  newRow +=
    "<td> <button id = 'u" +
    req["accountRequestId"] +
    "' onClick='createUser(" +
    req["accountRequestId"] +
    ")'> Account erstellen </button> </td>";
  newRow += "</tr>";

  $("#tblAccList tbody").append(newRow);
}
//userlöschen
function deleteUser(id) {
  var urlstring = "api/user/delete/" + id;
  console.log("delete  " + id);
  $.ajax({
    type: "DELETE",
    url: urlstring,
    success: deleteUserRespons,
    dataType: "json",
    contentType: "application/json",
  });
}
//accreq löschen
function deleteAccRequest(id) {
  console.log("delete request" + id);
  var urlstring = "/api/deleteAccountRequest/" + id;
  $.ajax({
    type: "DELETE",
    url: urlstring,
    success: deleteReqRespons,
    dataType: "json",
    contentType: "application/json",
  });
}
//user erstellen
function createUser(id) {
  console.log("create user" + id);
  var urlstring = "api/createUser/" + id;
  $.ajax({
    type: "Post",
    url: urlstring,
    success: createUserResponse,
    dataType: "json",
    contentType: "application/jsin",
  });
}
//antwort von userlöschen und update gui
function deleteUserRespons(response) {
  if (response == true) {
    userlistclicked();
  } else {
    console.log("fehler beim löschen des users");
  }
}
//antwort von accreq löschen und update gui
function deleteReqRespons(response) {
  if (response == true) {
    accountRequestlistclicked();
  } else {
    console.log("fehler beim löschen des request");
  }
}
//antwort von user erstellen und aktulisieren
function createUserResponse(response) {
  if (response == true) {
    accountRequestlistclicked();
  } else {
    console.log("fehler beim erstellen des users");
  }
}
let userlistreturnbtn = document.querySelector("#userlistreturnbtn");
let accreqreturnbtn = document.querySelector("#accreqreturnbtn");
//exit funktionen
userlistreturnbtn.addEventListener("click", () => {
  calendar.hidden = false;
  userlist.hidden = true;
});
accreqreturnbtn.addEventListener("click", () => {
  calendar.hidden = false;
  accreqlist.hidden = true;
});

//logout funktion
let logoutbtn = document.querySelector("#logoutbtn");

logoutbtn.addEventListener("click", () => {
  (user.userid = null), (user.authorization = ""), (calendar.hidden = true);
  home.hidden = false;
  console.log(user);
});
