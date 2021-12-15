import React, { Component } from 'react';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import './AdminManagement.css';

const { API_URL } = require('../utils/Constants').default;
class AdminManagement extends Component {
    constructor(props) {
        super(props);
        this.state = {
            clinicName: '',
            clinicAddress: '',
            opening: 8,
            closing: 17,
            physicians: 0,

            disease: '',
            description: '',
            currentDiseases: [
                {name:"COVID-19"},
                {name:"Measles"},
                {name:"Swine Flu"},
                {name:"Small Pox"},
            ],

            diseasesSelected: [

            ],
            vaccine: '',
            manufacturer: '',
            numberShots: 0,
            shotInterval: 0,
            duration: 0,
        };
    }

    // CLINIC FORM
    handleClinicChange = (e) => {
        this.setState({
            clinicName: e.target.value
        });
    }

    handleAddressChange = (e) => {
        this.setState({
            clinicAddress: e.target.value
        });
    }

    handleOpeningChange = (e) => {
        this.setState({
            opening: e.target.value
        });
    }

    handleClosingChange = (e) => {
        this.setState({
            closing: e.target.value
        });
    }

    handlePhysiciansChange = (e) => {
        this.setState({
            physicians: e.target.value
        });
    }

    createClinic = async () => {    
        const payload = {
            name: this.state.clinicName,
            address: this.state.clinicAddress,
            opening: this.state.opening,
            closing: this.state.closing,
            physicians: this.state.physicians,
        }
        try {
            const response = await axios.post(`${API_URL}/clinic`, payload);
        } catch (error) {
            console.log(error);
        }
    }

    // DISEASE FORM
    handleDiseaseChange = (e) => {
        this.setState({
            disease: e.target.value
        });
    }

    handleDescriptionChange = (e) => {
        this.setState({
            description: e.target.value
        });
    }

    createDisease = async () => {    
        const payload = {
            name: this.state.disease,
            description: this.state.description
        }
        try {
            const response = await axios.post(`${API_URL}/disease`, payload);
            this.setState({
                currentDiseases: response.diseases,
            });
        } catch (error) {
            console.log(error);
        }
    }

    // VACCINE FORM
    handleVaccineChange = (e) => {
        this.setState({
            vaccine: e.target.value
        });
    }

    handleManufacturerChange = (e) => {
        this.setState({
            manufacturer: e.target.value
        });
    }

    handleNumberShotsChange = (e) => {
        this.setState({
            numberShots: e.target.value
        });
    }

    handleShotIntervalChange = (e) => {
        this.setState({
            shotInterval: e.target.value
        });
    }

    handleDurationChange = (e) => {
        this.setState({
            duration: e.target.value
        });
    }

    handleVaccineDiseaseChange = (e) => {
        this.setState({
            diseasesSelected: e.target.value
        });
    }

    createVaccine = async () => {    
        const payload = {
            name: this.state.vaccine,
            manufacturer: this.state.manufacturer,
            disease: this.state.diseasesSelected,
            numShots: this.state.numberShots,
            shotInterval: this.state.shotInterval,
            duration: this.state.duration
        }
        try {
            const response = await axios.post(`${API_URL}/vaccine`, payload);
        } catch (error) {
            console.log(error);
        }
    }


    render() {
        return (
            <div className='d-flex flex-column justify-content-around admin'>
                <h1>Admin Management</h1>

                {/* Create clinic */}
                <div className="d-flex flex-column">
                    <h3>Create Clinic</h3>
                    <div className='d-flex'>
                        <TextField id="clinicname" label="Clinic Name" variant="outlined" required onChange={this.handleClinicChange}/>
                        <TextField id="clinicaddress" label="Address" variant="outlined" required onChange={this.handleAddressChange}/>
                        <TextField id="physicians" label="Number of Physicians" variant="outlined" required onChange={this.handlePhysiciansChange}/>
                    </div>
                    <div className='d-flex'>
                        <TextField id="opening" label="Opening Time" variant="outlined" required onChange={this.handleOpeningChange}/>
                        <TextField id="closing" label="Closing Time" variant="outlined" required onChange={this.handleClosingChange}/>
                    </div>
                    <div>
                        <Button variant="contained">
                            Create Clinic
                        </Button>
                    </div>
                </div>

                {/* Create disease */}
                <div className="d-flex flex-column">
                    <h3>Create Disease</h3>
                    <div className='d-flex'>
                        <TextField id="diseasename" label="Disease Name" variant="outlined" required onChange={this.handleDiseaseChange}/>
                        <TextField id="diseasedescription" label="Description" multiline rows={3} variant="outlined" onChange={this.handleDescriptionChange}/>
                    </div>
                    <div>
                        <Button variant="contained">
                            Create Disease
                        </Button>
                    </div>
                </div>

                {/* Create vaccine */}
                <div className="d-flex flex-column">
                    <h3>Create Vaccine</h3>
                    <div className='d-flex flex-column'>
                        <div className='d-flex'>
                            <TextField id="vaccinename" label="Vaccine Name" variant="outlined" required onChange={this.handleVaccineChange}/>
                            <TextField id="manufacturer" label="Manufacturer" variant="outlined" required inputProps={{ minLength:3 }} onChange={this.handleManufacturerChange}/>
                            <FormControl fullWidth>
                                <InputLabel id="disease-label">Diseases</InputLabel>
                                <Select
                                    labelId="disease-label"
                                    id="disease-select"
                                    multiple
                                    value={this.state.diseasesSelected}
                                    label="Diseases"
                                    onChange={this.handleVaccineDiseaseChange}
                                >
                                {
                                    this.state.currentDiseases.map((disease, index) => {
                                        return (
                                            <MenuItem key={index} value={disease.name}>{disease.name}</MenuItem>
                                        )
                                    })
                                }
                                </Select>
                            </FormControl>  
                        </div>
                        <div className='d-flex'>
                            <TextField id="numbershots" label="Number of Shots" variant="outlined" required onChange={this.handleNumberShotsChange}/>
                            <TextField id="shotinterval" label="Shot Interval" variant="outlined" onChange={this.handleShotIntervalChange}/>
                            <TextField id="duration" label="Duration" variant="outlined" required onChange={this.handleDurationChange}/>
                        </div>
                    </div>
                    <div>
                        <Button variant="contained">
                            Create Vaccine
                        </Button>
                    </div>
                </div>
            </div>
        )
    }
}

export default AdminManagement
