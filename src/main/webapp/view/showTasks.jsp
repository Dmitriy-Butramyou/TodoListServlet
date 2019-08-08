<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 16.07.2019
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список заданий</title>
</head>
<body>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Тема</td>
        <td>Описание</td>
        <td>State</td>
        <td>Действия</td>
    </tr>
    <%
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    if (tasks != null && !tasks.isEmpty()) {
    for (Task task : tasks) {
        out.println("<td>" + task.getId() + "</td>");
        out.println("<td>" + task.getName() + "</td>");
        out.println("<td>" + task.getDescription() + "</td>");
        out.println("<td>" + task.getState().toString() + "</td>");
        out.println("<td>" + "<form action = \"/mark_delete/" + task.getId() + "\">\n" +
                "    <input type=\"submit\" value=\"Mark Delete\">\n" +
                "</form>" + "</td>");
        out.println("<tr>" + "</tr>");
    }
    } else out.println("<p>There are no users yet!</p>");
    %>
</table>

<form method=""></form>

<form action = "/index">
    <input type="submit" value="Index page">
</form>
<form action = "/addTask">
    <input type="submit" value="Добавить новое задание">
</form>
</body>
</html>
