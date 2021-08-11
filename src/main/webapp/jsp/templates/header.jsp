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
    <script>
        let PROJECT_ROOT = 'http://localhost:8080/demo_war/'
        function changeLocaleOnRU(event) {
            let currentPage = encodeURIComponent(window.location.pathname + window.location.search);
            document.location.href = PROJECT_ROOT + "controller?command=change_locale_command&locale=" + 'ru' + "&prev_request=" + currentPage;
        }

        function changeLocaleOnEn(event) {
            let currentPage = encodeURIComponent(window.location.pathname + window.location.search);
            document.location.href = PROJECT_ROOT + "controller?command=change_locale_command&locale=" + 'en' + "&prev_request=" + currentPage;

        }
    </script>
    <script type="text/javascript">
        var speed = 'slow';
        $('html, body').hide();
        $(document).ready(function () {
            $('html, body').fadeIn(speed, function () {
                $('a[href], button[href]').click(function (event) {
                    var url = $(this).attr('href');
                    if (url.indexOf('#') == 0 || url.indexOf('javascript:') == 0) return;
                    event.preventDefault();
                    $('html, body').fadeOut(speed, function () {
                        window.location = url;
                    });
                });
            });
        });
    </script>
</header>