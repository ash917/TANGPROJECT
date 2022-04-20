<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>탕탕탕</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>회원가입</title>
</head>
<script type="text/javascript">
    $(document).ready(function(){
        // 취소
        $(".cencle").on("click", function(){
            location.href = "/";
        })

        $("#submit").on("click", function(){
            if($("#id").val()==""){
                alert("아이디를 입력해주세요.");
                $("#id").focus();
                return false;
            }
            if($("#pwd").val()==""){
                alert("비밀번호를 입력해주세요.");
                $("#pwd").focus();
                return false;
            }
            if($("#pwd2").val()==""){
                alert("비밀번호 확인을 입력해주세요.");
                $("#pwd2").focus();
                return false;
            }
            if($("#nick").val()==""){
                alert("닉네임을 입력해주세요.");
                $("#nick").focus();
                return false;
            }
            if($("#email").val()==""){
                alert("이메일을 입력해주세요.");
                $("#email").focus();
                return false;
            }
            if($("#hp").val()==""){
                alert("휴대폰번호을 입력해주세요.");
                $("#hp").focus();
                return false;
            }
            var idChkVal = $("#idChk").val();
            if(idChkVal == "N"){
                alert("중복확인 버튼을 눌러주세요.");
                return false;
            }else if(idChkVal == "Y"){
                $("#regForm").submit();
            }
        });
    })

    function fn_idChk(){
        $.ajax({
            url : "/member/idChk",
            type : "post",
            dataType : "json",
            data : {"id" : $("#id").val()},
            success : function(data){
                if(data == 1){
                    alert("중복된 아이디입니다.");
                }else if(data == 0){
                    $("#idChk").attr("value", "Y");
                    alert("사용가능한 아이디입니다.");
                }
            }
        })
    }
    function fn_nickChk(){
        $.ajax({
            url : "/member/nickChk",
            type : "post",
            dataType : "json",
            data : {"nick" : $("#nick").val()},
            success : function(data){
                if(data == 1){
                    alert("중복된 별명입니다.");
                }else if(data == 0){
                    $("#nickChk").attr("value", "Y");
                    alert("사용가능한 별명입니다.");
                }
            }
        })
    }
</script>
<body>
<jsp:include page="../etc/top.jsp" />
<script>
    let msg = "${msg}";
    if(msg=="REG_ERR")   alert("가입에 실패 하였습니다.");
    if(msg=="REG_OK")    alert("회원가입이 성공적으로 되었습니다.");
</script>
<section id="container">
    <form action="/member/register" method="post" id="regForm">
        <div class="form-group has-feedback">
            <label class="control-label" for="id">아이디</label>
            <input class="form-control" type="text" id="id" name="id" />
            <button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="pwd">비밀번호</label>
            <input class="form-control" type="password" id="pwd" name="pwd" />
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="pwd2">비밀번호확인</label>
            <input class="form-control" type="password" id="pwd2" name="pwd2" />
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="nick">닉네임</label>
            <input class="form-control" type="text" id="nick" name="nick" />
            <button class="nickChk" type="button" id="nickChk" onclick="fn_nickChk();" value="N">중복확인</button>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="email">이메일</label>
            <input class="form-control" type="text" id="email" name="email" />
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="hp">휴대폰번호</label>
            <input class="form-control" type="text" id="hp" name="hp" />
        </div>


    </form>
    <div class="form-group has-feedback">
        <button class="btn btn-success" type="button" id="submit">회원가입</button>
        <button class="cencle btn btn-danger" type="button">취소</button>
    </div>
</section>

</body>

</html>