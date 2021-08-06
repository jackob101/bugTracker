import React, { Component } from "react";
import Footer from "../Components/Footer.jsx";
import NavBar from "../Components/Navbar/NavBar.jsx";
import { useAuth0 } from "@auth0/auth0-react";
import { useState } from "react";

const Issues = () => {
  const [message, setMessage] = useState("");
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

      setMessage(responseData.message);
    } catch (error) {
      setMessage(error.message);
    }
  };

  return (
    <React.Fragment>
      <NavBar />
      <div className="height-full">
        <h1 className="text-center">Issues</h1>
        <button onClick={callIssueEndpoint}></button>
      </div>
      <Footer />
    </React.Fragment>
  );
};

export default Issues;
