<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="product.title"/></h3>

<%--    <form method="post" action="meals/filter">
        <dl>
            <dt><spring:message code="meal.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="meal.filter"/></button>
    </form>--%>
    <hr>
    <a href="meals/create"><spring:message code="product.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="product.article"/></th>
            <th><spring:message code="product.description"/></th>
            <th><spring:message code="product.price"/></th>
            <th colspan="2"><spring:message code="common.actions"/></th>
        </tr>
        </thead>
        <c:forEach items="${products}" var="product">
            <jsp:useBean id="product" scope="page" type="ru.myori.model.Product"/>
            <tr>
                <td>${product.article}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td><a href="products/update?id=${product.id}"><spring:message code="common.update"/></a></td>
                <td><a href="products/delete?id=${product.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
</body>
</html>