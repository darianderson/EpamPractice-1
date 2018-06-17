<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
<head>
    <title>Home</title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
</head>

<body>

<div id="wrap" class="container">
    <c:set var="title" value="login"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-15 last">
        <h1>Main content</h1>
    </div>
</div>

</body>
</html>