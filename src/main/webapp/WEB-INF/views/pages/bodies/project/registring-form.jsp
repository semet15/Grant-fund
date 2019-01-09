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
            <h3 class="text-center"><spring:message code="project-registring.title"/></h3>
        </div>

        <div class="panel-body bg-warning text_font">
            <%--@elvariable id="project" type="com.grant_fund.model.Project"--%>
            <form:form method="POST"
                       modelAttribute="project"
                       cssClass="form-horizontal"
                       action="${pageContext.request.contextPath}/project/registring">

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="name" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="project-registring.name"/>
                        </form:label>
                        <div class="col-md-6">
                            <form:input type="text" path="name" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="name" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="expertMark" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="project-registring.expertMark"/>
                        </form:label>
                        <div class="col-md-6">
                            <form:input type="text" path="expertMark" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="expertMark" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="sum" cssClass="col-md-4 col-md-offset-1 control-lable">
                            <spring:message code="project-registring.sum"/>
                        </form:label>
                        <div class="col-md-6">
                            <form:input type="text" path="sum" cssClass="form-control input-sm"/>
                            <div class="has-error text-danger">
                                <form:errors path="sum" cssClass="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions col-md-offset-4 col-md-4">
                        <input type="submit"
                               value="<spring:message code="project-registring.button-Register"/>"
                               class="btn btn-success btn-block ">
                    </div>
                </div>

            </form:form>
        </div>

    </div>
</div>