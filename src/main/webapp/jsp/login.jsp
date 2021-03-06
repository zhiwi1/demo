<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <script src="js/jquery.js"></script>
    <title><fmt:message key="login.title" bundle="${rb}"/></title>
</head>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>

<body>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>


<c:if test="${not empty error_message}">
    <p class="cntr"><c:out value="${error_message}"/></p>
</c:if>
<c:if test="${not empty email_message}">
    <p class="cntr"><c:out value="${email_message}"/></p>
</c:if>

<c:if test="${not empty block_message}">
    <p class="cntr"><fmt:message key="login.block" bundle="${rb}"/></p>
</c:if>

<form class="form" action="controller?command=sign_in_command"  method="post">
    <p class="cntr"><fmt:message key="login.login" bundle="${rb}"/></p>
    <input class="textfield" type="text" name="email-login"  pattern="${requestScope.regexp_email_or_login}"  required>
    <br>
    <p class="cntr"><fmt:message key="login.password" bundle="${ rb }"/></p>
    <input class="textfield" type="password" name="password" required pattern="${requestScope.regexp_password}">
    <br>
    <input class="bcenter button" type="submit" name="button" value="<fmt:message key="signin" bundle="${rb}"/>">
</form>
<hr>
<p class="cntr"><fmt:message key="login.are_register" bundle="${rb}"/></p>
<a class="cntr transition" href="controller?command=go_to_registration_page_command">
    <fmt:message key="login.register" bundle="${rb}"/>
</a>
<a class="cntr" href="controller?command=go_to_send_password_command">
    <fmt:message key="login.forget" bundle="${rb}"/>
</a>
</body>
<script src="js/main.js"></script>
<script src="js/validation_alert.js"></script>
</html>