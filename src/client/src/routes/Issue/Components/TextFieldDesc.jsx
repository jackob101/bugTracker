import React from "react";

const TextFieldDesc = (props) => {
  return (
    <div className="">
      <h4 className="text-center mx-auto mb-0 p-2 fst-italic ">
        {props.title}
      </h4>
      <p
        style={{
          wordBreak: "break-word",
        }}
        className="text-center mx-auto fs-4 p-2 "
      >
        {props.text}
      </p>
    </div>
  );
};

export default TextFieldDesc;
