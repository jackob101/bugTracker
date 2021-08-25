import React from "react";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import useRequestUtils from "Utils/RequestUtils";

const ProjectListLogic = () => {
  const [data, setData] = useState([]);
  const { get } = useRequestUtils();

  useEffect(() => {
    async function fetchData() {
      get("/project/all").then((result) => {
        setData(result.data);
      });
    }

    fetchData();
  }, []);

  const columns = React.useMemo(
    () => [
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
          return getControllButton(original);
        },
        disableSortBy: true,
      },
    ],
    []
  );

  const getControllButton = (original) => {
    return (
      <div className="d-flex justify-content-end">
        <Link
          to={{
            pathname: "/project/issues/" + original.id,
            aboutProps: {
              projectName: original.name,
              projectId: original.id,
            },
          }}
          className="btn btn-outline-primary me-2"
        >
          View issues
        </Link>
        <button className="btn btn-outline-secondary">Edit project</button>
      </div>
    );
  };

  return { data, columns };
};

export default ProjectListLogic;
