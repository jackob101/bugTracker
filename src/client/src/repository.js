import Cookies from "js-cookie";

export function isAuthenticated() {
  return (
    Cookies.get("Authorization") &&
    new Date(Cookies.get("Token-Expiration")) > Date.now()
  );
}

export function getToken() {
  if (isAuthenticated) return Cookies.get("Authorization");
}

export function getHeaders() {
  return {
    headers: {
      Authorization: getToken(),
    },
  };
}
