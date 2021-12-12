import React, { Component } from "react";
import { Route, Routes } from "react-router-dom";

import Landing from "./landing/Landing";
import Login from "./authentication/Login";

class Main extends Component {
    render() {
      return (
        <div>
          <div className="row">
            <div className="col">
                <Route exact path="/" element={Landing} />
                <Route path="/login" element={Login} />
            </div>
          </div>
        </div>
      );
    }
  }
  //Export The Main Component
  export default Main;