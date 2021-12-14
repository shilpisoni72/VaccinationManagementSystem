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
                {name:'Pfizer', numShots:2, dueDate:'2021-12-24'},
                {name:'Flu', numShots:1, dueDate:'2021-12-24'},
                {name:'Moderna', numShots:2, dueDate:'2021-12-24'},
            ],
            appointments: [
                {vaccine: "Pfizer", clinic:"Sunnyvale CVS", date:"2021-12-20T12:15:00", checkedIn: false},
                {vaccine: "Flu", clinic:"Sunnyvale CVS", date:"2021-12-20T12:15:00", checkedIn: false},
            ]
        }
    }

    async componentDidMount() {
        const cookies = new Cookies();
        let userId = cookies.get('userId');
        
        try {
            const response = await axios.get(`${API_URL}/dashboard&userId=${userId}`);
            this.setState({
                vaccinesDue: response.vaccines,
                appointments: response.appointments,
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
                            maxDate={addDays(this.props.chosenDate, 365)}
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
                                            <h5>{vaccine.name}</h5>
                                            <p>Shots Due: {vaccine.numShots}</p>
                                            <p>Due Date: {vaccine.dueDate}</p>
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
                                        <h5>{appointment.vaccine}</h5>
                                        <p>Clinic: {appointment.clinic}</p>
                                        <p>Date: {appointment.date}</p>
                                        {/* IF date is within 24hrs of chosen date && user hasn't checked in yet, show check in button ELSE hide button */}
                                        {
                                            !appointment.checkedIn && this.props.chosenDate < new Date(appointment.date) && (new Date(appointment.date) - this.props.chosenDate) / 36e5 <= 24  ? 
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