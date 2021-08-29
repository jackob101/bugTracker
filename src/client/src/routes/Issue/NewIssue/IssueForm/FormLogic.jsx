import { useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import useRequestUtils from "Utils/RequestUtils";

const IssueFormLogic = () => {
  const { id } = useParams();
  const [issue, setIssue] = useState({
    title: "",
    description: "",
    projectId: id,
    priority: "URGENT",
    createdBy: localStorage.getItem("userId"),
  });
  const history = useHistory();
  const { post } = useRequestUtils();

  const onChange = (event) => {
    const target = event.target;
    const value = target.type === "checkbox" ? target.checked : target.value;
    const name = target.name;
    setIssue({ ...issue, [name]: value });
  };

  const onSubmit = (event) => {
    event.preventDefault();
    console.log(issue);
    post("/issue/new", issue).then((response) =>
      history.push("/project/issues/" + id)
    );
  };

  const onCancel = (event) => {
    history.push("/project/issues/" + id);
  };

  return { onChange, onSubmit, onCancel, issue };
};

export default IssueFormLogic;
