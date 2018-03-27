<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/orderProductDatatables.js" defer></script>
<%--<script type="text/javascript" src="resources/js/productDatatables.js" defer></script>--%>
<script type="text/javascript" src="resources/js/customerDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <h3><spring:message code="order.order"/> ${order.orderId}</h3>
                <input type="hidden" id="orderId" name="orderId" value=${order.orderId}>

                <hr>

                <div class="view-box">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="customer" class="control-label col-xs-3"><spring:message
                                    code="common.customer"/></label>
                            <div class="col-xs-9">
                                <input type="text" id="customer" name="customer" placeholder="0" readonly
                                       value=${order.forUser.name}>
                                <a onclick='editCustomer();'><span class='glyphicon glyphicon-pencil'
                                                                   aria-hidden='true'></span></a>
                            </div>
                        </div>
                    </form>
                    <form class="form-horizontal" id="addProductInOrderForm">
                        <div class="form-group">
                            <label for="article" class="control-label col-xs-3"><spring:message
                                    code="product.article"/></label>
                            <div class="col-xs-9">
                                <input type="number" id="article" name="article" placeholder="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="volume" class="control-label col-xs-3"><spring:message
                                    code="common.volume"/></label>
                            <div class="col-xs-9">
                                <input type="number" id="volume" name="volume" placeholder="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-9">
                                <button class="btn btn-primary" type="button"
                                        onclick="addProdInOrder(${order.orderId})">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-striped display" id="orderProductsDatatable">
                        <thead>
                        <tr>
                            <%--<th><spring:message code="order.products"/></th>--%>
                            <th><spring:message code="product.article"/></th>
                            <th><spring:message code="product.description"/></th>
                            <th><spring:message code="product.price"/></th>
                            <th><spring:message code="common.volume"/></th>
                            <th><spring:message code="common.sum"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                    <%--                    <div class="form-group">
                                            <div class="col-xs-offset-10 col-xs-9">
                                                <button class="btn btn-primary" type="button" onclick="save()">
                                                    <spring:message code="common.save"/>
                                                </button>
                                            </div>
                                        </div>--%>
                </div>
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
                    <%--                    <div class="form-group">
                                            <div class="col-xs-offset-3 col-xs-9">
                                                <button class="btn btn-primary" type="button" onclick="save()">
                                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                                </button>
                                            </div>
                                        </div>--%>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>