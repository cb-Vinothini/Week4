<%-- 
    Document   : profile
    Created on : Feb 12, 2016, 9:16:37 AM
    Author     : cb-vinothini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Space Portal</title>
        <link href="CSS/profileCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="header">
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <div id="profile">
            <div>
                <h1>Welcome to the Space Portal</h1>
            </div>
            <br>
            <div id="content">
                <div>
                    <span class="title">NAME</span>
                    <span class="value"><%=user.firstName%> <%=user.lastName%></span>
                </div>
                <br>
                <div>
                    <span class="title">EMAIL</span>
                    <span class="value"><%=user.email%></span>
                </div>
                <br>
                <div>
                    <span class="title">ADDRESS</span>
                    <span class="value"><br><%=user.line1%><br><%=user.line2%><br><%=user.city%><br><%=user.state%><br><%=user.country%><br><%=user.zip%></span>
                </div>
            </div>
            <br>
            <div id="footer">
                <a href="editProfile.jsp">Edit Profile</a>
            </div>
        </div>
    </body>
</html>
