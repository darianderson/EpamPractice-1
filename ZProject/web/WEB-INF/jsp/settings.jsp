<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${requestScope.tickets == null}">
    <jsp:forward page="/controller?command=get_page&page=settings"/>
</c:if>

<c:if test="${requestScope.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
    <fmt:setBundle basename="resources"/>
    <c:set var="currentLocale" value="${param.locale}" scope="session"/>
</c:if>

<html>
<head>
    <title><fmt:message key='jsp.header.Settings'/></title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
    <script type="text/javascript" src="script/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="style/jquery-ui.css">

</head>

<body>

<div id="wrap" class="container">
    <c:set var="title" value="login"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-24 last">
        <div id="tickets" class="column span-15 last">

            <div class="form" style="max-width: 100%; font-size: 1.5em;">
            <table class="table table-hover">
                <tr>
                    <th><fmt:message key='jsp.RouteNo'/></th>
                    <th>Train No</th>
                    <th><fmt:message key='jsp.carriage'/></th>
                    <th><fmt:message key='jsp.place'/></th>
                    <td>Arrival</td>
                    <td>Departure</td>
                    <th>From</th>
                    <th>To</th>
                    <th>more</th>
                </tr>

                <c:forEach items="${requestScope.tickets}" var="r">
                    <tr>
                        <td> ${r.getRoute().getId()} </td>
                        <td> ${r.getRoute().getTrain().getId()}</td>
                        <td> ${r.getCarriageNo()}</td>
                        <td> ${r.getPlaceNo()}</td>
                        <td> ${r.getArrivalStr()}</td>
                        <td> ${r.getDepartureStr()}</td>
                        <td> ${r.getFrom().getName()}</td>
                        <td> ${r.getTo().getName()}</td>
                        <td> <a href="${r.getLinkToRoute()}">more</a></td>
                    </tr>
                </c:forEach>

            </table>
            </div>
        </div>
        <div id="sidebar" style="margin-left: 50px; margin-top: 0;" class="column span-8 last">
            <form action="controller" method="post" class="form">
                <input type="hidden" name="command" value="settings_pane"/>
                <%--c:if test="${requestScope.errorMessage == null}">
                    <ex:AllGood/>
                </c:if --%>
                <select name="locale" >
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                        <option value="${locale.key}" ${selected}>${locale.value}</option>
                    </c:forEach>
                </select>
                <input type="text" maxlength="32" value="${requestScope.userName}" placeholder="<fmt:message key='jsp.name'/>" name="name"  />
                <input type="text" maxlength="32" value="${requestScope.userSurname}" placeholder="<fmt:message key='jsp.surname'/>" name="surname" />
                <input type="submit" class="sub" value="<fmt:message key='jsp.save'/>"/>
            </form>
        </div>
    </div>
</div>

</body>
</html>