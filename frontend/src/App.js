import React, { Component } from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
} from 'react-router-dom';
import "./App.css"

import Main from './components/Main';
import Landing from './components/landing/Landing';
import Login from './components/authentication/Login';
import Dashboard from './components/user_dashboard/Dashboard';
import VaccinesDue from './components/user_dashboard/VaccinesDue';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <div>
            <Routes>
              <Route path="/" element={<Landing/>}/>
              <Route path="/login" element={<Login/>}/>
              <Route path="/dashboard" element={<Dashboard currentPage={<VaccinesDue/>}/>} />
            </Routes>
          </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
