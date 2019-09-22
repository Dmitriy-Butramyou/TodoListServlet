<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: butramyou
  Date: 17.08.2019
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>

<div class="list-group col-md-5 mx-auto">

<h4 class="test-decor">That performed tasks. You COOL!!! :)</h4>

</div>
<c:forEach var="task" items="${tasks}">
    <form method="post" enctype="multipart/form-data">
        <div class="card col-md-5 mx-auto">
            <div class="card-header">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <a href="${pageContext.request.contextPath}/button?command=MARK_ACTUAL&id=${task.id}">
                        <button type="button" class="badge badge-pill badge-primary">Actual</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/button?command=MARK_DELETE&id=${task.id}">
                        <button type="button" class="badge badge-pill badge-danger">Delete</button>
                    </a>
                    <c:if test="${not empty task.originalFileName}">
                        <a href="${pageContext.request.contextPath}/button?command=DELETE_FILE&id=${task.id}">
                            <button type="button" class="badge badge-pill badge-info">Delete file</button>
                        </a>
                    </c:if>
                    <c:if test="${empty task.originalFileName}">
                        <a class="badge badge-pill badge-dark" data-toggle="collapse" href="#collapseExample${task.id}" role="button" aria-expanded="false" aria-controls="collapseExample1">
                            Add file
                        </a>
                    </c:if>
                </div>
                <h6 class="deadline-position"><fmt:formatDate type="date" value="${task.eventDate}" /> </h6>
                <div>
                    <c:if test="${empty task.originalFileName}">
                        <div class="collapse" id="collapseExample${task.id}">
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
                <h4 class="text-center">${task.name}</h4>
                <p class="description text-center">${task.description}</p>
                <c:if test="${not empty task.originalFileName}">
                    <a href="${pageContext.request.contextPath}/button?command=DOWNLOAD_FILE&id=${task.id}">
                            ${task.originalFileName}</a>
                </c:if>
                <c:if test="${empty task.originalFileName}">
                    <a data-toggle="collapse" href="#collapseExample${task.id}" role="button" aria-expanded="false" aria-controls="collapseExample1">
                        The file is missing. Download?
                    </a>
                </c:if>
            </div>
        </div>
    </form>
</c:forEach>

<jsp:include page="parts/footer.jsp"></jsp:include>
