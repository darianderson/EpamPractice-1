<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title><fmt:message key='jsp.Buy'/></title>
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


                <select name="type">
                    <option value="compartment" name="compartment"><fmt:message key='jsp.compartment'/></option>
                    <option value="berth" name="berth"><fmt:message key='jsp.berth'/></option>
                    <option value="common" name="common"><fmt:message key='jsp.common'/></option>
                </select>

                <input type="text" maxlength="32" placeholder="<fmt:message key='jsp.carriage'/>" name="carriage" required="required"/>
                <input type="text" maxlength="32" placeholder="<fmt:message key='jsp.place'/>" name="place" required="required"/>
                <input type="submit" class="sub" value="<fmt:message key='jsp.Buy'/>"/>
            </form>
        </div>
    </div>

</body>
</html>
