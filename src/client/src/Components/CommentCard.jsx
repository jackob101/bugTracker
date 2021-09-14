import React from "react";

const CommentCard = ({comment, edit, toggleEdit}) =>{

    const userIdMatch = comment.userId === parseInt(localStorage.getItem("userId"));
    console.log(comment);
    return ( 
	<div className="card my-3">
	  <div className="card-header d-flex">
	    <strong>
	      {comment.userName + " " + comment.userLastName}
	    </strong>
	    &nbsp;
	    {"commented on " + comment.creationDate.split("T")[0]}
            {
                userIdMatch
                    ?
                    <button className="ms-auto btn" onClick={() => toggleEdit(comment.id)}>Edit</button>
                :
                ""
            }
	  </div>
	  <div className="card-body d-flex">
            {edit ? 
             <textarea className="flex-grow-1"/>
             :
	     <p className="card-text">{comment.comment}</p>
            }
	  </div>
	</div>
    );
};

export default CommentCard;
