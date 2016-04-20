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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/js.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .content {
            height: 100%;
        }
        .box{

            height: 100%;
            margin-top: 40px;
        }

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }
        .info {
            text-align: left;
            padding: 5px;
            font-size: 11px;
            color: #fff;
            position: absolute;
            display: none;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: -1px 1px 2px #a9a9a9;
            -moz-box-shadow: -1px 1px 2px #a9a9a9;
            box-shadow: -1px 1px 2px #a9a9a9;
        }
        .error {
            background: #f60000;
            border: 3px solid #d50000;
        }
        .correct {
            background: #56d800;
            border: 3px solid #008000;
        }
        .wrong {
            font-weight: bold;
            color: #e90000;
        }
        .normal {
            font-weight: normal;
            color: #222;
        }
        #send {
            -webkit-border-radius: 7px;
            -moz-border-radius: 7px;
            border-radius: 7px;
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
                <li class="active"><a href="#">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>
<%--<script type="text/javascript" src="js/bootstrap.min.js"></script>--%>
<%--<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>--%>

<div class="container-fluid text-center">
    <div class="row">
        <div class="col-sm-2 sidenav">


        </div>
        <div class="col-sm-8 content">

            <div class="row box">
            <h4 class="text-center">Поиска клиента</h4>
            <form  id="jform" class="form-inline" method="post" action="/account" >
                <div class="form-group">
                    <input class="form-control" type="text" name="accNumber" id="accNumber" placeholder="Номер карточного счета">
                </div>
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit" name="addClient" id="send" value="Найти" >
                </div>
            </form>

                <table class="table table-bordered table-hover">
                    <caption class="text-center">Данные о кредитном договоре</caption>
                    <tr class="success">
                        <th style="vertical-align: middle"><h5 class="text-center">Карточный счет</h5></th>
                        <th style="vertical-align: middle"><h5 class="text-center">Кредитный лимит</h5></th>
                        <th style="vertical-align: middle"><h5 class="text-center">Ставка по лимиту</h5></th>
                        <th style="vertical-align: middle"><h5 class="text-center">Обшая задолженность на сегодня</h5></th>
                    </tr>
                    <tr>
                        <th style="vertical-align: middle"><h5 class="text-center">${acc}</h5></th>
                        <th style="vertical-align: middle"><h5 class="text-center">${creditLimit}</h5></th>
                        <th style="vertical-align: middle"><h5 class="text-center">${percentDebitDue}</h5></th>
                        <th class="danger" style="vertical-align: middle"><h5 class="text-center"></h5></th>
                    </tr>
                </table>
            <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal">Посмотреть таблицу</button>
            </div>
        </div>
        <div class="col-sm-2 sidenav">

        </div>
    </div>
</div>

    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-body">
                    <table class="table table-bordered table-hover">
                        <caption class="text-center">Таблица задолженности по дням</caption>
                        <tr>
                            <th style="vertical-align: middle"><h5 class="text-center">№</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Дата</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Задолженность</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Платеж</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Процент по телу КЛ</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Процент по НО</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Сумма процентов по телу</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Сумма процентов по НО</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Обшая задолженность на дату</h5></th>
                        </tr>
                        <c:forEach var="cd" items="${calcDebts}">
                            <tr>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.id}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.date}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.debts}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.pay}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.percentPrincipalDebt}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.percentPastDueDebts}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.sumPercentPrincipalDebt}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.sumPercentPastDueDebts}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${cd.fullDebts}</h6></th>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>

        </div>
    </div>
<footer class="container-fluid text-center">
    <p>by Fordros</p>
</footer>
</body>
</html>
