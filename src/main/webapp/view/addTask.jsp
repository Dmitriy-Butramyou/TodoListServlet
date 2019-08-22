<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/navbar.jsp"></jsp:include>


<div>
    <div>
        <h2>Add Task :)</h2>
    </div>

<form method="post" enctype="multipart/form-data">
    <div>
        <table border="2">
            <tr>
                <td>Name</td>
                <td>Description</td>
                <td>EventDate</td>
            </tr>
            <td><input type="text" name="nameTask"></td>
            <td><input type="text" name="description"></td>
            <td><input type="date" name="eventDate"></td>
            <input type="hidden" name="userId" value="${user}">
        </table>
        <input type="file" name="attachment">
    </div>
    <button style="margin-top: 10px" type="submit">Post</button>
</form>

</div>
<div>
    <button onclick="location.href='/allTask'">Return to the task show</button>
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>