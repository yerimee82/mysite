<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="navigation">
    <ul>
        <li><a href="${pageContext.request.contextPath}"><spring:message code="navigation.li.main" /></a></li>
        <li><a href="${pageContext.request.contextPath}/guestbook"><spring:message code="navigation.li.guestbook" /></a></li>
        <li><a href="${pageContext.request.contextPath}/board"><spring:message code="navigation.li.board" /></a></li>
    </ul>
</div>