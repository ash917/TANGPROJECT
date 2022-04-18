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
        <div class="search-container">
            <form action="<c:url value="/product/list2"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>전체</option>
                </select>

                <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>


            <th class="text-right" >
                <!-- 상품 정렬 -->
                &emsp;<a href="http://localhost/product/list2?option=O&keyword=">인기순</a>&emsp;
                <a href="http://localhost/product/list2?option=N&keyword=">최신순</a>&emsp;
                <a href="http://localhost/product/list2?option=I&keyword=">높은가격순</a>&emsp;
                <a href="http://localhost/product/list2?option=Z&keyword=">낮은가격순</a> </th>
        </div>


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
        <div class="paging-container">
            <div class="paging">
                <c:if test="${totalCnt==null || totalCnt==0}">
                    <div> 게시물이 없습니다. </div>
                </c:if>
                <c:if test="${totalCnt!=null && totalCnt!=0}">
                    <c:if test="${ph.showPrev}">
                        <a class="page" href="<c:url value="/product/list2${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                        <a class="page ${i==ph.sc.page? "paging-active" : ""}" href="<c:url value="/product/list2${ph.sc.getQueryString(i)}"/>">${i}</a>
                    </c:forEach>
                    <c:if test="${ph.showNext}">
                        <a class="page" href="<c:url value="/product/list2${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
