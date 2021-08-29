import { useAuth0 } from "@auth0/auth0-react";
import Loading from "Components/Loading";
import React, { useEffect } from "react";
import useRequestUtils from "Utils/RequestUtils";
import { Redirect } from "react-router";
import axios from "axios";
import { useHistory } from "react-router-dom";

const Login = () => {
  const { user, isLoading, isAuthenticated } = useAuth0();
  const { getRequestHeaders } = useRequestUtils();
  const history = useHistory();

  useEffect(() => {
    (async () => {
      if (isAuthenticated) {
        const server = process.env.REACT_APP_SERVER_URL;
        const headers = await getRequestHeaders();

        await axios
          .post(server + "/user/check", user, headers)
          .then(async (response) => {
            console.log(response);
            if (!response.data.exist) history.push("/user/info");
            else {
              await axios
                .get(server + "/user/sub", {
                  params: {
                    sub: user.sub,
                  },
                  headers,
                })
                .then((response) => {
                  localStorage.setItem("userId", response.data.id);
                });
            }
          });
      }
    })();
  }, [isAuthenticated, getRequestHeaders, history, user]);

  return isLoading ? <Loading /> : <Redirect to="/" />;
};

export default Login;
