import React, { Component } from "react";
import TextField from "@mui/material/TextField";
import { Button } from "@mui/material";
import axios from "axios";
import Cookies from "universal-cookie";
import { Navigate } from "react-router-dom";
import "./Login.css";

const { API_URL } = require("../utils/Constants").default;
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      redirect: false,
      verified: true,
    };
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.handleLoginSubmit = this.handleLoginSubmit.bind(this);
  }

  handleEmailChange = (e) => {
    this.setState({
      email: e.target.value,
    });
  };

  handlePasswordChange = (e) => {
    this.setState({
      password: e.target.value,
    });
  };

  handleLoginSubmit = async (e) => {
    e.preventDefault();
    const { email, password } = this.state;
    const payload = {
      email,
      password,
    };

    try {
      let response = null;
      if (email.includes("@sjsu")) {
        response = await axios.post(`${API_URL}/adminlogin`, payload);
      } else {
        response = await axios.post(`${API_URL}/user/login`, payload);
      }

      if (response.userId != null) {
        const cookies = new Cookies();
        cookies.set("userId", response.userId);
        this.setState({
          redirect: true,
          verified: true,
        });
      } else {
        // USER isn't verified???
        this.setState({
          verified: false,
        });
      }
    } catch (error) {
      console.log(error);
    }
  };

  render() {
    const { email, password } = this.state;
    const { user } = this.props;

    const loginDiv = (
      <div className="d-flex flex-column justify-content-center align-items-center login">
        <h1>Log in</h1>
        {this.state.verified === false ? (
          <h4>You must verify your account first</h4>
        ) : null}
        <TextField
          id="loginemail"
          label="Email"
          variant="outlined"
          required
          onChange={this.handleEmailChange}
        />
        <TextField
          id="loginpassword"
          label="Password"
          variant="outlined"
          type="password"
          required
          onChange={this.handlePasswordChange}
        />
        <Button variant="contained" onClick={this.handleLoginSubmit}>
          Log in
        </Button>
      </div>
    );

    return (
      <div>
        {this.state.redirect === false ? (
          loginDiv
        ) : (
          <Navigate to="/dashboard" />
        )}
      </div>
    );
  }
}

export default Login;
