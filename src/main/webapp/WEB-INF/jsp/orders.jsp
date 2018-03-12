<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/orderDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">

            <section>
                <h3><spring:message code="order.title"/></h3>
                <hr>
                <a href="orders/create"><spring:message code="order.add"/></a>
                <hr>
                <table class="table table-striped display" id="orderDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="order.id"/></th>
                        <th><spring:message code="order.user"/></th>
                        <th><spring:message code="order.forUser"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>

<%--                    <c:forEach items="${orders}" var="order">
                        <jsp:useBean id="order" scope="page" type="ru.myori.model.Order"/>
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.user.id}</td>
                            <td>${order.forUser.id}</td>
                            <td><a href="orders/orderUpdate?orderId=${order.id}"><spring:message
                                    code="common.update"/></a></td>
                            <td><a href="orders/orderDelete?orderId=${order.id}"><spring:message
                                    code="common.delete"/></a></td>
                        </tr>
                    </c:forEach>--%>
                </table>
            </section>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>