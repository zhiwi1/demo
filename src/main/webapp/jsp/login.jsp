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
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>

<body>
<div class="time-weather"></div>
<div class='title'>
    <time id="time"> Monday, 25 September, 8:00:13 PM</time>
    <div class="city" contenteditable="true">Минск</div>
</div>
<div class="box">
    <div class="weather-description"></div>
</div>
<div class="box">
    <div class="weatherWind"></div>
</div>
<div class="box">
    <div class="temperature"></div>
</div>
<div class="box">
    <div class="airHumidity"></div>
</div>
<div class="box"><i class="weather-icon owf"></i></div>
<h1 class="hello"> <span  id="greeting">Good Afternoon</span>
    <span id="name" contenteditable="true"></span>
</h1>
<h2 class="hello">What is your focus for today?</h2>
<h2 class="focus"><span id="focus" contenteditable="true"></span></h2>
<center><button class="btn"> Change Photo</button></center>
<script src="js/main.js"></script>


<c:if test="${not empty error_message}">
    <c:out value="${error_message}"/>
</c:if>
<c:if test="${not empty email_message}">
    <c:out value="${email_message}"/>
</c:if>

<form action="controller?command=sign_in_command" method="post">
    <fmt:message key="login.login" bundle="${rb}"/>  <input type="text" name="email-login" pattern="${requestScope.regexp_email_or_login}" >
    <br>
    <fmt:message key="login.password" bundle="${ rb }"/>:  <input type="text" name="password" pattern="${requestScope.regexp_password}">
    <br>
    <input class="button" type="submit" name="button" value="login">
</form>
<hr>
<p><fmt:message key="login.are_register" bundle="${rb}"/></p>
<a href="controller?command=go_to_registration_page_command">
    <fmt:message key="login.register" bundle="${rb}"/>
</a>
<a href="controller?command=go_to_send_password_command">
    <fmt:message key="login.forget" bundle="${rb}"/>
</a>
</body>
</html>