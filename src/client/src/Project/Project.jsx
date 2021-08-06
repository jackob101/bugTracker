import React from "react";
import ProjectTable from "./Components/ProjectTable";
import axios from "axios";
import { useAuth0 } from "@auth0/auth0-react";
import NavBar from "../Components/Navbar/NavBar";
import Footer from "../Components/Footer";
import { useState } from "react";
import { useEffect } from "react";

const ProjectPage = () => {
  const [data, setData] = useState([]);
  const { getAccessTokenSilently } = useAuth0();

  useEffect(() => {
    (async () => {
      const serverUrl = process.env.REACT_APP_SERVER_URL;
      const token = await getAccessTokenSilently();
      const result = await axios.get(serverUrl + "/project/all", {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      setData(result.data);
    })();
  }, []);

  const columns = React.useMemo(() => [
    {
      accessor: "",
      Header: "#",
      Cell: ({ row: { index } }) => index + 1,
      disableSortBy: true,
    },
    {
      Header: "Project name",
      accessor: "name",
    },
    {
      Header: "Description",
      accessor: "description",
    },
    {
      accessor: "",
      Header: " ",
      Cell: ({ row: { original } }) => {
        return (
          <div className="d-flex justify-content-end">
            <button className="btn btn-outline-primary me-2">
              View issues
            </button>
            <button className="btn btn-outline-secondary">Edit project</button>
          </div>
        );
      },
      disableSortBy: true,
    },
  ]);

  return (
    <React.Fragment>
      <NavBar />
      <div className="height-full">
        <h1 className="text-center mt-5 mb-3">Projects</h1>
        <h4 className="text-center mb-3 text-secondary">
          {"Opened public projects : " + data.length}
        </h4>
        <ProjectTable columns={columns} data={data} />
      </div>
      <Footer />
    </React.Fragment>
  );
};

export default ProjectPage;
