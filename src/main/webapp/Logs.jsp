<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
  response.setHeader("Pragma", "no-cache"); // HTTP 1.0
  response.setHeader("Expires", "0"); // Proxies

  if (session == null || session.getAttribute("udata") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<html>
<head>
  <title>Home Page</title>
</head>

<style>
  body {
    font-size: 28px;
  }
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
    position: -webkit-sticky; /* Safari */
    position: sticky;
    top: 0;
  }
  li {
    float: left;
  }
  li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
  }
  li a:hover {
    background-color: #111;
  }
  .active {
    background-color: #4CAF50;
  }
  .logout-button {
    display: inline-block;
    padding: 10px 20px;
    background-color: #f00; /* Change this to your desired button color */
    color: #fff;
    text-decoration: none;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  .logout-button:hover {
    background-color: #d00; /* Change this to your desired button color on hover */
  }
</style>

<body>
<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="Logs.jsp">Logs.jsp</a></li>
  <li><a href="#users">Users</a></li>
  <form id="logout-form" action="<%= request.getContextPath() %>/logout-servlet" method="get">
    <li><button type="submit" class="logout-button" name="action" value="logout">Logout</button></li>
  </form>
</ul>

<h2>
  <%
    String username = (String) session.getAttribute("udata");
    if (username != null && username.equals("admin")) {
      out.println("<p>" + "hello " + "Logs" + "</p>");

    }

    else {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized");
    }
  %>
</h2>
</body>
</html>