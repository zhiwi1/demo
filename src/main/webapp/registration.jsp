<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru-RU" scope="session" />
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title><fmt:message key="registration.registration" bundle="${ rb }"/></title>
</head>
<body>
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