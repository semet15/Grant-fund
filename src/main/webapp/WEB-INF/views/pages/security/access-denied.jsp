<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <title>Access Denied</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<br/>

    <div class="container">
        <div class="panel-heading text-center bg-danger">
            <h3><spring:message code="access-denied.access-denied"/></h3>

            <a class="btn btn-primary" href="<c:url value='/'/>">
                <spring:message code="access-denied.start-page"/>
            </a>
            <br/>
        </div>
    </div>

</body>
</html>
