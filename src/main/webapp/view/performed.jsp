<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 17.08.2019
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>

<div class="list-group col-md-5 mx-auto mt-4">

<h4>That performed tasks. You COOL!!! :)</h4>

</div>
<c:forEach var="task" items="${tasks}">
    <form method="post" enctype="multipart/form-data">
        <div class="card col-md-5 mx-auto">
            <div class="card-header">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <h4>${task.name}</h4>
                    <a href="/mark_complete/${task.id}">
                        <button type="button" class="badge badge-pill badge-success">Actual</button>
                    </a>
                    <a href="/mark_delete/${task.id}">
                        <button type="button" class="badge badge-pill badge-danger">Delete</button>
                    </a>
                    <c:if test="${not empty task.originalFileName}">
                        <a href="/delete_task/${task.id}">
                            <button type="button" class="badge badge-pill badge-info">Delete file</button>
                        </a>
                    </c:if>
                    <c:if test="${empty task.originalFileName}">
                        <a class="badge badge-pill badge-light" data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false" aria-controls="collapseExample1">
                            Add file
                        </a>
                        <div class="collapse" id="collapseExample1">
                            <div class="card card-body">
                                <div>
                                    <input type="hidden" name="taskId" value="${task.id}">
                                    <input type="file" name="attachment">
                                    <button type="submit">Post</button>
                                </div>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
            <div class="card-body">
                    <%--<h5 class="card-title">Deadline:--%>
                    <%----%>
                    <%--</h5>--%>
                <p class="card-text">${task.description}</p>
                <c:if test="${not empty task.originalFileName}">
                    <a href="/download/${task.id}">${task.originalFileName}</a>
                </c:if>
                <c:if test="${empty task.originalFileName}">
                    <h6>File empty</h6>
                </c:if>
            </div>
        </div>
    </form>
</c:forEach>
<jsp:include page="parts/footer.jsp"></jsp:include>
