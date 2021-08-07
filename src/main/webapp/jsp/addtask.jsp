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
    <title>   <fmt:message key="addtask.addtask" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>

<form class="cntr form" name="add-post-form" method="POST" action="controller?command=add_task_command">
 <p class="cntr"> <fmt:message key="addtask.title" bundle="${ rb }" /> </p>
    <input class="cntr" type="text" name="title">
<%--    <input type="hidden" name="command" value=""/>--%>
    <br/>
    <p >  <fmt:message key="addtask.text" bundle="${ rb }" /></p>
    <textarea rows = "12" cols = "100" name="text" >Write your task</textarea>
    <br/>
    <p ><fmt:message key="addtask.complexity" bundle="${ rb }" /></p>
    <input class="cntr" type="text" name="complexity" value="">
    <br/>
    <button class="button" type="submit"> <fmt:message key="addtask.submit" bundle="${ rb }" /></button>
</form>

</body>
<script src="js/main.js"></script>
</html>
