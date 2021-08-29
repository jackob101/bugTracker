import React from "react";
import FormLogic from "routes/Issue/IssueEdit/Form/FormLogic";

const IssueForm = (props) => {
  const { issue, handleChange, handleSubmit, onCancel } = FormLogic(props);

  return (
    <div className="container px-5 my-5">
      <form
        id="contactForm"
        data-sb-form-api-token="API_TOKEN"
        onSubmit={handleSubmit}
      >
        <div className="form-floating mb-3">
          <input
            className="form-control"
            id="title"
            type="text"
            placeholder="Title"
            data-sb-validations="required"
            name="title"
            onChange={handleChange}
            value={issue.title}
          />
          <label htmlFor="title">Title</label>
          <div className="invalid-feedback" data-sb-feedback="title:required">
            Title is required.
          </div>
        </div>
        <div className="form-floating mb-3">
          <textarea
            className="form-control"
            id="description"
            type="text"
            name="description"
            placeholder="Description"
            style={{ height: "10rem" }}
            data-sb-validations="required"
            onChange={handleChange}
            value={issue.description}
          ></textarea>
          <label htmlFor="description">Description</label>
          <div
            className="invalid-feedback"
            data-sb-feedback="description:required"
          >
            Description is required.
          </div>
        </div>
        <div className="form-floating mb-3">
          <select
            className="form-select"
            id="priority"
            name="priority"
            onChange={handleChange}
            value={issue.priority}
            aria-label="Priority"
          >
            <option value="URGENT">URGENT</option>
            <option value="IMPORANT">IMPORANT</option>
            <option value="NOT_IMPORTANT">NOT_IMPORANT</option>
          </select>
          <label htmlFor="priority">Priority</label>
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
        <div className="d-flex flex-row">
          <button
            className="btn btn-primary btn-lg flex-grow-1 mx-2"
            id="submitButton"
            type="submit"
          >
            Submit
          </button>
          <button
            className="btn btn-outline-secondary mx-2 "
            type="button"
            onClick={onCancel}
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default IssueForm;
