<%--
  Created by IntelliJ IDEA.
  User: butramyou
  Date: 19.09.2019
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>
<div class="list-group col-md-5 mx-auto">
    <a class="btn btn-outline-light" data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false" aria-controls="collapseExample1">
       <h5>${name}</h5>
    </a>
    <div class="collapse" id="collapseExample1">
        <div class="form-background card-body">
            <div>
                <form method="post">
                    <div class="form-group row">
                        <label class="test-decor col-form-label">Username:</label>
                        <div class="ml-3">
                            <input type="text" name="username"  class="myForm" value="${name}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="test-decor col-form-label">Password:</label>
                        <div class="ml-3">
                            <input type="password" name="password" class="myForm" placeholder="Change Password"/>
                        </div>
                    </div>
                    <div class="d-flex bd-highlight mb-3">
                    <button class="btn btn-primary" type="submit">Update</button>
                            <button class="btn btn-danger my-sm-0 ml-3" type="button" onclick="isSure()">
                                Delete User
                            </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <table class="table table-dark">
        <thead>
        <tr>
            <th scope="col">Actual tasks</th>
            <th scope="col">Deadline Missing</th>
            <th scope="col">Performed</th>
            <th scope="col">Basket</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="mx-auto">
                <a href="${pageContext.request.contextPath}/allTask">
                <h3>${actual}</h3></a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/allTask?day=Deadline+Missing">
                    <h3>${deadlineMissing}</h3></a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/performed">
                    <h3>${performed}</h3></a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/basket">
                    <h3>${remote}</h3></a>
            </td>
        </tbody>
    </table>
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>

