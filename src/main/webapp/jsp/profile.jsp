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
<p class="cntr"><fmt:message key="login" bundle="${rb}"/>${user.login}</p>
<p class="cntr"><fmt:message key="email" bundle="${rb}"/>${user.email}</p>
<p class="cntr"><fmt:message key="count" bundle="${rb}"/>${user.countOfSolve}</p>
<p class="cntr"><fmt:message key="role" bundle="${rb}"/>${user.roleType}</p>
<p class="cntr"><fmt:message key="rates" bundle="${rb}"/>${user.ratesType}</p>
<p class="cntr"><fmt:message key="status" bundle="${rb}"/>${user.status}</p>

<a class="cntr" href="controller?command=find_editing_info_command"><fmt:message key="edit" bundle="${rb}"/></a>
<a class="cntr" href="controller?command=go_to_home_page_command">
    <fmt:message key="return" bundle="${rb}"/>
</a>

</body>
<script src="js/main.js"></script>
</html>
