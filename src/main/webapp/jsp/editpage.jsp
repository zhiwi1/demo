<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="editpage" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<form name="edit-info-form" method="POST" action="controller?command=edit_page_command">
    <p class="cntr textfield"><fmt:message key="login" bundle="${rb}"/>
        <input type="text" name="login" value="${login}" required pattern="${requestScope.regexp_email_or_login}"></p>
    <p class="cntr textfield"><fmt:message key="email" bundle="${rb}"/>
        <input type="text" name="email" value="${email}" required pattern="${requestScope.regexp_email}"></p>
    <br/>
    <button class="bcenter pb button" type="submit"><fmt:message key="change" bundle="${rb}"/></button>
</form>
<a class="cntr" href="controller?command=go_to_home_page_command">
    <fmt:message key="return" bundle="${rb}"/>
</a>
</body>
<script src="js/main.js"></script>
<script src="js/validation_alert.js"></script>
</html>
