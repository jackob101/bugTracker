import React from "react"
import {useState} from "react"
import OptionBox from "./OptionBox.jsx"

const Detail = ({content}) =>{
    
    const [isOptionOpen, setOptionOpen] = useState(false)
    
    return (
	<div className="d-flex flex-column ">
	    <div className="d-flex flex-row ">
		<span className="flex-grow-1 me-3 d-flex align-content-center"><strong>{content.title}</strong></span>
		<button className="btn-link btn p-0" onClick={event => setOptionOpen(!isOptionOpen)}>
		    <img style={{width:"25px"}} src={process.env.PUBLIC_URL + "/cogs.svg"} alt=""/>
		</button>
	    </div>
	    <div className="d-flex flex-column pt-3 position-relative">
		<OptionBox isOptionOpen={isOptionOpen} optionConfig={content.option}/>

		{content.body.map((entry, index) => <div key={index}>{entry}</div>)}
	    </div>
	    <hr/>
	</div>
    )
}

export default Detail;
