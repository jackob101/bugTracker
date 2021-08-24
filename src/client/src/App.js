import "App.css";
import WelcomePage from "routes/WelcomePage/WelcomePage";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import ProtectedRoute from "Components/ProtectedRoute";
import Issues from "routes/Issue/Issues";
import React from "react";
import ProjectPage from "routes/Project/ProjectsList/ProjectListPage";
import ProjectIssues from "routes/Issue/ProjectIssues/ProjectIssues";
import IssueEdit from "routes/Issue/IssueEdit/IssueEdit";
import IssueDetails from "routes/Issue/IssueDetails/IssueDetails";
import NewIssue from "routes/Issue/NewIssue";
import AfterLogin from "Data/AfterLogin";
import { Toaster } from "react-hot-toast";
import Login from "routes/Login";
import UserInformations from "routes/UserInformations/UserInformations";

const App = () => {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <WelcomePage />
        </Route>
        <Route exact path="/login" component={Login} />
        <Route exact path="/user/info" component={UserInformations} />
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
};

export default App;
