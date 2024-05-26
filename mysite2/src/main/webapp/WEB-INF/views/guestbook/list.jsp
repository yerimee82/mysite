<%@ page import="com.poscodx.mysite.vo.GuestbookVo" %>
<%@ page import="com.poscodx.mysite.dao.GuestbookDao" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<GuestbookVo> guestbookList = GuestbookDao.findAll();
%>
<!DOCTYPE html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	<div id="content">
		<div id="guestbook">
			<form action="<%=request.getContextPath()%>/guestbook" method="post">
				<input type="hidden" name="a" value="insert">
				<table>
					<tr>
						<td>이름</td><td><input type="text" name="name"></td>
						<td>비밀번호</td><td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td colspan=4><textarea name="contents" id="contents"></textarea></td>
					</tr>
					<tr>
						<td colspan=4 align=right><input type="submit" value=" 확인 "></td>
					</tr>
				</table>
			</form>
			<br>
			<%
				int count = 1;
				for (GuestbookVo vo : guestbookList) {
			%>
			<table width="510" border="1">
				<tr>
					<td>[<%= count++ %>]</td>
					<td><%= vo.getName() %></td>
					<td><%= vo.getRegDate() %></td>
					<td><a href="<%=request.getContextPath()%>/guestbook?a=deleteform&no=<%= vo.getNo()  %>">삭제</a></td>
				</tr>
				<tr>
					<td colspan="4"><%= vo.getContents().replaceAll("\n", "<br>") %></td>
				</tr>
			</table>
			<br>
			<%
				}
			%>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
	<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
</div>
</body>
</html>