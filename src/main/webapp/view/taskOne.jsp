<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 23.08.2019
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>

<form method="post" enctype="multipart/form-data">
    <div class="card text-center">

        <div class="card-header">
            <%--<h5>Deadline: ${task.deadline?date}</h5>--%>
            <div>
                <label class="col-sm-3 col-form-label text-danger">Edit deadline</label>
                <div class="col-sm-2 mx-auto">
                    <input type="date" id="deadline" name="deadline" class="form-control form-control-sm">
                </div>
            </div>

        </div>
        <div class="card-body">
            <div class="col-sm-3 mx-auto mb-2">
                <input type="text" name="topicTask" class="form-control" value="${task.name}">
            </div>
            <div class="col-sm-6 mx-auto">
            <textarea class="form-control" name="textTask" placeholder="${task.description}"
                      id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>

        </div>
        <div class="card-footer text-muted">
            <c:if test="${not empty attachment}">
            ${attachment.originalName}
            <a class="btn btn-outline-danger ml-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Replace file
            </a>
            <div class="collapse" id="collapseExample" >
                <div class="form-group row">
                    <div class="mx-auto mt-3">
                        <input type="file"  name="file" class="form-control-file" id="exampleFormControlFile1">                    
                    </div>
                </div>

            </div>
            </c:if>

            <c:if test="${empty attachment}">
            <div>
                <a class="btn btn-outline-success" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Add file
                </a>

                <div class="collapse" id="collapseExample">
                    <div class="form-group row">
                        <div class="mx-auto mt-3">
                            <input type="file"  name="file" class="form-control-file" id="exampleFormControlFile1">                    </div>
                    </div>
                </div>

            </div>
            </c:if>

        <div class="form-group">
            <button type="submit" class="btn btn-primary mb-2 mt-2">Save</button>
        </div>

    </div>
    </div>
</form>


<jsp:include page="parts/footer.jsp"></jsp:include>
