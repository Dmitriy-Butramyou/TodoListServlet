<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Attachment" %>
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

<span id="result"></span>
    <c:forEach var="task" items="${tasks}">
        <form id="mainForm" name="mainForm">
        <div class="card col-md-5 mx-auto">
            <div class="card-header">
                <div class="btn-group" role="group" aria-label="Basic example">
                  <h4>${task.name}</h4>
                        <a href="/mark_complete/${task.id}">
                            <button type="button" class="badge badge-pill badge-success">Complete</button>
                        </a>
                        <a href="/mark_delete/${task.id}">
                            <button type="button" class="badge badge-pill badge-danger">Delete</button>
                        </a>
                        <a href="/delete_task/${task.id}">
                            <button type="button" class="badge badge-pill badge-info">Delete file</button>
                        </a>
                        <a href="#">
                            <button type="button" class="badge badge-pill badge-light">Add file</button>
                        </a>
                </div>
            </div>
            <div class="card-body">
                <h5 class="card-title">Deadline: </h5>
                <p class="card-text">${task.description}</p>
                <%--<%--%>
                    <%--List<Attachment> attachments = (List<Attachment>) request.getAttribute("attachments");--%>
                    <%--if (attachments != null && !attachments.isEmpty()) {--%>
                        <%----%>
                    <%--};--%>
                <%--%>--%>


                <%--<a href="#" class="btn btn-primary">Go somewhere</a>--%>
            </div>
        </div>
        </form>
    </c:forEach>

<jsp:include page="parts/footer.jsp"></jsp:include>
