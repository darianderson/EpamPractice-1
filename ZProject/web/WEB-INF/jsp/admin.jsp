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
            <div class="station-list">
                <h1>Station List</h1>
                <div class="row">
                    <div class="col-lg-9 col-12">
                        <div class="box-body">
                            <div class="table-responsive">
                                <table id="station-list" class="table mt-0 table-hover no-wrap table-striped table-bordered"
                                       data-page-size="10">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name of Station</th>
                                        <th>city</th>
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
                                <form class="add_form" action="/controller?command=ticket_search" method="post">
                                    <input type="hidden" name="command" value="Station_add"/>
                                    <input type="text" name="name-of-station" id="station-name"
                                           placeholder="Name of Station">
                                    <input type="text" name="city" id="city" placeholder="city">
                                    <input type="text" name="land" id="land" placeholder="land">
                                    <input type="submit" class="submit" value="Add">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ways-list">
                <h1>Ways List</h1>
                <div class="row">
                    <div class="col-lg-9 col-12">
                        <div class="box-body">
                            <div class="table-responsive">
                                <table id="tickets" class="table mt-0 table-hover no-wrap table-striped table-bordered"
                                       data-page-size="10">
                                    <thead>
                                    <tr>
                                        <th>Way-ID</th>
                                        <th>From</th>
                                        <th>To</th>
                                        <th>Time</th>
                                        <th>Last Mod.</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>111</td>
                                        <td>Knowhere</td>
                                        <td>Anywhere</td>
                                        <td>1  light year</td>
                                        <td>14-11-2017</td>
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
                                <form class="add_form" action="/controller?command=ticket_search" method="post">
                                    <input type="hidden" name="command" value="Way_add"/>
                                    <input type="text" name="from-station" id="from-station"
                                           placeholder="From">
                                    <input type="text" name="to-station" id="to-station" placeholder="To">
                                    <input type="text" name="duration" id="duration" placeholder="Duration">
                                    <input type="submit" class="submit" value="Add">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>