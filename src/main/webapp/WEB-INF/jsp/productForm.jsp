<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="product" type="ru.myori.model.Product" scope="request"/>
    <h3><spring:message code="${product.isNew() ? 'product.add' : 'product.edit'}"/></h3>
    <hr>
    <form method="post" action="products">
        <input type="hidden" name="prodId" value="${product.prodId}">
        <dl>
            <dt><spring:message code="product.article"/>:</dt>
            <dd><input type="number" value="${product.article}" name="article"></dd>
        </dl>
        <dl>
            <dt><spring:message code="product.description"/>:</dt>
            <dd><input type="text" value="${product.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt><spring:message code="product.price"/>:</dt>
            <dd><input type="number" value="${product.price}" name="price"></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
