<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
<head>
    <title>Settings</title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
    <script type="text/javascript" src="script/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="style/jquery-ui.css">
</head>

<body>

<div id="wrap" class="container">
    <c:set var="title" value="login"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-24 last">
        <div id="tickets" class="column span-16 last">
            <c:if test="${requestScope.tickets != null}">
                    <table class="table table-hover">
                        <tr>
                            <th>Route No</th>
                            <th>carriage</th>
                            <th>place</th>
                        </tr>

                        <c:forEach items="${requestScope.tickets}" var="r">
                            <tr>
                                <td> ${r.getRoute().getId()} </td>
                                <td> ${r.getCarriageNo()}</td>
                                <td> ${r.getPlaceNo()}</td>
                            </tr>
                        </c:forEach>

                    </table>
            </c:if>

        </div>
        <div id="sidebar" class="column span-8 last">
            <form action="/controller?command=settings_pane" method="post" class="form">
                <input type="hidden" name="command" value="settings_pane"/>
                <p>${requestScope.errorMessage}</p>
                <select title="Language" name="language">
                    <option>English</option>
                    <option>Spanish</option>
                    <option>Russian</option>
                </select>
                <input type="text" maxlength="32" placeholder="name" name="name"  />
                <input type="text" maxlength="32" placeholder="surname" name="surname" />
                <input type="submit" class="sub" value="save"/>
            </form>
        </div>
    </div>
</div>

</body>
</html>