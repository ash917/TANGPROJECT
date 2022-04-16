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


            ItemViewService itemViewService = new ItemViewService();
            ProductDto article = itemViewService.getArticle(1);

            article.setCustomer_id(customer_id);

            String flag_1 = article.getFlag_1();
            String flag_2 = article.getFlag_2();
            String flag_3 = article.getFlag_3();
            String flag_4 = article.getFlag_4();
            String flag_5 = article.getFlag_5();
            Boolean bid_checked = article.isBid_checked();

            if (article.getCustomer_id().equals(flag_1)) {
                article.setBid_checked(true);
                System.out.println(1);
            }

            if (article.getCustomer_id().equals(flag_2)) {
                article.setBid_checked(true);
                System.out.println(2);
            }

        try {
            bidService.modify(article);
        } catch (Exception e) {
            e.printStackTrace();
        }


            try {

                int totalCnt = bidService.getCount();
                PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

                try {
                    bidService.modify(article);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                m.addAttribute("ph", pageHandler);
                m.addAttribute("page", page);
                m.addAttribute("pageSize", pageSize);
            } catch (Exception e) {
                e.printStackTrace();
            }


            itemViewService = new ItemViewService();
            ProductDto article2 = itemViewService.getArticle(2);

            article2.setCustomer_id(customer_id); //반드시 들어가야 함 , 새로운 객체에

            if (article2.getCustomer_id().equals(article2.getFlag_1())) {
                article2.setBid_checked(true);
                System.out.println(3);
            }

            if (article2.getCustomer_id().equals(article2.getFlag_2())) {
                article2.setBid_checked(true);
                System.out.println(4);
            }


            try {
                bidService.modify(article2);
            } catch (Exception e) {
                e.printStackTrace();
            }


        itemViewService = new ItemViewService();
        ProductDto article3 = itemViewService.getArticle(3);

        article3.setCustomer_id(customer_id); //반드시 들어가야 함 , 새로운 객체에

        if (article3.getCustomer_id().equals(article3.getFlag_1())) {
            article3.setBid_checked(true);
            //System.out.println(3);
        }

        if (article3.getCustomer_id().equals(article3.getFlag_2())) {
            article3.setBid_checked(true);
            //System.out.println(4);
        }


        try {
            bidService.modify(article3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemViewService = new ItemViewService();
        ProductDto article4 = itemViewService.getArticle(4);

        article4.setCustomer_id(customer_id); //반드시 들어가야 함 , 새로운 객체에

        if (article4.getCustomer_id().equals(article4.getFlag_1())) {
            article4.setBid_checked(true);
            //System.out.println(3);
        }

        if (article4.getCustomer_id().equals(article4.getFlag_2())) {
            article4.setBid_checked(true);
            //System.out.println(4);
        }


        try {
            bidService.modify(article4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemViewService = new ItemViewService();
        ProductDto article5 = itemViewService.getArticle(5);

        article5.setCustomer_id(customer_id); //반드시 들어가야 함 , 새로운 객체에

        if (article5.getCustomer_id().equals(article5.getFlag_1())) {
            article5.setBid_checked(true);
            //System.out.println(3);
        }

        if (article5.getCustomer_id().equals(article5.getFlag_2())) {
            article5.setBid_checked(true);
            //System.out.println(4);
        }


        try {
            bidService.modify(article5);
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
            map.put("p_num", p_num);
            map.put("p_status", p_status);
            map.put("flag_1", flag_1);
            map.put("flag_2", flag_2);
            map.put("flag_3", flag_3);
            map.put("flag_4", flag_4);
            map.put("flag_5", flag_5);
            map.put("bid_checked", bid_checked);

            List<ProductDto> list = bidService.read(map);


            article.setBid_checked(false); //false로 초기화
            article2.setBid_checked(false); //false로 초기화
            article3.setBid_checked(false); //false로 초기화
            article4.setBid_checked(false); //false로 초기화
            article5.setBid_checked(false); //false로 초기화


            try {
                bidService.modify(article);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                bidService.modify(article2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                bidService.modify(article3);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                bidService.modify(article4);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                bidService.modify(article5);
            } catch (Exception e) {
                e.printStackTrace();
            }

            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
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