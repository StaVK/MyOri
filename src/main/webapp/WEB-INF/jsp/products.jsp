<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<jsp:include page="fragments/i18n.jsp"/>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/productDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">

            <section>
                <h3><spring:message code="product.title"/></h3>

                <hr>
                <%--<a href="products/create"><spring:message code="product.add"/></a>--%>

                <a class="btn btn-primary" onclick="add()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <spring:message code="common.add"/>
                </a>
                <hr>
                <table class="table table-striped display" id="productDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="product.article"/></th>
                        <th><spring:message code="product.description"/></th>
                        <th><spring:message code="product.price"/></th>
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
                            <button class="btn btn-primary" type="button" onclick="save()"><spring:message code="common.save"/></button>
                            <button class="btn btn-primary" onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript">
    i18n["editTitle"] = '<spring:message code="common.edit"/>';
</script>
</html>