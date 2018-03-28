<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 27.03.2018
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/storageProductDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <h3><spring:message code="order.products"/></h3>
                <hr>
                <input type="hidden" id="storageId" name="orderId" value=${storageId}> <%--TODO--%>
                <%--<a href="storage/create"><spring:message code="common.add"/></a>--%>
                <hr>
                <table class="table table-striped display" id="storageProductDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="product.article"/></th>
                        <th><spring:message code="product.description"/></th>
                        <th><spring:message code="product.price"/></th>
                        <th><spring:message code="common.volume"/></th>
                        <th colspan="2"><spring:message code="common.actions"/></th>
                    </tr>
                    </thead>

                </table>
            </section>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
