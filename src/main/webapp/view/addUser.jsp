<%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 15.08.2019
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>

<div>
    <div>
        <h2>Create user please :)</h2>
    </div>

<%
String error =(String) request.getAttribute("error");
out.println("<div>" + error + "</div>");
%>

    <form method="post">
        <div>
            <table border="2">
                <tr>
                    <td>Name</td>
                    <td>Password</td>
                </tr>
                <td><input type="text" name="userName"></td>
                <td><input type="text" name="password"></td>
            </table>
        </div>
        <button style="margin-top: 10px" type="submit">Create</button>
    </form>

</div>
<div>
    <button onclick="location.href='/'">Login</button>
</div>

</body>
</html>
