import { useAuth0 } from "@auth0/auth0-react";
import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import useRequestUtils from "Utils/RequestUtils";

const ProjectIssuesLogic = () => {
  const [project, setProject] = useState([]);
  const [loading, setLoading] = useState(true);
  const { id } = useParams();
  const { getAccessTokenSilently } = useAuth0();
  const { get } = useRequestUtils();

  useEffect(() => {
    (async () => {
      get("/issue/project?id=" + id).then((response) => {
        setProject(response.data);
        setLoading(false);
      });
    })();
  }, []);

  const transformString = (tekst) => {
    let transformed = tekst.toLowerCase();
    transformed =
      transformed.charAt(0).toUpperCase() + transformed.substring(1);
    transformed = transformed.replace("_", " ");
    return transformed;
  };

  const columns = React.useMemo(
    () => [
      {
        accessor: "",
        Header: "#",
        Cell: ({ row: { index } }) => index + 1,
        disableSortBy: true,
      },
      {
        Header: "Title",
        accessor: "title",
      },
      {
        Header: "Priority",
        accessor: "priority",
        Cell: ({ row: { original } }) => {
          return (
            <div className="text-left">
              {transformString(original.priority)}
            </div>
          );
        },
      },
      {
        accessor: "",
        Header: " ",
        Cell: ({ row: { original } }) => {
          return (
            <div className="d-flex justify-content-end">
              <Link
                to={{
                  pathname: "/issue/details/" + original.id,
                  aboutProps: {
                    issue: original,
                  },
                }}
                className="btn btn-outline-primary me-2"
              >
                View details
              </Link>
            </div>
          );
        },
        disableSortBy: true,
      },
    ],
    []
  );

  return { loading, project, columns };
};

export default ProjectIssuesLogic;
