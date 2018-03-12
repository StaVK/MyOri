<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/util.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">

            <section>
                <h3><spring:message code="product.title"/></h3>

                <table class="table table-striped display">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th><spring:message code="product.article"/></th>
                        <th><spring:message code="product.description"/></th>
                        <th><spring:message code="product.price"/></th>
                        <th><spring:message code="common.volume"/></th>
                        <th colspan="2"><spring:message code="common.actions"/></th>
                    </tr>
                    </thead>
                    <c:forEach items="${products}" var="product">
                        <jsp:useBean id="product" scope="page" type="ru.myori.model.Product"/>
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.article}</td>
                            <td>${product.description}</td>
                            <td>${product.price}</td>
                            <td><input type="number" id=${product.id}></td>
                                <%--<td><a href="<c:url value="/orders/addProductInOrder?id=${product.id}&orderId=${orderId}&vol=${volume}"/>"><spring:message code="product.select"/></a></td>--%>
                            <td>
                                <button type="submit" onclick="addProductInOrder(${orderId},${product.id})">
                                    <spring:message code="common.add"/></button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>