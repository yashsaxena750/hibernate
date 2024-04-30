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

<style>

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



    <h2>

        <%

            String username = (String)session.getAttribute("udata");
            String respdata = (String)request.getAttribute("respdata");
            if(username!=null )
            {
                out.println("<p>"+"hello "+username+"</p>");
                out.println("<form action='" + request.getContextPath() + "/router-servlet' method='post'>");
                out.println("<input type='text' name='url'>");
                out.println("<input type='submit' value='Submit'>");
                out.println("<input type='hidden' name='action' value='getreq'>");
                out.println("</form>");
                if(respdata!=null) {
                    out.println("<p>" + respdata + "</p>");
                }
                String actionUrl = request.getContextPath() + "/logout-servlet";
                out.println("<form action='" + actionUrl + "' method='get'>");
                out.println("<input type='submit' class='logout-button' name='action' value='logout'></button>");
                out.println("</form>");
            }
            else {

                request.setAttribute("udata", "please login to continue");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

        %>
    </h2>







</body>
</html>
