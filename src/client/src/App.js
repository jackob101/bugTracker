import "./App.css";
import WelcomePage from "./WelcomePage/WelcomePage";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import ProtectedRoute from "./ProtectedRoute";
import Issues from "./Issue/Issues";
import React, { Component } from "react";
import ProjectPage from "./Project/Project";

class App extends Component {
  atemptToLogin() {
    console.log("Login attempt");
  }

  render() {
    return (
      <Router>
        <Switch>
          <Route exact path="/">
            <WelcomePage />
          </Route>
          <ProtectedRoute
            exact
            path="/issues"
            component={Issues}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/projects"
            component={ProjectPage}
          ></ProtectedRoute>
        </Switch>
      </Router>
    );
  }
}

export default App;
