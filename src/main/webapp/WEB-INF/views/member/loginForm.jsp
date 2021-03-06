<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
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
<form action="<c:url value="/login/login"/>" method="post" onsubmit="return formCheck(this);">
  <h3 id="title">Login</h3>
  <div id="msg">
    <c:if test="${not empty param.msg}">
      <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
    </c:if>
  </div>
  <input type="text" name="id" value="${cookie.id.value}" placeholder="" autofocus>
  <input type="password" name="pwd" placeholder="비밀번호">
  <input type="hidden" name="toURL" value="${param.toURL}">
  <button>로그인</button>
  <div>

    <a href="/member/register">회원가입</a>
  </div>
  <script>
    function formCheck(frm) {
      let msg ='';
      if(frm.id.value.length==0) {
        setMessage('id를 입력해주세요.', frm.id);
        return false;
      }
      if(frm.pwd.value.length==0) {
        setMessage('password를 입력해주세요.', frm.pwd);
        return false;
      }
      return true;
    }
    function setMessage(msg, element){
      document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
      if(element) {
        element.select();
      }
    }
  </script>
</form>
</body>
</html>