import React, { Component } from "react";
import { Link, Route } from 'react-router-dom';
import "./Dashboard.css";
import VaccinesDue from "./VaccinesDue";

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <div className="d-flex justify-content-center dashboard">
                    <div className="d-flex flex-column navbar-section">
                        <Link to="/dashboard">
                            <button type="button" className="d-flex align-items-center nav-button">
                                <span>
                                    Home
                                </span>
                            </button>
                        </Link>
                        <Link to="/history">
                            <button type="button" className="d-flex align-items-center nav-button">
                                <span>
                                   Vaccine History
                                </span>
                            </button>
                        </Link>
                        <Link to="/appointment">
                            <button type="button" className="d-flex align-items-center nav-button">
                                <span>
                                    Make/Cancel an Appointment
                                </span>
                            </button>
                        </Link>
                    </div>
                    <div className="d-flex flex-column current-page">
                        {this.props.currentPage}
                    </div>
                </div>
            </div>
        );
    }
}

export default Dashboard;