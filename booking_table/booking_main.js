// Booking - START
var today = new Date();
var monday = new Date();
monday.setDate(today.getDate() - today.getDay() + 1);
var weeksAhead = 0;
var current_slot = "";
var reservation_id = 0;
var idArray = [];

const booking_table = document.querySelector("#booking_table");
const booking_form = document.querySelector("#booking_form");
const reservierreturnbtn = document.querySelector("#reservierreturnbtn");
const date = document.querySelector("#date");

var days = [
  "Monday",
  "Tuesday",
  "Wednesday",
  "Thursday",
  "Friday",
  "Saturday",
  "Sunday",
];
var months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
var openDays = [
  "Monday",
  "Tuesday",
  "Wednesday",
  "Thursday",
  "Friday",
  "Saturday",
  "Sunday",
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
        date.getFullYear() +
        "</td>"
    );
  }
}
function splitForInt(buttonid) {
  //aus btnid Reservationsid gemacht
  var str = buttonid;
  idArray = str.split(";");
  let idAsString = "";
  for (let index = 0; index < idArray.length; index++) {
    idAsString += idArray[index];
  }
  reservation_id = parseInt(idAsString);
}
function timeSlotSelected(button) {
  splitForInt(button.id); //aus String der btn ID ein int gemacht für Reservationsid
  booking_table.hidden = true;
  booking_form.hidden = false;
  var month = parseInt(idArray[1]) + 1;
  var endTime = parseInt(idArray[2]) + 2;
  date.innerHTML =
    "Datum und Uhrzeit der Reservation " +
    idArray[0] +
    "." +
    month +
    " " +
    idArray[2] +
    ":00" +
    " bis " +
    endTime +
    ":00";
}
reservierreturnbtn.addEventListener("click", () => {
  booking_table.hidden = false;
  booking_form.hidden = true;
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
          ? "<td><input type='button' value='button' onClick='return timeSlotSelected(this)' id='" +
              identifier +
              "'></input><label for='" +
              identifier +
              "'></div></td>"
          : "<td>-</td>"
      );
      if (window.checked.indexOf(identifier) != -1)
        document.getElementById(identifier).checked = true;
    }
  }
}

function incrementWeek() {
  monday.setDate(monday.getDate() + 7);
  weeksAhead++;
  updateHeader();
  addRows();
}

function decrementWeek() {
  monday.setDate(monday.getDate() - 7);
  weeksAhead--;
  updateHeader();
  addRows();
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
