<%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 14.08.2019
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <div>
        <h2>Login please :)</h2>
    </div>

    <div>${error}</div>

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
        <button style="margin-top: 10px" type="submit">Login</button>
    </form>

</div>
<div>
    <button onclick="location.href='/addUser'">Add user</button>
</div>
</body>
</html>
