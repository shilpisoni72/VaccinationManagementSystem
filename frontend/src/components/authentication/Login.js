import React, { Component } from 'react';
import { Redirect } from 'react-router';
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

            <form className="d-flex flex-column justify-content-center login-form" onSubmit={this.handleLoginSubmit}>
                <label htmlFor="loginEmail" className="d-flex">
                    Email
                    <input required type="email" id="loginEmail" name="login_email" data-testid="loginEmail" onChange={this.handleEmailChange} value={email} className='flex-fill'/>
                </label>

                <label htmlFor="loginPassword" className="d-flex">
                    Password
                    <input required type="password" id="loginPassword" name="login_password" onChange={this.handlePasswordChange} value={password} className='flex-fill' />
                </label>

                <button type="submit" className="login-button align-self-center" >
                    <span>Log in</span>
                </button>
            </form>
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
