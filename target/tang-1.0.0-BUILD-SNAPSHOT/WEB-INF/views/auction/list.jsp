<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<%@ page import="com.my.tang.domain.auction.ProductDto"%>
<%@ page import="com.my.tang.controller.list.ItemViewService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.my.tang.service.auction.ProductService" %>
<%@ page import="com.my.tang.service.auction.ProductServiceImpl" %>
<%@ page import="java.util.*" %>
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

        <table>
            <c:forEach var="po" items="${list}">
                <tr>
                    <th class="no">번호</th>
                    <th class="no">사진</th>
                    <th class="title">제목</th>
                    <th class="title">카테고리명</th>
                    <th class="title">시작가</th>
                    <th class="writer">즉구가</th>
                    <th class="writer">현재가</th>
                    <th class="regdate">마감일시</th>

                </tr>
                <tr>
                    <td>${po.p_num}</td>
                    <td><img src=${po.p_img1} width=100 height=100></td>


<%--                    <%--%>

<%--                        List<ProductDto> list = (List)request.getAttribute("list");--%>
<%--                        Integer no = list.get(0).getP_num();--%>

<%--                        ItemViewService itemViewService = new ItemViewService();--%>
<%--                        ProductDto article = itemViewService.getArticle(no);--%>

<%--                        Calendar now= Calendar.getInstance();--%>

<%--                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");--%>
<%--                        Date date = format.parse(article.getP_date()); // String -> Date--%>
<%--                        Calendar cal = Calendar.getInstance();--%>
<%--                        cal.setTime(date);  // Date -> Calendar--%>

<%--                        long diffSec = (now.getTimeInMillis() - cal.getTimeInMillis()) / 1000;--%>

<%--                        System.out.println("지금" + now.getTimeInMillis());--%>
<%--                        System.out.println("마감" + cal.getTimeInMillis());--%>

<%--                        if (article.getP_eprice() != article.getA_price() || diffSec < 0) {--%>
<%--                    %>--%>
                    <td><a href="<c:url value="/product/read?p_num=${po.p_num}&page=${page}&pageSize=${pageSize}"/>">${po.p_title}</a></td>
<%--                    <%--%>
<%--                        }--%>
<%--                    %>--%>

                    <td>${po.p_ca}</td>
                    <td>${po.p_sprice}</td>
                    <td class="writer">${po.p_eprice}</td>
                    <td class="writer">${po.a_price}</td>
                    <td><h5 id="d-day" style="font-size: 2em">${po.p_date}</h5></td>

                </tr>
            </c:forEach>
        </table>
        <br>
        <div>
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/product/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/product/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a href="<c:url value='/product/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
