<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <jsp:include page="/WEB-INF/views/includes/header.jsp" />
    <div id="content">
        <div id="user">
            <p class="jr-success">
                회원정보를 성공적으로 수정했습니다.
                <br><br>
                <a href="<%=request.getContextPath() %>">메인으로 돌아가기</a>
            </p>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
    <jsp:include page="/WEB-INF/views/includes/footer.jsp" />
</div>
</body>
</html>