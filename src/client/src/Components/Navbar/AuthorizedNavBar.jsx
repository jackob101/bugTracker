import React from "react";
import { Link } from "react-router-dom";
import AuthorizationButton from "../AuthorizationButton";

const AuthorizedNavBar = () => {
  return (
    <React.Fragment>
      <ul className="navbar-nav me-auto mb-2 mb-xl-0">
        <li className="nav-item mt-sm-3 mt-xl-0">
          <Link className="nav-link active" aria-current="page" to="/">
            Home
          </Link>
        </li>
        <li className="nav-item mt-sm-3 mt-xl-0">
          <Link className="nav-link active" aria-current="page" to="/issues">
            Issues
          </Link>
        </li>
        <li className="nav-item mt-sm-3 mt-xl-0">
          <Link className="nav-link active" aria-current="page" to="/projects">
            Projects
          </Link>
        </li>
        <li className="nav-item mt-sm-3 mt-xl-0">
          <Link className="nav-link active" aria-current="page" to="#">
            Users
          </Link>
        </li>
      </ul>

      <AuthorizationButton />
    </React.Fragment>
  );
};

export default AuthorizedNavBar;
