<%-- 
    Document   : register.jsp
    Created on : Feb 12, 2016, 1:32:07 AM
    Author     : cb-vinothini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Space Portal</title>
        <link href="CSS/registerCSS.css" rel="stylesheet" type="text/css">
        <script src="JS/registerJS.js" type="text/javascript"></script>
        <script src="AJAX/registerAJAX.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="registerForm">
            <form action="register" method="post" name="form" onsubmit="return(validateForm())">
                <div id="header">
                    <h5>Register to</h5>
                    <h1>Phone Book</h1>
                </div>
                <div id="userInput">
                    <input type="text" id="userID" name="userName" placeholder="User Name" required onchange="checkEmail()">
                    <br>
                    <span>
                        <input type = "text" name = "first_name" placeholder="First Name" required>
                        <input type = "text" name = "last_name" placeholder="Last Name" required>
                    </span>
                    <br>
                    <span>
                        <input type = "password" name = "password" placeholder="Password" required>
                        <input type = "password" name = "confirm_password" placeholder="Confirm Password" required>
                    </span>
                </div>
                <div id="footer">
                    <input type="submit" value="Create an account"><br>
                    <a href="login.jsp">Have an account already?</a>
                </div>
            </form>
        </div>
    </body>
</html>
