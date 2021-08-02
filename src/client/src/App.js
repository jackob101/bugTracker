import "./App.css";
import WelcomePage from "./WelcomePage/WelcomePage";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import LoginPage from "./Login/LoginPage";
import ProtectedRoute from "./ProtectedRoute";
import Issues from "./Issue/Issues";
import React, { Component } from "react";
import NavBar from "./Components/Navbar/NavBar";

class App extends Component {
  atemptToLogin() {
    console.log("Login attempt");
  }

  render() {
    return (
      <Router>
        <Switch>
          <Route exact path="/login">
            <LoginPage onLogin={this.atemptToLogin} />
          </Route>
          <ProtectedRoute exact path="/welcome">
            <NavBar />
            <WelcomePage />
          </ProtectedRoute>
          <ProtectedRoute exact path="/issues">
            <NavBar />
            <Issues />
          </ProtectedRoute>
        </Switch>
      </Router>
    );
  }
}

export default App;
