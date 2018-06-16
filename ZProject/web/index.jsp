<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 6/16/2018
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <div align="center">
    <p>${sessionScope.login}</p>
    <form action="/controller" method="post">
      <label>
        login: <input type="text" name="login">
      </label>
      <label>
        password <input type="password" name="pass">
      </label>
      <input type="submit">
    </form>
  </div>
  </body>
</html>
