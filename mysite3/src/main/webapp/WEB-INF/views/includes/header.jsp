<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    $(function(){
        $("#languages a").click(function(event){
            event.preventDefault();
            console.log($(this).data("lang"));

            document.cookie =
                "lang="    + $(this).data("lang") + ";" +                 // name=value
                "path="    + "${pageContext.request.contextPath}" + ";" + // path
                "max-age=" + (30*24*60*60)                                // max-age

            // reload
            location.reload();
        });
    });
</script>
<div id="header">
    <h1>
        ${site.title}
    </h1>
    <div id="languages">
        <c:choose>
            <c:when test='${language == "en"}'>
                <a href="" data-lang="ko">KR</a><a href="" class="active" data-lang="en">EN</a>
            </c:when>
            <c:otherwise>
                <a href="" data-lang="ko" class="active">KR</a><a href="" data-lang="en">EN</a>
            </c:otherwise>
        </c:choose>
    </div>
    <ul>
        <c:choose>
            <c:when test="${empty authUser}">
                <li><a href="${pageContext.request.contextPath}/user/login">로그인</a>
                <li>
                <li><a href="${pageContext.request.contextPath}/user/join">회원가입</a>
                <li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/user/update">회원정보수정</a>
                <li>
                <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
                <li>
                <li>${authUser.name}님 안녕하세요 ^^;</li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>