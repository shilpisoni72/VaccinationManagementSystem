import React, { Component } from 'react';
import { Redirect } from 'react-router';
import DatePicker from "react-datepicker";
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import './Signup.css';
import { Button } from '@mui/material';

class Signup extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      firstName: '',
      lastName: '',
      middleName: '',
      address: '',
      dateOfBirth: new Date(),
      mrn: '',
      gender: '',
    };
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.handleSignupSubmit = this.handleSignupSubmit.bind(this);
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

  handleFirstNameChange = (e) => {
    this.setState({
      firstName: e.target.value,
    });
  }

  handleLastNameChange = (e) => {
    this.setState({
      lastName: e.target.value,
    });
  }

  handleMiddleNameChange = (e) => {
    this.setState({
      middleName: e.target.value,
    });
  }

    handleDobChange = (date) => {
        this.setState({
            dateOfBirth: date
        });
    }

    handleAddressChange = (e) => {
        this.setState({
            address: e.target.value,
        })
    }

    handleGenderChange = (e) => {
        this.setState({
            gender: e.target.value,
        })
    }

    handleMRNChange = (e) => {
        this.setState({
            mrn: e.target.value,
        })
    }

  handleSignupSubmit = (e) => {
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
      email, password, firstName, lastName, middleName, address, dateOfBirth, mrn, gender,
    } = this.state;
    const { user } = this.props;

    const signupDiv = (
        <div className='d-flex justify-content-center signup'>
            <div className="d-flex flex-column justify-content-evenly align-items-center signup-section">
                <h1>Sign up</h1>

                <TextField id="signupemail" label="Email" variant="outlined" required onChange={this.handleEmailChange}/>
                <TextField id="signuppassword" label="Password" variant="outlined" type="password" required onChange={this.handlePasswordChange}/>
                <TextField id="firstname" label="First Name" variant="outlined" required onChange={this.handleFirstNameChange}/>
                <TextField id="lastname" label="Last Name" variant="outlined" required onChange={this.handleLastNameChange}/>
                <TextField id="middlename" label="Middle Name" variant="outlined" onChange={this.handleMiddleNameChange}/>
                <TextField id="address" label="Address" variant="outlined" required onChange={this.handleAddressChange}/>
                <FormControl fullWidth>
                    <InputLabel id="gender-label">Gender</InputLabel>
                    <Select
                        required
                        labelId="gender-label"
                        id="gender"
                        value={gender}
                        label="Gender"
                        onChange={this.handleGenderChange}
                    >
                        <MenuItem value={"male"}>male</MenuItem>
                        <MenuItem value={"female"}>female</MenuItem>
                        <MenuItem value={"other"}>other</MenuItem>
                    </Select>
                </FormControl>
                <TextField id="mrn" label="Medical Record Number" variant="outlined" required onChange={this.handleMRNChange}/>
                <label>
                  Date of Birth
                  <DatePicker 
                    selected={dateOfBirth} 
                    onChange={this.handleDobChange} 
                  />  
                </label>
                <Button variant="contained">
                  Sign up
                </Button>
            </div>
        </div>
        
    );

    return (
      <div>
        {signupDiv}
      </div>
    );
  }
}

export default Signup;