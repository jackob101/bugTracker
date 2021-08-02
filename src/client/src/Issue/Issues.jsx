import axios from "axios";
import React, { Component } from "react";
import { getHeaders } from "../repository.js";

class Issues extends Component {
  componentDidMount() {
    console.log(axios.get("/issue/all", getHeaders()));
  }

  render() {
    return (
      <div>
        <h1 className="text-center">Issues</h1>
      </div>
    );
  }
}

export default Issues;
