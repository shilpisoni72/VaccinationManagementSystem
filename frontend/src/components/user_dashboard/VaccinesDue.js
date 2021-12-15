import { Button } from '@mui/material';
import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import addDays from 'date-fns/addDays';
import Cookies from 'universal-cookie';
import axios from 'axios';
import './VaccinesDue.css';


const { API_URL } = require('../utils/Constants').default;
class VaccinesDue extends Component {
    constructor(props) {
        super(props)
        this.state = {
            vaccinesDue: [
                {vaccinatioName:'Pfizer', numberOfShotDue:2, dueDate:'2021-12-24'},
                {vaccinatioName:'Flu', numberOfShotDue:1, dueDate:'2021-12-24'},
                {vaccinatioName:'Moderna', numberOfShotDue:2, dueDate:'2021-12-24'},
            ],
            appointments: [
                {vaccinatioName: "Pfizer", clinicName:"Sunnyvale CVS", appointmentDateTime:"2021-12-20T12:15:00", checkIn: false},
                {vaccinatioName: "Flu", clinicName:"Sunnyvale CVS", appointmentDateTime:"2021-12-20T12:15:00", checkIn: false},
            ]
        }
    }

    async componentDidMount() {
        const cookies = new Cookies();
        let userId = cookies.get('userId');
        
        const payload = {
            userId: userId,
            date: this.props.chosenDate 
        }

        try {
            const response = await axios.post(`${API_URL}/uservaccination/due`, payload);

            let tempAppointments = [];
            for(let vaccinationsDue of response.vaccinationsDue){
                if(vaccinationsDue.appointment){
                    vaccinationsDue.appointment["clinicName"] = vaccinationsDue.clinicName;
                    vaccinationsDue.appointment["vaccinationName"] = vaccinationsDue.vaccinatioName;
                    tempAppointments.push(vaccinationsDue.appointment);
                }
            }

            this.setState({
                vaccinesDue: response.vaccinationsDue,
                appointments: tempAppointments,
            });
        } catch (error) {
            console.log(error);
        }
    }

    render() {
        return (
            <div className='d-flex flex-column'>
                <div className='d-flex justify-content-evenly time-section'>
                    <h5>Current Date: {this.props.currentDate.toLocaleString()}</h5>
                    <h5>Chosen Date: {this.props.chosenDate.toLocaleString()}</h5>
                    <DatePicker 
                            selected={this.props.chosenDate} 
                            onChange={(date) => this.props.chosenDateHandler(date)} 
                            showTimeSelect
                            timeFormat="HH:mm"
                            timeIntervals={15}
                            timeCaption="time"
                            dateFormat="MMMM d, yyyy h:mm aa"
                            minDate={this.props.currentDate}
                            maxDate={addDays(this.props.currentDate, 365)}
                    />
                    <Button variant="outlined" onClick={() => this.props.chosenDateHandler(this.props.currentDate)}>
                        Revert to Current Date
                    </Button>
                </div>
                <div className="d-flex flex-fill flex-column due-section">
                    <h1>Vaccines Due</h1>
                    <div className="d-flex flex-column due-list">
                        {
                                this.state.vaccinesDue.map((vaccine, index) => {
                                    return (
                                        <div className='d-flex due-vaccine align-items-center'>
                                            <h5>{vaccine.vaccinatioName}</h5>
                                            <p>Shots Due: {vaccine.numberOfShotDue}</p>
                                            <p>Due Date: {vaccine.dueDate.toString()}</p>
                                            <p>Status: {vaccine.status}</p>
                                        </div>
                                    )
                                })
                            }
                    </div>
                </div>
                <div className="d-flex flex-fill flex-column">
                    <h1>Upcoming Appointments</h1>
                    <div className="d-flex flex-column due-list">
                        {
                            this.state.appointments.map((appointment, index) => {
                                return (
                                    <div className='d-flex due-vaccine align-items-center'>
                                        <h5>{appointment.vaccinatioName}</h5>
                                        <p>Clinic: {appointment.clinicName}</p>
                                        <p>Date: {appointment.appointmentDateTime.toString()}</p>
                                        {/* IF date is within 24hrs of chosen date && user hasn't checked in yet, show check in button ELSE hide button */}
                                        {
                                            !appointment.checkIn && this.props.chosenDate < new Date(appointment.appointmentDateTime) && (new Date(appointment.appointmentDateTime) - this.props.chosenDate) / 36e5 <= 24  ? 
                                            <Button variant="outlined" onClick={this.handleCheckin}>
                                                Check In
                                            </Button>
                                            : null 
                                            
                                        }
                                    </div>
                                )
                            })
                        }
                    </div>
                </div>
            </div>
        )
    }
}

export default VaccinesDue;