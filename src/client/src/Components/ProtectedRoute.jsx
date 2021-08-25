import { Route, withRouter } from "react-router-dom";
import React, { Component } from "react";
import { withAuthenticationRequired } from "@auth0/auth0-react";

class ProtectedRoute extends Component {
  state = {};

  render() {
    return (
      <Route
        component={withAuthenticationRequired(this.props.component)}
        path={this.props.path}
        {...this.props.args}
      />
    );
  }
}

export default withRouter(ProtectedRoute);
