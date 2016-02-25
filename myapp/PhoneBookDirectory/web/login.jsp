<%-- 
    Document   : login
    Created on : Feb 12, 2016, 5:11:04 PM
    Author     : cb-vinothini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phone Book</title>
        <link href="CSS/loginCSS.css" rel="stylesheet" type="text/css">
        <script src="AJAX/registerAJAX.js" type="text/javascript"></script>        
    </head>
    <body>
        <div id="loginForm">
            <form method="post" action="login">
                <div id="header">
                    <h1>Phone Directory</h1>
                    <h4>Login</h4>
                </div>
                <div id="userInput">
                    <input type="text" id="userID" name="userName" placeholder="User Name" required onchange="loginCheck()">
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
