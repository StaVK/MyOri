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
<%--<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>--%>
<script type="text/javascript" src="resources/js/storageDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <section>
                <h3><spring:message code="storage.storages"/></h3>
                <hr>
                <%--<a href="storage/create"><spring:message code="common.add"/></a>--%>

                <a class="btn btn-primary" onclick="add()">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <spring:message code="common.add"/>
                </a>

                <hr>
                <table class="table table-striped display" id="storageDatatable">
                    <thead>
                    <tr>
                        <th><spring:message code="storage.name"/></th>
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
                    <input type="hidden" id="storageId" name="storageId">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message
                                code="storage.name"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="name" name="name"
                                   placeholder="<spring:message code="storage.name"/>">
                        </div>
                    </div>
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
<script type="text/javascript">
    i18n["editTitle"] = '<spring:message code="common.edit"/>';
</script>
</html>
