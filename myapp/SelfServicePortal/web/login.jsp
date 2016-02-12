<%-- 
    Document   : login
    Created on : Feb 12, 2016, 12:24:41 AM
    Author     : cb-vinothini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Space Portal</title>
        <link href="CSS/loginCSS.css" rel="stylesheet" type="text/css">
        <script src="AJAX/registerAJAX.js" type="text/javascript"></script>        
    </head>
    <body>
        <div id="loginForm">
            <form method="post" action="login">
                <div id="header">
                    <h1>Space Portal</h1>
                    <h4>Login</h4>
                </div>
                <div id="userInput">
                    <input type="text" name="email" placeholder="Email" id="email" onchange="loginCheck()" required><br>
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <div id="submition">
                    <span>
                        <input type="submit" value="LOGIN">
                        <a href="#">Don't Remember your password?</a>
                    </span>
                </div>
                <div id="register">
                    <a href="register.jsp">New around here?</a>
                </div>
            </form>
        </div>
    </body>
</html>
