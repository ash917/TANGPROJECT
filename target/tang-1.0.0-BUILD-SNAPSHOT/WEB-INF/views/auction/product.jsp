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
</head>
<body>
<jsp:include page="../etc/top.jsp" />
<div class="container">
    <h2 class="writing-header">경매물품 등록</h2>
    <form id="form" action="/product/write" method="post" enctype="multipart/form-data">
        제목<input name="p_title" id="p_title" type="text" value="<c:out value='${po.p_title}'/>" placeholder="  제목을 입력해 주세요." ${""}> <br>
        <div class="dropdown">
            <span class="dropbtn">카테고리 선택</span>
            <select name="p_ca" id="p_ca" class="dropdown-content">
                <option value="">선택</option>
                <option value="주방">주방</option>
                <option value="가전">가전</option>
                <option value="스포츠 레저">스포츠 레저</option>
                <option value="의류">의류</option>
                <option value="기타 잡화">기타 잡화</option>
            </select>
        </div>  <br>
        시작가<input name="p_sprice" id="p_sprice" type="text" value="${po.p_sprice}" placeholder="  시작가를 입력해 주세요." ${""}> <br>
        즉시구매가<input name="p_eprice" id="p_eprice" type="text" value="${po.p_eprice}" placeholder="  즉시구매가를 입력해 주세요." ${""}> <br>
        종료일자 <input name="p_date" id="p_date" type="text" value="${po.p_date}" placeholder="  2022-01-01 18:23 식으로 입력해주세요" ${""}> <br>
        <input name="p_num" id="p_num" type="hidden" value="${po.p_num}">



        <%-- <sec:csrfInput /> 또는 --%>
        <!--  파일첨부 -->
        이미지등록
        <input type="file" id="file" name="file" value="${po.p_img1}"/> <br>
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
            if(form.p_ca.value=="") {
                alert("카테고리를 선택해 주세요.");
                form.p_ca.focus();
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
            if(form.file.value=="") {
                alert("사진을 첨부해 주세요.");
                form.file.focus();
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