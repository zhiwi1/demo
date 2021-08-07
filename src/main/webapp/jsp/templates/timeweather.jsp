<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<div class="time-weather"></div>
<div class='title'>
    <time id="time"> Monday, 25 September, 8:00:13 PM</time>
    <div class="city" contenteditable="true">Minsk</div>
</div>
<div class="box">
    <div class="temperature"></div>
</div>
<div class="box">
    <div class="airHumidity"></div>
</div>
<div class="box"><i class="weather-icon owf"></i></div>
<h1 class="cntr"> <span id="greeting"></span>
</h1>


