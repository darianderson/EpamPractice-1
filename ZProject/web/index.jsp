<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>

  <%@ include file="WEB-INF/jspf/head.jspf" %>
  <body>

  <div id="wrap" class="container">
      <c:set var="title" value="login"/>
    <%@ include file="WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-15 last">

    </div>
  </div>

  <c:choose>
      <c:when test="${sessionScope.login == null}">
          <div align="center" style="margin-top: 300px;">
            <form action="controller" method="post">
              <input type="hidden" name="command" value="login"/>

              <label>
                login: <input type="text" name="login">
              </label><br>

              <label>
                password: <input type="password" name="pass">
              </label> <br>

              <input type="submit">
            </form>
          </div>
      </c:when>
      <c:otherwise>
          <h1>Main content</h1>
      </c:otherwise>
  </c:choose>


  </body>
</html>
