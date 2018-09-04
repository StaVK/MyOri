<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 27.08.2018
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script type="text/javascript" src="resources/js/customerProductsDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <input type="hidden" id="customerId" name="customerId" value=${customer}>
                <hr>
                <table class="table table-striped display" id="customerProductDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="product.article"/></th>
                        <th><spring:message code="product.description"/></th>
                        <th><spring:message code="common.volume"/></th>
                        <%--<th colspan="2"><spring:message code="common.actions"/></th>--%>
                    </tr>
                    </thead>

                </table>
            </section>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>
