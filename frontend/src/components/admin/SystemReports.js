import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import Button from '@mui/material/Button';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import addDays from 'date-fns/addDays';
import './SystemReports.css';

class SystemReports extends Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: this.props.chosenDate,
            endDate: this.props.chosenDate,
            clinicSelected:{},
            allClinics: [
                {name: "Sunnyvale CVS"},
                {name: "Evergreen CVS"},
                {name: "Palo Alto CVS"},
            ],
            noShowRate: 0,
            noShowAppointments: [
                {vaccine: "Pfizer", firstName:"Spencer", lastName:"Siu", date:"12/20/21"},
                {vaccine: "Flu", firstName:"Joe", lastName:"Biden", date:"12/20/21"},
            ],
            totalAppointments: [
                {vaccine: "Pfizer", firstName:"Spencer", lastName:"Siu", date:"12/20/21"},
                {vaccine: "Flu", firstName:"Joe", lastName:"Biden", date:"12/20/21"},
            ]
        }
    }

    handleClinicChange = (e) => {
        this.setState({
            clinicSelected: e.target.value
        });
    }

    handleStartChange = (date) => {
        this.setState({
            startDate: date
        });
    }

    handleEndChange = (date) => {
        this.setState({
            endDate: date
        });
    }

    render() {
        return (
            <div className='d-flex flex-column justify-content-around system-reports'>
                <h1>System Reports</h1>
                <div className="d-flex flex-column">
                    <FormControl fullWidth>
                        <InputLabel id="sys-clinic-label">Clinic</InputLabel>
                        <Select
                            labelId="sys-clinic-label"
                            id="sys-clinic-select"
                            value={this.state.clinicSelected}
                            label="Clinic"
                            onChange={this.handleClinicChange}
                        >
                        {
                            this.state.allClinics.map((clinic, index) => {
                                return (
                                    <MenuItem key={index} value={clinic.name}>{clinic.name}</MenuItem>
                                )
                            })
                        }
                        </Select>
                    </FormControl>
                    <label>
                        Start Date:
                        <DatePicker 
                            selected={this.state.startDate} 
                            onChange={this.handleStartChange} 
                            minDate={addDays(this.props.chosenDate, -365)}
                            maxDate={this.props.chosenDate}
                        />
                    </label>
                    <label>
                        End Date:
                        <DatePicker 
                            selected={this.state.endDate} 
                            onChange={this.handleEndChange} 
                            minDate={addDays(this.props.chosenDate, -365)}
                            maxDate={this.props.chosenDate}
                        />
                    </label>
                    <div>
                        <Button variant="contained">
                            Get Statistics
                        </Button>
                    </div>
                </div>
                <div className='d-flex flex-column'>
                    <h4>No Show Rate</h4>
                    <div>{this.state.noShowRate}</div>
                </div>
                <div className='d-flex flex-column'>
                    <h4>No Show Appointments</h4>
                    {
                        this.state.noShowAppointments.map((apt, index) => {
                            return (
                                <div className="d-flex align-items-center justify-content-evenly sys-no-show-block" key={index}>
                                    <h4>{apt.vaccine}</h4>
                                    <div>Patient: {apt.firstName} {apt.lastName}</div>
                                    <div>Date: {apt.date}</div>
                                </div>
                            )
                        })
                    }
                </div>
                <div className='d-flex flex-column'>
                    <h4>Total Appointments</h4>
                    {
                        this.state.totalAppointments.map((apt, index) => {
                            return (
                                <div className="d-flex align-items-center justify-content-evenly sys-total-block" key={index}>
                                    <h4>{apt.vaccine}</h4>
                                    <div>Patient: {apt.firstName} {apt.lastName}</div>
                                    <div>Date: {apt.date}</div>
                                </div>
                            )
                        })
                    }
                </div>
            </div>
        )
    }
}

export default SystemReports; 
