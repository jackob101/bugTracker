import React from "react";
import axios from "axios";
import { useState, useEffect } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import Loading from "../Components/Loading";
import { Redirect } from "react-router";

const AfterLogin = () => {
  const [loading, setLoading] = useState(true);
  const { getAccessTokenSilently } = useAuth0();

  useEffect(() => {
    (async () => {
      const serverUrl = process.env.REACT_APP_SERVER_URL;
      const token = await getAccessTokenSilently();
      const headers = {
        headers: {
          Authorization: "Bearer " + token,
        },
      };
      axios.get(serverUrl + "/user/1", headers).then((response) => {
        setLoading(false);
      });
    })();
  });

  return loading ? <Loading /> : <Redirect to="/" />;
};

export default AfterLogin;
