<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: butramyou
  Date: 15.08.2019
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbarWithoutLinks.jsp"></jsp:include>
<div class="col-sm-10 mx-auto">
<h4 class="test-decor">Add new User</h4>

    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>

<form method="post">
    <div class="form-group row">
    <label class="test-decor col-sm-2 col-form-label"> User Name: </label>
    <div class="col-sm-4">
        <input type="text" name="userName"
               class="myForm"
               placeholder="User name"/>
    </div>
</div>
<div class="form-group row">
    <label class="test-decor col-sm-2 col-form-label"> Password: </label>
    <div class="col-sm-4">
        <input type="password" name="password"
               class="myForm"
               placeholder="Password"/>
    </div>
</div>

    <div class="form-group row">
        <label class="test-decor col-sm-2 col-form-label"> Password: </label>
        <div class="col-sm-4">
            <input type="password" name="retypePassword"
                   class="myForm"
                   placeholder="Retype password"/>
        </div>

</div>
<button class="btn btn-primary" type="submit">Create</button>
</form>
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>
