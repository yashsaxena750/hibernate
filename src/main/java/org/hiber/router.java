package org.hiber;

import java.io.*;
import java.util.logging.Logger;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import org.hiber.entity.function_point;

@WebServlet(name = "routerServlet", value = "/router-servlet")
public class router extends HttpServlet {

    public void init() {
        //part of life-cycle method
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        function_point fp = new function_point();
        String action = request.getParameter("action");
        final Logger logger = Logger.getLogger(getClass().getName());
        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "login":
                    fp.login(request, response);
                    break;
                default:
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.println("<script>alert('unknown action')</script>");
                    } catch (Exception e) {
                        logger.info("Error"+e);
                    }
                    break;
            }

        }
        else{
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.println("<script>alert('something went wrong!');</script>");
                out.println("<script>setTimeout(function(){ window.location.href = '" + request.getContextPath() + "/index.jsp'; }, 1000);</script>");
            } catch (Exception e) {
                logger.info("Error"+e);
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        function_point fp = new function_point();
        String action = request.getParameter("action");
        final Logger logger = Logger.getLogger(getClass().getName());
        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "signup":
                    fp.signup(request, response);
                    break;
                case "getreq":
                    fp.getreq(request,response);
                    break;
                default:
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.println("<script>alert('unknown action')</script>");
                    } catch (Exception e) {
                        logger.info("Error"+e);
                    }
                    break;
            }

        }
        else{
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.println("<script>alert('something went wrong!');</script>");
                out.println("<script>setTimeout(function(){ window.location.href = '" + request.getContextPath() + "/index.jsp'; }, 1000);</script>");
            } catch (Exception e) {
                logger.info("Error"+e);
            }
        }
    }

    public void destroy() {
    }
}
