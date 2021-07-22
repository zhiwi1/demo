<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 7/21/2021
  Time: 3:33 PM
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
    <title>Add post</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/templates/header.jsp"/>
<form name="reg-form" method="POST" action="controller?command=sign_up_command">
    <fmt:message key="registration.email" bundle="${ rb }" /> <input type="text" name="email">
    <input type="hidden" name="command" value="register"/>
    <br/> <fmt:message key="registration.login" bundle="${ rb }" />
    <input type="text" name="login" value=""/>
    <br/><fmt:message key="registration.password" bundle="${ rb }" />
    <input type="text" name="password" value="">
    <br/><fmt:message key="registration.confirm" bundle="${ rb }" />
    <input type="text" name="confirm-password" value="">
    <br/>


    <button type="submit"> <fmt:message key="registration.signup" bundle="${ rb }" /></button>
</form>

</body>
</html>
