package org.hiber;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;


@WebServlet(name = "logoutServlet", value = "/logout-servlet", initParams = {@WebInitParam(name = "HttpSession", value = "false")})
public class logout extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        response.setContentType("text/html");

        if (session != null) {
            session.removeAttribute("username");
            session.invalidate();
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getValue());
                if (cookie.getName().equals("JSESSIONID")) {
                    Cookie invalidatedCookie = new Cookie("JSESSIONID", "");
                    invalidatedCookie.setMaxAge(0);
                    invalidatedCookie.setPath("/"); // Set the same path as the original cookie if needed
                    invalidatedCookie.setSecure(cookie.getSecure()); // Set the same secure flag as the original cookie if needed
                    invalidatedCookie.setHttpOnly(cookie.isHttpOnly()); // Set the same HttpOnly flag as the original cookie if needed

                    // Add the invalidated cookie to the response
                    response.addCookie(invalidatedCookie);
                    break;
                }
            }
        }


        request.setAttribute("logdata", "you have been logged out successfully!");
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    public void destroy() {
    }
}