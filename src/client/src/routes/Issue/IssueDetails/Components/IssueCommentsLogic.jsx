import React, { useState } from "react";
import useRequestUtils from "Utils/RequestUtils";

const IssueCommentsLogic = (setComments, comments) => {
  const [comment, setComment] = useState("");
  const [editingId, setEditingId] = useState(0);
  const { post } = useRequestUtils();

  const onCommentChange = (event) => {
    setComment(event.target.value);
  };

  const getUserName = () => {
    return localStorage.getItem("userName");
  };

  const getLastName = () => {
    return localStorage.getItem("userLastName");
  };

  const toggleEdit = (commentId) => {
    if (commentId === editingId) setEditingId(0);
    else setEditingId(commentId);
  };

  const onCommentEditSubmit = (event, comment, editComment) => {
    event.preventDefault();
    let newComments = Object.values({ ...comments });
    newComments.find((entry) => entry.id === comment.id).comment = editComment;
    setComments(newComments);
    post("/comment/update", {
      commentId: comment.id,
      comment: editComment,
    });
    setEditingId(0);
  };

  return {
    onCommentChange,
    getUserName,
    getLastName,
    comment,
    editingId,
    toggleEdit,
    onCommentEditSubmit,
  };
};

export default IssueCommentsLogic;
