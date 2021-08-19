import "App.css";
import WelcomePage from "routes/WelcomePage/WelcomePage";
import {
  Route,
  BrowserRouter as Router,
  Switch,
  Redirect,
} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import ProtectedRoute from "Components/ProtectedRoute";
import Issues from "routes/Issue/Issues";
import React, { Component } from "react";
import ProjectPage from "routes/Project/Project";
import ProjectIssues from "routes/Issue/ProjectIssues";
import IssueEdit from "routes/Issue/IssueEdit/IssueEdit";
import IssueDetails from "routes/Issue/IssueDetails/IssueDetails";
import NewIssue from "routes/Issue/NewIssue";
import AfterLogin from "Data/AfterLogin";
import { Toaster } from "react-hot-toast";

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
            path="/afterLogin"
            component={AfterLogin}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/issues"
            component={Issues}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/issue/details/:id"
            component={IssueDetails}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/issue/edit"
            component={IssueEdit}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/project/issues/:id"
            component={ProjectIssues}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/projects"
            component={ProjectPage}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/issue/edit"
            component={IssueEdit}
          ></ProtectedRoute>
          <ProtectedRoute
            exact
            path="/issue/new"
            component={NewIssue}
          ></ProtectedRoute>
        </Switch>
        <Toaster />
      </Router>
    );
  }
}

export default App;
