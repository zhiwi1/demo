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
<c:out value="${user}"/>;

<a href="controller?command=find_editing_info_command">Редактировать страницу</a>
<a href="controller?command=go_to_home_page_command">
    Вернуться на домашнюю страницу
</a>

</body>
</html>
