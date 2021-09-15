import React, {useState} from "react";

const IssueCommentsLogic = ({setIssue}) =>{

    const [comment, setComment] = useState("");
    const [editingId, setEditingId] = useState(0);

    
    const onCommentChange = (event) =>{
        setComment(event.target.value);
    };

    const getUserName = () => {
        return localStorage.getItem("userName");
    };

    const getLastName = () => {
        return localStorage.getItem("userLastName");
    };

    const toggleEdit = (commentId) => {
        if(commentId === editingId)
            setEditingId(0);
        else
            setEditingId(commentId);
    };


    return {onCommentChange, getUserName, getLastName, comment, editingId, toggleEdit};
};

export default IssueCommentsLogic;
