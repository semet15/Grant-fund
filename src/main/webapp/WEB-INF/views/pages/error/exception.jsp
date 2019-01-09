<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <title>Error</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<br/>

<div class="container">

    <div class="panel panel-danger">

        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="exception.title"/></h3>
        </div>

        <div class="panel-body text-left">

            <div class="row col-md-offset-1">
                <h3>${exception.toString()}</h3>
            </div>

        </div>

    </div>

</div>

</body>
</html>
