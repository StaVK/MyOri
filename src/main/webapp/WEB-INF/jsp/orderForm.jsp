<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="order.order"/> ${order.id}</h3>

    <hr>
    <a href="<c:url value="/op/getProductForOrder?orderId=${order.id}"/>"><spring:message
            code="order.addProduct"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0" id="orderProductsDatatable">
        <thead>
        <tr>
            <%--<th><spring:message code="order.products"/></th>--%>
            <th><spring:message code="product.article"/></th>
            <th><spring:message code="product.description"/></th>
            <th><spring:message code="product.price"/></th>
            <th><spring:message code="common.volume"/></th>
            <th><spring:message code="common.sum"/></th>
            <th colspan="2"><spring:message code="common.actions"/></th>
        </tr>
        </thead>
        <%--TODO--%>
        <c:forEach items="${orderProduct}" var="orderProduct">
            <jsp:useBean id="orderProduct" scope="page" type="ru.myori.model.OrderProduct"/>
            <tr>
                    <%--<td>${order.products}</td>--%>
                <td>${orderProduct.product.article}</td>
                <td>${orderProduct.product.description}</td>
                <td>${orderProduct.product.price}</td>
                <td>${orderProduct.volume}</td>
                <td>${orderProduct.product.price*orderProduct.volume}</td>
                <td><a href="orders/update?id=${orderProduct.id}"><spring:message code="common.update"/></a></td>
                <td><a href="op/productDelete?id=${orderProduct.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>