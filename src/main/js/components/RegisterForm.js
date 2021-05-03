import axios from "axios";
import React from "react";
import Header from "./Header";
import InputField from "./InputField";
import SubmitButton from "./SubmitButton";

class RegisterForm extends React.Component {
  constructor(props) {
    super(props); //zugriff auf alle coponent methoden
    this.state = {
      name: "",
      email: "",
      mobile: "",
      password: "",
      password_repeat: "",
      buttonDisabled: false,
      registrationErrors: "",
    };
  }

  setInputValue(property, value) {
    value = value.trim();
    if (value.length > 25) {
      return;
    }
    this.setState({
      [property]: value,
    });
  }

  resetForm() {
    this.setState({
      name: "",
      email: "",
      mobile: "",
      password: "",
      password_repeat: "",
      buttonDisabled: false,
    });
  }

  doRegistration() {
    if (!this.state.name) {
      console.log("'Name' darf nicht leer sein");
      return;
    }
    if (!this.state.email) {
      console.log("'Email' darf nicht leer sein");
      return;
    }
    if (!this.state.mobile) {
      console.log("'Mobile' darf nicht leer sein");
      return;
    }
    if (!this.state.password) {
      console.log("'Passwort' darf nicht leer sein");
      return;
    }
    if (!this.state.password_repeat) {
      console.log("'Passwort wiederholen' darf nicht leer sein");
      return;
    }
    if (this.state.password != this.state.password_repeat) {
      console.log("Passwörter stimmen nicht überein");
      return;
    }
    this.setState({
      buttonDisabled: true,
    });
    axios
      .post(
        "http://localhost:8080/api/account_request",
        {
          accountRequestName: this.state.name,
          accountRequestEmail: this.state.email,
          accountRequestMobile: this.state.mobile,
          accountRequestPassword: this.state.password,
        },
        {
          withCredentials: true, //ein Cookie wird gesetzt
        }
      )
      .then((response) => {
        if (response.data) {
          console.log("acc_req erfolgreich", response);
        } else {
          console.log("acc_req fehlgeschlagen", response);
        }
      })
      .catch((error) => {
        console.log("API-Call fehlgeschlagen", error);
      });
  }

  render() {
    return (
      <div className="registerForm">
        <Header name="Account Anfragen" />
        <InputField
          type="text"
          name="name"
          placeholder="Name"
          value={this.state.name ? this.state.name : ""}
          onChange={(value) => this.setInputValue("name", value)}
        />
        <InputField
          type="email"
          name="email"
          placeholder="Email"
          value={this.state.email ? this.state.email : ""}
          onChange={(value) => this.setInputValue("email", value)}
        />
        <InputField
          type="tel"
          name="mobile"
          placeholder="Mobile"
          value={this.state.mobile ? this.state.mobile : ""}
          onChange={(value) => this.setInputValue("mobile", value)}
        />
        <InputField
          type="password"
          name="password"
          placeholder="Passwort"
          value={this.state.password ? this.state.password : ""}
          onChange={(value) => this.setInputValue("password", value)}
        />
        <InputField
          type="password"
          name="password_repeat"
          placeholder="Passwort wiederholen"
          value={this.state.password_repeat ? this.state.password_repeat : ""}
          onChange={(value) => this.setInputValue("password_repeat", value)}
        />
        <SubmitButton
          text="Anfragen"
          disabled={this.state.buttonDisabled}
          onClick={() => this.doRegistration()}
        />
      </div>
    );
  }
}

export default RegisterForm;
