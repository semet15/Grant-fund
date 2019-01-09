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
            <h3 class="text-center" ><spring:message code="grantfund-distributing.title"/></h3>
        </div>

        <div class="panel-body bg-warning text_font">

            <c:if test="${not empty warning}">
                <div class="alert alert-danger text-center">
                    <p><spring:message code="grantfund-distributing.warning"/></p>
                </div>
            </c:if>

            <%--@elvariable id="grantfund" type="com.grant_fund.model.GrantFund"--%>
            <form:form method="POST"
                       modelAttribute="grantfund"
                       cssClass="form-horizontal"
                       action="${pageContext.request.contextPath}/grantfund/distribution">

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="grantFundSum" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="grantfund-distributing.sum"/>
                        </form:label>
                        <div class="col-md-4">
                            <form:input type="text" path="grantFundSum" cssClass="form-control input-sm" />
                            <div class="has-error text-danger">
                                <form:errors path="grantFundSum" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="minSum" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="grantfund-distributing.minSum"/>
                        </form:label>
                        <div class="col-md-4">
                            <form:input type="text" path="minSum" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="minSum" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions col-md-offset-4 col-md-4">
                        <input type="submit"
                               value="<spring:message code="grantfund-distributing.buttonDistribute"/>"
                               class="btn btn-success btn-block ">
                    </div>
                </div>

            </form:form>

        </div>

    </div>
</div>