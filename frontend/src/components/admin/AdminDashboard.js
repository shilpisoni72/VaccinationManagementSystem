import React, { Component } from 'react'
import { Link, Route } from 'react-router-dom';


class AdminDashboard extends Component {
    render() {
        return (
            <div>
                <div className="d-flex justify-content-center dashboard">
                    <div className="d-flex flex-column navbar-section">
                        <Link to="/admin">
                            <button type="button" className="d-flex align-items-center nav-button">
                                <span>
                                    Admin Home
                                </span>
                            </button>
                        </Link>
                        <Link to="/systemreports">
                            <button type="button" className="d-flex align-items-center nav-button">
                                <span>
                                   System Reports
                                </span>
                            </button>
                        </Link>
                    </div>
                    <div className="d-flex flex-column current-page">
                        {this.props.currentPage}
                    </div>
                </div>
            </div>
        )
    }
}

export default AdminDashboard
