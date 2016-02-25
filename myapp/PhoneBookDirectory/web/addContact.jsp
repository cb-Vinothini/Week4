<%-- 
    Document   : addContact
    Created on : Feb 17, 2016, 1:23:45 PM
    Author     : cb-vinothini
--%>

<%@page import="java.util.List"%>
<%@page import="model.UserDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phone Directory</title>
        <link href="CSS/addCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="header">
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <div id="addForm">
            <form action="add" method="post" name="form">
                <div id="header">
                    <h1>Add Contacts</h1>
                </div>
                <div id="userInput">
                    <span>
                        <input type = "text" name = "first_name" placeholder="First Name" required>
                        <input type = "text" name = "last_name" placeholder="Last Name" required>
                    </span>
                    <br>
                    <span>
                        <input type = "text" name = "mobile" placeholder="Mobile No" required>
                        <input type = "text" name = "home" placeholder="Home No" required>
                        <input type = "text" name = "work" placeholder="Work No" required>
                    </span>
                    <br>
                    <span>
                        <input type = "text" name = "line1" placeholder="Address Line 1" required>
                        <input type = "text" name = "line2" placeholder="Address Line 2" required>
                        <br>
                        <input type = "text" name = "city" placeholder="City" required>
                        <select name="state_id">
                            <option value="1" selected>state</option>
                            <option value="2">Tamil Nadu</option>
                            <option value="3">Kerala</option>
                            <option value="4">Karanataka</option>
                        </select>
                        <br>
                        <input type = "text" name = "zip" placeholder="Zip" required>
                        <select name="country_id">
                            <option value="1" selected>country</option>
                            <option value="2">India</option>
                        </select>
                        <br>
                    </span>
                </div>
                <div id="footer">
                    <input type="submit" value="Add Contact" />
                    <br>
                    <a href="profile.jsp">Back</a>
                </div>
            </form>
        </div>
    </body>
</html>
