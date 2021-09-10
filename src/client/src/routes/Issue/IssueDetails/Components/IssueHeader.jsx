import React from "react"

const IssueHeader = ({issue,isClosed }) =>{


    let badge = "badge rounded-pill fs-6 me-2 "
    let icon = isClosed ? "/checkmark.svg" : "/square.svg";
    let text = isClosed ? "Closed" : "Open";
    badge += isClosed ? "bg-danger" : "bg-success";

    return (
	<div>
	    <h2>{issue.title}</h2>
	    <div className="d-flex flex-row align-items-center">

		<div className={badge}>
		    <div className=" d-flex flex-row">
			<img style={{width: "16px"}}  src={process.env.PUBLIC_URL + icon} alt=""/>
			<span className="mx-1 d-flex flex-row align-items-center">{text}</span>
		    </div>
		</div>

		<div className="d-flex align-items-center">
		    <div style={{fontWeight: "bold"}}>{issue.createdBy.name + " " + issue.createdBy.lastName}</div>
		    <div>&nbsp;</div>
		    <div>{"opened this issue on " + issue.createdDate}</div>
		    <div className="mx-2">●</div>
		    <div>{issue.comments.length + " comments"}</div>
		</div>
	    </div>
	</div>
    )
}

export default IssueHeader;
