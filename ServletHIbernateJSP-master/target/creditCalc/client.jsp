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
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 85%}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }
        .center {
            vertical-align: middle
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
<body>
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
    <div class="row content">
        <div class="col-sm-2 sidenav">


        </div>
        <div class="col-sm-8">
            <div class="row">
            <h4 class="text-center">Поиска клиента</h4>
            <form  class="form-inline" method="post" action="/account" >
                <div class="form-group">
                    <input class="form-control" type="text" name="accNumber" id="accNumber" placeholder="Номер карточного счета">
                </div>
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit" name="addClient" value="Найти" >
                </div>
            </form>
            <div class="text-center">
                <h3>Данные о кредитном договоре</h3>
                <h4>Карточный счет: <small>${acc}</small></h4>
                <h4>Кредитный лимит: <small>${creditLimit} копеек</small></h4>
                <h4>Ставка по лимиту: <small>${percentDebitDue}%</small></h4>
            </div>
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
