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
    <form method="post" action="products" class="form-horizontal">
        <input type="hidden" name="prodId" value="${product.prodId}">
        <div class="form-group">
            <label for="article" class="control-label col-xs-3"><spring:message
                    code="product.article"/></label>
            <div class="col-xs-9">
                <input type="number" id="article" name="article" placeholder="0">
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="control-label col-xs-3"><spring:message
                    code="product.description"/></label>
            <div class="col-xs-9">
                <input type="text" id="description" name="description">
            </div>
        </div>
        <div class="form-group">
            <label for="price" class="control-label col-xs-3"><spring:message
                    code="product.price"/></label>
            <div class="col-xs-9">
                <input type="number" id="price" name="price">
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-3 col-xs-9">
                <button type="submit"><spring:message code="common.save"/></button>
                <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
            </div>
        </div>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
