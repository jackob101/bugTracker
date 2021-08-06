import React from "react";
import { Link } from "react-router-dom";
import LoginButton from "../LoginButton";

const GuestNavBar = () => {
  return (
    <ul className="navbar-nav ms-auto mb-2 mb-xl-0 justify-content-end">
      <li className="nav-item me-3 mt-xl-0 mt-sm-3">
        <LoginButton />
      </li>
      <li className="nav-item ">
        <Link to="#" className="btn btn-secondary me-3 mt-sm-3 mt-xl-0">
          Create account
        </Link>
      </li>
    </ul>
  );
};

export default GuestNavBar;
