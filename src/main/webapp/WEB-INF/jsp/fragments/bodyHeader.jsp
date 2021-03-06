<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="orders" class="navbar-brand"><spring:message code="app.title"/></a>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form" action="logout" method="post">
                        <sec:authorize access="isAuthenticated()">
                            <a class="btn btn-info" href="orders"><spring:message code="order.orders"/></a>
                            <a class="btn btn-info" href="summaryOrder"><spring:message code="order.summaryOrder"/></a>
                            <a class="btn btn-info" href="boxes"><spring:message code="common.delivery"/></a>
                            <a class="btn btn-info" href="storage"><spring:message code="common.storage"/></a>
                            <a class="btn btn-info" href="customers"><spring:message code="customer.customers"/></a>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info" href="products"><spring:message code="product.title"/></a>
                                <a class="btn btn-info" href="users"><spring:message code="user.title"/></a>
                            </sec:authorize>
                            <a class="btn btn-info" href="userProfile"><sec:authentication property="principal.userTo.name"/> <spring:message code="app.profile"/></a>
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            </button>
                        </sec:authorize>
                    </form:form>
                </li>
                <jsp:include page="lang.jsp"/>
            </ul>
        </div>
    </div>
</div>