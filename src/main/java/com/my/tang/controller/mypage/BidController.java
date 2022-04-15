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
    public String list(Integer p_status , Integer p_num, Integer page, Integer pageSize, Model m, HttpSession session,HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        String customer_id = (String)session.getAttribute("id");

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {

            int totalCnt = bidService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

//            Map map = new HashMap();
            //System.out.println(customer_id);
//            List<ProductDto> list2 = bidService.read(map);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);
            map.put("customer_id", customer_id);
            map.put("p_num", p_num);
            map.put("p_status", p_status);

            List<ProductDto> list = bidService.read(map);

//            Integer no = list.get(0).getP_num();
//            System.out.println(no);
//            ItemViewService itemViewService = new ItemViewService();
//            ProductDto article = itemViewService.getArticle(no);
//            System.out.println("list = " + list);
//            List<ProductDto> list2 = bidService.getPage(map);

//            if (article.getFlag_1().equals(customer_id)) {
//                article.setCustomer_id(article.getFlag_1());
//            } else if (article.getFlag_2().equals(customer_id)) {
//                article.setCustomer_id(article.getFlag_2());
//            } else if (article.getFlag_3().equals(customer_id)) {
//                article.setCustomer_id(article.getFlag_3());
//            } else if (article.getFlag_4().equals(customer_id)) {
//                article.setCustomer_id(article.getFlag_4());
//            } else if (article.getFlag_5().equals(customer_id)) {
//                article.setCustomer_id(article.getFlag_5());
//            }


//            map = new HashMap();
//            map.put("article", article);
//

//            m.addAttribute("article", article);
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
