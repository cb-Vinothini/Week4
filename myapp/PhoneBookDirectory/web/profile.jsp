<%-- 
    Document   : profile
    Created on : Feb 12, 2016, 9:16:37 AM
    Author     : cb-vinothini
--%>

<%@page import="model.Contact"%>
<%@page import="java.util.List"%>
<%@page import="model.UserDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phone Book</title>
        <link href="CSS/profileCSS.css" rel="stylesheet" type="text/css">
        <script src="AJAX/matchContactAJAX.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" ></script>
    </head>
    <body>
        <script type="text/javascript"> 
            $(document).on('click', '#editID', function(){
                var row = $(this).closest("tr");
                var c_id = row.find("#c_id").val();
                var fname = row.find("#first_name").val();
                var lname = row.find("#last_name").val();
                var line1 = row.find("#line1").val();
                var line2 = row.find("#line2").val();
                var city = row.find("#city").val();
                var state = row.find("#state").val();
                var country = row.find("#country").val();
                var zip = row.find("#zip").val();
                var mobile = row.find("#mobile").val();
                var home = row.find("#home").val();
                var work = row.find("#work").val();
//                alert(c_id+fname+lname+line1+line2+city+state+country+zip+mobile+home+work);
//                var temp = c_id+"&fname="+fname+"&lname="+lname+"&line1="+line1+"&line2="+line2+"&city="+city+"&state="+state+"&country="+country+"&zip="+zip+"&mobile="+mobile+"&home="+home+"&work="+work;
                document.location.href="/PhoneBookDirectory/editDetails?c_id="+c_id+"&fname="+fname+"&lname="+lname+"&line1="+line1+"&line2="+line2+"&city="+city+"&state="+state+"&country="+country+"&zip="+zip+"&mobile="+mobile+"&home="+home+"&work="+work;
            });
        </script>

        <div id="header">
            <%@include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <div id="profile">
            <div id ="left">
            <div>
                <h3><%=user.firstName%>'s Phone Directory</h3>
            </div>
            <br>
            <div id="footer">
                <a href="addContact.jsp">Add Contact</a>
                <br>
                <button type="button" onclick="dispCont('<%=user.userID%>')">Display Contact</button> 
            </div>
            </div>
            <br> 
            <div id="right">
            <input type="text" id="partialUserName" placeholder="Partial Name match" onchange="dispByPatrilaName('<%=user.userID%>')">
            <br>
            <input type="text" id="number" placeholder="Phone Number" onchange="dispByNumber('<%=user.userID%>')">
            <br>
            </div>
        </div>
        <table cellspacing="0" > 
            <thead>
                <th scope="col">Contact ID</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Line1</th>
                <th scope="col">Line2</th>
                <th scope="col">City</th>
                <th scope="col">State</th>
                <th scope="col">Country</th>
                <th scope="col">Zip</th>
                <th scope="col">Mobile</th>
                <th scope="col">Home</th>
                <th scope="col">Work</th>
            </thead>

            <tbody id='disp'>
                
            
            <%
                ServletContext sc = getServletContext();
                UserDB disp = new UserDB(sc);
                List<Contact> contacts = disp.displayAllContactDetails(user.userID);
                for(Contact contact :contacts){
            %>
            <%--<%=contact.toString()%>--%>
            <tr>
            <td><input type="text" id = "c_id" value='<%=contact.contact_id%>' readonly/></td>
            <td><input type="text" id = "first_name" value='<%=contact.first_name%>' /></td>
            <td><input type="text" id = "last_name" value='<%=contact.last_name%>' /></td>
            <td><input type="text" id = "line1" value='<%=contact.line1%>' /></td>
            <td><input type="text" id = "line2" value='<%=contact.line2%>' /></td>
            <td><input type="text" id = "city" value='<%=contact.city%>' /></td>
            <td><input type="text" id = "state" value='<%=contact.state%>' /></td>
            <td><input type="text" id = "country" value='<%=contact.country%>' /></td>
            <td><input type="text" id = "zip" value='<%=contact.zip%>' /></td>
            <td><input type="text" id = "mobile" value='<%=contact.mobile%>' /></td>
            <td><input type="text" id = "home" value='<%=contact.home%>' /></td>
            <td><input type="text" id = "work" value='<%=contact.work%>' /></td>
            <td><button id="editID">Save changes</button></td>
            </tr>
            
            <%}%>
            
            </tbody>
        </table>
    </body>
</html>
