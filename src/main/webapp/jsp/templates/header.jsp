<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtag" prefix="mytag" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@600&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">

    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/owfont-regular.css">


</head>
<header class="header flex flex__justify-content_space-between flex__align-items_center">


    <ul class="dropdown-menu">
        <li>
            <button class="button-en" type="submit" name="locale" value="ru" onclick="changeLocaleOnEn()">
                <img class="image-header-dropdown" src="static/image/russia.png"/>
            </button>
        </li>
        <li>
            <button class="button-ru" type="submit" name="locale" value="en" onclick="changeLocaleOnRU()">
                <img class="image-header-dropdown" src="static/image/en.png"/></button>
        </li>
        <li>
            <c:if test="${ not empty login }" var="firstOperation" scope="page">
                <a href="controller?command=find_profile_info_command">
                    <fmt:message key="header.profile" bundle="${rb}"/>
                </a>
            </c:if>
        </li>

    </ul>

    <br/>
    <mytag:image/>
        <script>
        let PROJECT_ROOT = 'http://localhost:8080/demo_war/'
        function changeLocaleOnRU(event) {
        let currentPage = encodeURIComponent(window.location.pathname + window.location.search);
        document.location.href = PROJECT_ROOT + "controller?command=change_locale_command&locale=" + 'ru' + "&prev_request=" + currentPage;
        }
        function  changeLocaleOnEn(event){
        let currentPage = encodeURIComponent(window.location.pathname + window.location.search);
        document.location.href = PROJECT_ROOT + "controller?command=change_locale_command&locale=" + 'en' + "&prev_request=" + currentPage;

        }
        </script>


</header>