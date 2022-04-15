package com.my.tang.controller.member;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.tang.controller.list.ItemViewService;
import com.my.tang.dao.member.UserDao;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import com.my.tang.service.mypage.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserDao userDao;

    @Autowired
    BidService bidService;

    @GetMapping("/login")
    public String loginForm() {
        return "member/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response, Integer pageSize, Integer page, Integer p_num, Integer p_status, Model m) throws Exception {


        // 1. id와 pwd를 확인
        if(!loginCheck(id, pwd)) {
            // 2-1   일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

            return "redirect:/login/login?msg="+msg;
        }
        // 2-2. id와 pwd가 일치하면,
        //  세션 객체를 얻어오기
        HttpSession session = request.getSession();
        //  세션 객체에 id를 저장
        session.setAttribute("id", id);


        if(rememberId) {
            //     1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
//		       2. 응답에 저장
            response.addCookie(cookie);
        } else {
            // 1. 쿠키를 삭제
            Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
            cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
            response.addCookie(cookie);
        }
//		       3. 홈으로 이동
        toURL = toURL==null || toURL.equals("") ? "/" : toURL;

//        String customer_id = (String) session.getAttribute("id");
//
//        if(page==null) page=1;
//        if(pageSize==null) pageSize=10;
//
//        List<ProductDto> list = null;
//        try {
//            int totalCnt = bidService.getCount();
//            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
//
//            Map map = new HashMap();
//            map.put("offset", (page-1)*pageSize);
//            map.put("pageSize", pageSize);
//            map.put("customer_id", customer_id);
//            map.put("p_num", p_num);
//            map.put("p_status", p_status);
//
//            list = bidService.read(map);
//
//            m.addAttribute("list", list);
//            m.addAttribute("ph", pageHandler);
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Integer no = list.get(0).getP_num();
//        ItemViewService itemViewService = new ItemViewService();
//        ProductDto article = itemViewService.getArticle(no);
//
//        if (article.getFlag_1().equals(customer_id)) {
//            article.setCustomer_id(article.getFlag_1());
//        } else if (article.getFlag_2().equals(customer_id)) {
//            article.setCustomer_id(article.getFlag_2());
//        } else if (article.getFlag_3().equals(customer_id)) {
//            article.setCustomer_id(article.getFlag_3());
//        } else if (article.getFlag_4().equals(customer_id)) {
//            article.setCustomer_id(article.getFlag_4());
//        } else if (article.getFlag_5().equals(customer_id)) {
//            article.setCustomer_id(article.getFlag_5());
//        }



        return "redirect:"+toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        User user = null;

        try {
            user = userDao.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return user!=null && user.getPwd().equals(pwd);
//        return "ccc".equals(id) && "1111".equals(pwd);
    }
}