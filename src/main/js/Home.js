import React, { Component } from "react";
import ReactDOM from "react-dom";
import UserStore from "./stores/UserStore";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";

class Home extends React.Component {
  render() {
    return (
      <div className="home">
        <LoginForm />
        <RegisterForm />
      </div>
    );
  }
}

export default Home;
