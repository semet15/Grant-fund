<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container col-md-offset-3 col-md-6">
    <div class="panel panel-success">

        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="client-details-edition.title"/></h3>
        </div>

        <div class="panel-body bg-warning text_font">

            <%--@elvariable id="clientDetails" type="com.grant_fund.model.ClientDetails"--%>
            <form:form method="POST"
                       modelAttribute="clientDetails"
                       cssClass="form-horizontal"
                       action="${pageContext.request.contextPath}/client-details/edit">

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="address" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="client-details-edition.address"/>
                        </form:label>
                        <div class="col-md-4">
                            <form:input type="text" path="address" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="address" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="phone" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="client-details-edition.phone"/>
                        </form:label>
                        <div class="col-md-4">
                            <form:input type="text" path="phone" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="phone" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="email" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="client-details-edition.email"/>
                        </form:label>
                        <div class="col-md-4">
                            <form:input type="text" path="email" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="email" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions col-md-offset-4 col-md-4">
                        <input type="submit"
                               value="<spring:message code="client-details-edition.button-Accept"/>"
                               class="btn btn-success btn-block ">
                    </div>
                </div>

            </form:form>

        </div>

    </div>
</div>