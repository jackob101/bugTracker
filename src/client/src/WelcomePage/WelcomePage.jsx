import React, { Component } from "react";
import Header from "./Components/Header.jsx";
import Features from "./Components/Features";
import PricingSections from "./Components/PricingSections";
import Testimonials from "./Components/Testimonials";
import NavBar from "../Components/Navbar/NavBar.jsx";
import Footer from "../Components/Footer.jsx";

class WelcomePage extends Component {
  state = {
    message: [],
  };

  componentDidMount() {
    let axios = require("axios");
    axios.get("/user/all").then((response) => {
      this.setState({ message: response.data });
    });
  }

  render() {
    return (
      <React.Fragment>
        <NavBar />
        <div>
          <Header about="about" />
          <Features />
          <PricingSections />
          <Testimonials id="about" />
        </div>
        <Footer />
      </React.Fragment>
    );
  }
}

export default WelcomePage;
