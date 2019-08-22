<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="parts/navbar.jsp"></jsp:include>

<div class="list-group col-md-5 mx-auto mt-4">
    <%--<h4>${location}</h4>--%>
    <form method="get" action="#">
        <div class="btn-group mb-3" role="group" aria-label="Basic example">
            <button type="submit" class="btn btn-outline-primary" name="day" value="All">All</button>
            <button type="submit" class="btn btn-outline-primary" name="day" value="Today">Today</button>
            <button type="submit" class="btn btn-outline-primary" name="day" value="Tomorrow">Tomorrow</button>
            <a class="btn btn-outline-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Someday
            </a>
            <button type="submit" class="btn btn-outline-danger" name="day" value="Deadline Missing">Deadline Missing</button>
        </div>
    </form>
</div>

<div class="collapse" id="collapseExample">
    <div class="form-group mt-3 col-md-5 mx-auto">
        <form method="get" class="form-inline">
            <div class="form-group mb-2">
                <input type="date" id="deadline" name="deadline" class="form-control form-control-sm"
                       placeholder="YYYY-MM-DD">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary ml-3 mb-2">Find</button>
            </div>
        </form>
    </div>
</div>


<%--<div class="d-flex bd-highlight mb-3">--%>
<%--<div style="width: 980px; margin: auto">--%>
<%--<table border="2" style="margin: auto">--%>
    <%--<tr>--%>
        <%--<td>ID</td>--%>
        <%--<td>Тема</td>--%>
        <%--<td>Описание</td>--%>
        <%--<td>State</td>--%>
        <%--<td>User Id</td>--%>
        <%--<td>Действия</td>--%>
    <%--</tr>--%>
    <%--<%--%>
<%--//        List<Task> tasks = (List<Task>) request.getAttribute("tasks");--%>
        <%--if (tasks != null && !tasks.isEmpty()) {--%>
            <%--for (Task task : tasks) {--%>
                <%--out.println("<td>" + task.getId() + "</td>");--%>
                <%--out.println("<td>" + task.getName() + "</td>");--%>
                <%--out.println("<td>" + task.getDescription() + "</td>");--%>
                <%--out.println("<td>" + task.getState().toString() + "</td>");--%>
                <%--out.println("<td>" + task.getUserId() + "</td>");--%>
                <%--out.println("<td>" + "<form action = \"/mark_delete/" + task.getId() + "\">\n" +--%>
                        <%--"    <input type=\"submit\" value=\"Mark Delete\">\n" +--%>
                        <%--"</form>" +--%>
                        <%--"<form action = \"/mark_complete/" + task.getId() + "\">\n" +--%>
                        <%--"    <input type=\"submit\" value=\"Mark Complete\">\n" +--%>
                        <%--"</form>" +"</td>");--%>
                <%--out.println("<tr>" + "</tr>");--%>
            <%--}--%>
        <%--} else out.println("<p>No urgent tasks. Have a rest))</p>");--%>
    <%--%>--%>
<%--</table>--%>
<%--</div>--%>

<div class="list-group col-md-5 mx-auto">
<c:forEach var="task" items="${tasks}">
    <a href="/task/${task.id}" class="list-group-item list-group-item-action mb-2 ">

        <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">${task.name}
                <%--<span class="badge badge-success"><#if task.tag>New<#else></#if></span></h5>--%>
            <%--<small>${task.deadline?date}</small>--%>
        </div>
        <p class="mb-1">${task.description} </p>
        <small>${task.userId} </small>
    </a>
    <div class="d-flex bd-highlight mb-3">
        <a href="/mark_complete/${task.id}">
            <button type="button" class="btn btn-outline-success mb-3">Performed</button>
        </a>
        <a href="/mark_delete/${task.id}" class="ml-auto">
            <button type="button" class="btn btn-outline-danger mb-3">Delete</button>
        </a>
    </div>
    </c:forEach>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>
