<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key='jsp.header.Home'/></title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
    <script type="text/javascript" src="script/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="style/jquery-ui.css">
</head>

<body>

<div id="wrap" class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-24 last">
        <div class="form" style="max-width: 100%; font-size: 1.3em;"
        <c:if test="${requestScope.route != null}">
            <p>id = ${requestScope.route.getId()}</p>
            <p>Train Model = ${requestScope.route.getTrain().getModel().getModel()}</p>
            <p>Station List:</p>
            <table>
                <tr>
                    <th>Station Name</th>
                    <th>Arrival</th>
                    <th>Departure</th>
                </tr>
                <c:forEach var="r" items="${requestScope.route.getStations()}">
                    <tr>
                        <td>${r.getStation().getName()}</td>
                        <td>${r.getArrival()}</td>
                        <td>${r.getDeparture()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>


    </div>
</div>

</body>
</html>