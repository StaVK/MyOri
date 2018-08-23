<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 21.08.2018
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/customersDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <h3><spring:message code="customer.customers"/></h3>
                <hr>

                <a class="btn btn-primary" onclick="add()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <spring:message code="common.add"/>
                </a>
                <hr>

                <table class="table table-striped display" id="customersDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="people.name"/></th>
                        <th><spring:message code="people.surname"/></th>
                        <th><spring:message code="people.patronymic"/></th>
                        <th><spring:message code="people.phoneNumber"/></th>
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
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="user.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="people.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="surname" class="control-label col-xs-3"><spring:message code="people.surname"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="surname" name="surname" placeholder="<spring:message code="people.surname"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="patronymic" class="control-label col-xs-3"><spring:message code="people.patronymic"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="patronymic" name="patronymic" placeholder="<spring:message code="people.patronymic"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber" class="control-label col-xs-3"><spring:message code="people.phoneNumber"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="<spring:message code="people.phoneNumber"/>">
                        </div>
                    </div>

<%--                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="user.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="user.email"/>">
                        </div>
                    </div>--%>


                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="saveCustomer()" class="btn btn-primary">
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
