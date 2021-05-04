import React, { Component } from "react";
import { observer } from "mobx-react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import UserStore from "./stores/UserStore";
import Home from "./Home";
import UserList from "./UserList";
import Calendar from "./Calendar";

class App extends React.Component {
  componentDidMount() {
    console.log("blabla");
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
    return(
      <div>
      <Home/>
    </div>
    );
  }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));
