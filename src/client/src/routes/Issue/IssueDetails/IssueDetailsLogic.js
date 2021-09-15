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


    const [issue, setIssue] = useState({});
    const [users, setUsers] = useState([]);
    const [isClosed, setIsClosed] = useState(false);
    const [loading, setLoading] = useState(true);

    const getIssue = () => {
	return get("/issue/" + id);
    };
    const getUsers = () => {
	return get("/user/notAssigned", { issueId: id });
    };

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
    }, []);

    const onIssueDelete = async (issue) => {
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

    const assignUser = async (id) => {
	console.log(id);
	post("/issue/assign", {}, { issueId: issue.id, userId: id }).then(
	    (response) => {
		setIssue(response.data);
	    }
	);
	const newUsers = users.filter((user) => user.id != id);
	setUsers(newUsers);
    };

    const unassignUser = async (user) => {
	console.log(user);
	post("/issue/unassign", {}, { issueId: issue.id, userId: user.id }).then(
	    (response) => {
		setIssue(response.data);
	    }
	);
	let newUsers = users.slice();
	newUsers.push({ id: user.id, name: user.name, lastName: user.lastName });
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

    const onCommentSubmit = (event, comment) =>{
	event.preventDefault();
	if(comment.length > 5){
	    const requestBody = {
		comment,
		issueId: issue.id,
		userId: parseInt(localStorage.getItem('userId')),
	    }
	    post("/comment/new", requestBody)
		.then(response => {

		    console.log(response);
		    console.log(issue);
		    let comment = {
			id: response.data.id,
			comment: response.data.comment,
			creationDate: response.data.creationDate,
			userName: response.data.user.name,
			userLastName: response.data.user.lastName,
			userId: response.data.user.id,
		    }
		    setIssue({
			...issue,
			comments:[
			    ...issue.comments,
			    comment,
			]	
		    });
		    toast.success("Comment created successfully")
		});
	}
    }

    const priorities = [["Urgent", "URGENT"],  ["Important", "IMPORTANT"], ["Not important", "NOT_IMPORTANT"]];

    const onChangePriority = (event) => {
	const target = event.target;
	const value = target.value;
	post("/issue/edit/priority", {} , {issueId: issue.id, priority:value})
	    .then(response => setIssue({...issue, priority: value}))
    }

    const formatPriority = () => {
	let formated = issue.priority.toLowerCase();
	formated = formated.charAt(0).toUpperCase() + formated.substring(1);
	return formated.replace("_" , " ");
    }
    
    const detailsContent = () => {
	return [
	    {
		title: "Assigned",
		body: issue.users.map((user, index) => <div className="d-flex flex-row" key={index}><span className="flex-grow-1 d-flex align-items-center">{user.name + " " + user.lastName}</span><button onClick={() => unassignUser(user)} className="btn">-</button></div>),
		option: {
		    title: "Assign people",
		    body: [
			<input className="option-search-bar" placeholder="Search"/>,
			users.map((user, index) => <button key={index} onClick={() => assignUser(user.id)} className="option-entry">{user.name + " " + user.lastName}</button>)    
		    ]
		}
	    },
	    {
		title: "Priority",
		body:[
		    <p>{formatPriority()}</p>
		],
		option: {
		    title: "Change priority",
		    body:[
			priorities.map((entry,index) => <div key={index} className="d-flex"><button value={entry[1]} onClick={onChangePriority} className="option-entry">{entry[0]}</button>{entry[1] === issue.priority ? <img alt="" width="15px" src={process.env.PUBLIC_URL + "/check.svg"}/> : ""}</div>)
		    ] 
		}
	    },
	]
    }
    
    
    return {
	issue,
	loading,
	onIssueDelete,
	onToggleIssueClosedStatus,
	onReturnToIssues,
	isClosed,
	users,
	assignUser,
	unassignUser,
	onCommentSubmit,
	onCommentDelete,
	detailsContent,
	setIssue,
    };
};

export default IssueDetailsLogic;
