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
        포인트 ${user.m_point} P <br>
            <!-- 최근 게시물 소스 -->
                <%
              try {
                String title;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select title from noticeboard order by bno desc limit 0,1");

                while(rs.next()){
                title = rs.getString(1);

    %>
            <table border="0" width="300">
                <tr>
                    <td  width="250">
                       공지사항: <a href="/notice/list" target="_self" style="color:black;"><small><%=title%></small></a>
                    </td>
                </tr>
            </table>
                <%    } %>
                <%
          }catch(Exception e){
          out.println(e);
          }

     %>

            <br>
            <!-- 최근 게시물 소스 -->
                <%
              try {
                String title2;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt2 = conn2.createStatement();

                ResultSet rs2 = stmt2.executeQuery("select title from board order by bno desc limit 0,1");

                while(rs2.next()){
                title2 = rs2.getString(1);

    %>
            <table border="0" width="300">
                <tr>
                    <td  width="250">
                       이벤트: <a href="/board/list" target="_self" style="color:black;"><small><%=title2%></small></a>

                    </td>
                </tr>
            </table>
                <%    } %>
                <%
          }catch(Exception e){
          out.println(e);
          }
     %>

            <br>



            <!-- 최근 게시물 소스 -->
        <table border="0" width="1000">
            <tr> <td width="250">
                마감임박 &nbsp;

                <a href="/product/list" target="_self" style="color:black;"><small>더 보기 ></small></a> </td>  </tr>  </table>
            <%
              try {
                String p_img1;
                String p_title;
                String p_ca;
                String nick;
                String p_date;
                int p_sprice;
                int a_price;
                int p_eprice;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt3 = conn3.createStatement();

                ResultSet rs3 = stmt3.executeQuery("select p.p_img1, p.p_title, p.p_ca, u.nick, p.p_date, p.p_sprice, p.a_price, p.p_eprice from product as p join user_info as u on p.m_id = u.id order by p.p_num desc limit 0,3");

                while(rs3.next()){
                p_img1 = rs3.getString(1);
                p_title = rs3.getString(2);
                p_ca = rs3.getString(3);
                nick = rs3.getString(4);
                p_date =rs3.getString(5);
                p_sprice =rs3.getInt(6);
                a_price =rs3.getInt(7);
                p_eprice =rs3.getInt(8);

    %>
        <table border="1" width="1000">
            <tr>
                <td  width="250">
                    <img src=<%=p_img1%> width=100 height=100>
                    <%=p_title%>
                    <%=p_ca%>|
                    <%=nick%>
                    마감시간: <%=p_date%>
                    시작가 <%=p_sprice %>
                    현재가 <%=a_price%>
                    즉구가 <%=p_eprice%>
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
                인기경매 &nbsp;

                <a href="/product/list" target="_self" style="color:black;"><small>더 보기 ></small></a> </td>  </tr>  </table>
            <%
              try {
                String p_img1;
                String p_title;
                String p_ca;
                String nick;
                String p_date;
                int p_sprice;
                int a_price;
                int p_eprice;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt4 = conn4.createStatement();

                ResultSet rs4 = stmt4.executeQuery("select p.p_img1, p.p_title, p.p_ca, u.nick, p.p_date, p.p_sprice, p.a_price, p.p_eprice from product as p join user_info as u on p.m_id = u.id order by p.p_num desc limit 0,3");

                while(rs4.next()){
                p_img1 = rs4.getString(1);
                p_title = rs4.getString(2);
                p_ca = rs4.getString(3);
                nick = rs4.getString(4);
                p_date =rs4.getString(5);
                p_sprice =rs4.getInt(6);
                a_price =rs4.getInt(7);
                p_eprice =rs4.getInt(8);

    %>
        <table border="1" width="1000">
            <tr>
                <td  width="250">
                    <img src=<%=p_img1%> width=100 height=100>
                    <%=p_title%>
                    <%=p_ca%>|
                    <%=nick%>
                    마감시간: <%=p_date%>
                    시작가 <%=p_sprice %>
                    현재가 <%=a_price%>
                    즉구가 <%=p_eprice%>
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
                    최신경매 &nbsp;

                    <a href="/product/list" target="_self" style="color:black;"><small>더 보기 ></small></a> </td>  </tr>  </table>
                <%
              try {
                String p_img1;
                String p_title;
                String p_ca;
                String nick;
                String p_date;
                int p_sprice;
                int a_price;
                int p_eprice;

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt5 = conn5.createStatement();

                ResultSet rs5 = stmt5.executeQuery("select p.p_img1, p.p_title, p.p_ca, u.nick, p.p_date, p.p_sprice, p.a_price, p.p_eprice from product as p join user_info as u on p.m_id = u.id order by p.p_num desc limit 0,3");

                while(rs5.next()){
                p_img1 = rs5.getString(1);
                p_title = rs5.getString(2);
                p_ca = rs5.getString(3);
                nick = rs5.getString(4);
                p_date =rs5.getString(5);
                p_sprice =rs5.getInt(6);
                a_price =rs5.getInt(7);
                p_eprice =rs5.getInt(8);

    %>
            <table border="1" width="1000">
                <tr>
                    <td  width="250">
                        <img src=<%=p_img1%> width=100 height=100>
                        <%=p_title%>
                        <%=p_ca%>|
                        <%=nick%>
                        마감시간: <%=p_date%>
                        시작가 <%=p_sprice %>
                        현재가 <%=a_price%>
                        즉구가 <%=p_eprice%>
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
