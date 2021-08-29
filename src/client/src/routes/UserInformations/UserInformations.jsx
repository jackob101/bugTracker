import React from "react";
import UserInformationsLogic from "./UserInformationsLogic";

const UserInformations = () => {
  const { onChange, onSubmit, userInfo, onIntegerChange } =
    UserInformationsLogic();

  return (
    <div className="height-full d-flex flex-column m-auto justify-content-center w-60">
      <h2 className="text-center">Please fill these fields</h2>
      <div className="container px-5 my-5">
        <form id="contactForm" onSubmit={onSubmit}>
          <div className="form-floating mb-3">
            <input
              className="form-control"
              id="name"
              type="text"
              placeholder="Name"
              data-sb-validations="required"
              onChange={onChange}
              value={userInfo.name}
              name="name"
            />
            <label htmlFor="name">Name</label>
            <div className="invalid-feedback" data-sb-feedback="name:required">
              Name is required.
            </div>
          </div>
          <div className="form-floating mb-3">
            <input
              className="form-control"
              id="lastName"
              type="text"
              placeholder="Last Name"
              data-sb-validations="required"
              onChange={onChange}
              value={userInfo.lastName}
              name="lastName"
            />
            <label htmlFor="lastName">Last Name</label>
            <div
              className="invalid-feedback"
              data-sb-feedback="lastName:required"
            >
              Last Name is required.
            </div>
          </div>
          <div className="form-floating mb-3">
            <input
              className="form-control"
              id="emailAddress"
              type="email"
              placeholder="Email Address"
              data-sb-validations="required,email"
              disabled
              value={userInfo.email}
              name="email"
            />
            <label htmlFor="emailAddress">Email Address</label>
            <div
              className="invalid-feedback"
              data-sb-feedback="emailAddress:required"
            >
              Email Address is required.
            </div>
            <div
              className="invalid-feedback"
              data-sb-feedback="emailAddress:email"
            >
              Email Address Email is not valid.
            </div>
          </div>
          <div className="form-floating mb-3">
            <input
              className="form-control"
              id="age"
              type="text"
              placeholder="Age"
              data-sb-validations="required"
              onChange={onIntegerChange}
              value={userInfo.age}
              name="age"
            />
            <label htmlFor="age">Age</label>
            <div className="invalid-feedback" data-sb-feedback="age:required">
              Age is required.
            </div>
          </div>
          <div className="d-none" id="submitSuccessMessage">
            <div className="text-center mb-3">
              <div className="fw-bolder">Form submission successful!</div>
              <p>To activate this form, sign up at</p>
              <a href="https://startbootstrap.com/solution/contact-forms">
                https://startbootstrap.com/solution/contact-forms
              </a>
            </div>
          </div>
          <div className="d-none" id="submitErrorMessage">
            <div className="text-center text-danger mb-3">
              Error sending message!
            </div>
          </div>
          <div className="d-grid">
            <button
              className="btn btn-primary btn-lg"
              id="submitButton"
              type="submit"
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UserInformations;
