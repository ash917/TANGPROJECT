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
<div style="text-align:center">
    <div class="board-container">
        물품등록내역
        <table>
            <c:forEach var="po" items="${list}">
                <tr>
                    <td>${po.p_num}</td>
                    <td><a href="<c:url value="/product/read?p_num=${po.p_num}&page=${page}&pageSize=${pageSize}"/>">${po.p_title}</a></td>
                    <td>마감시간 ${po.p_date}</td>
                    <td>시작가 ${po.p_sprice}<br>
                            현재가 ${po.a_price}<br>
                   즉시구매가 ${po.p_eprice}<br>
                    입찰횟수 ${po.a_count}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div>
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/enroll/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/enroll/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a href="<c:url value='/enroll/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
            </c:if>
        </div>
    </div>
</body>
</html>