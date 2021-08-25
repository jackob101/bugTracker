import { useState } from "react";
import { useHistory } from "react-router";
import useRequestUtils from "Utils/RequestUtils";
import toast from "react-hot-toast";

const FormLogic = (props) => {
  const [issue, setIssue] = useState(props.issue);

  const { post } = useRequestUtils();
  const history = useHistory();

  const handleChange = (event) => {
    const target = event.target;
    const value = target.type === "checkbox" ? target.checked : target.value;
    const name = target.name;
    setIssue({ ...issue, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    post("/issue/update", issue)
      .then((response) => {
        history.push("/issue/details/" + issue.id);
        toast.success("Successfully updated issue");
      })
      .catch((error) => {
        toast.error("Could not update issue. Please check input values");
      });
  };

  const onCancel = async () => {
    history.push("/issue/details/" + issue.id);
  };

  return { issue, handleChange, handleSubmit, onCancel };
};

export default FormLogic;
