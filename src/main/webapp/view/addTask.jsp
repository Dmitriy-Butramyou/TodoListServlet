<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Task</title>
</head>
<body>
<div>
    <div>
        <h2>Add Task :)</h2>
    </div>

<form method="post">
    <div>
        <table border="2">
            <tr>
                <td>Name</td>
                <td>Description</td>
                <td>EventDate</td>
            </tr>
            <td><input type="text" name="nameTask"></td>
            <td><input type="text" name="description"></td>
            <td><input type="date" name="eventDate"></td>
            <input type="hidden" name="userId" value="${user}">
        </table>
    </div>
    <button style="margin-top: 10px" type="submit">Post</button>
</form>

</div>
<div>
    <button onclick="location.href='/allTask'">Return to the task show</button>
</div>
</body>
</html>