package com.my.tang.controller.mypage;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
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
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
    EnrollService enrollService;

    @GetMapping("/list")
    public String list(Integer p_num, Integer page, Integer pageSize, Model m, HttpSession session,HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        String writer = (String)session.getAttribute("id");

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {

            int totalCnt = enrollService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

            //System.out.println(p_num);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);
            map.put("m_id", writer);
            map.put("p_num", p_num);

            List<ProductDto> list = enrollService.read(map);
            //System.out.println("list = " + list);
            //List<ProductDto> list2 = enrollService.getPage(map);



            m.addAttribute("writer",writer);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "mypage/enrollList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}
