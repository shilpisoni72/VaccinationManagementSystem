import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import Button from '@mui/material/Button';
import addDays from 'date-fns/addDays';
import './PatientReports.css';

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
