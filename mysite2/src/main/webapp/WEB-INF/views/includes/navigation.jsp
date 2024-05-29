<%@ page import="com.poscodx.mysite.vo.UserVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<div id="navigation">
    <ul>
        <li><a href="${pageContext.request.contextPath}">김예림</a></li>
        <li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
        <li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
    </ul>
</div>