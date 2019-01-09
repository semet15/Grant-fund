<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<div class="container col-md-offset-3 col-md-6">

    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="client-signup.title"/></h3>
        </div>
        <div class="panel-body bg-warning text_font">
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger text-center">
                    <p><spring:message code="client-signup.error"/></p>
                </div>
            </c:if>
            <%--@elvariable id="client" type="com.grant_fund.model.Client"--%>
            <form:form method="POST" modelAttribute="client" class="form-horizontal" action="${pageContext.request.contextPath}/client/signup">

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="firstname">
                            <spring:message code="client-signup.firstname"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:input type="text" path="firstname" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="firstname" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="lastname">
                            <spring:message code="client-signup.lastname"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:input type="text" path="lastname" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="lastname" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="username">
                            <spring:message code="client-signup.username"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:input type="text" path="username" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="username" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="password">
                            <spring:message code="client-signup.password"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:password path="password" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="password" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="clientDetails.address">
                            <spring:message code="client-signup.address"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:input type="text" path="clientDetails.address" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="clientDetails.address" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="clientDetails.phone">
                            <spring:message code="client-signup.phone"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:input type="text" path="clientDetails.phone" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="clientDetails.phone" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label cssClass="col-md-2 col-md-offset-1 control-lable" path="clientDetails.email">
                            <spring:message code="client-signup.email"/>
                        </form:label>
                        <div class="col-md-7">
                            <form:input type="text" path="clientDetails.email" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="clientDetails.email" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions col-md-offset-4 col-md-4">
                        <input type="submit" value="<spring:message code="client-signup.button-sugnup"/>" class="btn btn-success btn-block ">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

