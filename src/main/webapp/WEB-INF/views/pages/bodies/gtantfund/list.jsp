<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">

    <div class="panel panel-warning">

        <div class="panel-heading">
            <h3 class="text-center" ><spring:message code="grantfund-list.title"/></h3>
        </div>

        <div class="panel-body">
            <table class="table table-hover text-center">

                <thead>
                    <tr >
                        <th class="text-center"><spring:message code="grantfund-list.id"/></th>
                        <th class="text-center"><spring:message code="grantfund-list.state"/></th>
                        <th class="text-center"><spring:message code="grantfund-list.sum"/></th>
                        <th class="text-center"><spring:message code="grantfund-list.projectCount"/></th>
                        <th class="text-center"><spring:message code="grantfund-list.endingDate"/></th>
                    </tr>
                </thead>

                <c:forEach items="${grantfunds}" var="grantfund">
                    <tbody>
                        <tr>
                            <td>${grantfund.grantFundId}</td>

                            <c:if test="${grantfund.state.toString() == 'ACTIVE'}">
                                <td class="state_green">${grantfund.state}</td>
                                <td><spring:message code="grantfund-list.not-yet"/></td>
                            </c:if>

                            <c:if test="${grantfund.state.toString() == 'FINISHED'}">
                                <td class="state_red">${grantfund.state}</td>
                                <td>${grantfund.grantFundSum}</td>
                            </c:if>

                            <td>${grantfund.projects.size()}</td>
                            <fmt:formatDate value="${grantfund.endingDate}"
                                            var="fmtDate"
                                            type="date"
                                            pattern="dd-MM-yyyy HH:mm" />
                            <td>${fmtDate}</td>
                        </tr>
                    </tbody>
                </c:forEach>

            </table>
        </div>

    </div>

</div>