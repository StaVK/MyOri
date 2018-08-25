<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/orderDatatables.js" defer></script>
<script type="text/javascript" src="resources/js/customerDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <h3><spring:message code="order.title"/></h3>
                <hr>
<%--                <a href="orders/create"><spring:message code="order.add"/></a>
                <hr>--%>
                <a class="btn btn-primary" onclick="add()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <spring:message code="common.add"/>
                </a>
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

                </table>
            </section>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <table class="table table-striped display" id="customerDatatable">
                        <thead>
                        <tr>
                            <th><spring:message code="user.name"/></th>
                            <th><spring:message code="user.email"/></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>