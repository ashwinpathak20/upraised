<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel='stylesheet' href='<c:url value="/resources/css/custom.css" />' type='text/css' media='all' />
    <!-- Icons font CSS-->
    <link href='<c:url value="vendor/mdi-font/css/material-design-iconic-font.min.css" />' rel="stylesheet" media="all">
    <link href='<c:url value="vendor/font-awesome-4.7/css/font-awesome.min.css" />' rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href='<c:url value="vendor/select2/select2.min.css" />' rel="stylesheet" media="all">
    <link href='<c:url value="vendor/datepicker/daterangepicker.css" />'  rel="stylesheet" media="all">

    <link rel='stylesheet' href='<c:url value="/resources/css/main.css" />' type='text/css' media='all' />
    <link rel='stylesheet' href='<c:url value="/resources/css/menu.css" />' type='text/css' media='all' />

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Openings</title>

</head>


<body>
    <nav class="navbar navbar-inverse" style="margin-bottom:0px;">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="<c:url value='/companies' />">Upraised</a>
            </div>
            <ul class="nav navbar-nav">
              <li><a href="<c:url value='/newCompany' />">Add New Company</a></li>
              <li><a href="<c:url value='/newOpening' />">Add New Opening</a></li>
              <li><a href="<c:url value='/companies' />">Companies</a></li>
              <li class="active"><a href="<c:url value='/openings' />">Openings</a></li>
            </ul>
          </div>
    </nav>
    <div class="page-wrapper bg-blue p-t-180 p-b-100 font-robo" style="padding-top:100px;">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                    <div class="card-body" style="background-color:#f5f5f0;">
                        <h2>${Openings}</h2>
                        <ul>
                            <c:forEach items="${openings}" var="opening">
                                <li> <a href="<c:url value='/edit-${opening.job_id}-opening' />"> ${opening.job_id} </a> | ${opening.job_title}</li>
                                <p>${opening.seniority_level} | ${opening.location} | <a href="<c:url value='${opening.job_link}' />"> ${opening.job_link} </a> </p>
                                <p>${opening.postingDate} | ${opening.salary}</p>
                                <p>${opening.job_description}</p>
                                <p>${opening.skills_required}</p>
                                <p><a href="<c:url value='/delete-${opening.job_id}-opening' />">delete</a></p>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>