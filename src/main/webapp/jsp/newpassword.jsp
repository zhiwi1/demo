<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>   <fmt:message key="home.home" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<p>
    <fmt:message key="newpassword.on_your_email" bundle="${rb}"/>
</p>
<form action="controller?command=send_password_command" method="post">
    <fmt:message key="newpassword.email" bundle="${rb}"/>  <input type="text" name="email">
    <input type="submit" name="button" value="<fmt:message key="newpassword.send" bundle="${rb}"/>  ">
</form>

</body>
</html>