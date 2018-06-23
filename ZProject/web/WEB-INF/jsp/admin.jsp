<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.login == null || sessionScope.userRoleId != 1}">
    <jsp:forward page="/controller?command=get_page&page=index"/>
</c:if>

<c:if test="${requestScope.stations == null || requestScope.routes == null}">
    <jsp:forward page="/controller?command=get_page&page=admin"/>
</c:if>

<html>
<head>
    <title>Home</title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
    <script type="text/javascript" src="script/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="style/jquery-ui.css">
</head>

<body>

<div id="wrap" class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-24 last">
        <div class="admin-box">
            <div class="station-list">
                <h1>Station List</h1>
                <div class="row">
                    <div class="col-lg-9 col-12">
                        <div class="box-body">
                            <div class="table-responsive">
                                <table id="station-list" class="table mt-0 table-hover no-wrap table-striped table-bordered"
                                       data-page-size="10">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name of Station</th>
                                        <th>Country ID</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.stations}" var="s">
                                        <tr>
                                            <td>${s.getId()}</td>
                                            <td>${s.getName()}</td>
                                            <td>${s.getCountry().getId()}</td>
                                            <td>
                                                <!--button type="button"
                                                        class="btn btn-sm btn-green-outline"
                                                        data-toggle="tooltip"
                                                        data-original-title="Modify">Modify
                                                </button-->
                                                <a href="controller?command=station_delete&name-of-station=${s.getName()}&country=${s.getCountry().getId()}">
                                                    <button type="button"
                                                            class="btn btn-sm btn-danger-outline"
                                                            data-toggle="tooltip"
                                                            data-original-title="Delete">Delete
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                                <form class="add_form" action="controller" method="post">
                                    <input type="hidden" name="command" value="station_add"/>
                                    <input type="text" name="name-of-station" id="station-name"
                                           placeholder="Name of Station">
                                    <input type="number" name="country" id="city" placeholder="county id">
                                    <input type="submit" class="submit" value="Add">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ways-list">
                <h1>Route List</h1>
                <div class="row">
                    <div class="col-lg-9 col-12">
                        <div class="box-body">
                            <div class="table-responsive">
                                <table id="tickets" class="table mt-0 table-hover no-wrap table-striped table-bordered"
                                       data-page-size="10">
                                    <thead>
                                    <tr>
                                        <th>Route ID</th>
                                        <th>Train ID</th>
                                        <th>Station ID</th>
                                        <th>Arrival</th>
                                        <th>Departure</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${requestScope.routes}" var="r">
                                        <tr>
                                            <td>${r.getRouteId()}</td>
                                            <td>${r.getTrainId()}</td>
                                            <td>${r.getStationId()}</td>
                                            <td>${r.getArrival()}</td>
                                            <td>${r.getDeparture()}</td>
                                            <td>
                                                <!--button type="button"
                                                        class="btn btn-sm btn-green-outline"
                                                        data-toggle="tooltip"
                                                        data-original-title="Modify">Modify
                                                </button-->
                                                <a href="controller?command=route_delete&route_id=${r.getRouteId()}&train_id=${r.getTrainId()}&station_id=${r.getStationId()}">
                                                     <button type="button"
                                                            class="btn btn-sm btn-danger-outline"
                                                            data-toggle="tooltip"
                                                            data-original-title="Delete">Delete
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                                <form class="add_form" action="controller" method="post">
                                    <input type="hidden"  name="command" value="route_add"/>
                                    <input type="number" style="width: 80px;" name="route_id" class="add_field" id="from-station" placeholder="route id"/>
                                    <input type="number" style="width: 80px;" name="train_id" class="add_field" id="from-station" placeholder="train id">
                                    <input type="number" style="width: 80px;" name="station_id" class="add_field" id="to-station"
                                           placeholder="station id">
                                    <input type="text" name="arrival" class="add_field" id="duration" placeholder="arrival">
                                    <input type="text" name="departure" class="add_field" id="duration" placeholder="departure">
                                    <input type="submit" class="submit" value="Add">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>