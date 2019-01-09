<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="startHeader">
    <div class="container-fluid text-center" >

        <div class="row no_padding_margin" >

            <div class="col-md-4 col-md-offset-4 text_font">
                <div class="btn-group-justified">
                    <a class="btn btn-lg btn-primary" href="<c:url value='/start-page' />">
                        <small><spring:message code="start-header.button-Info"/></small>
                    </a>
                    <a class="btn btn-lg btn-primary" href="<c:url value='/grantfund/list' />">
                        <small><spring:message code="start-header.button-ListOfGrantFunds"/></small>
                    </a>
                </div>
            </div>

            <div class="col-md-2 col-md-offset-1 text-center">

                <sec:authorize access="isAnonymous()">
                    <a class="btn btn-success" href="<c:url value='/login' />">
                        <spring:message code="start-header.button-Login"/>
                    </a>
                    <a class="btn btn-success" href="<c:url value='/client/signup-form' />">
                        <spring:message code="start-header.button-Signup"/>
                    </a>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                        <a class="btn btn-success" href="<c:url value='/user/redirect-by-role' />">
                            <spring:message code="start-header.button-UserMenu"/>
                        </a>
                        <input class="btn btn-danger " type="submit" value="<spring:message code="start-header.button-Logout"/>" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </sec:authorize>

            </div>

            <div class="col-md-1 text-right">
                <c:if test="${pageContext.request.method == 'GET'}">
                    <a class="btn btn-xs btn-default" href="${pageContext.request.pathInfo}?languageVar=en">EN</a>
                    <a class="btn btn-xs btn-default" href="${pageContext.request.pathInfo}?languageVar=ru">RU</a>
                </c:if>
            </div>

        </div>

    </div>
</div>
