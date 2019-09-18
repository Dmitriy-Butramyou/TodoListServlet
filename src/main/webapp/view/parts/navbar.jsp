<%--
  Created by IntelliJ IDEA.
  User: bytri
  Date: 22.08.2019
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>ToDoListServlet</title>
</head>
<body>
<div style="background-color: #0b245d;">
    <nav class="navbar navbar-expand-lg navbar-dark col-sm-10 mx-auto" style="background-color: #0b245d;">
        <a class="navbar-brand" href="#">
            ToDoList</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link " href="#">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/allTask">Tasks</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/addTask">New Task</a>
                </li>

                <a href="${pageContext.request.contextPath}/performed">
                    <button class="btn btn-outline-success my-2 my-sm-0 ml-3" type="button">
                        Performed
                    </button>
                </a>
                </form>

                <a href="${pageContext.request.contextPath}/basket">
                    <button class="btn btn-outline-danger my-2 my-sm-0 ml-3" type="button">
                        Basket
                    </button>
                </a>


            </ul>
            </a>

            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${name}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Profile</a>

                    <a class="dropdown-item" href="#">User List</a>

                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </div>
            <<a href="${pageContext.request.contextPath}/button?command=SIGN_OUT">
            <button class="btn btn-outline-light my-2 my-sm-0 ml-3" type="button">Sign Out</button>
        </a>
        </div>
    </nav>
</div>

