<%@ page import="com.poscodx.mysite.vo.UserVo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
<div id="navigation">
    <ul>
        <li><a href="<%=request.getContextPath()%>">김예림</a></li>
        <li><a href="<%=request.getContextPath()%>/guestbook">방명록</a></li>
        <li><a href="<%=request.getContextPath()%>/board">게시판</a></li>
    </ul>
</div>