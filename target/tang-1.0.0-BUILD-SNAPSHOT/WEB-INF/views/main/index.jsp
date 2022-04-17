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
        <%
            String id = (String)session.getAttribute("id");
            if(id == null || id.equals("")) {
        %>
        <li><a href="<c:url value='/login/login'/>">로그인</a></li>
        <%
        } else {
        %>
        <li><a href="<c:url value='/login/logout'/>">로그아웃</a></li>
        <%
            }
        %>
        <li><a href="<c:url value='/member/register'/>">회원가입</a></li>
        <li><a href="<c:url value='/mypage/list'/>">마이페이지</a></li>
        <li><a href="<c:url value='/history/list'/>">포인트 내역</a></li>
        <li><a href="<c:url value='/enroll/list'/>">물품등록내역</a></li>
        <li><a href="<c:url value='/bid/list'/>">경매입찰내역</a></li>
    </ul>
</div>

<div style="text-align:center">
    <div class="board-container">
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
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select p.p_img1, p.p_title, p.p_ca, u.nick, p.p_date, p.p_sprice, p.a_price, p.p_eprice from product as p join user_info as u on p.m_id = u.id order by p.p_num desc limit 0,3");

                while(rs.next()){
                p_img1 = rs.getString(1);
                p_title = rs.getString(2);
                p_ca = rs.getString(3);
                nick = rs.getString(4);
                p_date =rs.getString(5);
                p_sprice =rs.getInt(6);
                a_price =rs.getInt(7);
                p_eprice =rs.getInt(8);

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
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select p.p_img1, p.p_title, p.p_ca, u.nick, p.p_date, p.p_sprice, p.a_price, p.p_eprice from product as p join user_info as u on p.m_id = u.id order by p.p_num desc limit 0,3");

                while(rs.next()){
                p_img1 = rs.getString(1);
                p_title = rs.getString(2);
                p_ca = rs.getString(3);
                nick = rs.getString(4);
                p_date =rs.getString(5);
                p_sprice =rs.getInt(6);
                a_price =rs.getInt(7);
                p_eprice =rs.getInt(8);

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
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbasic?autoReconnect=true&useUnicode = true&characterEncoding = UTF-8  ", "root", "root");
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("select p.p_img1, p.p_title, p.p_ca, u.nick, p.p_date, p.p_sprice, p.a_price, p.p_eprice from product as p join user_info as u on p.m_id = u.id order by p.p_num desc limit 0,3");

                while(rs.next()){
                p_img1 = rs.getString(1);
                p_title = rs.getString(2);
                p_ca = rs.getString(3);
                nick = rs.getString(4);
                p_date =rs.getString(5);
                p_sprice =rs.getInt(6);
                a_price =rs.getInt(7);
                p_eprice =rs.getInt(8);

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
