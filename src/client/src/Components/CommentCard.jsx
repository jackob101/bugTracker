import React from "react"


const CommentCard = ({name, lastName, date, body}) =>{


    return ( 
	<div className="card my-3">
	    <div className="card-header">
		<strong>
		    {name + " " + lastName}
		</strong>
		&nbsp;
		{"commented on " + date}
	    </div>
	    <div className="card-body">
		<p className="card-text">{body}</p>
	    </div>
	</div>
    )
}

export default CommentCard;
