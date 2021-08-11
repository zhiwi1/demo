<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtag" prefix="mytag" %>
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
<form name="add-post-form" method="get" action="controller?">
   <fmt:message key="find" bundle="${ rb }" />
    <input class="textfield" type="text" name="text">
    <input type="hidden" name="command" value = "users_full_text_search_command">
    <button class="pb button" type="submit"> <fmt:message key="go" bundle="${ rb }" /></button>
</form>
<br>

<c:forEach var="fts_user" items="${fts_users}">
    <p class="cntr"><fmt:message key="login" bundle="${rb}"/>${fts_user.login}</p>
    <p class="cntr"><fmt:message key="email" bundle="${rb}"/>${fts_user.email}</p>
    <p class="cntr"><fmt:message key="count" bundle="${rb}"/>${fts_user.countOfSolve}</p>
    <p class="cntr"><fmt:message key="role" bundle="${rb}"/>${fts_user.roleType}</p>
    <p class="cntr"><fmt:message key="rates" bundle="${rb}"/>${fts_user.ratesType}</p>
    <p class="cntr"><fmt:message key="status" bundle="${rb}"/>${fts_user.status}</p>

    <c:if test="${fts_user.status =='NORMAL'}">
        <form  name="reg-form" method="POST" action="controller?command=block_user_command&login=${user.login}">
            <button class="button bcenter" type="submit">  <fmt:message key="allusers.block" bundle="${ rb }" /></button>
        </form>
    </c:if>
    <c:if test="${fts_user.status =='BLOCKED'}">
        <form class="form" name="reg-form" method="POST" action="controller?command=unblock_user_command&login=${user.login}">
            <button class="button bcenter" type="submit">  <fmt:message key="allusers.unblock" bundle="${ rb }" /></button>
        </form>
    </c:if>
    <hr>
</c:forEach>

<c:forEach var="user" items="${users}">
    <p class="cntr"><fmt:message key="login" bundle="${rb}"/>${user.login}</p>
    <p class="cntr"><fmt:message key="email" bundle="${rb}"/>${user.email}</p>
    <p class="cntr"><fmt:message key="count" bundle="${rb}"/>${user.countOfSolve}</p>
    <p class="cntr"><fmt:message key="role" bundle="${rb}"/>${user.roleType}</p>
    <p class="cntr"><fmt:message key="rates" bundle="${rb}"/>${user.ratesType}</p>
    <p class="cntr"><fmt:message key="status" bundle="${rb}"/>${user.status}</p>

    <c:if test="${user.status =='NORMAL'}">
        <form  name="reg-form" method="POST" action="controller?command=block_user_command&login=${user.login}">
            <button class="button bcenter" type="submit">
               <fmt:message key="allusers.block" bundle="${ rb }" /></button>
        </form>
    </c:if>
    <c:if test="${user.status =='BLOCKED'}">
        <form  name="reg-form" method="POST" action="controller?command=unblock_user_command&login=${user.login}">
            <button class="button bcenter" type="submit"> <fmt:message key="allusers.unblock" bundle="${ rb }" /></button>
        </form>
    </c:if>
<hr>
</c:forEach>
<br>
<mytag:pagination page="${requestScope.current_page}" maxPage="${requestScope.max_page}" />
<a href="controller?command=go_to_home_page_command">
   <p class="cntr"><fmt:message key="allusers.return" bundle="${ rb }" /></p>
</a>
</body>
<script src="js/main.js"></script>
</html>
