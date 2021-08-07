<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title>Add post</title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<form name="edit-info-form" method="POST" action="controller?command=edit_page_command">
    <p class="cntr textfield">Login: <input type="text" name="login" value="${login}"></p>
    <p class="cntr textfield">Email: <input type="text" name="email" value="${email}"></p>
    <br/>
    <button class="bcenter pb button" type="submit"> Изменить</button>
</form>
</body>
<script src="js/main.js"></script>
</html>
