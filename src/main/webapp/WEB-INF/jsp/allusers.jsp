<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>All users</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/templates/header.jsp" charEncoding="utf-8"/>
<body>
<form name="add-post-form" method="POST" action="controller?command=add_task_command">
    FIND
    <input type="text" name="search">

    <button type="submit"> <fmt:message key="addpost.submit" bundle="${ rb }" /></button>
</form>
<a href="controller?command=go_to_home_page_command">
    Вернуться на домашнюю страницу
</a>
<c:forEach var="user" items="${users}">
    <p>${user}</p>
    <a href="controller?command=go_to_user_page_command" ></a> <p>Подробнее</p></a>
</c:forEach>
</body>
</html>
