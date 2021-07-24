<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/templates/header.jsp"/>
<body>
<c:forEach var="task" items="${tasks}">
    <p>${task}</p>
</c:forEach>
</body>
</html>
