package org.hiber.entity;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.text.StringEscapeUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.out;

public class function_point {

    private static String generateSalt(int length) {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder salt = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            salt.append(saltChars.charAt(random.nextInt(saltChars.length())));
        }

        return salt.toString();
    }

    public static String hashPassword(String password, String salt) {
        try {
            byte[] saltBytes = salt.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(saltBytes);
            md.update(password.getBytes());
            byte[] hashedBytes = md.digest();
            byte[] combinedBytes = new byte[saltBytes.length + hashedBytes.length];
            System.arraycopy(saltBytes, 0, combinedBytes, 0, saltBytes.length);
            System.arraycopy(hashedBytes, 0, combinedBytes, saltBytes.length, hashedBytes.length);
            String hashedPassword = Base64.getEncoder().encodeToString(combinedBytes);
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    Users us = new Users();
    private static final SessionFactory sessionFactory;

    static {
        // Initialize SessionFactory once during application startup
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Users.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = Logger.getLogger(getClass().getName());
        try {
            String username = StringEscapeUtils.escapeHtml4(request.getParameter("signupName"));
            String firstname = StringEscapeUtils.escapeHtml4(request.getParameter("firstname"));
            String lastname = StringEscapeUtils.escapeHtml4(request.getParameter("lastname"));
            String useremail = StringEscapeUtils.escapeHtml4(request.getParameter("signupEmail"));
            String userpass = StringEscapeUtils.escapeHtml4(request.getParameter("signupPassword"));
            String salt = generateSalt(5);
            String hashedPassword = hashPassword(userpass,salt);
            String usercnpass = StringEscapeUtils.escapeHtml4(request.getParameter("confirmPassword"));

            if (usercnpass.equals(userpass)) {
                try (Session session = sessionFactory.getCurrentSession()) {

                    session.beginTransaction();
                    Users user = new Users(username, hashedPassword, firstname, lastname, useremail, salt);
                    session.save(user);
                    session.getTransaction().commit();
                    logger.info("Row Added!");
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('Registration done!');</script>");
                    out.println("<script>setTimeout(function(){ window.location.href = '" + request.getContextPath() + "/index.jsp'; }, 1000);</script>");
                }
            } else {
                logger.warning("password doesn't match!");
                request.setAttribute("udata", "password doesn't match");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }

        } catch (Exception e) {
            logger.info("Error" + e);
            request.setAttribute("udata", "user details already exists!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Logger logger = Logger.getLogger(getClass().getName());
        boolean alreadyProcessed = true; // Flag to track whether the request has already been processed

        try {
            String useremail = request.getParameter("email");
            String userpass = request.getParameter("Password");

            if (useremail != null && userpass != null && !useremail.isEmpty() && !userpass.isEmpty()) {
                try (Session session = sessionFactory.getCurrentSession()) {
                    session.beginTransaction();
                    Users user = (Users) session.createQuery("from users where Email = :email")
                            .setParameter("email", useremail)
                            .uniqueResult();

                    if (user == null) {
                        logger.warning("No user found for the provided email: " + useremail);
                        request.setAttribute("udata", "Wrong Credentials");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        String saltString = user.getSalt();
                        String storedpasswd = user.getPassword();
                        String inputPasswordHash = hashPassword(userpass, saltString);

                        if (inputPasswordHash.equals(storedpasswd) ) {
                            String username = user.getUsername();
                            HttpSession sessionlog = request.getSession();
                            sessionlog.setMaxInactiveInterval(60);
                            sessionlog.setAttribute("udata", username);
                            if(username.equals("admin"))
                            {
                                response.sendRedirect("admin-control.jsp");
                            }

                            else {
                                response.sendRedirect("welcome.jsp");
                            }

                        } else {
                            logger.warning("Incorrect password for user: " + useremail);
                            request.setAttribute("udata", "Wrong Credentials");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                    }
                }
            } else {
                logger.warning("Email or password is empty!");
                request.setAttribute("udata", "Email or password is empty");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.severe("An error occurred during login: " + e.getMessage());
            request.setAttribute("udata", "An error occurred during login");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }

    }

    public static String extractDomain(String url) {
        String domainPattern = "^(?:https?://)?(?:www\\.)?([^:/\\s]+)";
        Pattern pattern = Pattern.compile(domainPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private boolean check_url(String url)
    {
        List<String> allowedDomains;
        allowedDomains = new ArrayList<>();
        allowedDomains.add("bxss.me");
        return allowedDomains.contains(url);

    }

    public void getreq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Logger logger = Logger.getLogger(getClass().getName());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String urlParam = request.getParameter("url");
        String murl = "https://bxss.me" + urlParam;


        if (urlParam == null || urlParam.isEmpty()) {
            out.println("URL parameter is missing.");
            return;
        }

        try {
            String domain = extractDomain(murl);
            if(!check_url(domain))
            {
                out.println("Invalid URL.");
                return;
            }
            URI uri = new URI(murl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            request.setAttribute("respdata", StringEscapeUtils.escapeHtml4(responseContent.toString()));
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
            connection.disconnect();
        }catch (Exception e) {
            request.setAttribute("respdata", "something went wrong");
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }

    }


        public static String extractValue(String data, String key) {
        int startIndex = data.indexOf(key + "=") + key.length() + 1;
        int endIndex = data.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = data.indexOf("]", startIndex);
        }
        return data.substring(startIndex, endIndex);
    }




    }
