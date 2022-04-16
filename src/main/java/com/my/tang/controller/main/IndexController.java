package com.my.tang.controller.main;


import com.my.tang.controller.list.ItemViewService;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import com.my.tang.service.main.IndexService;
import com.my.tang.service.mypage.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/*")
public class IndexController {
    @Autowired
    IndexService indexService;

    @GetMapping("/")
    public String index(Integer page, Integer pageSize, Model m, HttpServletRequest request, HttpSession session) {


        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {
            String customer_id = (String) session.getAttribute("id");

            PageHandler pageHandler = new PageHandler(page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);
            map.put("customer_id", customer_id);

            List<ProductDto> list = indexService.getPage(map);

            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);


            ProductDto article = null;
            try {
                Integer no = list.get(0).getP_num();
                ItemViewService itemViewService = new ItemViewService();
                article = itemViewService.getArticle(no);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //System.out.println(no);
//            session.setAttribute("p_num", article.getP_num());

//            if (article.getFlag_1() != null && article.getFlag_1().equals("") && article.getCustomer_id() != null && article.getCustomer_id().equals("")) {
//                if (article.getFlag_1().equals(customer_id)) {
//                    article.setCustomer_id(article.getFlag_1());
//                } else if (article.getFlag_2().equals(customer_id)) {
//                    article.setCustomer_id(article.getFlag_2());
//                } else if (article.getFlag_3().equals(customer_id)) {
//                    article.setCustomer_id(article.getFlag_3());
//                } else if (article.getFlag_4().equals(customer_id)) {
//                    article.setCustomer_id(article.getFlag_4());
//                } else if (article.getFlag_5().equals(customer_id)) {
//                    article.setCustomer_id(article.getFlag_5());
//                }
//            }
//            //System.out.println(article.getFlag_1());
//            indexService.modify(article);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return "/main/index";
    }

}