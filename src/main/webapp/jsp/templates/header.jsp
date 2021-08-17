<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtag" prefix="mytag" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@600&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/owfont-regular.css">
    <script src="js/jquery.js"></script>
</head>
<header class="header">
    <mytag:image/>
    <c:if test="${ not empty login }" var="firstOperation" scope="page">
        <a class="profile float-right" href="controller?command=find_profile_info_command">
            <button class="button"><fmt:message key="header.profile" bundle="${rb}"/>
            </button>
        </a>
    </c:if>
    <button class="button btn float-right"><fmt:message key="changephoto" bundle="${rb}"/></button>
    <button class="button float-right" type="submit" name="locale" value="en" onclick="changeLocaleOnRU()">
        <fmt:message key="ru" bundle="${rb}"/>
    </button>
    <button class="button float-right" type="submit" name="locale" value="ru" onclick="changeLocaleOnEn()">
        <fmt:message key="en" bundle="${rb}"/>
    </button>
    <br/>
    <div hidden class="valueMissing" ><fmt:message key="valueMissing" bundle="${rb}"/></div>
    <div hidden class="typeMismatch" ><fmt:message key="typeMismatch" bundle="${rb}"/></div>
    <div hidden class="patternMismatching" >    <fmt:message key="patternMismatching" bundle="${rb}"/></div>
    <script src="js/localization.js"></script>
</header>