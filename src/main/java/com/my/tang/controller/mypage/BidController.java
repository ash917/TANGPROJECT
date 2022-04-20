package com.my.tang.controller.mypage;

import com.my.tang.controller.list.ItemViewService;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import com.my.tang.service.mypage.BidService;
import com.my.tang.service.mypage.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bid")
public class BidController {
    @Autowired
    BidService bidService;

    @GetMapping("/list")
    public String list(Integer p_status, Integer page, Integer pageSize, Model m, HttpSession session,HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        String customer_id = (String)session.getAttribute("id");

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        Integer p_num = (Integer)session.getAttribute("p_num");
        ProductDto article1 = null;

        for (int i = 0; i< p_num; i++) {
            ItemViewService itemViewService = new ItemViewService();
            article1 = itemViewService.getArticle(p_num - i);

            if (article1 == null) {
                break;
            }

            if (article1.getCustomer_id() != customer_id) {
                if (article1 != null) {
                    article1.setBid_checked(false); //false로 초기화

                    try {
                        bidService.modify(article1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            article1.setCustomer_id(customer_id);

            if (article1.getCustomer_id().equals(article1.getFlag_1())) {
                article1.setBid_checked(true);
                //System.out.println(1);
            }

            if (article1.getCustomer_id().equals(article1.getFlag_2())) {
                article1.setBid_checked(true);
                //System.out.println(2);
            }

            if (article1.getCustomer_id().equals(article1.getFlag_3())) {
                article1.setBid_checked(true);
                //System.out.println(1);
            }

            if (article1.getCustomer_id().equals(article1.getFlag_4())) {
                article1.setBid_checked(true);
                //System.out.println(2);
            }

            if (article1.getCustomer_id().equals(article1.getFlag_5())) {
                article1.setBid_checked(true);
                //System.out.println(1);
            }

            try {
                bidService.modify(article1);
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {

                int totalCnt = bidService.getCount();
                PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);


                Map map = new HashMap();
                map.put("offset", (page-1)*pageSize);
                map.put("pageSize", pageSize);
                map.put("customer_id", customer_id);
                map.put("p_num", p_num- i);
                map.put("p_status", p_status);

                List<ProductDto> list = bidService.read(map);

                m.addAttribute("list", list);
                m.addAttribute("ph", pageHandler);
                m.addAttribute("page", page);
                m.addAttribute("pageSize", pageSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (article1 != null) {
            article1.setBid_checked(false); //false로 초기화

            try {
                bidService.modify(article1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "mypage/bidList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}
