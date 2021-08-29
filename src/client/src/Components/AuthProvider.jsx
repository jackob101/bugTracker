import { Auth0Provider } from "@auth0/auth0-react";
import React from "react";

const AuthProvider = ({ children }) => {
  const domain = process.env.REACT_APP_AUTH0_DOMAIN;
  const clientId = process.env.REACT_APP_AUTH0_CLIENT_ID;
  const audience = process.env.REACT_APP_AUTH0_AUDIENCE;

  return (
    <Auth0Provider
      domain={domain}
      clientId={clientId}
      redirectUri={window.location.origin + "/login"}
      audience={audience}
    >
      {children}
    </Auth0Provider>
  );
};

export default AuthProvider;
