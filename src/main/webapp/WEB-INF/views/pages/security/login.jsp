<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <title>Login Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

<div class="container col-md-offset-3 col-md-6 login-form">
    <div class="panel panel-success">

        <div class="panel-heading">
            <h3 class="text-center"><spring:message code="login.form-title"/></h3>
        </div>

        <div class="panel-body bg-warning text_font">
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger text-center text_font">
                    <p><spring:message code="login.error"/></p>
                </div>
            </c:if>

            <c:url var="loginUrl" value="/login" />
            <form action="${loginUrl}" method="post" class="form-horizontal">

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 col-md-offset-1 control-lable" for="username">
                            <spring:message code="login.username"/>
                        </label>
                        <div class="col-md-7">
                            <input class="form-control input-sm" type="text" id="username" name="j_username" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 col-md-offset-1 control-lable" for="password">
                            <spring:message code="login.password"/>
                        </label>
                        <div class="col-md-7">
                            <input class="form-control input-sm" type="password" id="password" name="j_password" required>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />

                <div class="row">
                    <div class="form-actions col-md-offset-4 col-md-4">
                        <input type="submit"
                               value="<spring:message code="login.button-login"/>"
                               class="btn btn-success btn-block ">
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="form-actions col-md-offset-4 col-md-4">
                    <a class="btn btn-primary btn-block" href="<c:url value='/start-page' />">
                        <spring:message code="login.botton-startpage"/>
                    </a>
                </div>
            </div>

        </div>

    </div>
</div>

</body>
</html>