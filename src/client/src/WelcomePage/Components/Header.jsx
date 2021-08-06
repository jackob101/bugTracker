import React from "react";
import { Link } from "react-router-dom";
import { Link as ScrollLink } from "react-scroll";

const Header = (props) => {
  return (
    <header className="bg-dark py-5">
      <div className="container px-5">
        <div className="row gx-5 justify-content-center">
          <div className="col-lg-6">
            <div className="text-center my-5">
              <h1 className="display-5 fw-bolder text-white mb-2">
                Bug Tracker
              </h1>
              <p className="lead text-white-50 mb-4">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam
                at nulla congue, tincidunt ex sit amet, mattis dolor. Sed
                interdum hendrerit est in tristique. Duis nec dolor quam. Sed mi
                nisl, mattis ut ullamcorper vel, malesuada eu ex. Etiam
                pellentesque sapien id arcu porta fringilla{" "}
              </p>
              <div className="d-grid gap-3 d-sm-flex justify-content-sm-center">
                <Link
                  className="btn btn-primary btn-lg px-4 me-sm-3"
                  to="/login"
                >
                  Create account
                </Link>
                <ScrollLink
                  className="btn btn-outline-light btn-lg px-4"
                  to={props.about}
                  spy={true}
                  smooth={true}
                  duration={250}
                >
                  Learn More
                </ScrollLink>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;
