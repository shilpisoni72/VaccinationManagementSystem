import React, { Component } from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
} from 'react-router-dom';
import DateAdapter from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import "./App.css"

import Landing from './components/landing/Landing';
import Login from './components/authentication/Login';
import Signup from './components/authentication/Signup';
import Dashboard from './components/user_dashboard/Dashboard';
import VaccinesDue from './components/user_dashboard/VaccinesDue';
import Appointment from './components/user_dashboard/Appointment';
import VaccineHistory from './components/user_dashboard/VaccineHistory';
import PatientReports from './components/user_dashboard/PatientReports';
import AdminDashboard from './components/admin/AdminDashboard';
import AdminManagement from './components/admin/AdminManagement';

function App() {
  return (
    <LocalizationProvider dateAdapter={DateAdapter}>
      <div className="App">
        <BrowserRouter>
            <div>
              <Routes>
                <Route path="/" element={<Landing/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/signup" element={<Signup/>}/>
                <Route path="/dashboard" element={<Dashboard currentPage={<VaccinesDue/>}/>} />
                <Route path="/appointment" element={<Dashboard currentPage={<Appointment/>}/>} />
                <Route path="/history" element={<Dashboard currentPage={<VaccineHistory/>}/>} />
                <Route path="/patientreports" element={<Dashboard currentPage={<PatientReports/>}/>} />
                <Route path="/admin" element={<AdminDashboard currentPage={<AdminManagement/>}/>} />
              </Routes>
            </div>
        </BrowserRouter>
      </div>
    </LocalizationProvider>
    
  );
}

export default App;
