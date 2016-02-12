<%-- 
    Document   : error
    Created on : Feb 6, 2016, 2:40:58 PM
    Author     : cb-vinothini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <center>
            Error :<%=request.getAttribute("msg") %>
        </center>
    </body>
</html>
