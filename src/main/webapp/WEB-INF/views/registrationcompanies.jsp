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
    <title>Add New Company</title>

    <style>

         .error {
             color: #ff0000;
         }

         .errorblock {
             color: #000;
             background-color: #ffEEEE;
             border: 3px solid #ff0000;
             padding: 8px;
             margin: 16px;
         }

    </style>
</head>


<body>
    <nav class="navbar navbar-inverse" style="margin-bottom:0px;">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="<c:url value='/companies' />">Upraised</a>
            </div>
            <ul class="nav navbar-nav">
              <li class="active"><a href="<c:url value='/newCompany' />">Add New Company</a></li>
              <li><a href="<c:url value='/newOpening' />">Add New Opening</a></li>
              <li><a href="<c:url value='/companies' />">Companies</a></li>
              <li><a href="<c:url value='/openings' />">Openings</a></li>
            </ul>
          </div>
    </nav>
    <div class="page-wrapper bg-blue p-t-180 p-b-100 font-robo" style="padding-top:100px;">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-body" style="padding:10px;">
                    <h2>Add New Company</h2>
                    <form:form method="POST" modelAttribute="company">
                    <form:errors path = "*" cssClass = "errorblock" element = "div" />
                    <form:input type="hidden" path="id" id="id"/>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Company Name" name="name" path="name" id="name"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Company Type" name="company_type" path="company_type" id="company_type"/>
                        <form:errors path="company_type" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Location" name="location" path="location" id="location"/>
                        <form:errors path="location" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Founder Name" name="founder_name" path="founder_name" id="founder_name"/>
                        <form:errors path="founder_name" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Founder Description" name="founder_desc" path="founder_desc" id="founder_desc"/>
                        <form:errors path="founder_desc" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Company Description" name="company_desc" path="company_desc" id="company_desc"/>
                        <form:errors path="company_desc" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Company Link" name="company_link" path="company_link" id="company_link"/>
                        <form:errors path="company_link" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Introductory Line" name="intro" path="intro" id="intro"/>
                        <form:errors path="intro" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Company Domain" name="domain" path="domain" id="domain"/>
                        <form:errors path="domain" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Min Employees" name="employees_min" path="employees_min" id="employees_min"/>
                        <form:errors path="employees_min" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Max Employees" name="employees_max" path="employees_max" id="employees_max"/>
                        <form:errors path="employees_max" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" placeholder="Logo" type="file" name="logo" path="logo" id="logo"/>
                        <form:errors path="logo" cssClass="error"/>
                    </div>
                    <div class="p-t-30">
                        <c:choose>
                            <c:when test="${edit}">
                                <input class="btn btn--radius btn--green" type="submit" value="Update"/>
                            </c:when>
                            <c:otherwise>
                                <input class="btn btn--radius btn--green" type="submit" value="Register"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    </form:form>
                </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>