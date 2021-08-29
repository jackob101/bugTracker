import React from "react";
import AuthorizedNavBar from "./AuthorizedNavBar";
import { useAuth0 } from "@auth0/auth0-react";
import GuestNavBar from "./GuestNavBar";
import { Link } from "react-router-dom";

const NavBar = () => {
  const { isAuthenticated } = useAuth0();

  return (
    <nav className="navbar navbar-expand-xl navbar-dark  lightdark">
      <div className="container-fluid">
        <Link
          className="navbar-brand d-flex flex-row align-items-center"
          to="/"
        >
          <img
            src={process.env.PUBLIC_URL + "/icon.svg"}
            alt="Logo"
            style={{ height: "40px" }}
          />
          <h4
            className="ms-2 mb-0"
            style={{ color: "white", textDecoration: "none" }}
          >
            Bug-tracker
          </h4>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarBasic"
          aria-controls="navbarBasic"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarBasic">
          {isAuthenticated ? <AuthorizedNavBar /> : <GuestNavBar />}
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
