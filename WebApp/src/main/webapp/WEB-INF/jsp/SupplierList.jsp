<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include  file="../../css/supplierListJsp.css" %>
        <%@include  file="../../css/style.css" %>
    </style>
    <meta charset="UTF-8">
    <title>semicolon</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="container-fluid px-0">
    <div class="row no-gutters">
        <div class="col-3 col-md-3 col-xl-2" id="sidebar-html">
            <%@include  file="../../pages/sidebar.html" %>
            <c:import url="include/show-message.jsp"/>
        </div>
        <div class="col-12 col-md-9" style="margin-left: 2%">
            <div class="row" style="margin-top: 2%">
                <div class="col-lg-12">
                    <h4 style="margin-bottom: 1%; font-weight: bold;">Supplier</h4>
                </div>
                <div class="col-lg-6">
                    <div class="search-box">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search..."
                                   id="search-input">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="add-btn text-lg-right">
                        <button class="btn btn-custom">Add supplier</button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div test='${not empty suppliers && !message.error}'>
                        <div class="table-responsive" style=" overflow-x:auto">
                            <table class="table table-hover " style="border-radius: 10px !important;">
                                <thead>
                                <tr class="">
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Address</th>
                                    <th>Telephone Number</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${suppliers}" var="supplier">
                                    <tr>
                                        <td>${supplier.name}</td>
                                        <td>${supplier.email}</td>
                                        <td>${supplier.address}</td>
                                        <td>${supplier.telephoneNumber}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../../js/supplierListJsp.js" type=""></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
</body>
</html>

