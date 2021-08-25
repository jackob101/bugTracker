import { useAuth0 } from "@auth0/auth0-react";
import axios from "axios";
import { useState } from "react";
import useRequestUtils from "Utils/RequestUtils";
import toast from "react-hot-toast";
import { useHistory } from "react-router-dom";

const UserInformationsLogic = () => {
  const { user } = useAuth0();
  const { getRequestHeaders } = useRequestUtils();
  const history = useHistory();

  const [userInfo, setUserInfo] = useState({
    email: user.email,
    name: "",
    lastName: "",
    age: 0,
  });

  const onChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setUserInfo({ ...userInfo, [name]: value });
  };

  const onIntegerChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    const parsed = parseInt(value);
    if (parsed) {
      setUserInfo({ ...userInfo, [name]: parsed });
    }
  };

  const onSubmit = async (event) => {
    event.preventDefault();
    const requestBody = {
      ...userInfo,
      nickname: user.nickname,
      emailVerified: user.email_verified,
      sub: user.sub,
    };
    const server = process.env.REACT_APP_SERVER_URL;
    axios
      .post(server + "/user/new", requestBody, await getRequestHeaders())
      .then((response) => {
        localStorage.setItem("userId", response.data.id);
        history.push("/");
        toast.success("Your account was created successfully");
      })
      .catch((error) =>
        toast.error("Unexpected error happened please refresh page")
      );
  };
  return { onChange, onSubmit, userInfo, onIntegerChange };
};

export default UserInformationsLogic;
