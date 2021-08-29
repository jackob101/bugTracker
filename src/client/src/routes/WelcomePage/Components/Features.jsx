import React from "react";
import FeatureCard from "./FeatureCard";

const Features = () => {
  return (
    <React.Fragment>
      {/* <!-- Features section--> */}
      <section className="py-5 border-bottom" id="features">
        <div className="container px-5 my-5">
          <div className="row gx-5">
            <FeatureCard number="1" />
            <FeatureCard number="2" />
            <FeatureCard number="3" />
          </div>
        </div>
      </section>
    </React.Fragment>
  );
};

export default Features;
