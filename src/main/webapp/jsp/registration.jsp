<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="registration.registration" bundle="${ rb }"/></title>
</head>
<body>

<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<c:if test="${not empty error_message}">
    <p class="cntr"><c:out value="${error_message}"/></p>
</c:if>
<form class="form" name="reg-form" method="POST" action="controller?command=sign_up_command">
<p class="cntr"><fmt:message key="registration.email" bundle="${ rb }"/></p>
<input  class="textfield" required pattern="${requestScope.regexp_email}" type="text" name="email"/>
<input  type="hidden" name="command" value="register"/>
<br/>
    <p class="cntr"><fmt:message key="registration.login" bundle="${ rb }"/></p>
<input class="textfield" type="text" required pattern="${requestScope.regexp_email_or_login}" name="login" value=""/>
<br/>
<p class="cntr"><fmt:message key="registration.password" bundle="${ rb }"/></p>

<input class="textfield" type="password" name="password" value="" required pattern="${requestScope.regexp_password}">
<br/>
<p class="cntr"><fmt:message key="registration.confirm" bundle="${ rb }"/></p>
<input class="textfield" type="password" name="confirm-password" value="" required pattern="${requestScope.regexp_password}">
<br/>


<button class="button bcenter" type="submit"><fmt:message key="registration.signup" bundle="${ rb }"/></button>
</form>

<p class="cntr"><fmt:message key="registration.are_register" bundle="${ rb }"/></p>
<a class="cntr" href="controller?command=go_to_login_page_command">
    <fmt:message key="registration.signin" bundle="${ rb }"/></a>

</body>
<script src="js/main.js"></script>
<script src="js/validation_alert.js"></script>
</html>