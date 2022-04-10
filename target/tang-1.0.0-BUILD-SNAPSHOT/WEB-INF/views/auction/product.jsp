<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
        .container {
            width : 50%;
            margin : auto;
        }
        .writing-header {
            position: relative;
            margin: 20px 0 0 0;
            padding-bottom: 10px;
            border-bottom: 1px solid #323232;
        }
        input {
            width: 100%;
            height: 35px;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            padding: 8px;
            background: #f8f8f8;
            outline-color: #e6e6e6;
        }
        textarea {
            width: 100%;
            background: #f8f8f8;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            resize: none;
            padding: 8px;
            outline-color: #e6e6e6;
        }
        .frm {
            width:100%;
        }
        .btn {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none; /* Remove borders */
            color: black; /* White text */
            padding: 6px 12px; /* Some padding */
            font-size: 16px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
            border-radius: 5px;
        }
        .btn:hover {
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
    </ul>
</div>
<div class="container">
    <h2 class="writing-header">경매물품 등록</h2>
    <form id="form" action="/product/write" method="post" enctype="multipart/form-data">
        제목<input name="p_title" id="p_title" type="text" value="${po.p_title}" placeholder="  제목을 입력해 주세요." ${""}> <br>
        카테고리 선택 <input name="p_ca" id="p_ca" type="text" value="${po.p_ca}" placeholder="  카테고리명을 입력해 주세요." ${""}><br>
        시작가<input name="p_sprice" id="p_sprice" type="text" value="${po.p_sprice}" placeholder="  시작가를 입력해 주세요." ${""}> <br>
        즉시구매가<input name="p_eprice" id="p_eprice" type="text" value="${po.p_eprice}" placeholder="  즉시구매가를 입력해 주세요." ${""}> <br>
        종료일자 <input name="p_date" id="p_date" type="text" value="${po.p_date}" placeholder="  2022-01-01 18:23:00 식으로 입력해주세요" ${""}> <br>

        <%-- <sec:csrfInput /> 또는 --%>
        <!--  파일첨부 -->
        이미지등록
        <input type="file" name="file" value="${po.p_img1}"/> <br>
        <textarea name="p_detail" id="p_detail" rows="20"  placeholder=" 내용을 입력해 주세요.">${po.p_detail}</textarea> <br>
        <button type="submit" id="writeBtn" class="btn">글쓰기</button>
    </form>

</div>
<script>
    $(document).ready(function(){
        let formCheck = function() {
            let form = document.getElementById("form");
            if(form.p_title.value=="") {
                alert("제목을 입력해 주세요.");
                form.p_title.focus();
                return false;
            }
            if(form.p_sprice.value=="") {
                alert("시작가를 입력해 주세요.");
                form.p_sprice.focus();
                return false;
            }
            if(form.p_eprice.value=="") {
                alert("즉구가를 입력해 주세요.");
                form.p_eprice.focus();
                return false;
            }
            if(form.p_date.value=="") {
                alert("경매기간을 입력해 주세요.");
                form.p_date.focus();
                return false;
            }
            if(form.p_detail.value=="") {
                alert("내용을 입력해 주세요.");
                form.p_detail.focus();
                return false;
            }
            return true;
        }
        $("#writeNewBtn").on("click", function(){
            location.href="<c:url value='/product/write'/>";
        });

    });
</script>
</body>
</html>