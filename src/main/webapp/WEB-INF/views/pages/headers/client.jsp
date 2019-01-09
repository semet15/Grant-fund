<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="clientHeader">
    <div class="container-fluid text-center" >

        <div class="row">

            <div class="col-md-6 col-md-offset-3">
                <div class="btn-group-justified">
                    <a class="btn btn-primary btn-lg " href="<c:url value='/client/' />">
                        <small><spring:message code="client-header.button-ListOfGrantFunds"/></small>
                    </a>
                    <a class="btn btn-primary btn-lg " href="<c:url value='/project/list' />">
                        <small><spring:message code="client-header.button-ListOfProjects"/></small>
                    </a>
                    <a class="btn btn-primary btn-lg " href="<c:url value='/client-details/list' />">
                        <small><spring:message code="client-header.button-ContactDetails"/></small>
                    </a>
                </div>
            </div>

            <div class="row col-md-3 no_padding_margin">

                <div class="col-md-7 text text-right">
                    <h4>${client.getFirstname()} ${client.getLastname()}</h4>
                </div>

                <div class="col-md-2 text-center">
                    <sec:authorize access="isAuthenticated()">
                        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                            <input class="btn btn-danger" type="submit" value="<spring:message code="client-header.button-Logout"/>" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form:form>
                    </sec:authorize>
                </div>

                <div class="col-md-3 text-right">
                    <c:if test="${pageContext.request.method == 'GET'}">
                        <a class="btn btn-xs btn-default" href="${pageContext.request.pathInfo}?languageVar=en">EN</a>
                        <a class="btn btn-xs btn-default" href="${pageContext.request.pathInfo}?languageVar=ru">RU</a>
                    </c:if>
                </div>

            </div>

        </div>

    </div>
</div>