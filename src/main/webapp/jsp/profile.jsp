<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />

<html>
<head>
    <title><fmt:message key="header.profile" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<body>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<p class="cntr">Login: ${user.login}</p>
<p class="cntr">Email: ${user.email}</p>
<p class="cntr">Count of solve: ${user.countOfSolve}</p>
<p class="cntr">Role: ${user.roleType}</p>
<p class="cntr">Rates: ${user.ratesType}</p>
<p class="cntr">Status: ${user.status}</p>

<a class="cntr" href="controller?command=find_editing_info_command">Редактировать страницу</a>
<a class="cntr" href="controller?command=go_to_home_page_command">
    Вернуться на домашнюю страницу
</a>

</body>
<script src="js/main.js"></script>
</html>
