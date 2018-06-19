<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
<head>
    <title>Home</title>
    <%@ include file="/WEB-INF/jspf/links.jspf" %>
    <script type="text/javascript" src="script/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="style/jquery-ui.css">
</head>

<body>

<div id="wrap" class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div id="content" class="column span-24 last">

        <c:if test="${requestScope.stations == null}">
            <jsp:forward page="/controller?command=get_stations"/>
        </c:if>


        <script>
            $( function() {
                var stations = [
                    <c:forEach items="${requestScope.stations}" var="station">
                        "${station}",
                    </c:forEach>
                ];
                $( "#start" ).autocomplete({
                    source: stations
                });
                $( "#end" ).autocomplete({
                    source: stations
                });
            } );
        </script>

        <form id="search_form" action="/controller?command=ticket_search" method="post">
            <input type="hidden" name="command" value="ticket_search"/>
            <input type="text" name="from" id="start" placeholder="from">
            <input type="text" name="to" id="end" placeholder="to">
            <input type="submit" id="submit" value="search">
        </form>

        <c:if test="${requestScope.appRoute != null}">
            <div class="form" style="max-width: 100%; margin-top: 50px;font-size: 1.5em;">
                <table class="table table-hover">
                    <tr>
                        <th>Route No</th>
                        <th>Train Model</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                    </tr>

                    <c:forEach items="${requestScope.appRoute}" var="r">
                        <tr>
                            <td> ${r} </td>
                            <td> ${r}</td>
                            <td> ${r}</td>
                            <td> ${r}</td>
                        </tr>
                    </c:forEach>

                </table>

            </div>
        </c:if>
    </div>
</div>

</body>
</html>