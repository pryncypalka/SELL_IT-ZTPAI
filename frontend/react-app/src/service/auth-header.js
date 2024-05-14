export default function authHeader() {
  const user = JSON.parse(localStorage.getItem("user"));

  if (user && useruser.accessToken) {
    return { Authorization: "Bearer" + user.accessToken };
  } else {
    return {};
  }
}
