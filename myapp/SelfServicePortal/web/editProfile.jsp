<%-- 
    Document   : editProfile
    Created on : Feb 12, 2016, 11:26:42 AM
    Author     : cb-vinothini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Space Portal</title>
        <style>
            #editForm{
                text-align: center;
                width: 30%;
                padding-top: 2%;
                margin: auto;
            }
            .title{
                float: left;                
            }
        </style>
        <script src="AJAX/listAJAX.js" type="text/javascript"></script>        
    </head>
    <body>
        <div id="header">
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <div id="editForm">

            <form action="edit" method="post">
            <h1>Edit Details</h1>
            <div>
                <span class="title">Name</span>
                <span><input type="text" name="firstname" placeholder="<%=user.firstName%>" value="">
                <input type="text" name="lastname" placeholder = "<%=user.lastName%>" value=""></span>
            </div>
            <br>
            <div>
                <span class="title">Email</span>
                <span><input type="text" name="email" placeholder = "<%=user.email%>" readonly></span> 
            </div>
            <br>
            <div>            
                <span class="title">Address</span>
                <span>
                    <input type="text" name="line1"  placeholder="<%=user.line1%>">
                    <br>
                    <input type="text" name="line2"  placeholder="<%=user.line2%>">
                    <br>
                    <input type="text" name="city"  placeholder="<%=user.city%>">
                    <br>
                    <select name="state_id" onclick="list(states)">
                        <option value="1" selected><%=user.state%></option>
                        <!--<option value="" id="state"></option>-->                        
                        <option value="2">Tamil Nadu</option>
                        <option value="3">Kerala</option>
                        <option value="4">Karnataka</option>
                    </select>
                    <br>
                    <select name="country_id">
                        <option value="1" selected><%=user.country%></option>
                        <option value="2">India</option>
                        <option value="3">China</option>
                        <option value="4">Australia</option>
                    </select>
                    <br>
                    <input type="text" name="zip"  placeholder="<%=user.zip%>">
                </span>
            </div>
            <br>
            <input type="submit" value="Save details" />
            </form>
        </div>
    </body>
</html>
