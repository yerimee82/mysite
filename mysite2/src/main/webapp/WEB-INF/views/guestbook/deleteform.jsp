<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="guestbook" class="delete-form">
            <form method="post" name="df" action="${pageContext.request.contextPath}/guestbook">
                <input type="hidden" name="a" value="delete">
                <input type="hidden" name="no" value="${param.no }">
                <table>
                    <tr>
                        <td>비밀번호</td>
                        <td><input type="password" name="password" value=""></td>
                        <td><input type="submit" value="확인"></td>
                    </tr>
                </table>
            </form>
            <c:if test="${not empty message}">
                <p style="text-align: center;">${message}</p>
            </c:if>
            <a href="${pageContext.request.contextPath}/guestbook">방명록 리스트</a>
        </div>
    </div>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"/>
    <c:import url="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
</html>