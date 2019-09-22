<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>
<div class="col-md-8 mx-auto">
    <div>
        <div>
            <h4 class="test-decor">Add NEW Task :)</h4>
        </div>
<%--        <c:if test="${not empty error}">--%>
<%--            <div class="alert alert-danger" role="alert">--%>
<%--                    ${error}--%>
<%--            </div>--%>
<%--        </c:if>--%>
<form method="post" enctype="multipart/form-data">

    <div class="form-group row">
        <label class="test-decor col-sm-3 col-form-label">Subject:</label>
        <div class="col-sm-7">
            <input type="text" class="myForm"
                   name="nameTask" placeholder="Enter subject..." />
        </div>
    </div>

    <div class="form-group row">
        <label class="test-decor col-sm-3 col-form-label">Deadline:</label>
        <div class="col-sm-7">
            <input type="date" id="deadline" name="eventDate" class="myForm form-control form-control-sm">
        </div>
    </div>

    <div class="form-group row">
        <label class="test-decor col-sm-3 col-form-label">Enter a task:</label>
        <div class="col-sm-7">
                <textarea class="textarea-decor"
                          name="description" placeholder="Enter a task..." id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
    </div>
    <div class="form-group row">
        <label class="test-decor col-sm-3 col-form-label">Upload file:</label>
        <div class="col-sm-8">
            <input type="file"  name="attachment" class="test-decor form-control-file" id="exampleFormControlFile1">                    </div>
    </div>
    <input type="hidden" name="userId" value="${user}">
    <div class="form-group">
        <button type="submit" class="btn btn-primary mb-2">Send</button>
    </div>
</form>
</div>

<jsp:include page="parts/footer.jsp"></jsp:include>