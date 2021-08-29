import React from "react";
import Header from "routes/WelcomePage/Components/Header.jsx";
import Features from "routes/WelcomePage/Components/Features";
import PricingSections from "routes/WelcomePage/Components/PricingSections";
import Testimonials from "routes/WelcomePage/Components/Testimonials";
import Layout from "Components/Layout.jsx";

const WelcomePage = () => {
  return (
    <Layout>
      <Header about="about" />
      <Features />
      <PricingSections />
      <Testimonials id="about" />
    </Layout>
  );
};

export default WelcomePage;
