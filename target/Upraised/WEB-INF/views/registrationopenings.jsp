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
    <title>Add New Job Opening</title>

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
              <li><a href="<c:url value='/newCompany' />">Add New Company</a></li>
              <li class="active"><a href="<c:url value='/newOpening' />">Add New Opening</a></li>
              <li><a href="<c:url value='/companies' />">Companies</a></li>
              <li><a href="<c:url value='/openings' />">Openings</a></li>
            </ul>
          </div>
    </nav>
    <div class="page-wrapper bg-blue p-t-180 p-b-100 font-robo" style="padding-top:100px;">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-body" style="padding:10px;">
                    <h2>Add New Job Opening</h2>
                    <form:form method="POST" modelAttribute="openings">
                    <form:errors path = "*" cssClass = "errorblock" element = "div" />
                    <form:input type="hidden" path="id" id="id" />
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Job Id" name="job_id" path="job_id" id="job_id"/>
                        <form:errors path="job_id" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Job Title" name="job_title" path="job_title" id="job_title"/>
                        <form:errors path="job_title" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Location" name="location" path="location" id="location"/>
                        <form:errors path="location" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Seniority Level, possible values : senior, associate or entry" name="seniority_level" path="seniority_level" id="seniority_level"/>
                        <form:errors path="seniority_level" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Job Description" name="job_description" path="job_description" id="job_description"/>
                        <form:errors path="job_description" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Skills Required" name="skills_required" path="skills_required" id="skills_required"/>
                        <form:errors path="skills_required" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Job Link" name="job_link" path="job_link" id="job_link"/>
                        <form:errors path="job_link" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Salary" name="salary" path="salary" id="salary"/>
                        <form:errors path="salary" cssClass="error"/>
                    </div>
                    <div class="input-group">
                        <form:input class="input--style-2" type="text" placeholder="Posting Date" name="postingDate" path="postingDate" id="postingDate"/>
                        <form:errors path="postingDate" cssClass="error"/>
                    </div>
                    <c:choose>
                        <c:when test="${not edit}">
                            <div class="input-group">
                                <form:input class="input--style-2" type="text" placeholder="Company" name="company" path="company" id="company"/>
                                <form:errors path="company" cssClass="error"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form:input type="hidden" path="company" id="company"/>
                        </c:otherwise>
                    </c:choose>
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