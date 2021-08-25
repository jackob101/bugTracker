import axios from "axios";

const serverUrl = process.env.REACT_APP_SERVER_URL;
const instance = axios.create({ baseURL: serverUrl });
instance.defaults.headers.common["Content-Type"] = "application/json";

export default instance;
