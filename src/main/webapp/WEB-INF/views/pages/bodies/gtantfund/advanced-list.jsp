<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container col-md-12">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <c:if test="${not empty param.error}">
                <div class="alert alert-danger text-center">
                    <p><spring:message code="advanced-grantfund-list.error"/></p>
                </div>
            </c:if>

            <a class="btn btn-success btn-block" href="<c:url value='/grantfund/creating-form' />">
                <spring:message code="advanced-grantfund-list.create"/>
            </a>

        </div>
    </div><br/>

    <div class="panel panel-warning">

        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="advanced-grantfund-list.title"/></h3>
        </div>

        <div class="panel-body">
            <table class="table table-hover text-center text_font_small">

                <thead>
                    <tr>
                        <th></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.idFund"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.state"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.sum"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.minSum"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.endingDate"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.idProject"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.nameProject"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.sumProject"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.expertMark"/></th>
                        <th class="text-center"><spring:message code="advanced-grantfund-list.registringDate"/></th>
                    </tr>
                </thead>

                <c:forEach items="${grantfunds}" var="grantfund">
                    <tbody>
                        <tr class="row_fund">
                            <td>
                                <c:if test="${grantfund.state.toString() == 'ACTIVE'}">
                                    <a class="btn btn-warning btn-block btn-xm"
                                       href="<c:url value='/grantfund/distribution-form' />">
                                        <spring:message code="advanced-grantfund-list.distribute"/>
                                    </a>
                                </c:if>
                            </td>
                            <td>${grantfund.grantFundId}</td>

                            <c:if test="${grantfund.state.toString() == 'ACTIVE'}">
                                 <td class="state_green">${grantfund.state}</td>
                                 <td><spring:message code="advanced-grantfund-list.not-yet"/></td>
                                 <td><spring:message code="advanced-grantfund-list.not-yet"/></td>
                             </c:if>

                            <c:if test="${grantfund.state.toString() == 'FINISHED'}">
                                 <td class="state_red">${grantfund.state}</td>
                                 <td>${grantfund.grantFundSum}</td>
                                 <td>${grantfund.minSum}</td>
                            </c:if>

                            <ftm:formatDate value="${grantfund.endingDate}"
                                            var="fmtDate"
                                            type="date"
                                            pattern="dd-MM-yyyy HH:mm" />
                            <td>${fmtDate}</td>
                            <td></td><td></td><td></td><td></td><td></td>

                            <c:forEach items="${grantfund.projects}" var="project">
                                <tr class="row_project">
                                    <td></td><td></td><td></td><td></td><td></td><td></td>
                                    <td>${project.projectId}</td>
                                    <td>${project.name}</td>

                                    <td>
                                        <c:if test="${project.grantFund.state.toString() == 'FINISHED' and project.sum == 0}">
                                            <spring:message code="advanced-grantfund-list.noGrant"/>
                                        </c:if>
                                        <c:if test="${not (project.grantFund.state.toString() == 'FINISHED' and project.sum == 0)}">
                                            ${project.sum}
                                        </c:if>
                                    </td>

                                    <td>${project.expertMark}</td>
                                    <ftm:formatDate value="${project.date}"
                                                    var="fmtProjectDate"
                                                    type="date"
                                                    pattern="dd-MM-yyyy HH:mm" />
                                    <td>${fmtProjectDate}</td>
                                </tr>
                            </c:forEach>

                        </tr>
                    </tbody>
                </c:forEach>

            </table>
        </div>

    </div>

</div>