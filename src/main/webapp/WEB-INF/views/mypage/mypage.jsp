<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<%@ page import="java.sql.*" %>
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
        ${user.nick} 님 반갑습니다
        포인트 ${user.m_point} P

            <!-- 최근 게시물 소스 -->
            <table border="0" width="2000">
                <tr> <td width="500">
                    포인트 사용 내역 &nbsp;

                    <a href="/history/list" target="_self" style="color:black;"><small>더 보기 ></small></a> </td>  </tr>  </table>
                <%
              try {

                String classification;
                int in_point;
                Date in_date;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select in_date, classification, in_point from pointinfo order by in_num desc limit 0,3");

                while(rs.next()){
                in_date =rs.getDate(1);
                classification=rs.getString(2);
                in_point = rs.getInt(3);

    %>
            <table border="1" width="2000">
                <tr>
                    <td  width="500">
                         <%=in_date%>
                         <%=classification%>
                         <%=in_point%>
                    </td>
                </tr>
            </table>
                <%    } %>
                <%
          }catch(Exception e){
          out.println(e);
          }
     %>
<br><br><br>
            <!-- 최근 게시물 소스 -->
            <table border="0" width="1000">
                <tr> <td width="250">
                    공지 사항 &nbsp;

                    <a href="/notice/list" target="_self" style="color:black;"><small>더 보기 ></small></a> </td>  </tr>  </table>
                <%
              try {

                String title;
                Date reg_date;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select title, reg_date from noticeboard order by bno desc limit 0,3");

                while(rs.next()){
                title = rs.getString(1);
                reg_date =rs.getDate(2);

    %>
            <table border="1" width="1000">
                <tr>
                    <td  width="250">
                        <%=title%>
                        <%=reg_date%>
                    </td>
                </tr>
            </table>
                <%    } %>
                <%
          }catch(Exception e){
          out.println(e);
          }
     %>
<br><br><br>
            <!-- 최근 게시물 소스 -->
            <table border="0" width="1000">
                <tr> <td width="250">
                    이벤트 &nbsp;

                    <a href="/board/list" target="_self" style="color:black;"><small>더 보기 ></small></a> </td>  </tr>  </table>
                <%
              try {

                String title;
                Date reg_date;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select title, reg_date from board order by bno desc limit 0,3");

                while(rs.next()){
                title = rs.getString(1);
                reg_date =rs.getDate(2);

    %>
            <table border="1" width="1000">
                <tr>
                    <td  width="250">
                        <%=title%>
                        <%=reg_date%>
                    </td>
                </tr>
            </table>
                <%    } %>
                <%
          }catch(Exception e){
          out.println(e);
          }
     %>


</body>
</html>
