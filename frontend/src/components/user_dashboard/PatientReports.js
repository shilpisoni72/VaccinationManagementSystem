import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import Button from '@mui/material/Button';
import addDays from 'date-fns/addDays';
import Cookies from 'universal-cookie';
import axios from 'axios';
import './PatientReports.css';

const { API_URL } = require('../utils/Constants').default;
class PatientReports extends Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: this.props.chosenDate,
            endDate: this.props.chosenDate,
            noShowRate: 0,
            noShowAppointments: [
                {vaccine: "Pfizer", clinic:"Sunnyvale CVS", date:"12/20/21"},
                {vaccine: "Flu", clinic:"Sunnyvale CVS", date:"12/20/21"},
            ],
            totalAppointments: [
                {vaccine: "Pfizer", clinic:"Sunnyvale CVS", date:"12/20/21"},
                {vaccine: "Flu", clinic:"Sunnyvale CVS", date:"12/20/21"},
            ]
        }
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

    handleGetStats = async () => {
        const cookies = new Cookies();
        let userId = cookies.get('userId');
    
        try {
            const response = await axios.get(`${API_URL}/history&userId=${userId}&start=${this.state.startDate}&end=${this.state.endDate}`);
            this.setState({
                totalAppointments: response.totalAppointments,
                noShowAppointments: response.noShowAppointments,
                noShowRate : response.noShowAppointments.length / response.totalAppointments.length,
            });
        } catch (error) {
            console.log(error);
        }
    }


    render() {
        return (
            <div className='d-flex flex-column justify-content-around patient-reports'>
                <h1>Patient Reports</h1>
                <div className="d-flex flex-column">
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
                        <Button variant="contained" onClick={this.handleGetStats}>
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
                                <div className="d-flex align-items-center justify-content-evenly no-show-block" key={index}>
                                    <h4>{apt.vaccine}</h4>
                                    <div>Clinic: {apt.clinic}</div>
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
                                <div className="d-flex align-items-center justify-content-evenly total-block" key={index}>
                                    <h4>{apt.vaccine}</h4>
                                    <div>Clinic: {apt.clinic}</div>
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

export default PatientReports; 
