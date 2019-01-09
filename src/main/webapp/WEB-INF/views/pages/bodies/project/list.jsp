<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container col-md-12">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger text_font text-center">
                    <p><spring:message code="project-list.error"/></p>
                </div>
            </c:if>

            <a class="btn btn-success btn-block" href="<c:url value='/project/registring-form' />">
                <spring:message code="project-list.register"/>
            </a>
        </div>
    </div><br/>

    <div class="panel panel-warning">

        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="project-list.title"/></h3>
        </div>

        <div class="panel-body">
            <table class="table table-hover text-center">

                <thead>
                    <tr>
                        <th class="text-center"><spring:message code="project-list.idProject"/></th>
                        <th class="text-center"><spring:message code="project-list.name"/></th>
                        <th class="text-center"><spring:message code="project-list.sum"/></th>
                        <th class="text-center"><spring:message code="project-list.expertMark"/></th>
                        <th class="text-center"><spring:message code="project-list.registringDate"/></th>
                        <th class="text-center"><spring:message code="project-list.idFund"/></th>
                        <th class="text-center"><spring:message code="project-list.state"/></th>
                        <th class="text-center"><spring:message code="project-list.endingDate"/></th>
                    </tr>
                </thead>

                <c:forEach items="${projects}" var="project">
                    <tbody>
                        <tr>
                            <td>${project.projectId}</td>
                            <td>${project.name}</td>

                            <td>
                                <c:if test="${project.grantFund.state.toString() == 'FINISHED' and project.sum == 0}">
                                    <spring:message code="project-list.noGrant"/>
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
                            <td>${project.grantFund.grantFundId}</td>

                            <c:if test="${project.grantFund.state.toString() == 'ACTIVE'}">
                                <td class="state_green">${project.grantFund.state}</td>
                            </c:if>

                            <c:if test="${project.grantFund.state.toString() == 'FINISHED'}">
                                <td class="state_red">${project.grantFund.state}</td>
                            </c:if>

                            <ftm:formatDate value="${project.grantFund.endingDate}"
                                            var="fmtGrantFundDate"
                                            type="date"
                                            pattern="dd-MM-yyyy HH:mm" />
                            <td>${fmtGrantFundDate}</td>
                        </tr>
                    </tbody>
                </c:forEach>

            </table>
        </div>

    </div>

</div>