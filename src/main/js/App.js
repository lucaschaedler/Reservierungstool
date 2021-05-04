import React, { Component } from "react";
import { observer } from "mobx-react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import UserStore from "./stores/UserStore";
import Home from "./Home";
import UserList from "./UserList";
import Calendar from "./Calendar";

class App extends React.Component {
  componentDidUpdate() {
    if (UserStore.id != -1 && UserStore.id != 0) {
      console.log(UserStore.id);
      console.log("Calendar");
    }
  }
  /*
async componentDidMount() {
  await fetch(UserStore.isLoggedIn) {
    console.log("is logged in");
  }
  
    try {
      let res = await fetch("/isLoggedIn", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });

      let result = await res.json();

      if (result && result.success) {
        UserStore.loading = false;
        UserStore.isLoggedIn = true;
        UserStore.userName = result.userName;
      } else {
        UserStore.loading = false;
        UserStore.isLoggedIn = false;
      }
    } catch (e) {
      UserStore.loading = false;
      UserStore.isLoggedIn = false;
      console.log(e);
    }
    
  }
  */
  render() {
    return (
      <Router>
        <div>
          <nav>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/calendar">Calendar</Link>
              </li>
              <li>
                <Link to="/userlist">Userlist</Link>
              </li>
            </ul>
          </nav>

          {/* A <Switch> looks through its children <Route>s and
                renders the first one that matches the current URL. */}
          <Switch>
            <Route path="/calendar">
              <Calendar />
            </Route>
            <Route path="/userlist">
              <UserList />
            </Route>
            <Route path="/">
              <Home />
            </Route>
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));
