import React from "react";

const SectionHeader = (props) => {
  return (
    <div className="d-flex flex-column">
      <h2 className="text-center">{props.text}</h2>
      {props.children}
      <hr />
    </div>
  );
};

export default SectionHeader;
