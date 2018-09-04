<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 14.05.2018
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>

    <title>Title</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<script type="text/javascript" src="resources/js/boxDatatables.js" defer></script>
<script type="text/javascript" src="resources/js/customerDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">

            <section>
                <hr>
                <div class="view-box">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-xs-offset-1 col-xs-9">
                                <button class="btn btn-primary" type="button"
                                        onclick="addBox()">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>


                <table class="table table-striped display" id="boxDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="box.id"/></th>
                        <th><spring:message code="order.customer"/></th>
                        <%--<th><spring:message code="box.storages"/></th>--%>
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

</body>
<jsp:include page="fragments/i18n.jsp"/>
</html>
