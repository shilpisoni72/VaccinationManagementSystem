import React, { Component } from 'react'
import './VaccinesDue.css';

class VaccinesDue extends Component {
    render() {
        return (
            <div className="d-flex flex-column">
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
        )
    }
}

export default VaccinesDue;