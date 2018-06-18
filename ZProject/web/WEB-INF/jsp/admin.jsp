<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
        <div class="admin-box">
            <h1>Station List</h1>
            <div class="row">
                <div class="col-lg-9 col-12">
                    <div class="box-body">
                        <div class="table-responsive">
                            <table id="tickets" class="table mt-0 table-hover no-wrap table-striped table-bordered"
                                   data-page-size="10">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name of Station</th>
                                    <th>Sity</th>
                                    <th>Land</th>
                                    <th>Last Mod.</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>101</td>
                                    <td>Knowhere</td>
                                    <td>Charkow</td>
                                    <td>Marvel</td>
                                    <td>14-10-2017</td>
                                    <td>
                                        <button type="button"
                                                class="btn btn-sm btn-green-outline"
                                                data-toggle="tooltip"
                                                data-original-title="Modify">Modify
                                        </button>
                                        <button type="button"
                                                class="btn btn-sm btn-danger-outline"
                                                data-toggle="tooltip"
                                                data-original-title="Delete">Delete
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>