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
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: "Noto Sans KR", sans-serif;
        }
        a {
            text-decoration: none;
            color: black;
        }
        button,
        input {
            border: none;
            outline: none;
        }
        .board-container {
            width: 60%;
            height: 1200px;
            margin: 0 auto;
            /* border: 1px solid black; */
        }
        .search-container {
            background-color: rgb(253, 253, 250);
            width: 100%;
            height: 110px;
            border: 1px solid #ddd;
            margin-top : 10px;
            margin-bottom: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .search-form {
            height: 37px;
            display: flex;
        }
        .search-option {
            width: 100px;
            height: 100%;
            outline: none;
            margin-right: 5px;
            border: 1px solid #ccc;
            color: gray;
        }
        .search-option > option {
            text-align: center;
        }
        .search-input {
            color: gray;
            background-color: white;
            border: 1px solid #ccc;
            height: 100%;
            width: 300px;
            font-size: 5px;
            padding: 5px 7px;
        }
        .search-input::placeholder {
            color: gray;
        }
        .search-button {
            /* 메뉴바의 검색 버튼 아이콘  */
            width: 20%;
            height: 100%;
            background-color: rgb(22, 22, 22);
            color: rgb(209, 209, 209);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 5px;
        }
        .search-button:hover {
            color: rgb(165, 165, 165);
        }
        table {
            border-collapse: collapse;
            width: 100%;
            border-top: 2px solid rgb(39, 39, 39);
        }
        tr:nth-child(even) {
            background-color: #f0f0f070;
        }
        th,
        td {
            width:300px;
            text-align: center;
            padding: 10px 12px;
            border-bottom: 1px solid #ddd;
        }
        td {
            color: rgb(53, 53, 53);
        }
        .no      { width:150px;}
        .title   { width:50%;  }
        td.title   { text-align: left;  }
        td.writer  { text-align: left;  }
        td.viewcnt { text-align: right; }
        td.title:hover {
            text-decoration: underline;
        }
        .paging {
            color: black;
            width: 100%;
            align-items: center;
        }
        .page {
            color: black;
            padding: 6px;
            margin-right: 10px;
        }
        .paging-active {
            background-color: rgb(216, 216, 216);
            border-radius: 5px;
            color: rgb(24, 24, 24);
        }
        .paging-container {
            width:100%;
            height: 70px;
            display: flex;
            margin-top: 50px;
            margin : auto;
        }
        .btn-write {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none; /* Remove borders */
            color: black; /* White text */
            padding: 6px 12px; /* Some padding */
            font-size: 5px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
            border-radius: 5px;
            margin-left: 30px;
        }
        .btn-write:hover {
            text-decoration: underline;
        }
    </style>
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
                <th class="title">날짜</th>
                <th class="writer">분류</th>
                <th class="regdate">변동포인트</th>

            </tr>
            <c:forEach var="historyDto" items="${list}">
                <tr>
                    <td>${historyDto.in_date}</td>
                    <td class="writer">${productDto.classify}</td>
                    <td class="viewcnt">${historyDto.in_point}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div>
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
            </c:if>
        </div>
    </div>
</body>
</html>