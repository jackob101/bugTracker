import React from "react";
import Layout from "Components/Layout";
import SectionHeader from "routes/Issue/Components/SectionHeader";
import TextFieldDesc from "routes/Issue/Components/TextFieldDesc";
import { Link } from "react-router-dom";
import Loading from "Components/Loading";
import IssueDetailsLogic from "routes/Issue/IssueDetails/IssueDetailsLogic";
import Pagedtable from "Components/PagedTable";
import CommentCard from "Components/CommentCard"

const IssueDetails = () => {
  const {
    issue,
    isClosed,
    loading,
    onDelete,
    onToggleIssueClosedStatus,
    onReturnToIssues,
    users,
    assignUser,
    unassignUser,
      commentsColumns,
      onChange,
      onSubmit,
  } = IssueDetailsLogic();

  const columns = React.useMemo(
    () => [
      {
        accessor: "name",
        Header: "Name",
      },
      {
        Header: "Last name",
        accessor: "lastName",
      },
    ],
    []
  );

    console.log(issue);

    let badge = "badge rounded-pill fs-6 me-2 "
    let icon = isClosed ? "/checkmark.svg" : "/square.svg";
    let text = isClosed ? "Closed" : "Open";
    badge += isClosed ? "bg-danger" : "bg-success";

  return loading ? (
    <Loading />
  ) : (
    <Layout>
	{/*	
      <button onClick={onReturnToIssues}>Go back</button>
      <div className="d-flex flex-row flex-wrap">
        {/* Ticked details /}
        <div className="f-col flex-column d-flex m-3">
          <SectionHeader text="Ticked Details">
            <div className="d-flex flex-row justify-content-center">
              <button
                className="btn btn-outline-primary mx-2"
                onClick={() => onToggleIssueClosedStatus(issue)}
              >
                {isClosed ? "Reopen Issue" : "Close issue"}
              </button>
              <Link
                className="text-center btn btn-outline-secondary mx-2"
                to={{
                  pathname: "/issue/edit",
                  aboutProps: {
                    issue,
                  },
                }}
              >
                Edit ticket
              </Link>
              <button
                className="btn btn-outline-danger mx-2"
                onClick={() => onDelete(issue)}
                disabled={isClosed}
              >
                Delete issue
              </button>
            </div>
          </SectionHeader>
          <div className="grid">
            <TextFieldDesc title="Project" text={issue.project.name} />
            <TextFieldDesc title="Title" text={issue.title} />
            <TextFieldDesc title="Description" text={issue.description} />
            <TextFieldDesc title="Priority" text={issue.priority} />
            <TextFieldDesc title="Created" text={issue.createdDate} />
            <TextFieldDesc
              title="Opened By"
              text={issue.createdBy.name + " " + issue.createdBy.lastName}
            />
            <TextFieldDesc title="Closed" text={isClosed ? "Yes" : "No"} />
          </div>{" "}
        </div>
        {/* Comments /}
        <div className="d-flex flex-column flex-grow-1 m-3 f-col m-3">
          <SectionHeader text="Comments"></SectionHeader>
            <Pagedtable columns={commentsColumns} data={issue.comments} defaultPageSize="10"/>
	    <form className="d-flex flex-column width-80 my-3 mx-auto" onSubmit={onSubmit}>
		<textarea className="flex-grow-1" id="comment" rows="5" name="comment" onChange={onChange} />

		<button type="submit" className="btn btn-outline-primary">Submit</button>
	    </form>
        </div>
      </div>
      <div className="d-flex flex-wrap flex-row">
        <div className="d-flex flex-column flex-grow-1 m-3 f-col m-3">
          <SectionHeader text="Assigned Users" />
          <Pagedtable
            className="flex-grow-1"
            columns={columns}
            data={issue.users}
            onRowClick={unassignUser}
          ></Pagedtable>

        </div>
        <div className="d-flex flex-column flex-grow-1 m-3 f-col m-3">
          <SectionHeader text="All users" />

          <Pagedtable
            columns={columns}
            data={users}
            onRowClick={assignUser}
          ></Pagedtable>
        </div>
      </div>*/}
	<div>
	    <button onClick={onReturnToIssues} className="my-4 mx-4 btn-link btn fs-3 text-decoration-none" >{issue.project.name}</button>
	</div>
	<div style={{maxWidth:"1280px",
		     paddingLeft: "32px",
		     paddingRight: "32px"}} className="mx-auto d-flex flex-column">
	    <div>
		<h2>{issue.title}</h2>
		<div className="d-flex flex-row align-items-center">

		    <div className={badge}>
			<div className=" d-flex flex-row">
			     <img style={{width: "16px"}}  src={process.env.PUBLIC_URL + icon} alt=""/>
			     <span className="mx-1 d-flex flex-row align-items-center">{text}</span>
			 </div>
		    </div>

		    <div className="d-flex align-items-center">
			<div style={{fontWeight: "bold"}}>{issue.createdBy.name + " " + issue.createdBy.lastName}</div>
			<div>&nbsp;</div>
			<div>{"opened this issue on " + issue.createdDate}</div>
			<div className="mx-2">●</div>
			<div>{issue.comments.length + " comments"}</div>
		    </div>
		</div>
	    </div> 
	    <hr/>
	    <div className="d-flex flex-row">
		<div className="d-flex flex-column" style={{width:"74%"}}>

		    <CommentCard name={issue.createdBy.name}
				 lastName={issue.createdBy.lastName}
				 body={issue.description}
				 date={issue.createdDate}/>

		    {issue.comments.map(comment => <CommentCard name={comment.userName}
								lastName={comment.userLastName}
								body={comment.comment}
								date={comment.creationDate.split("T")[0]}/>)}
		</div>

		<div className="d-flex flex-column">

		</div>

	    </div>
	</div>
    </Layout>
  );
};

export default IssueDetails;
