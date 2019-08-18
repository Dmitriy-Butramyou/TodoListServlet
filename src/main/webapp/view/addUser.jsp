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
<div style="width: 980px; margin: auto">
<div>
    <div>
        <h2 align="center">Create user please :)</h2>
    </div>
<div>
    ${error}
</div>

    <form method="post">
        <div>
            <table border="2" style= "margin: auto">
                <tr>
                    <td>Name</td>
                    <td>Password</td>
                </tr>
                <td><input type="text" name="userName"></td>
                <td><input type="text" name="password"></td>
            </table>
        </div>
        <div align="center">
        <button style="margin: auto" type="submit">Create</button>
        </div>
    </form>

</div>
        <div align="center">
                <button style="margin: auto" onclick="location.href='/'">Login</button>
        </div>
</div>
</body>
</html>
