package org.hiber;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "logoutServlet", value = "/logout-servlet", initParams = {@WebInitParam(name = "HttpSession", value = "false")})
public class logout extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Add cache-control headers
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        String action = request.getParameter("action");
        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "logout":
                    logout(request, response);
                    break;
            }
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");

        if (session != null) {
            session.invalidate();
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    Cookie invalidatedCookie = new Cookie("JSESSIONID", "");
                    invalidatedCookie.setMaxAge(0);
                    invalidatedCookie.setPath("/"); // Set the same path as the original cookie if needed
                    response.addCookie(invalidatedCookie);
                    break;
                }
            }
        }

        request.setAttribute("logdata", "you have been logged out successfully!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
