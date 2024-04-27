<%--
  Created by IntelliJ IDEA.
  User: yashs
  Date: 27-04-2024
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>


<form action="<%= request.getContextPath()%>/logout-servlet" method="get">

    <h2>

        <%

            String username = (String)request.getAttribute("udata");
            if(username!=null)
            {
                out.println("<p>"+"hello "+username+"</p>");
            }
            else{

                request.setAttribute("udata", "please login to continue");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

        %>
    </h2>
    <h3>Welcome to GeeksforGeeks..</h3>

    <br> <input type="submit" value="Logout" />
</form>

</body>
</html>
