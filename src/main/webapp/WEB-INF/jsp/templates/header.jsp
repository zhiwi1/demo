<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header flex flex__justify-content_space-between flex__align-items_center">
    <script type = "text/javascript" >
        function preventBack(){window.history.forward();}
        setTimeout("preventBack()", 0);
        window.onunload=function(){null};
    </script>

    <form id="localeForm" action="controller" method="post">
        <input type="hidden" name="command" value="change_locale_command">
    </form>
    <ul class="dropdown-menu">
        <li>
            <button form="localeForm" type="submit" name="locale" value="ru">
                <img class="image-header-dropdown" src="static/image/russia.png"/>
            </button>
        </li>
        <li>
            <button form="localeForm" type="submit" name="locale" value="en">
                <img class="image-header-dropdown" src="static/image/en.png"/></button>
        </li>
    </ul>

</header>