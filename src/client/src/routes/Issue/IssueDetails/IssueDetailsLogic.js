import { useAuth0 } from "@auth0/auth0-react";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useHistory } from "react-router";
import { useParams } from "react-router-dom";
import RequestUtils from "Utils/RequestUtils";
import toast from "react-hot-toast";

const IssueDetailsLogic = () => {
  const { id } = useParams();
  const history = useHistory();
  const { get, deleteRequest, post } = RequestUtils();


    const userId = parseInt(localStorage.getItem("userId"));
  const [issue, setIssue] = useState();
    const [comment, setComment] = useState("");
  const [users, setUsers] = useState([]);
  const [isClosed, setIsClosed] = useState(false);
  const [loading, setLoading] = useState(true);
  const { getAccessTokenSilently } = useAuth0();

  useEffect(() => {
    async function fetchData() {
      await axios.all([getIssue(), getUsers()]).then(
        axios.spread((issue, users) => {
          setIssue(issue.data);
          setUsers(users.data);
          setLoading(false);
          setIsClosed(issue.data.closedTime !== "null");
        })
      );
    }
    fetchData();
  }, [getAccessTokenSilently, id]);

  const getIssue = () => {
    return get("/issue/" + id);
  };
  const getUsers = () => {
    return get("/user/notAssigned", { issueId: id });
  };

  const onDelete = async (issue) => {
    const projectId = issue.project.id;
    await deleteRequest("/issue/delete", { id: issue.id });
    history.push("/project/issues/" + projectId);
  };

  const onToggleIssueClosedStatus = async (issue) => {
    post("/issue/close", {}, { id: issue.id }).then((response) => {
      isClosed
        ? toast.success("Issue reopened successfully")
        : toast.success("Issue closed successfully");
      setIssue({
        ...issue,
        closedTime: issue.closedTime === "null" ? Date.now : "null",
      });
      setIsClosed(!isClosed);
    });
  };

  const assignUser = async (row) => {
    post("/issue/assign", {}, { issueId: issue.id, userId: row.id }).then(
      (response) => {
        setIssue(response.data);
      }
    );
    const newUsers = users.filter((user) => user.id != row.id);
    setUsers(newUsers);
  };

  const unassignUser = async (row) => {
    post("/issue/unassign", {}, { issueId: issue.id, userId: row.id }).then(
      (response) => {
        setIssue(response.data);
      }
    );
    let newUsers = users.slice();
    newUsers.push({ id: row.id, name: row.name, lastName: row.lastName });
    setUsers(newUsers);
  };

  const onReturnToIssues = () => {
    history.push("/project/issues/" + issue.project.id);
  };

    const onCommentDelete = (row) =>{
	
	deleteRequest("/comment/delete", {id:row.id})
	    .then(response => {
		setIssue({
		    ...issue,
		    comments: issue.comments.filter(comment => comment.id !== row.id)
		    
		})
		if(response.data){
		    toast.success("Comment deleted successfully")
		}else{
		    toast.error("Error occured while deleting comment")
		}
	    })

    }

    const commentsColumns = React.useMemo(
	() => [
	    {
		accessor: "index",
		Headers: "#",
		Cell :({row: {index} }) => index + 1,
		disableSortBy: true,
	    },
	    {
		accessor: "comment",
		Header: "Comment",
	    },
	    {
		accessor: "",
		Header: "User",
		Cell:  ({row: {original}}) =>{
		    return original.userName + " " + original.userLastName;
		}
	    },
	    {
		accessor: "date",
		Header: "Posted on",
		Cell: ({row: {original}}) =>{
		    return original.creationDate.split("T")[0]
		}
	    },
	    {
		accessor: "controls",
		Headers: "Controls",
		Cell:({row: {original}}) =>{
		    if(original.userId === userId){
			return <button className="btn btn-outline-secondary" onClick={() => onCommentDelete(original)}>Delete</button>
		    }else{
			return "";
		    }
		}
	    }
	], [onCommentDelete]
    )

    const onChange = (event) =>{
	const value = event.target.value;
	setComment(value);
    }

    const onSubmit = (event) =>{
	event.preventDefault();
	if(comment.length > 5){
	    const requestBody = {
		comment,
		issueId: issue.id,
		userId: parseInt(localStorage.getItem('userId')),
	    }
	    post("/comment/new", requestBody)
		.then(response => {
		    setIssue({
			...issue,
			comments:[
			    ...issue.comments,
			    response.data,
			]	
		    });
		    toast.success("Comment created successfully")
		});
	}
    }
	
    
    return {
	issue,
	loading,
	onDelete,
	onToggleIssueClosedStatus,
	onReturnToIssues,
	isClosed,
	users,
	assignUser,
	unassignUser,
	commentsColumns,
	onChange,
	onSubmit,
	onCommentDelete,
    };
};

export default IssueDetailsLogic;
