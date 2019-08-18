<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>ToDoListServlet</title>
</head>
<body>
<div style="background-color: #0b245d;">
    <nav class="navbar navbar-expand-lg navbar-dark col-sm-10 mx-auto" style="background-color: #0b245d;">
        <a class="navbar-brand" href="/">
            ToDoList</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link " href="/">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/allTask">Tasks</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/addTask">New Task</a>
                </li>

            <a href="/performed">
                <button class="btn btn-outline-success my-2 my-sm-0 ml-3" type="button">
                    Performed
                </button>
            </a>
            </form>

        <a href="/basket">
            <button class="btn btn-outline-danger my-2 my-sm-0 ml-3" type="button">
                Basket
            </button>
        </a>


    </ul>
    </a>

    <div class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ${name}
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">Profile</a>

        <a class="dropdown-item" href="#">User List</a>

    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="#">Something else here</a>
</div>
</div>
            <<a href="/login">
                <button class="btn btn-outline-light my-2 my-sm-0 ml-3" type="button">
                    <%
                        if (request.getAttribute("name") != null) {
                            out.println("Sign Out");
                        } else {
                            out.println("Sign In");
                        }
                    %>
                    </button>
            </a>
</div>
</nav>
</div>

<div style="width: 980px; margin: auto">
<table border="2" style="margin: auto">
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
                        "    <input type=\"submit\" value=\"Mark Delete\">\n" +
                        "</form>" +
                        "<form action = \"/mark_complete/" + task.getId() + "\">\n" +
                        "    <input type=\"submit\" value=\"Mark Complete\">\n" +
                        "</form>" +"</td>");
                out.println("<tr>" + "</tr>");
            }
        } else out.println("<p>No urgent tasks. Have a rest))</p>");
    %>
</table>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>