import React from "react";
import PagedTable from "Components/PagedTable";
import Layout from "Components/Layout";
import ProjectListLogic from "./ProjectListLogic";

const ProjectPage = () => {
  const { data, columns } = ProjectListLogic();

  return (
    <Layout>
      <h1 className="text-center mt-5 mb-3">Projects</h1>
      <h4 className="text-center mb-3 text-secondary">
        {"Opened public projects : " + data.length}
      </h4>
	<PagedTable columns={columns} data={data} showPageSize={true}/>
    </Layout>
  );
};

export default ProjectPage;
