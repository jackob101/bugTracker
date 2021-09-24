import React from "react";
import Layout from "Components/Layout";
import Loading from "Components/Loading";
import IssueDetailsLogic from "routes/Issue/IssueDetails/IssueDetailsLogic";
import IssueHeader from "./Components/IssueHeader.jsx";
import IssueComments from "./Components/IssueComments.jsx";
import DetailsPanel from "./Components/DetailsPanel.jsx";
import IssueDescription from "./Components/IssueDescription";

const IssueDetails = () => {
  const {
    issue,
    isClosed,
    loading,
    onReturnToIssues,
    detailsContent,
    onCommentSubmit,
    setComments,
    comments,
  } = IssueDetailsLogic();

  return loading ? (
    <Loading />
  ) : (
    <Layout>
      <div>
        <button
          onClick={onReturnToIssues}
          className="my-4 mx-4 btn-link btn fs-3 text-decoration-none"
        >
          {issue.project.name}
        </button>
      </div>
      <div
        style={{
          maxWidth: "1280px",
          paddingLeft: "32px",
          paddingRight: "32px",
        }}
        className="mx-auto d-flex flex-column"
      >
        <IssueHeader issue={issue} isClosed={isClosed} />
        <hr />
        <div className="d-flex flex-row">
          <div style={{ width: "74%" }}>
            <IssueDescription issue={issue} />
            <IssueComments
              comments={comments}
              setComments={setComments}
              onCommentSubmit={onCommentSubmit}
            />
          </div>
          <DetailsPanel issue={issue} content={detailsContent()} />
        </div>
      </div>
    </Layout>
  );
};

export default IssueDetails;
