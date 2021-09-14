import React from "react";

const IssueDescription = ({issue}) => {
    return (
	<div className="card my-3">
	  <div className="card-header d-flex">
	    <strong>
	      {issue.createdBy.name + " " + issue.createdBy.lastName}
	    </strong>
	    &nbsp;
            added description
	  </div>
	  <div className="card-body d-flex">
            <p>{issue.description}</p>
	  </div>
	</div>

    ) ;
};

export default IssueDescription;
