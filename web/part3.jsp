<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="form" method="post">
        <input type="text" name="myname">
        <input type="submit">
    </form>
    <h1> ${sessionScope.get("names")}</h1>
    <a href="form?remove=true">remove</a>
</body>
</html>
