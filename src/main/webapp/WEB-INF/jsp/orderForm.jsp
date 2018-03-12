<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/orderProductDatatables.js" defer></script>
<script type="text/javascript" src="resources/js/productDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <h3><spring:message code="order.order"/> ${order.orderId}</h3>
                <input type="hidden" id="orderId" name="orderId" value=${order.orderId}>

                <hr>
                <%--                <a href="<c:url value="/op/getProductForOrder?orderId=${order.id}"/>"><spring:message
                                        code="product.add"/></a>
                                <hr>--%>
                <div class="view-box">
                    <%--                    <a class="btn btn-primary" onclick="add()">
                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                            <spring:message code="common.add"/>
                                        </a>--%>
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
                        <%--                        <c:forEach items="${orderProduct}" var="orderProduct">
                                                    <jsp:useBean id="orderProduct" scope="page" type="ru.myori.model.OrderProduct"/>
                                                    <tr>
                                                            &lt;%&ndash;<td>${order.products}</td>&ndash;%&gt;
                                                        <td>${orderProduct.product.article}</td>
                                                        <td>${orderProduct.product.description}</td>
                                                        <td>${orderProduct.product.price}</td>
                                                        <td>${orderProduct.volume}</td>
                                                        <td>${orderProduct.product.price*orderProduct.volume}</td>
                                                        <td><a href="orders/update?id=${orderProduct.id}"><spring:message
                                                                code="common.update"/></a></td>
                                                        <td><a onclick="deleteRow(${orderProduct.id})"><span
                                                                class="glyphicon glyphicon-remove"
                                                                aria-hidden="true"></span></a></td>
                                                    </tr>
                                                </c:forEach>--%>
                    </table>
                    <div class="form-group">
                        <div class="col-xs-offset-10 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <spring:message code="common.save"/>
                            </button>
                        </div>
                    </div>
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
                    <%--<input type="number" name="vol1" placeholder="5">--%>
                    <%--<input type="number" name="vol2" placeholder="10">--%>
                    <table class="table table-striped display" id="productDatatable">
                        <thead>
                        <tr>
                            <th><spring:message code="product.article"/></th>
                            <th><spring:message code="product.description"/></th>
                            <th><spring:message code="product.price"/></th>
                            <th><spring:message code="common.volume"/></th>
                            <%--<th><spring:message code="common.add"/></th>--%>
                        </tr>
                        </thead>
                    </table>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>