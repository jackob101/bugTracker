import React from "react"
import CommentCard from "Components/CommentCard"

const IssueComments = ({issue}) =>{
    

    return (
	
	<div className="d-flex flex-column" style={{width:"74%"}}>

	    <CommentCard name={issue.createdBy.name}
			 lastName={issue.createdBy.lastName}
			 body={issue.description}
			 date={issue.createdDate}/>

	    {issue.comments.map(comment => <CommentCard key={comment.id}
							name={comment.userName}
							lastName={comment.userLastName}
							body={comment.comment}
							date={comment.creationDate.split("T")[0]}/>)}
	</div>
    );
}

export default IssueComments;
