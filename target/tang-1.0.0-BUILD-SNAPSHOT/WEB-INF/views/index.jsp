<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>탕탕탕</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo"><a href="<c:url value='/'/>">탕탕탕</a></li>
        <li><a href="<c:url value='/product/list'/>">경매정보창</a></li>
        <li><a href="<c:url value='/product/write'/>">물품 등록</a></li>
        <li><a href="<c:url value='/board/list'/>">이벤트</a></li>
        <li><a href="<c:url value='/notice/list'/>">공지사항</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/member/register'/>">회원가입</a></li>
        <li><a href="<c:url value='/mypage/list'/>">마이페이지</a></li>
        <li><a href="<c:url value='/history/list'/>">포인트 내역</a></li>
        <li><a href="<c:url value='/enroll/list'/>">물품등록내역</a></li>
        <li><a href="<c:url value='/bid/list'/>">경매입찰내역</a></li>
    </ul>
</div>
<div style="text-align:center">
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>
</body>
</html>
