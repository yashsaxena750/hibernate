<%@ page import="org.apache.commons.text.StringEscapeUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.restartme.entity.logindata" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Basic -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <!-- Site Metas -->
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Hirevac</title>


    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

    <!-- fonts style -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700&display=swap" rel="stylesheet">

    <!-- font awesome style -->
    <link href="css/font-awesome.min.css" rel="stylesheet" />
    <!-- nice select -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css" integrity="sha256-mLBIhmBvigTFWPSCtvdu6a76T+3Xyt+K571hupeFLg4=" crossorigin="anonymous" />

    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet" />
    <!-- responsive style -->
    <link href="css/responsive.css" rel="stylesheet" />

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <style>
        .signup-box {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .profile-pic {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 20px;
        }
        .btn {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
        .btn:hover {
            background-color: #0056b3;
        }

    </style>

</head>

<body class="sub_page">

<div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container ">
                <a class="navbar-brand" href="index.jsp">
            <span>
              Hirevac
            </span>
                </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class=""> </span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav  ml-auto">
                        <li class="nav-item ">
                            <a class="nav-link" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="about.jsp"> About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="job.jsp">Jobs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span>
                    User Info
                  </span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="signup.jsp">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span>
                    Sign Up
                  </span>
                            </a>
                        </li>
                        <form class="form-inline">
                            <button class="btn   nav_search-btn" type="submit">
                                <i class="fa fa-search" aria-hidden="true"></i>
                            </button>
                        </form>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <!-- end header section -->
</div>
<br>

<div class="info_form signup-box">

    <div class="container">
        <img src="https://i.pinimg.com/474x/4b/71/f8/4b71f8137985eaa992d17a315997791e.jpg" alt="Profile Picture" class="profile-pic">
        <div>
            <h2>User Information</h2>
            <%
                List<logindata> userinfo = (List<logindata>) request.getAttribute("udata");
                for (logindata _edata : userinfo) {
            %>
            <form action="<%= request.getContextPath() %>/my-servlet" method="post">
                Id <input type="text" name="uid" value="<%= _edata.getUid() %>">input<br><br>
                Email<input type="email" name="email" value="<%= StringEscapeUtils.escapeHtml4(_edata.getUemail()) %>"><br><br>
                Username<input type="text" name="username" value="<%= StringEscapeUtils.escapeHtml4(_edata.getUname()) %>"><br><br>
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Update" class="btn">
            </form>
            <%
                }
            %>

        </div>
    </div>

</div>

<br><br>


<!-- info section -->
<section class="info_section ">
    <div class="container">
        <div class="row">
            <div class="col-md-2 info_links">
                <h4>
                    Menu
                </h4>
                <ul>
                    <li>
                        <a href="index.jsp">
                            Home
                        </a>
                    </li>
                    <li>
                        <a href="about.jsp">
                            About
                        </a>
                    </li>
                    <li>
                        <a href="job.jsp">
                            Jobs
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-3">
                <h4>
                    Jobs
                </h4>
                <p>
                    Established fact that a reader will be distracted by the readable content of a page when looking at its
                    layout. The point of using Lorem Ipsum
                </p>
            </div>
            <div class="col-md-3">
                <div class="info_social">
                    <h4>
                        Social Link
                    </h4>
                    <div class="social_container">
                        <div>
                            <a href="#">
                                <i class="fa fa-facebook" aria-hidden="true"></i>
                                not-working
                            </a>
                        </div>
                        <div>
                            <a href="">
                                <i class="fa fa-twitter" aria-hidden="true"></i>
                                not-working
                            </a>
                        </div>
                        <div>
                            <a href="">
                                <i class="fa fa-linkedin" aria-hidden="true"></i>
                                not-working
                            </a>
                        </div>
                        <div>
                            <a href="">
                                <i class="fa fa-instagram" aria-hidden="true"></i>
                                not-working
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="info_form">
                    <h4>
                        Newsletter
                    </h4>
                    <form action="">
                        <label>
                            Email Address
                            <input type="text" placeholder="Enter Your Email" />
                        </label>
                        <button type="submit">
                            Subscribe
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end info_section -->

<!-- footer section -->
<footer class="footer_section">
    <div class="container">
        <p>
            &copy; <span id="displayYear"></span> All Rights Reserved By
            <a href="https://html.design/">Free Html Templates</a>
        </p>
    </div>
</footer>
<!-- footer section -->

<!-- jQery -->
<script src="js/jquery-3.4.1.min.js"></script>
<!-- bootstrap js -->
<script src="js/bootstrap.js"></script>
<!-- nice select -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js" integrity="sha256-Zr3vByTlMGQhvMfgkQ5BtWRSKBGa2QlspKYJnkjZTmo=" crossorigin="anonymous"></script>
<!-- custom js -->
<script src="js/custom.js"></script>


</body>

</html>