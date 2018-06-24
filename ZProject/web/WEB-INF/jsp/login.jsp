<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

  <head>
      <title><fmt:message key='jsp.login.Sign_In'/></title>
      <%@ include file="/WEB-INF/jspf/links.jspf" %>

  </head>


  <body id="body">


      <div id="wrap" class="container">
          <%@ include file="/WEB-INF/jspf/header.jspf" %>
      </div>
        <c:if test="${sessionScope.login != null}">
            <jsp:forward page = "/WEB-INF/jsp/index.jsp" />
        </c:if>

      <div class="login-page">
          <h1>Railway Express</h1>
          <div class="form">
              <p id="info">${requestScope.signinError}</p>
              <form action="controller" method="post"  class="register-form">
                  <input type="hidden" name="command" value="register"/>
                  <input type="text" maxlength="32" autofocus="autofocus" placeholder="<fmt:message key='jsp.login.username'/>" name="login" required="required"/>
                  <input type="password" maxlength="32" placeholder="<fmt:message key='jsp.login.password'/>" name="pass" required="required" />
                  <input type="password" maxlength="32" placeholder="<fmt:message key='jsp.login.repeat_password'/>" name="pass_repeat" required="required"/>
                  <input type="submit" class="sub" value="create"/>
                  <p class="message"><fmt:message key='jsp.login.already_registered'/><a onclick="onCreateClick()" href="#"><fmt:message key='jsp.login.Sign_In'/></a></p>
              </form>
              <form action="controller" method="post" class="login-form">
                  <input type="hidden" name="command" value="login"/>
                  <input type="text" placeholder="<fmt:message key='jsp.login.username'/>" name="login" required="required"/>
                  <input type="password" placeholder="<fmt:message key='jsp.login.password'/>" name="pass" required="required"/>
                  <input type="submit" class="sub" value="login"/>
                  <p class="message"><fmt:message key='jsp.login.not_registered'/> <a onclick="onCreateClick()" href="#"><fmt:message key='jsp.login.create_account'/></a></p>
              </form>
              <c:if test="${requestScope.isRegistrLoaded == true}">
                  <script>$('form').animate({height: "toggle", opacity: "toggle"}, 1);</script>
              </c:if>
          </div>
      </div>

  </body>
</html>
