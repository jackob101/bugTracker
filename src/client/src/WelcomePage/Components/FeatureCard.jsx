import React from "react";

const FeatureCard = (props) => {
  return (
    <div className="col-lg-4 mb-5 mb-lg-0">
      <div className="feature bg-primary bg-gradient text-white rounded-3 mb-3">
        <i className="bi bi-collection"></i>
      </div>
      <h2 className="h4 fw-bolder">
        {"Featured title number:" + props.number}{" "}
      </h2>
      <p>
        Paragraph of text beneath the heading to explain the heading. We'll add
        onto it with another sentence and probably just keep going until we run
        out of words.
      </p>
      <a className="text-decoration-none" href="#!">
        Call to action
        <i className="bi bi-arrow-right"></i>
      </a>
    </div>
  );
};

export default FeatureCard;
