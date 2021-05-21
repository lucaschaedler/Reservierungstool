//LOGIN
const loginForm = document.querySelector("#login");

//ACCOUNT REQUEST
const requestAccountForm = document.querySelector("#requestAccount");
const accreqMessage = document.querySelector("#accreq_message");
const loginMessage = document.querySelector("#login_message");

//pages
const home = document.querySelector("#home");
const calendar = document.querySelector("#calendar");
const userlist = document.querySelector("#userlist");
const accreqlist = document.querySelector("#accreqlist");

const button_userlist = document.querySelector("#button_userlist");
const button_accreqlist = document.querySelector("#button_accreqlist");
const userdetailbtn = document.querySelector("#userdetailbtn");
const nameUser = document.querySelector("#nameUser");

//current user object
let user = {
  userid: null,
  authorization: "",
  name: "",
};
//current reservation object
let reservation ={
  userIdReservation: null,
};

function getUserObject(userid) {
  $.ajax({
    type: "GET",
    url: "/api/user/" + userid,
    success: (response) => {
      user.authorization = response.authorization;
      user.name = response.userName;
      updateNameUser();
      if (user.authorization.localeCompare("administrator") == 0) {
        button_userlist.hidden = false;
        button_accreqlist.hidden = false;
      }else{
        button_userlist.hidden = true;
        button_accreqlist.hidden = true;
      }
    },
    dataType: "json",
    contentType: "application/json",
  });
}

function updateNameUser(){
  nameUser.innerHTML = "Hallo, " + user.name;
}
//Wenn Login erfolgreich wird die Kalendersicht angezeigen
function showCalendar(response) {
  let message = "";
  let isOk = true;
  if (response == -1) {
    message = "Login inkorrekt";
    isOk = false;
  } else {
    message = "Login korrekt";
    user.userid = response;
    getUserObject(user.userid)
    home.hidden = true;
    calendar.hidden = false;
  }
  setFormMessage(loginForm, message, isOk);
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
  setTimeout(() => {
    messageElement.textContent = "";
  }, 3000); //nach 3 sek message löschen
}

//CALENDAR SHIZZZLE

userdetailbtn.addEventListener("click", () => {
  calendar.hidden = true;
  userdetail.hidden = false;
});

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

// Booking - START
var today = new Date();
var year = new Date().getFullYear(); //aktuelles Jahr
var monday = new Date();
monday.setDate(today.getDate() - today.getDay() + 1);
var weeksAhead = 0;
var current_slot_date; //datum & startzeit der reservation
var reservation_id = 0;
var player_names = "";
var idArray = [];


const booking_table = document.querySelector("#booking_table");
const booking_form = document.querySelector("#booking_form");
const confirmBookingform = document.querySelector("#confirmBookingform");
const backToBookingTblBtn = document.querySelector("#backToBookingTblBtn");
const currentReservationLbl = document.querySelector("#currentReservationLbl");
const currentReservationLbl2 = document.querySelector("#currentReservationLbl2");
const reservationdetail = document.querySelector("#reservationdetail");
const returnbtndeleteRes= document.querySelector("#returnbtndeleteRes");
const btndeleteRes= document.querySelector("#btndeleteRes");
const resDetailform = document.querySelector("#resDetailform");

var days = [
  "Montag",
  "Dienstag",
  "Mittwoch",
  "Donnerstag",
  "Freitag",
  "Samstag",
  "Sonntag",
];
var months = [
  "Januar",
  "Februar",
  "März",
  "April",
  "Mai",
  "Juni",
  "Juli",
  "August",
  "September",
  "Oktober",
  "November",
  "Dezember",
];
var openDays = [
  "Montag",
  "Dienstag",
  "Mittwoch",
  "Donnerstag",
  "Freitag",
  "Samstag",
  "Sonntag",
];
var openTime = 8;
var closeTime = 18;

function getMonday() {
  return new Date(monday.getTime());
}

function load() {
  if (typeof window.Storage !== "undefined")
    window.checked = window.localStorage.getItem("checked");
  if (window.checked === null) window.checked = [];
  else window.checked = JSON.parse(window.checked);
}

function save() {
  if (typeof window.Storage !== "undefined")
    window.localStorage.setItem("checked", JSON.stringify(window.checked));
}

function updateHeader() {
  $("#booking-table").empty();
  $("#booking-table").prepend("<tr id='header'></tr>");
  $("#header").append("<td class='time'>Time</td>");

  //Add all days to headers
  for (var i = 0; i < days.length; i++) {
    var date = getMonday();
    date.setDate(date.getDate() + i);
    $("#header").append(
      "<td>" +
        days[i] +
        " " +
        date.getDate() +
        ". " +
        months[date.getMonth()] +
        " " +
        year +
        "</td>"
    );
  }
}
function convertIdtoInteger(buttonid) {
  //aus btnid Reservationsid gemacht
  let str = buttonid;
  idArray = str.split(";");
  let idAsString = "";
  for (let i = 0; i < idArray.length; i++) {
    idAsString += idArray[i];
  }
  return parseInt(idAsString);
}
var btnArray=[];
var resbtnid="";
var num = 0;
function timeSlotSelected(button) {
 
  //document.getElementById(button.id).disabled = true;
  resbtnid=button.id;
  reservation_id = convertIdtoInteger(button.id); //aus String der btn ID ein int gemacht für Reservationsid
  if(button.value.localeCompare("reservieren")==0){
  booking_table.hidden = true;
  booking_form.hidden = false;

  var endTime = parseInt(idArray[2]) + 2;
  //js date format (vollständiges datum und startzeit integriert) -> (year,month,day,hours) --> datum + startzeit in einem objekt
  current_slot_date = new Date(year, idArray[1], idArray[0], idArray[2]);
  let dayName = days[current_slot_date.getDay() - 1];
  let monthName = months[idArray[1]];
  currentReservationLbl.innerHTML =
    "Datum und Uhrzeit der Reservation: " +
    dayName +
    ", " +
    idArray[0] +
    ". " +
    monthName +
    " " +
    year +
    " | " +
    idArray[2] +
    ":00" +
    " bis " +
    endTime +
    ":00 |";
}
  if(button.value.localeCompare("bearbeiten/löschen")== 0){
    booking_table.hidden = true;
    reservationdetail.hidden = false;
    
    var endTime = parseInt(idArray[2]) + 2;
  //js date format (vollständiges datum und startzeit integriert) -> (year,month,day,hours) --> datum + startzeit in einem objekt
  current_slot_date = new Date(year, idArray[1], idArray[0], idArray[2]);
  let dayName = days[current_slot_date.getDay() - 1];
  let monthName = months[idArray[1]];
  currentReservationLbl2.innerHTML =
    "Datum und Uhrzeit der Reservation: " +
    dayName +
    ", " +
    idArray[0] +
    ". " +
    monthName +
    " " +
    year +
    " | " +
    idArray[2] +
    ":00" +
    " bis " +
    endTime +
    ":00 |";
}
}
//Reservation löschen
btndeleteRes.addEventListener("click", ()=>{
  $.ajax({
    type: "GET",
    url: "/api/reservation/" + reservation_id,
    success: (response) => {
      reservation.userIdReservation = response.userIdReservation;
      //console.log(reservation);
      if(user.authorization.localeCompare("administrator")==0){
        deleteReservation(reservation_id);
      }else if(reservation.userIdReservation == user.userid){
        deleteReservation(reservation_id);
      }else{
        window.alert("Sie sind nicht berechtigt fremde reservationen zu bearbeiten/löschen!")
      }
    },
    dataType: "json",
    contentType: "application/json",
  });
});
//ajx zum löschen btn wieder zu reservieren nennen
function deleteReservation(reservation_id){
  $.ajax({
    type: "DELETE",
    url: "/api/reservation/" + reservation_id,
    success: (response) => {
      if(response){
        console.log("löschen war erfolgreich");
        
      }else{
        console.log("löschen war nicht erfolgreich");
      }
     document.getElementById(resbtnid).value = "reservieren";
     fetchReservations();
    },
    dataType: "json",
    contentType: "application/json",
  });
}
//zurück btn
returnbtndeleteRes.addEventListener("click", ()=>{
  booking_table.hidden = false;
  reservationdetail.hidden = true;
  reservation.userIdReservation = null;
  playernames_detail.value ="";
})
const playernames_detail = document.querySelector("#playernames_detail");
//reservation bearbeiten
resDetailform.addEventListener("submit", (e)=>{
  e.preventDefault();
  $.ajax({
    type: "GET",
    url: "/api/reservation/" + reservation_id,
    success: (response) => {
      reservation.userIdReservation = response.userIdReservation;
     // console.log(reservation);
      if(user.authorization.localeCompare("administrator")==0){
        modifyReservation(reservation_id);
      }else if(reservation.userIdReservation == user.userid){
        modifyReservation(reservation_id);
      }else{
        window.alert("Sie sind nicht berechtigt fremde reservationen zu bearbeiten/löschen!")
      }
    },
    dataType: "json",
    contentType: "application/json",
  });
});
//ändern der reservation
function modifyReservation(reservation_id){
  names = playernames_detail.value;
  console.log(names);
  //ajax zum ändern
  $.ajax({
    type: "PUT",
    url: "api/reservation/modify/" + reservation_id,
    data: JSON.stringify({
      playerNames: names,
    }),
    success: function (response) {
      if(response){
        console.log("erfolgreicht geändert");
      }else{
        console.log("nicht geändert");
      }},
      //let message = "Userdatenänderung erfolgreich";
      //if (!response) {
      //  message = "Userdatenänderung fehlgeschlagen";
     // }
     // setFormMessage(userdetailform, message, response);
     // detailMessage.hidden = false;
    //},
    dataType: "json",
    contentType: "application/json",
  });

}
const player_namesfield =document.querySelector("#playernames");
//createReservation-Prozess
confirmBookingform.addEventListener("submit", (e) => {
  e.preventDefault();
  player_names = document.querySelector("#playernames").value;
  if (player_names == null) {
    alert("Bitte Spieler einschreiben");
    return;
  }
  $.ajax({
    type: "POST",
    url: "/api/reservation",
    data: JSON.stringify({
      reservationId: reservation_id,
      bookingDate: current_slot_date,
      playerNames: player_names,
      userIdReservation: user.userid,
      btnId: resbtnid,
    }),
    success: createReservationSuccess,
    dataType: "json",
    contentType: "application/json",
  });
});

function createReservationSuccess(response) {
  if(response){
  console.log("message erfolgreich");
  document.getElementById(resbtnid).value = "bearbeiten/reservieren";
  fetchReservations();
  }else{
   console.log("message nicht erfolgreich");
  }

}


function fetchReservations(){
  $.getJSON("/api/reservations").done(handleReservations);

}
function handleReservations(reservations){

  for(let reservation of reservations){
    for ( let buttonid of btnArray){
      if(reservation.btnId.localeCompare(buttonid) == 0){
        document.getElementById(buttonid).value = "bearbeiten/löschen";
      } 
    }
  }

}

backToBookingTblBtn.addEventListener("click", () => {
  booking_table.hidden = false;
  booking_form.hidden = true;
  player_namesfield.value = "";
});
function addRows() {
  //Create a new row for each time
  for (var i = openTime; i < closeTime; i += 2) {
    $("#booking-table").append("<tr id='slot-" + i + "'></tr>");
    $("#slot-" + i).append(
      "<td class='time'>" + i + ":00 - " + (i + 2) + ":00" + "</td>"
    );

    //Create a new cell for each time & day pair
    for (var y = 0; y < 7; y++) {
      //Don't allow dates in the past
      if ((new Date().getDay() - 1 > y && weeksAhead === 0) || 0 > weeksAhead) {
        $("#slot-" + i).append("<td>-</td>");
        continue;
      }

      var hour = i;

      var day = new Date();
      day.setDate(monday.getDate() + y);

      var identifier = day.getDate() + ";" + day.getMonth() + ";" + hour;
      var open = openDays.indexOf(days[y]) != -1;
      $("#slot-" + i).append(
        open
          ? "<td><input type='button' class= 'btnCalendar' value='reservieren' onClick='return timeSlotSelected(this)' id='" +
              identifier +
              "'></input><label for='" +
              identifier +
              "'></div></td>"
          : "<td>-</td>"
      );
      if (window.checked.indexOf(identifier) != -1)
        document.getElementById(identifier).checked = true;

        //buttons zu button array hinzufügen
        let button = document.getElementById(identifier);
        btnArray[num] = button.id;
        num += 1;
    }
   
  }
  fetchReservations()
}
function clearBtnArray(){
  btnArray=[];
  num=0;
}

function incrementWeek() {
  clearBtnArray();
  monday.setDate(monday.getDate() + 7);
  weeksAhead++;
  updateHeader();
  addRows();
  fetchReservations()
}

function decrementWeek() {
  clearBtnArray();
  monday.setDate(monday.getDate() - 7);
  weeksAhead--;
  updateHeader();
  addRows();
  fetchReservations()
}

function generate() {
  load();
  updateHeader();
  addRows();
}

document.getElementById("next").addEventListener("click", incrementWeek);
document.getElementById("previous").addEventListener("click", decrementWeek);

generate();

// Booking - END

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
const logoutbtn = document.querySelector("#logoutbtn");

logoutbtn.addEventListener("click", () => {
  (user.userid = null), (user.authorization = ""),(user.name=""), (calendar.hidden = true)
  ,(reservationdetail.hidden=true);
  home.hidden = false;
  //console.log(user);
});

//Userdetail ändern shizzle
const userdetailreturnbtn = document.querySelector("#userdetailreturnbtn");
const userdetailform = document.querySelector("#userdetailform");
//const detailMessage = document.querySelector("#detail_message");

userdetailreturnbtn.addEventListener("click", () => {
  calendar.hidden = false;
  userdetail.hidden = true;
  //detailMessage.hidden = true;
});

userdetailform.addEventListener("submit", (e) => {
  e.preventDefault();
  const detail_name = document.querySelector("#detail_name").value;
  const detail_email = document.querySelector("#detail_email").value;
  const detail_mobile = document.querySelector("#detail_mobile").value;
  const detail_password = document.querySelector("#detail_password").value;
  const detail_password_repeat = document.querySelector(
    "#detail_password_repeat"
  ).value;

  if (
    matchPassword(detail_password, detail_password_repeat) &&
    verifyPassword(detail_password)
  ) {
    // Ajax Prozess --> Rest-Service Aufruf
    $.ajax({
      type: "PUT",
      url: "api/user/modify/" + user.userid, //parameter anschauen
      data: JSON.stringify({
        userName: detail_name,
        userEmail: detail_email,
        userMobile: detail_mobile,
        userPassword: detail_password,
      }),
      success: function (response) {
        let message = "Userdatenänderung erfolgreich";
        if (!response) {
          message = "Userdatenänderung fehlgeschlagen";
        }
        setFormMessage(userdetailform, message, response);
        detailMessage.hidden = false;
      },
      dataType: "json",
      contentType: "application/json",
    });
  }
});
