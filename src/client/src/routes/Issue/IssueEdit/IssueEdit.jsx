import React from "react";
import Layout from "Components/Layout";
import IssueForm from "routes/Issue/IssueEdit/Form/Form";

const IssueEdit = (props) => {
  const issue = props.location.aboutProps.issue;
  return (
    <Layout>
      <h1 className="text-center my-5">Edit issue</h1>
      <IssueForm issue={issue} />
    </Layout>
  );
};

export default IssueEdit;
