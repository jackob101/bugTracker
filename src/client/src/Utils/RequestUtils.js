import { useAuth0 } from "@auth0/auth0-react";
import axios from "Data/Axios";

const useRequestUtils = () => {
  const { getAccessTokenSilently } = useAuth0();

  async function getRequestHeaders(params) {
    const token = await getAccessTokenSilently();

    let headers = {
      headers: {
        Authorization: "Bearer " + token,
      },
      params,
    };

    return headers;
  }

  async function post(url, body, params) {
    return axios.post(url, body, await getRequestHeaders(params));
  }
  async function postDefaultHeaders(url, body) {
    return axios.post(url, body, await getRequestHeaders());
  }

  async function get(url, params) {
    return axios.get(url, await getRequestHeaders(params));
  }

  async function deleteRequest(url, params) {
    return axios.delete(url, await getRequestHeaders(params));
  }

  return { getRequestHeaders, post, postDefaultHeaders, get, deleteRequest };
};

export default useRequestUtils;
