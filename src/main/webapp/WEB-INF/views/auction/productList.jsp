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
  <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
  <form id="form" action="/product/write" method="post" enctype="multipart/form-data">
    <input type="hidden" name="p_num" value="${productDto.p_num}" readonly="readonly">
    <input name="p_title" type="text" value="${productDto.p_title}" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}> <br>
    <!--  파일첨부 -->
    이미지등록
    <input type="file" name="file" value="${productDto.p_img1}"/> <br>
    <textarea name="p_detail" rows="20" id="" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><img src=${productDto.p_img1}>${productDto.p_detail}</textarea> <br>
    <button type="button" id="writeBtn" class="btn">글쓰기</button>
    <button type="button" id="modifyBtn" class="btn">수정</button>
    <button type="button" id="removeBtn" class="btn">삭제</button>
    <button type="button" id="listBtn" class="btn">목록</button>
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
    $("#writeBtn").on("click", function(){
      let form = $("#form");
      form.attr("action", "<c:url value='/product/write'/>");
      form.attr("method", "post");
      if(formCheck())
        form.submit();
    });
    $("#modifyBtn").on("click", function(){
      let form = $("#form");
      let readonly = $("input[name=title]", "#form").attr('readonly');
      // 1. 읽기 상태이면, 수정 상태로 변경
      if(readonly!=undefined) {
        $(".writing-header").html("게시판 수정");
        $("input[name=title]", "#form").attr('readonly', false);
        $("textarea", "#form").attr('readonly', false);
        $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
        return;
      }
      // 2. 수정 상태이면, 수정된 내용을 서버로 전송
      form.attr("action", "<c:url value='/product/modify'/>");
      form.attr("method", "post");
      if(formCheck())
        form.submit();
    });
    $("#removeBtn").on("click", function(){
      if(!confirm("정말로 삭제하시겠습니까?")) return;
      let form = $("#form");
      form.attr("action", "<c:url value='/product/remove?page=${page}&pageSize=${pageSize}'/>");
      form.attr("method", "post");
      form.submit();
    });
    $("#listBtn").on("click", function(){
      location.href="<c:url value='/product/list?page=${page}&pageSize=${pageSize}'/>";
    });
  });
</script>
</body>
</html>