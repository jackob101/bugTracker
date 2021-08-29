import React from "react";
import { Link } from "react-router-dom";
import ProjectTable from "Components/PagedTable";
import Layout from "Components/Layout";
import Loading from "Components/Loading";
import ProjectIssuesLogic from "./ProjectIssuesLogic";

const ProjectIssues = () => {
  const { loading, columns, project } = ProjectIssuesLogic();

  return loading ? (
    <Loading />
  ) : (
    <Layout>
      <h1 className="text-center mt-5 mb-2">
        {"Project: " + project.projectName}
      </h1>
      <h4 className="text-center  mt-2 text-secondary">
        {"Open issues: " + project.issues.length}
      </h4>
      <div className="d-flex align-items-center justify content center flex-column">
        <ProjectTable columns={columns} data={project.issues}>
          <Link
            to={{
              pathname: "/issue/new/" + project.id,
              aboutProps: {
                projectId: project.id,
              },
            }}
            className="btn btn-outline-primary width-80 mt-0 mb-3"
          >
            Add new
          </Link>
        </ProjectTable>
      </div>
    </Layout>
  );
};

export default ProjectIssues;
