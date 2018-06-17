<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

  <head>
      <title>Sign In</title>
      <%@ include file="/WEB-INF/jspf/links.jspf" %>
      <link rel="stylesheet" type="text/css" media="screen" href="style/form.css">

      <script type="text/javascript" src="script/jquery-3.3.1.min.js"></script>
      <script type="text/javascript" src="script/script.js"></script>
  </head>


  <body>

      <div class="login-page">
          <h1>Railway Express</h1>
          <div class="form">
              <p id="info">${requestScope.signinError}</p>
              <form action="controller" method="post"  class="register-form">
                  <input type="hidden" name="command" value="register"/>
                  <input type="text" placeholder="username" name="login" required="required"/>
                  <input type="password" placeholder="password" name="pass" required="required" />
                  <input type="password" placeholder="repeat password" name="pass_repeat" required="required"/>
                  <input type="submit" class="button" value="create"/>
                  <p class="message">Already registered? <a onclick="onCreateClick()" href="#">Sign In</a></p>
              </form>
              <form action="controller" method="post" class="login-form">
                  <input type="hidden" name="command" value="login"/>
                  <input type="text" placeholder="username" name="login" required="required"/>
                  <input type="password" placeholder="password" name="pass" required="required"/>
                  <input type="submit" class="button" value="login"/>
                  <p class="message">Not registered? <a onclick="onCreateClick()" href="#">Create an account</a></p>
              </form>
              <c:if test="${requestScope.isRegistrLoaded == true}">
                  <script>$('form').animate({height: "toggle", opacity: "toggle"}, 1);</script>
              </c:if>
          </div>
      </div>

  </body>
</html>
