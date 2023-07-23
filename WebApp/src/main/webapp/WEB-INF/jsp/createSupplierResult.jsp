<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include  file="../../css/style.css" %>
    </style>
    <meta charset="UTF-8">
    <title>semicolon</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
    <%--<link rel="stylesheet" href="../../css/style.css">
    --%>
    <%--<link rel="stylesheet" href=".">--%>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</head>
<body>
<div class="container-fluid px-0">
    <div class="row no-gutters">
        <div class="col-3 col-md-3 col-xl-2" id="sidebar-html">
            <%@include file="../../pages/sidebar.html" %>
            <c:import url="include/show-message.jsp"/>
        </div>
        <div class="col-12 col-md-9" style="margin-left: 2%; margin-top: 5%">
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <form method="POST" action="supplier">
                                <div class="form-row">
                                    <div class="col">
                                        <label for="name">Name:</label>
                                        <input id="name"
                                               placeholder="supplier name"
                                               name="supplierName"
                                               type="text"
                                               class="form-control"/>
                                    </div>
                                    <div class="col">
                                        <label for="email">Email:</label>
                                        <input id="email" name="supplierEmail"
                                               placeholder="example@unipd.it"
                                               type="email" class="form-control"/>
                                    </div>
                                    <div class="col">
                                        <label for="telephoneNumber">Telephone Number:</label>
                                        <input id="telephoneNumber"
                                               name="supplierTelephoneNumber"
                                               type="tel"
                                               class="form-control"
                                               placeholder="123-45-678"
                                               pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
                                               required/>
                                    </div>
                                </div>
                                <div class="form-row mt-3">
                                    <div class="col">
                                        <label for="address">Address:</label>
                                        <input id="address"
                                               name="supplierAddress"
                                               type="text"
                                               class="form-control"
                                               placeholder="Enter supplier address"/>
                                    </div>
                                </div>
                                <br>
                                <br>
                                <div class="form-row mt-3 text-right">
                                    <div class="col">
                                        <button class="btn btn-secondary" type="reset">Reset the form</button>
                                        <button class="btn btn-custom test-ali" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form>
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
