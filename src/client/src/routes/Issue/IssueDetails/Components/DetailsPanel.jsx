import React from "react"
import Detail from "./Detail.jsx"

const DetailsPanel = ({issue, content}) =>{
    

    console.log(content)
    return (
	<div style={{width:"25%"}} className="p-4">

	    {content.map((entry,index) => <Detail
					      key={index}
					      content={entry}/>)}
	</div>
    );
}

export default DetailsPanel;
