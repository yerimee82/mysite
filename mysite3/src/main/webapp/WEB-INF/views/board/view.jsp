<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${boardVo.contents}</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:choose>
						<c:when test="${not empty kwd}">
							<a href="${pageContext.request.contextPath}/board?page=${currentPage}&kwd=${kwd}">글목록</a>
							<c:if test="${not empty authUser}">
								<a href="${pageContext.request.contextPath}/board/reply/${boardVo.no}?page=${currentPage}&kwd=${kwd}">답글 달기</a>
								<c:if test="${authUser.no == boardVo.userNo}">
									<a href="${pageContext.request.contextPath}/board/modify/${boardVo.no}?page=${currentPage}&kwd=${kwd}">글수정</a>
								</c:if>
							</c:if>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/board?page=${currentPage}">글목록</a>
							<c:if test="${not empty authUser}">
								<a href="${pageContext.request.contextPath}/board/reply/${boardVo.no}?page=${currentPage}">답글 달기</a>
								<c:if test="${authUser.no == boardVo.userNo}">
									<a href="${pageContext.request.contextPath}/board/modify/${boardVo.no}?page=${currentPage}">글수정</a>
								</c:if>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>