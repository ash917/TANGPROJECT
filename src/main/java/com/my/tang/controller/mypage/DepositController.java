package com.my.tang.controller.mypage;


import com.my.tang.domain.etc.PageHandler;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import com.my.tang.service.mypage.DepositService;
import com.my.tang.service.mypage.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    DepositService depositService;

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request, HttpSession session) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {
            String id = (String)session.getAttribute("id");
            User user = depositService.getPoint(id);

            PageHandler pageHandler = new PageHandler(page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);



            m.addAttribute("user", user);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/mypage/deposit";
    }

    @RequestMapping(value = "/modify1", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify1(User user, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            if (depositService.setPoint(writer)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/mypage/deposit"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("writer", writer);
            m.addAttribute("msg", "MOD_ERR");
            return "/mypage/deposit";
        }
    }

    @RequestMapping(value = "/modify2", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify2(User user, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            if (depositService.setPointTwo(writer)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/mypage/deposit"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("writer", writer);
            m.addAttribute("msg", "MOD_ERR");
            return "/mypage/deposit";
        }
    }

    @RequestMapping(value = "/modify3", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify3(User user, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            if (depositService.setPointThree(writer)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/mypage/deposit"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("writer", writer);
            m.addAttribute("msg", "MOD_ERR");
            return "/mypage/deposit";
        }
    }

    @RequestMapping(value = "/modify4", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify4(User user, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            if (depositService.setPointFour(writer)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/mypage/deposit"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("writer", writer);
            m.addAttribute("msg", "MOD_ERR");
            return "/mypage/deposit";
        }
    }

    @RequestMapping(value = "/modify5", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify5(User user, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            if (depositService.setPointFive(writer)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/mypage/deposit"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("writer", writer);
            m.addAttribute("msg", "MOD_ERR");
            return "/mypage/deposit";
        }
    }

    @RequestMapping(value = "/modify6", method= {RequestMethod.GET, RequestMethod.POST})
    public String modify6(User user, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            if (depositService.setPointSix(writer)!= 1)
                throw new Exception("Modify failed.");

            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/mypage/deposit"+sc.getQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("writer", writer);
            m.addAttribute("msg", "MOD_ERR");
            return "/mypage/deposit";
        }
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}
