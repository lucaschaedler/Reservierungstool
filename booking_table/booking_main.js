var today = new Date();
var monday = new Date();
monday.setDate(today.getDate() - today.getDay() + 1);
var weeksAhead = 0;

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
var openDays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
var openTime = 8;
var closeTime = 18;

function getMonday() {
  return new Date(monday.getTime());
}

function getOrdinal(number) {
  var d = number % 10;
  return ~~((number % 100) / 10) === 1
    ? "th"
    : d === 1
    ? "st"
    : d === 2
    ? "nd"
    : d === 3
    ? "rd"
    : "th";
}

function load() {
  if (typeof window.Storage !== "undefined")
    window.checked = window.localStorage.getItem("checked");
  if (window.checked === null) window.checked = [];
  else window.checked = JSON.parse(window.checked);
  document.getElementById("total").innerHTML = checked.length;
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
        getOrdinal(date.getDate()) +
        " " +
        months[date.getMonth()] +
        " " +
        date.getFullYear() +
        "</td>"
    );
  }
}

function addRows() {
  //Create a new row for each time
  for (var i = openTime; i < closeTime; i++) {
    $("#booking-table").append("<tr id='slot-" + i + "'></tr>");
    $("#slot-" + i).append("<td class='time'>" + i + ":00</td>");

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

      var identifier = day.getDate() + "/" + day.getMonth() + ":" + hour;
      var open = openDays.indexOf(days[y]) != -1;
      $("#slot-" + i).append(
        open
          ? "<td><input type='checkbox' id='" +
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
  registerListeners();
}

function decrementWeek() {
  monday.setDate(monday.getDate() - 7);
  weeksAhead--;
  updateHeader();
  addRows();
  registerListeners();
}

var updateSelection = function () {
  var checkbox = document.getElementById(this.htmlFor);
  if (!checkbox.checked) checked.push(this.htmlFor);
  else checked.splice(checked.indexOf(this.htmlFor), 1);
  document.getElementById("total").innerHTML = checked.length;
  save();
};

function registerListeners() {
  var inputs = document.getElementsByTagName("label");
  for (var i = 0; i < inputs.length; i++) {
    inputs[i].onclick = updateSelection;
  }
}

function generate() {
  load();
  updateHeader();
  addRows();
  registerListeners();
}

document.getElementById("next").addEventListener("click", incrementWeek);
document.getElementById("previous").addEventListener("click", decrementWeek);
document.getElementById("clear").addEventListener("click", function () {
  window.checked = [];
  save();
  generate();
});

generate();
