<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: butramyou
  Date: 17.08.2019
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>

<div class="list-group col-md-5 mx-auto mt-4">

<h4>That deleted tasks.</h4>

    <a href="${pageContext.request.contextPath}/button?command=DELETE_ALL">
        <button type="button" class="btn btn-outline-danger btn-lg btn-block mb-2">Delete all from Basket</button>
    </a>
</div>
<c:forEach var="task" items="${tasks}">
    <form method="post" enctype="multipart/form-data">
        <div class="card col-md-5 mx-auto">
            <div class="card-header">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <h4>${task.name}</h4>
                    <a href="${pageContext.request.contextPath}/button?command=MARK_ACTUAL&id=${task.id}">
                    <button type="button" class="badge badge-pill badge-primary">Actual</button>
                     </a>
                    <a href="${pageContext.request.contextPath}/button?command=MARK_COMPLETE&id=${task.id}">
                        <button type="button" class="badge badge-pill badge-success">Complete</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/button?command=DELETE_TASK&id=${task.id}">
                        <button type="button" class="badge badge-pill badge-danger">Delete</button>
                    </a>
                    <c:if test="${not empty task.originalFileName}">
                        <a href="${pageContext.request.contextPath}/button?command=DELETE_FILE&id=${task.id}">
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
                <h6 class="card-title">Deadline: <fmt:formatDate type="date" value="${task.eventDate}" /> </h6>

                <p class="card-text">${task.description}</p>
                <c:if test="${not empty task.originalFileName}">
                    <a href="${pageContext.request.contextPath}/button?command=DOWNLOAD_FILE&id=${task.id}">
                            ${task.originalFileName}</a>
                </c:if>
                <c:if test="${empty task.originalFileName}">
                    <h6>File empty</h6>
                </c:if>
            </div>
        </div>
    </form>
</c:forEach>

<jsp:include page="parts/footer.jsp"></jsp:include>
