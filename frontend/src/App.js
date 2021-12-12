import React, { Component } from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
} from 'react-router-dom';
import "./App.css"

import Landing from './components/landing/Landing';
import Login from './components/authentication/Login';
import Dashboard from './components/user_dashboard/Dashboard';
import VaccinesDue from './components/user_dashboard/VaccinesDue';
import Appointment from './components/user_dashboard/Appointment';
import VaccineHistory from './components/user_dashboard/VaccineHistory';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <div>
            <Routes>
              <Route path="/" element={<Landing/>}/>
              <Route path="/login" element={<Login/>}/>
              <Route path="/dashboard" element={<Dashboard currentPage={<VaccinesDue/>}/>} />
              <Route path="/appointment" element={<Dashboard currentPage={<Appointment/>}/>} />
              <Route path="/history" element={<Dashboard currentPage={<VaccineHistory/>}/>} />
            </Routes>
          </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
