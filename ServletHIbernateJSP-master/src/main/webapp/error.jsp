<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <title>Test servlet</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/js.js"></script>
    <style>
        label.error {
            color: red;
            font-style: italic;
            margin-left: 5px;
        }

        .yel {
            border: 1px dotted #ffffe0;
            background-color: #ffffe0;
        }
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .content {
            height: 95%;
        }
        .box{

            height: 100%;
            margin-top: 40px;
        }

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 95%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid  767px*/
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>
<body style="height: 100%">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/account">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row">
        <div class="col-sm-1 sidenav">


        </div>
        <div class="col-sm-10 content">

            <div class="row box">
                <h4 class="text-center">Поиска клиента</h4>
                <form  id="jform" class="form-inline" method="post" action="/account" >
                    <div class="form-group">
                        <input type="text" class="form-control" maxlength="17" name="accNumber" id="accNumber" placeholder="Номер карточного счета">
                    </div>
                    <div class="form-group">
                        <input class="form-control" class="btn btn-default" type="submit"  name="addClient" id="send" value="Найти" >
                    </div>
                </form>

                <h1 class="text-center">Карточный счёт не найден в базе</h1>

            </div>
        </div>
        <div class="col-sm-1 sidenav">

        </div>
    </div>
</div>


<footer class="container-fluid text-center">
    <p>by Fordros</p>
</footer>
</body>
</html>
