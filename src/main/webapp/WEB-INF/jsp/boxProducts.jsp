<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/boxProductDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">

            <section>
                <h3><spring:message code="box.products"/> ${box}</h3>
                <hr>
                <h3><spring:message code="common.customer"/>: ${customer}</h3>

                <input type="hidden" id="boxId" name="boxId" value=${box}>
                <hr>
                <table class="table table-striped display" id="boxProductDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="product.article"/></th>
                        <th><spring:message code="product.description"/></th>
                        <th><spring:message code="common.volume"/></th>
                    </tr>
                    </thead>
                </table>
                <hr>
                <a class="btn btn-primary" onclick="printSpReport()">
                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                    <spring:message code="common.print"/>
                </a>
            </section>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>