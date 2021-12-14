import React, { Component } from "react";
import Cookies from 'universal-cookie';
import axios from 'axios';
import './VaccineHistory.css';

const { API_URL } = require('../utils/Constants').default;
class VaccineHistory extends Component {
    constructor(props) {
        super(props);

        this.state = {
            vaccinations: [
                {name:"Pfizer", numberShots:2, clinic:"Castro Valley Rite Aid", date:"12/8/21"},
                {name:"Moderna", numberShots:2, clinic:"Castro Valley Rite Aid", date:"12/8/21"}
            ]
        }
    }

    async componentDidMount() {
        const cookies = new Cookies();
        let userId = cookies.get('userId');
        
        try {
            const response = await axios.get(`${API_URL}/history&userId=${userId}`);
            this.setState({
                vaccinations: response.vaccines
            });
        } catch (error) {
            console.log(error);
        }
    }

    render() {
        return (
            <div className="vaccine-history">
                <div className="d-flex flex-column">
                    <h1>Vaccine History</h1>

                    {
                        this.state.vaccinations.map((v, index) => {
                            return (
                                <div className="d-flex align-items-center justify-content-evenly history-block" key={index}>
                                    <h4>{v.name}</h4>
                                    <div>Number of Shots: {v.numberShots}</div>
                                    <div>Clinic: {v.clinic}</div>
                                    <div>Date: {v.date}</div>
                                </div>
                            )
                        })
                    }
                    
                </div>
            </div>
        )
    }
}

export default VaccineHistory;