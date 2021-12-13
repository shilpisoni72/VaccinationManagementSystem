import React, { Component } from 'react';
import { Redirect } from 'react-router';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import './Login.css';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
    };
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.handleLoginSubmit = this.handleLoginSubmit.bind(this);
  }

  handleEmailChange = (e) => {
    this.setState({
      email: e.target.value,
    });
  }

  handlePasswordChange = (e) => {
    this.setState({
      password: e.target.value,
    });
  }

  handleLoginSubmit = (e) => {
    e.preventDefault();

    const { email, password } = this.state;
    const user = {
      email,
      password,
    };

    const { loginWrapper } = this.props;
    loginWrapper(user);
  }

  render() {
    const {
      email, password,
    } = this.state;
    const { user } = this.props;

    const loginDiv = (
        <div className="d-flex flex-column justify-content-center align-items-center login">
            <h1>Log in</h1>

            <TextField id="loginemail" label="Email" variant="outlined" required onChange={this.handleEmailChange}/>
            <TextField id="loginpassword" label="Password" variant="outlined" type="password" required onChange={this.handlePasswordChange}/>
            <Button variant="contained">
              Log in
            </Button>
        </div>
    );

    return (
      <div>
        {loginDiv}
      </div>
    );
  }
}

export default Login;
