import { active_user } from "./main.js";
const id = active_user;
console.log("modul calendar_script.js: " + id);

let court1Btn = document.querySelector("#button_show_court1");
let court2Btn = document.querySelector("#button_show_court2");
let court1Tbl = document.querySelector("#table_court_1");
let court2Tbl = document.querySelector("#table_court_2");

let datePicker = document.querySelector("#datepicker");
let dateObject;

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
