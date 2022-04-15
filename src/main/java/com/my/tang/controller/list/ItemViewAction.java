package com.my.tang.controller.list;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.tang.domain.auction.ProductDto;


public class ItemViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		int no = Integer.parseInt(request.getParameter("no"));
		
		//쿠키값 저장 : it_no 쿠키저장, 유효기간 7일 
		 String cookieName = "p_num"+no;
		 Cookie cookie = new Cookie(cookieName,request.getParameter("no"));
		 cookie.setPath("/");
		 cookie.setMaxAge(60*60*24*7);
		 response.addCookie(cookie);

		ItemViewService itemViewService = new ItemViewService();
		ProductDto article = itemViewService.getArticle(no);
		int orderarticle = itemViewService.getOrderArticle(no);
		// 출품자 회원 아이디 가져오기
//		TalkSelectMemberService sps = new TalkSelectMemberService();
//		String strangerID = sps.getMemberID(no); // 출품자 회원 아이디
		
		request.setAttribute("article", article);
		request.setAttribute("orderarticle", orderarticle);
		request.setAttribute("no", no);
//		request.setAttribute("strangerID", strangerID);

		forward = new ActionForward();
		forward.setPath("/item/view.jsp");

		return forward;
	}

}
