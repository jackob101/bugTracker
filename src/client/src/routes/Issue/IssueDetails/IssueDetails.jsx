import React from "react";
import Layout from "Components/Layout";
import SectionHeader from "routes/Issue/Components/SectionHeader";
import TextFieldDesc from "routes/Issue/Components/TextFieldDesc";
import { Link } from "react-router-dom";
import Loading from "Components/Loading";
import IssueDetailsLogic from "routes/Issue/IssueDetails/IssueDetailsLogic";
import Pagedtable from "Components/PagedTable";

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

  return loading ? (
    <Loading />
  ) : (
    <Layout>
      <button onClick={onReturnToIssues}>Go back</button>
      <div className="d-flex flex-row flex-wrap">
        {/* Ticked details */}
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
        {/* Comments */}
        <div className="d-flex flex-column flex-grow-1 m-3 f-col m-3">
          <SectionHeader text="Comments"></SectionHeader>
          <div
            className="flex-grow-1 bg-dark"
            style={{
              height: 300,
            }}
          ></div>
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
      </div>
    </Layout>
  );
};

export default IssueDetails;
