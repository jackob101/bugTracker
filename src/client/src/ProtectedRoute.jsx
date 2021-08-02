import { Route, Redirect } from "react-router-dom";
import React, { Component } from "react";
import { isAuthenticated } from "./repository";

class ProtectedRoute extends Component {
  state = {};

  render() {
    return (
      <Route
        {...this.props.rest}
        render={({ location }) =>
          isAuthenticated() ? (
            this.props.children
          ) : (
            <Redirect
              to={{
                pathname: "/login",
                state: { from: location },
              }}
            />
          )
        }
      />
    );
  }
}

export default ProtectedRoute;
