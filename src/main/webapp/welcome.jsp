<%-- Created by IntelliJ IDEA. User: yashs Date: 27-04-2024 Time: 18:45 To change this template use File | Settings | File Templates. --%>
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
    <li><a href="#news">News</a></li>
    <li><a href="#contact">Contact</a></li>
    <form id="logout-form" action="<%= request.getContextPath() %>/logout-servlet" method="get">
        <li><button type="submit" class="logout-button" name="action" value="logout">Logout</button></li>
    </form>
</ul>

<h2>
    <%
        String username = (String) session.getAttribute("udata");
        String respdata = (String) request.getAttribute("respdata");

        if (username != null) {
            out.println("<p>" + "hello " + username + "</p>");
            out.println("<form action='" + request.getContextPath() + "/router-servlet' method='post'>");
            out.println("<input type='text' name='url'>");
            out.println("<input type='submit' value='Submit'>");
            out.println("<input type='hidden' name='action' value='getreq'>");
            out.println("</form>");
            if (respdata != null) {
                out.println("<p>" + respdata + "</p>");
            }
        }

        else {
            request.setAttribute("udata", "please login to continue");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    %>
</h2>
</body>
</html>
