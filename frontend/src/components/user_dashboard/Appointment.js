import React, { Component } from 'react'
import DatePicker from "react-datepicker";
import './Appointment.css';
import "react-datepicker/dist/react-datepicker.css";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Button from '@mui/material/Button';

class Appointment extends Component {
    constructor(props) {
        super(props);
        this.state = {
            appointmentDate: new Date(),
            clinicSelected:"",
            availableClinics: [
                {name: "Sunnyvale CVS"},
                {name: "Evergreen CVS"},
                {name: "Palo Alto CVS"},
            ],
            vaccinesSelected: [],
            availableVaccines: [
                {name: "Pfizer"},
                {name: "Moderna"},
                {name: "Tetnis"},
                {name: "Swine flu"},
                {name: "Johnson&Johnson"},
                {name: "Small pox"},
                {name: "Meningitis"}
            ],

            scheduledAppointments: [
                {vaccine: "Pfizer", clinic:"Sunnyvale CVS", date:"12/20/21"},
                {vaccine: "Flu", clinic:"Sunnyvale CVS", date:"12/20/21"},
            ],
            appointmentSelected: {},
            rescheduleDate: new Date(),
            rescheduleClinicSelected:"",
            rescheduleAvailableClinics: [
                {name: "Sunnyvale CVS"},
                {name: "Evergreen CVS"},
                {name: "Palo Alto CVS"},
            ],
        }
    }

    componentDidMount() {
        // GET call to get vaccines available to user
    }

    handleDateChange = (date) => {
        this.setState({
            appointmentDate: date
        });

        // GET call to get available clinic for this date and specific vaccine?
    }

    handleVaccineChange = (e) => {
        this.setState({
            vaccinesSelected: e.target.value
        });
    }

    handleClinicChange = (e) => {
        this.setState({
            clinicSelected: e.target.value
        });
    }

    handleAppointmentChange = (e) => {
        this.setState({
            appointmentSelected: e.target.value
        });
    }

    handleRescheduleDateChange = (date) => {
        this.setState({
            rescheduleDate: date
        });

        // GET call to get available clinic for this date and specific vaccine?
    }

    handleRescheduleClinicChange = (e) => {
        this.setState({
            rescheduleClinicSelected: e.target.value
        });
    }

    render() {
        return (
            <div className='d-flex flex-column'>
                <div className="d-flex flex-fill flex-column appt-section">
                    <h1>Make an Appointment</h1>
                    <div className="d-flex flex-column appt-form justify-content-evenly">
                        <DatePicker 
                            selected={this.state.appointmentDate} 
                            onChange={this.handleDateChange} 
                            showTimeSelect
                            timeFormat="HH:mm"
                            timeIntervals={15}
                            timeCaption="time"
                            dateFormat="MMMM d, yyyy h:mm aa"
                        />
                        <FormControl fullWidth>
                            <InputLabel id="vaccine-label">Vaccine</InputLabel>
                            <Select
                                labelId="vaccine-label"
                                id="vaccine-select"
                                multiple
                                value={this.state.vaccinesSelected}
                                label="Vaccine"
                                onChange={this.handleVaccineChange}
                            >
                            {
                                this.state.availableVaccines.map((vaccine, index) => {
                                    return (
                                        <MenuItem 
                                            key={index}
                                            value={vaccine.name}>{vaccine.name}
                                        </MenuItem>
                                    )
                                })
                            }
                            </Select>
                        </FormControl>
                        <FormControl fullWidth>
                            <InputLabel id="clinic-label">Clinic</InputLabel>
                            <Select
                                labelId="clinic-label"
                                id="clinic-select"
                                value={this.state.clinicSelected}
                                label="Clinic"
                                onChange={this.handleClinicChange}
                            >
                            {
                                this.state.availableClinics.map((clinic, index) => {
                                    return (
                                        <MenuItem key={index} value={clinic.name}>{clinic.name}</MenuItem>
                                    )
                                })
                            }
                            </Select>
                        </FormControl>
                        <Button variant="contained">
                            Schedule Appointment
                        </Button>
                    </div>
                </div>
                <div className="d-flex flex-fill flex-column appt-section">
                    <h1>Reschedule an Appointment</h1>
                    <div className="d-flex flex-column appt-form justify-content-evenly">
                        <FormControl fullWidth>
                            <InputLabel id="scheduled-label">Appointment</InputLabel>
                            <Select
                                labelId="scheduled-label"
                                id="scheduled-select"
                                value={this.state.appointmentSelected}
                                label="Scheduled"
                                onChange={this.handleAppointmentChange}
                            >
                            {
                                this.state.scheduledAppointments.map((appointment, index) => {
                                    return (
                                        <MenuItem 
                                            key={index}
                                            value={appointment}
                                        >
                                            {`${appointment.vaccine} at ${appointment.clinic} on ${appointment.date}`}
                                        </MenuItem>
                                    )
                                })
                            }
                            </Select>
                        </FormControl>
                        <DatePicker 
                            selected={this.state.rescheduleDate} 
                            onChange={this.handleRescheduleDateChange} 
                            showTimeSelect
                            timeFormat="HH:mm"
                            timeIntervals={15}
                            timeCaption="time"
                            dateFormat="MMMM d, yyyy h:mm aa"
                        />
                        <FormControl fullWidth>
                            <InputLabel id="reschedule-clinic-label">Clinic</InputLabel>
                            <Select
                                labelId="reschedule-clinic-label"
                                id="reschedule-clinic-select"
                                value={this.state.rescheduleClinicSelected}
                                label="Reschedule Clinic"
                                onChange={this.handleRescheduleClinicChange}
                            >
                            {
                                this.state.rescheduleAvailableClinics.map((clinic, index) => {
                                    return (
                                        <MenuItem key={index} value={clinic.name}>{clinic.name}</MenuItem>
                                    )
                                })
                            }
                            </Select>
                        </FormControl>
                        <Button variant="contained">
                            Reschedule Appointment
                        </Button>
                    </div>
                </div>
                <div className="d-flex flex-fill flex-column">
                    <h1>Upcoming Appointments</h1>
                    <div className="d-flex flex-column due-list">
                        {
                            this.state.scheduledAppointments.map((appointment) => {
                                return (
                                    <div className='d-flex due-vaccine align-items-center'>
                                        <h5>{appointment.vaccine}</h5>
                                        <p>Clinic: {appointment.clinic}</p>
                                        <p>Date: {appointment.date}</p>
                                        <Button variant="contained" color='error'>
                                            Cancel
                                        </Button>
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

export default Appointment;