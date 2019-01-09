<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container col-md-6 col-md-offset-3">

    <div class="panel panel-warning">

        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="client-details-list.title"/></h3>
        </div>

        <div class="panel-body text-left">

            <div class="row">
                <div class="col-md-4 col-md-offset-2">
                    <h4><spring:message code="client-details-list.address"/></h4>
                </div>
                <div class="col-md-3">
                    <h4>${clientDetails.address}</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4 col-md-offset-2">
                    <h4><spring:message code="client-details-list.phone"/></h4>
                </div>
                <div class="col-md-3">
                    <h4>${clientDetails.phone}</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4 col-md-offset-2">
                    <h4><spring:message code="client-details-list.email"/></h4>
                </div>
                <div class="col-md-3">
                    <h4>${clientDetails.email}</h4>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <a class="btn btn-warning btn-block" href="<c:url value='/client-details/edition-form' />">
                    <spring:message code="client-details-list.button-Edit"/>
                </a>
            </div>
        </div><br/>

    </div>

</div>