package com.my.tang.controller.mypage;


import com.my.tang.controller.list.ItemViewService;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;
import com.my.tang.service.mypage.HistoryService;
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
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping("/list")
    public String list(ProductDto productDto, Integer page, Integer pageSize, Model m, HttpServletRequest request, HttpSession session) throws Exception {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        ProductDto article = null;



        Integer p_num = (Integer)session.getAttribute("p_num");
        String current_id = (String)session.getAttribute("id");
        //System.out.println(p_num);

        try {
            historyService.updateCurrent(current_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i< p_num; i++) {
            ItemViewService itemViewService = new ItemViewService();
            article = itemViewService.getArticle(p_num - i);

            if (article == null) {
                break;
            }

            //System.out.println(article);

            try {
                if (article.getClassify_sell().contains(article.getM_id() + "/")) {
                    // System.out.println("1");
                    article.setClassify_sell("판매수익");
                }

                if (article.getClassify_buy().contains(article.getCustomer_id() + "/")) {
                    //System.out.println("2");
                    article.setClassify_buy("낙찰");
                }

                historyService.updateClassify(article);

                String id = (String) session.getAttribute("id");
                User user = historyService.getPoint(id);

                User readUser = historyService.read(id);
//            List<ProductDto> readProduct = historyService.selectProduct(id);
                //System.out.println("0");

                PageHandler pageHandler = new PageHandler(page, pageSize);

                Map map = new HashMap();
                map.put("offset", (page - 1) * pageSize);
                map.put("pageSize", pageSize);

                List<ProductDto> list = historyService.getPage(map);

                m.addAttribute("article", article);
                m.addAttribute("readUser", readUser);
                m.addAttribute("list", list);
                m.addAttribute("user", user);
                m.addAttribute("ph", pageHandler);
                m.addAttribute("page", page);
                m.addAttribute("pageSize", pageSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "/mypage/historyList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}
