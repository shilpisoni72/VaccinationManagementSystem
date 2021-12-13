import { Button } from '@mui/material';
import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import addDays from 'date-fns/addDays'
import './VaccinesDue.css';

class VaccinesDue extends Component {
    constructor(props) {
        super(props)
        this.state = {
    
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
                        {/* MAP LIST OF VACCINES? */}
                        <div className='d-flex due-vaccine align-items-center'>
                            <h5>Pfizer</h5>
                            <p>Shots due: 1</p>
                            <p>Due Date: 12/24/21</p>
                        </div>
                        <div className='d-flex due-vaccine align-items-center'>
                            <h5>Flu</h5>
                            <p>Shots due: 1</p>
                            <p>Due Date: 12/24/21</p>
                        </div>
                        <div className='d-flex due-vaccine align-items-center'>
                            <h5>Moderna</h5>
                            <p>Shots due: 2</p>
                            <p>Due Date: 12/24/21</p>
                        </div>
                    </div>
                </div>
                <div className="d-flex flex-fill flex-column">
                    <h1>Upcoming Appointments</h1>
                    <div className="d-flex flex-column due-list">
                        {/* MAP LIST OF VACCINES? */}
                        <div className='d-flex due-vaccine align-items-center'>
                            <h5>Pfizer</h5>
                            <p>Clinic: Sunnyvale CVS</p>
                            <p>Date: 12/20/21</p>
                            {/* IF date is within 24hrs of chosen date && user hasn't checked in yet, show check in button ELSE hide button */}
                            <Button variant="outlined" onClick={this.handleCheckin}>
                                Check In
                            </Button>
                        </div>
                        <div className='d-flex due-vaccine align-items-center'>
                            <h5>Flu</h5>
                            <p>Clinic: Sunnyvale CVS</p>
                            <p>Date: 12/20/21</p>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default VaccinesDue;