<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

<head>
    <title>Sign In</title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
</head>

<body id="body">

    <div id="wrap" class="container">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>

    <div class="login-page">
        <div class="form">
            <p id="info">${requestScope.errorMessage}</p>
            <p>Route ID: ${requestScope.routeId}</p>
            <form action="controller" class="login-form">
                <input type="hidden" name="command" value="buy_ticket"/>
                <input type="hidden" name="from" value="${requestScope.from}"/>
                <input type="hidden" name="to" value="${requestScope.to}"/>
                <input type="hidden" name="routeId" value="${requestScope.routeId}"/>
                <input type="text" maxlength="32" placeholder="carriage" name="carriage" required="required"/>
                <input type="text" maxlength="32" placeholder="place" name="place" required="required"/>
                <input type="submit" class="sub" value="buy"/>
            </form>
        </div>
    </div>

</body>
</html>
