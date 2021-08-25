import React from "react";
import Footer from "./Footer";
import NavBar from "./Navbar/NavBar";

const Layout = (props) => {
  return (
    <React.Fragment>
      <NavBar />
      <div className="height-full">{props.children}</div>
      <Footer />
    </React.Fragment>
  );
};

export default Layout;
