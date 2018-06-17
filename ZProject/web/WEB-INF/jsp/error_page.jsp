<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
    <c:set var="title" value="error"/>
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>

    <h1 align="center">${requestScope.errorMessage}</h1>

</body>
</html>
