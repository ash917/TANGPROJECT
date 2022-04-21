<%@ page import="com.my.tang.domain.auction.ProductDto" %>
<%@ page import="com.my.tang.controller.list.ItemViewService" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="com.my.tang.domain.member.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>탕탕탕</title>
</head>
<body>

<jsp:include page="../etc/top.jsp" />
<script>
    let msg = "${msg}";
    if(msg=="LIST_ERR")  alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");
    if(msg=="READ_ERR")  alert("삭제되었거나 없는 게시물입니다.");
    if(msg=="DEL_ERR")   alert("삭제되었거나 없는 게시물입니다.");
    if(msg=="DEL_OK")    alert("성공적으로 삭제되었습니다.");
    if(msg=="WRT_OK")    alert("성공적으로 등록되었습니다.");
    if(msg=="MOD_OK")    alert("성공적으로 수정되었습니다.");
</script>
<div style="text-align:center">
    <div class="board-container">
        포인트 사용 내역
        포인트 ${user.m_point} P
        <table>
            <tr>
                <th class="title">번호</th>
                <th class="writer">분류</th>
                <th class="regdate">변동포인트</th>

            </tr>
            <c:forEach var="historyDto" items="${list}">
                <tr>
                    <td>${historyDto.mod_reg_date}</td>
                    <td>${ historyDto.current_id.equals(historyDto.m_id) ? historyDto.classify_sell : historyDto.classify_buy }</td>
                    <td>${ historyDto.current_id.equals(historyDto.m_id) ? historyDto.in_point_sell : historyDto.in_point_buy }</td>

                </tr>
            </c:forEach>
        </table>
        <br>
        <div>
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/history/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/history/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a href="<c:url value='/history/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
            </c:if>
        </div>
    </div>
</body>
</html>