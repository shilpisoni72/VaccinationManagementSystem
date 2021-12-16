import React, { Component } from 'react'
import DatePicker from "react-datepicker";
import Cookies from 'universal-cookie';
import axios from 'axios';
import './Appointment.css';
import "react-datepicker/dist/react-datepicker.css";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Button from '@mui/material/Button';

const { API_URL } = require('../utils/Constants').default;
class Appointment extends Component {
    constructor(props) {
        super(props);
        this.state = {
            appointmentDate: this.props.chosenDate,
            clinicSelected:"",
            availableClinics: [
                {name: "Sunnyvale CVS"},
                {name: "Evergreen CVS"},
                {name: "Palo Alto CVS"},
            ],
            vaccinesSelected: [],
            availableVaccines: [
                {id:"123", name: "Pfizer"},
                {id:"124", name: "Moderna"},
                {id:"125", name: "Tetnis"},
                {id:"126", name: "Swine flu"},
                {id:"127", name: "Johnson&Johnson"},
                {id:"128", name: "Small pox"},
                {id:"129", name: "Meningitis"}
            ],

            scheduledAppointments: [
                {vaccine: "Pfizer", clinic:"Sunnyvale CVS", date:"12/20/21"},
                {vaccine: "Flu", clinic:"Sunnyvale CVS", date:"12/20/21"},
            ],
            appointmentSelected: {},
            rescheduleDate: this.props.chosenDate,
            minRescheduleDate: this.props.chosenDate,
            rescheduleClinicSelected:"",
            rescheduleAvailableClinics: [
                {name: "Sunnyvale CVS"},
                {name: "Evergreen CVS"},
                {name: "Palo Alto CVS"},
            ],
        }
    }

    async componentDidMount() {
        const cookies = new Cookies();
        cookies.set('userId', 106);

        let userId = cookies.get('userId');
        const payload = {
            userId: userId,
        }
        const clinicPayload = {
            appointmentTime: this.state.appointmentDate,
        }
        try {
            const response = await axios.post(`${API_URL}/appointment/user`, payload);
            const clinicResponse = await axios.post(`${API_URL}/clinic/getAvailableClinics`, clinicPayload);
            console.log(clinicResponse);
            this.setState({
                scheduledAppointments: response.userAppointments,
                availableClinics: clinicResponse.clinics,
                rescheduleAvailableClinics: clinicResponse.clinics,
            });
        } catch (error) {
            console.log(error);
        }
    }

    handleDateChange = async (date) => {
        this.setState({
            appointmentDate: date
        });

        const payload = {
            appointmentTime: this.state.appointmentDate.toString(),
        }
        console.log("appointment time  - " , payload);
        try {
            const response = await axios.post(`${API_URL}/clinic/getAvailableClinics`, payload);
            console.log("response = " ,  response.data);
            // if(response.data.length == 0) alert("no clinic available on that date time");
            this.setState({
                availableClinics: response.data,
            });
        } catch (error) {
            console.log(error);
        }
    }

    handleVaccineChange = async (e) => {
        if(e.target.value.length > this.state.vaccinesSelected) {
            const cookies = new Cookies();
            let userId = cookies.get('userId');

            const payload = {
                vaccinationId: e.target.value.id,
                userId: userId,
                date: this.state.appointmentDate,
            }

            try {
                const response = await axios.post(`${API_URL}/appointment/shot_number`, payload);
                if(response.shotNumber !== -1) {
                    let addedVac = e.target.value.filter(v => !this.state.vaccinesSelected.includes(v));
                    console.log(addedVac);
                    for(let v of this.state.vaccinesSelected){
                        if(v.id === addedVac[0].id) {
                            addedVac[0]["shotNumber"] = response.shotNumber;
                        }
                    }
                    this.setState({
                        vaccinesSelected: [...this.state.vaccinesSelected, addedVac[0]]
                    });
                }
            } catch (error) {
                console.log(error);
            }
        } else {
            this.setState({
                vaccinesSelected: e.target.value,
            });
        }
    }

    handleClinicChange = (e) => {
        this.setState({
            clinicSelected: e.target.value
        });
    }

    bookAppointment = async (e) => {
        const cookies = new Cookies();
        let userId = cookies.get('userId');

        let shotNumbers = [];
        let vaccineIds = [];
        this.state.vaccinesSelected.forEach((v) => {
            shotNumbers.push(v.shotNumber);
            vaccineIds.push(v.id);
        })

        const payload = {
            userId: userId,
            appointmentDate: this.state.appointmentDate.toString(),
            appointmentBookedDate: this.props.chosenDate,
            vaccinationIds: vaccineIds,
            clinicId: this.state.clinicSelected.clinicId,
            shotNumber: shotNumbers, 
        }

        try {
            const response = await axios.post(`${API_URL}/appointment/book`, payload);
            this.setState({
                scheduledAppointments: [...this.state.scheduledAppointments, response.bookedAppointment]
            });
        } catch (error) {
            console.log(error);
        }

    }

    handleAppointmentChange = (e) => {
        this.setState({
            appointmentSelected: e.target.value,
            minRescheduleDate: e.target.value.date,
        });
    }

    handleRescheduleDateChange = async(date) => {
        const payload = {
            appointmentTime: date.toString(),
        }

        try {
            const response = await axios.post(`${API_URL}/clinic/getAvailableClinics`, payload);
            this.setState({
                availableClinics: response.clinics,
                rescheduleDate: date
            });
        } catch (error) {
            console.log(error);
        }
    }

    handleRescheduleClinicChange = (e) => {
        this.setState({
            rescheduleClinicSelected: e.target.value
        });
    }

    rescheduleAppointment = async (e) => {
        const cookies = new Cookies();
        let userId = cookies.get('userId');

        const payload = {
            user: userId,
            date: this.state.rescheduleDate,
            clinic: this.state.rescheduleClinicSelected,
        }

        try {
            const response = await axios.post(`${API_URL}/rescheduleappointment`, payload);
            this.setState({
                scheduledAppointments: response.appointments
            });
        } catch (error) {
            console.log(error);
        }

    }

    cancelAppointment = async (e) => {
        const payload = {
            appointmentId: e.target.value.appointmentId,
        }

        try {
            const response = await axios.post(`${API_URL}/appointment/cancel`, payload);
            this.setState({
                scheduledAppointments: this.state.scheduledAppointments.filter((appt) => {
                    return appt.id !== response.cancelledAppointment.id
                })
            });
        } catch (error) {
            console.log(error);
        }
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
                            minDate={this.props.chosenDate}
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
                                this.state.availableClinics?.map((clinic, index) => {
                                    return (
                                        <MenuItem key={index} value={clinic}>{clinic.clinicName}</MenuItem>
                                    )
                                })
                            }
                            </Select>
                        </FormControl>
                        <Button variant="contained" onClick={this.bookAppointment}>
                            Schedule Appointment
                        </Button>
                    </div>
                </div>

                {/*reschedule to time later than set appt */}
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
                            minDate={this.state.minRescheduleDate}
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