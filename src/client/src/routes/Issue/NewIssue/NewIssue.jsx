import React from "react";
import Layout from "Components/Layout";
import IssueForm from "./IssueForm/Form";

const NewIssue = () => {
  return (
    <Layout>
      <h1 className="text-center my-3">Create new issue</h1>
      <IssueForm></IssueForm>
    </Layout>
  );
};

export default NewIssue;
