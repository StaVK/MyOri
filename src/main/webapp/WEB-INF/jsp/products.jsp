<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">

            <section>
                <h3><spring:message code="product.title"/></h3>

                <hr>
                <a href="products/create"><spring:message code="product.add"/></a>
                <hr>
                <table class="table table-striped display">
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
                            <td><a href="products/update?prodId=${product.prodId}"><spring:message
                                    code="common.update"/></a></td>
                            <td><a href="products/delete?prodId=${product.prodId}"><spring:message
                                    code="common.delete"/></a></td>
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