<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 7/18/2021
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>LogInPage</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/templates/header.jsp"/>
<form action="controller?command=sign_in_command" method="post">
    Email:  <input type="text" name="email-login">
    <fmt:message key="login.password" bundle="${ rb }"/>:  <input type="text" name="password">
    <input type="submit" name="button" value="login">
</form>
<hr>
<p>Вы не зарегистрированы?</p>
<a href="controller?command=go_to_registration_page_command">
    Зарегистрироваться
</a>
</body>
</html>