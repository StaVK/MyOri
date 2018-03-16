<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <section>
<%--            <form method="post" action="users">
                <spring:message code="app.login"/>: <select name="userId">
                <option value="100000" selected>User</option>
                <option value="100001">Admin</option>
            </select>
                <button type="submit"><spring:message code="common.select"/></button>
            </form>--%>
            <ul>
                <li><a href="users"><spring:message code="user.title"/></a></li>
                <li><a href="products"><spring:message code="product.title"/></a></li>
                <li><a href="orders"><spring:message code="order.title"/></a></li>
            </ul>
        </section>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>