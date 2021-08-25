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
      console.log("calling API");
      get("/issue/project?id=" + id).then((response) => {
        setProject(response.data);
        setLoading(false);
      });
    })();
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
        Header: "Description",
        accessor: "description",
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
