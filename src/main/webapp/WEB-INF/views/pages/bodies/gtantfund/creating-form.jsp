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
            <h3 class="text-center" ><spring:message code="grantfund-creating.title"/></h3>
        </div>

        <div class="panel-body bg-warning text_font">
            <%--@elvariable id="grantfund" type="com.grant_fund.model.GrantFund"--%>
            <form:form method="POST"
                       modelAttribute="grantfund"
                       cssClass="form-horizontal"
                       action="${pageContext.request.contextPath}/grantfund/creating">

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="endingDate" cssClass="row col-md-8 col-md-offset-3 control-lable">
                            <spring:message code="grantfund-creating.endingDate"/>
                            <small><spring:message code="grantfund-creating.format"/></small>
                        </form:label>
                        <div class="row  col-md-6 col-md-offset-3">
                            <form:input type="text" path="endingDate" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="endingDate" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions col-md-offset-4 col-md-4">
                        <input type="submit"
                               value="<spring:message code="grantfund-creating.button-Create"/>"
                               class="btn btn-success btn-block ">
                    </div>
                </div>

            </form:form>
         </div>

    </div>
</div>