import React, { Component } from "react";
import { observer } from "mobx-react";
import UserStore from "./stores/UserStore";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import SubmitButton from "./components/SubmitButton";
import ReactDOM from "react-dom";

class App extends React.Component {
  /*async componentDidMount() {
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
      <div>
        <LoginForm />
        <RegisterForm />
      </div>
    );
  }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));
