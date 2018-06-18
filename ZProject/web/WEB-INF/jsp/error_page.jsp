<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
    <head>
        <title>Error</title>
        <%@ include file="/WEB-INF/jspf/links.jspf"%>
    </head>
<body>

    <h1 align="center">${requestScope.errorMessage}</h1>

</body>
</html>
