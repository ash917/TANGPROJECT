<%@ page import="com.my.tang.domain.auction.ProductDto" %>
<%@ page import="com.my.tang.controller.list.ItemViewService" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<c:set var="article" value="${article}"/>
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
    <li><a href="<c:url value='/bid/list'/>">경매입찰내역</a></li>
  </ul>
</div>
<div class="container">
  <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
  <form id="form" action="/product/write" method="post" enctype="multipart/form-data">
    <input type="hidden" name="p_num" value="${article.p_num}" readonly="readonly">
    <input name="p_title" type="text" value="${article.p_title}" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}> <br>
    <textarea name="p_detail" rows="20" id="" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><img src=${article.p_img1}>${article.p_detail}</textarea> <br>
  </form>

    </div>
    <div id="jb-sidebar-right">
      <h5 class="a" style="font-size: 4em">경매종료일</h5>
      <h5 id="d-day" style="font-size: 2em">${article.p_date}</h5>
      <hr class="a" style="border: solid 10px red;">
      <div>
        <div>
          <div>
            <div style="padding-top: 0;">
              <br> <br> <br>
              <h5 class="a" style="font-size: 3em">시작가</h5>
              <p style="font-size: 2em">${article.p_sprice}원</p>
            </div>
            <div>
              <br>
              <h5 class="a" style="font-size: 3em">현재 입찰가</h5>
              <input type="text" id="nowprice" value="${article.a_price}원"
                     readonly style=" font-style: normal; border:0px; text-align: center; font-size: 2em">
              <br>
              <input type="hidden" id="nowprice2" value="${article.a_price}">
              <br>
              <h5 class="a" style="font-size: 3em">즉시 구매가</h5>
              <p style="font-size: 2em">${article.p_eprice}원</p>
              <span style="color:red;">※즉시 구매가 입력시 즉시 낙찰됩니다.</span>
              <p style="font-size: 2em">
              </p><br>
              <p class="a">
                입찰 횟수<strong style="font-size: 2em">${article.a_count}</strong>
              </p>
            </div>
            <div class="myipchal">
              <br>
              <br>
              <h5 class="a" style="font-size: 2em">입찰가
                직접 입력</h5><span style="color:red;">※(주의하세요!)현재 입찰가에 금액이 더해집니다.</span><br>
              <form action="/product/modify" method="post" id="ipchal">
                <input type="hidden" id="p_num" name="p_num" value="${article.p_num}">
                <input type="hidden" id="m_id" name="m_id" value="${article.m_id}">
                <input type="hidden" id="customer_id" name="customer_id" value="${article.customer_id}">
                <input type="hidden" id="a_price" name="a_price" value="${article.a_price}">
                <input type="hidden" id="p_eprice" name="p_eprice" value="${article.p_eprice}">
                <input type="hidden" id="a_count" name="a_count" value="${article.a_count}">
                <input type="hidden" id="p_status" name="p_status" value="${article.p_status}">
                <input type="hidden" id="a_nprice" name="a_nprice" value="${article.a_nprice}">
                <input type="hidden" id="flag_1" name="flag_1" value="${article.flag_1}">
                <input type="hidden" id="flag_2" name="flag_2" value="${article.flag_2}">
                <input type="hidden" id="flag_3" name="flag_3" value="${article.flag_3}">
                <input type="hidden" id="flag_4" name="flag_4" value="${article.flag_4}">
                <input type="hidden" id="flag_5" name="flag_5" value="${article.flag_5}">
                <input type="text" id="p_plus" name="p_plus" value="${article.p_plus}" placeholder="입찰가 입력">

                <%

                  ProductDto dto = (ProductDto)request.getAttribute("article");
                  Integer no = dto.getP_num();

                  ItemViewService itemViewService = new ItemViewService();
                  ProductDto article = itemViewService.getArticle(no);

                  Calendar now= Calendar.getInstance();

                  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                  Date date = format.parse(article.getP_date()); // String -> Date
                  Calendar cal = Calendar.getInstance();
                  cal.setTime(date);  // Date -> Calendar

                  long diffSec = (now.getTimeInMillis() - cal.getTimeInMillis()) / 1000;

                  System.out.println("지금" + now.getTimeInMillis());
                  System.out.println("마감" + cal.getTimeInMillis());

                  if (article.getP_eprice() != article.getA_price() && diffSec < 0) {
                %>

                  <input type="submit" id="addprice" name="addprice" value="입찰">

                <%
                  }
                %>
              </form>
            </div>
<%--            <div>--%>
<%--              <br><br><br><br><br><br>--%>
<%--              <h5 class="a" style="font-size: 2em">고정 입찰</h5>--%>
<%--              <br>--%>
<%--              <input type="button" onclick="btn1()" id="btn1" value="5000">&nbsp&nbsp--%>
<%--              <input type="button" onclick="btn2()" id="btn2" value="10000">&nbsp&nbsp--%>
<%--              <input type="button" onclick="btn3()" id="btn3" value="15000">&nbsp&nbsp--%>
<%--            </div>--%>
          </div>
        </div>
      </div>
      <div style="border-bottom:1px solid #bcbcbc"><h5>판매자 정보</h5></div><br>
      판매자 아이디 : ${article.m_id}<br>
    </div>


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
      if(formCheck())
        form.attr("action", "<c:url value='/product/modify'/>");
        form.attr("method", "post");
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
  $('#addprice').click(function() {
    location.reload();
  });

  // 입찰 서브밋 제어문
  $('#ipchal').submit(function(){
    var now=${article.a_price}
    var max=${article.p_eprice}

    if(${not empty sessionScope.id}){

      if($("#p_plus").val()==""||$("#p_plus").val()=="0"){
        alert("입찰가를 입력해주세요");
        return false;
      // }else if($("#p_plus").val()>max){
      //   alert("입찰가가 최대 입찰가보다 높을 수 없습니다.");
      //   return false;
      // }else if(now=max){
      //   alert("현재입찰가가 최대가보다 높을수 없습니다");
      //   return false;
      // }else if((Number($("#p_plus").val())+now)>max){
      //   alert("입찰가가 최대가를 초과하였습니다.");
      //   return false;
      }else if(now>=max){
        alert("경매가 종료되었습니다.");
      }
    }else{
      alert("로그인이 필요한 서비스입니다.");
    }

  });

  $('#ipchal').submit(function(){
    var now=${article.a_price}
    var max=${article.p_eprice}
    if(now>=max){
      alert("낙찰되어 경매가 종료되었습니다.");
      return false;
    }
  });

    // 타이머 제어문
    var countDownDate = new Date("${article.p_date}").getTime();
    var now = new Date().getTime();
    if((countDownDate - now)>0){
    var x = setInterval(function() { //1초마다 갱신되도록 함수 생성,실행
    var now = new Date().getTime(); // 오늘 날짜 등록
    var distance = countDownDate - now; // 종료일자에서 현재일자를 뺀 시간
    var d = Math.floor(distance / (1000 * 60 * 60 * 24));
    var h = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var m = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var s = Math.floor((distance % (1000 * 60)) / 1000);
    //id가 d-day인 HTML코드에 내용 삽입
    document.getElementById("d-day").innerHTML = "경매종료까지 " + d +"일 " + h + "시간 " + m + "분 " + s + "초";
    if(distance<=0){

    $.ajax({
    url:"/product/list",
    type:"post",
    dataType:"html",
    data:{"p_num":${article.p_num},"m_id":"${article.m_id}","p_date":"${article.p_date}"},
    success: function(data){
    alert("경매가 종료되었습니다.");
    clearInterval(x);
  }
  });

    alert("마감된 경매입니다.");
    document.getElementById("d-day").innerHTML = "종료된 경매";

    $('#ipchal').submit(function(){
    if(distance<=0){
    alert("경매가 종료되었습니다.");
    return false;
  }
  });


  }
  });
  }else{
    alert("종료된 경매입니다.");
    document.getElementById("d-day").innerHTML = "종료된 경매";
  }
  function btn1(){
    var num1 = document.getElementById("btn1");
    var num2 = document.getElementById("price");
    var value1 = parseInt(num1.value)
    num2.value = value1;
  }

  function btn2(){
    var num1 = document.getElementById("btn2");
    var num2 = document.getElementById("price");
    var value1 = parseInt(num1.value)
    num2.value = value1;
  }
  function btn3(){
    var num1 = document.getElementById("btn3");
    var num2 = document.getElementById("price");
    var value1 = parseInt(num1.value)
    num2.value = value1;
  }




</script>
</body>
</html>