import React from "react"


const OptionBox = ({isOptionOpen, optionConfig}) =>{

    return (
	<div className="option-box position-absolute bg-dark rounded" style={{width: "100%", display: isOptionOpen ? "block" : "none" }}>
	    <div className="d-flex flex-column position-relative"> 
		<span className="color-white"><strong>{optionConfig.title}</strong></span>
		<hr className="mt-0"/>
	    </div>

	    <div className="d-flex flex-column">

		{optionConfig.body.map((entry, index) => <div className="d-flex flex-column" key={index}>{entry}</div>)}

	    </div>
	</div>
    );
}

export default OptionBox;
