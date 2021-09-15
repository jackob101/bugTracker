import React, {useState} from "react";

const CommentCard = ({comment, edit, toggleEdit, editSubmit}) =>{

    const userIdMatch = comment.userId === parseInt(localStorage.getItem("userId"));
    const [editComment, setEditingId] = useState(comment.comment);

    const onEditChange = (event) =>{
        setEditingId(event.target.value);
    };

    const cancelEdit = () => {
        setEditingId(comment.comment);
        toggleEdit();
    };
    
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
             <form onSubmit={(event) => editSubmit(event, comment, editComment )} className="d-flex flex-column flex-grow-1">
               <textarea className="flex-grow-1" value={editComment} onChange={onEditChange}/>
               <div className="d-flex flex-row align-content-end justify-content-end">
                 <button className="btn btn-danger m-2" onClick={cancelEdit} type="button">Cancel</button>
                 <button className="btn btn-primary m-2 " type="submit">Submit</button>
               </div>
             </form>
             :
	     <p className="card-text">{comment.comment}</p>
            }
	  </div>
	</div>
    );
};

export default CommentCard;
