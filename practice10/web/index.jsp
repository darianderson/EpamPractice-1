<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MTable</title>
</head>
<body>


<%
    StringBuilder sb = new StringBuilder();
    sb.append("<table border=\"1\" style=\"font-size: 1.5em;\">");
    for(int i=0; i<10; ++i){
        sb.append("<tr>");
        for(int j=0; j<10; ++j){
            sb.append("<td>");
            if (i == 0 && j == 0) continue;
            if (i==0)
                sb.append(j);
            else if (j==0)
                sb.append(i);
            else
                sb.append(i*j);

            sb.append("</td>");
        }

        sb.append("</tr>");
    }
    sb.append("</table>");
%>

<%= sb.toString() %>

<hr>
<table border="1" style="font-size: 1.5em;">
    <c:forEach var="j" begin="0" end="9">
        <tr>
            <c:forEach var="i" begin="0" end="9">
                <c:choose>
                    <c:when test="${(i==0) || (j==0)}">
                        <c:choose>
                            <c:when test="${i==0 && j==0}">
                                <td></td>
                            </c:when>
                            <c:when test="${i==0}">
                                <td>${j}</td>
                            </c:when>
                            <c:when test="${j==0}">
                                <td>${i}</td>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <td>${i*j}</td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </c:forEach>
</table>



</body>
</html>
