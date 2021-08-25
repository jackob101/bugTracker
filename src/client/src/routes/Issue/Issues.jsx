import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import Layout from "Components/Layout.jsx";

const Issues = () => {
  const serverUrl = process.env.REACT_APP_SERVER_URL;

  const { getAccessTokenSilently } = useAuth0();

  const callIssueEndpoint = async () => {
    try {
      const token = await getAccessTokenSilently();
      console.log(token);

      const response = await fetch(`${serverUrl}/issue/all`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const responseData = await response.json();
      console.log(responseData);
    } catch (error) {}
  };

  return (
    <Layout>
      <h1 className="text-center">Issues</h1>
      <button onClick={callIssueEndpoint}></button>
    </Layout>
  );
};

export default Issues;
