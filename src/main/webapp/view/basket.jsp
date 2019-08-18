<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 17.08.2019
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<h4>That deleted tasks.</h4>

<form action = "/allTask">
    <input type="submit" value="Index page">
</form>

<form action = "/deleteAll">
    <input type="submit" value="Delete all from Basket">
</form>

<table border="2">
    <tr>
        <td>ID</td>
        <td>Тема</td>
        <td>Описание</td>
        <td>State</td>
        <td>User Id</td>
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
                out.println("<td>" + task.getUserId() + "</td>");
                out.println("<td>" + "<form action = \"/mark_delete/" + task.getId() + "\">\n" +
                        "    <input type=\"submit\" value=\"Mark Actual\">\n" +
                        "</form>" +
                        "<form action = \"/mark_complete/" + task.getId() + "\">\n" +
                        "    <input type=\"submit\" value=\"Mark Complete\">\n" +
                        "</form>" +
                        "<form action = \"/delete/" + task.getId() + "\">\n" +
                        "    <input type=\"submit\" value=\"Delete\">\n" +
                        "</form>" +"</td>");
                out.println("<tr>" + "</tr>");
            }
        } else out.println("<p>No deleted tasks.</p>");
    %>
</table>
</body>
</html>
