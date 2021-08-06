<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="allusers.allusers" bundle="${ rb }" /></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<body>
<form name="add-post-form" method="POST" action="controller?command=users_full_text_search_command">
    <fmt:message key="find" bundle="${ rb }" />
    <input type="text" name="text">

    <button type="submit"> <fmt:message key="go" bundle="${ rb }" /></button>
</form>
<a href="controller?command=go_to_home_page_command">
    <fmt:message key="allusers.return" bundle="${ rb }" />
</a>
<c:forEach var="fts_user" items="${fts_users}">
    <p>${fts_user}</p>
    <c:out value="${fts_user.status}"/>
    <c:if test="${fts_user.status =='NORMAL'}">
        <form name="reg-form" method="POST" action="controller?command=block_user_command&login=${user.login}">
            <button type="submit">  <fmt:message key="allusers.block" bundle="${ rb }" /></button>
        </form>
    </c:if>
    <c:if test="${fts_user.status =='BLOCKED'}">
        <form name="reg-form" method="POST" action="controller?command=unblock_user_command&login=${user.login}">
            <button type="submit">  <fmt:message key="allusers.unblock" bundle="${ rb }" /></button>
        </form>
    </c:if>

</c:forEach>

<c:forEach var="user" items="${users}">
    <p>${user}</p>
    <c:out value="${user.status}"/>
    <c:if test="${user.status =='NORMAL'}">
        <form name="reg-form" method="POST" action="controller?command=block_user_command&login=${user.login}">
            <button type="submit">  <fmt:message key="allusers.block" bundle="${ rb }" /></button>
        </form>
    </c:if>
    <c:if test="${user.status =='BLOCKED'}">
        <form name="reg-form" method="POST" action="controller?command=unblock_user_command&login=${user.login}">
            <button type="submit"> <fmt:message key="allusers.unblock" bundle="${ rb }" /></button>
        </form>
    </c:if>

</c:forEach>
</body>
</html>
