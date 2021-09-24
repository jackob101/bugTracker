import React from "react";
import CommentCard from "Components/CommentCard";
import IssueCommentsLogic from "./IssueCommentsLogic";

const IssueComments = ({ comments, setComments, onCommentSubmit }) => {
  const {
    getUserName,
    getLastName,
    onCommentChange,
    comment,
    editingId,
    toggleEdit,
    onCommentEditSubmit,
  } = IssueCommentsLogic(setComments, comments);

  console.log(comments);
  return (
    <div className="d-flex flex-column">
      {comments.map((comment) => (
        <CommentCard
          key={comment.id}
          comment={comment}
          edit={comment.id === editingId}
          editSubmit={onCommentEditSubmit}
          toggleEdit={toggleEdit}
        />
      ))}

      <hr />
      <form onSubmit={(event) => onCommentSubmit(event, comment)}>
        <div className="card my-3">
          <div className="card-header">
            {"This comment will be posted by " +
              getUserName() +
              " " +
              getLastName()}
          </div>
          <div className="card-body d-flex p-1">
            <textarea
              rows="7"
              name="comment"
              className="flex-grow-1 p-1"
              value={comment}
              onChange={onCommentChange}
            />
          </div>
          <div className="card-footer d-flex justify-content-end">
            <button className="btn btn-danger me-3">Close issue</button>
            <button className="btn btn-primary" type="submit">
              Comment
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default IssueComments;
