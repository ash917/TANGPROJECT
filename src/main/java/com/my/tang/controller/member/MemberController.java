package com.my.tang.controller.member;




import com.my.tang.dao.member.UserDao;
import com.my.tang.domain.member.User;
import com.my.tang.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Inject
    MemberService memberService;

    @Inject
    UserDao userDao;

    // 회원가입 get
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void getRegister() throws Exception {
        logger.info("get register");
    }

    // 회원가입 post
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(User user, HttpSession session) throws Exception {
        String id = null;
        if(session.getAttribute("id") != null){
            id = (String) session.getAttribute("id");
        }
        if(id != null){
            return null;
        }

        userDao.insertUser(user);

        return "redirect:/";
    }

    // 회원가입2 get
    @RequestMapping(value = "/registerMaster", method = RequestMethod.GET)
    public void getRegisterMaster() throws Exception {
        logger.info("get registerMaster");
    }



    // 아이디 중복 체크
    @ResponseBody
    @RequestMapping(value="/idChk", method = RequestMethod.POST)
    public int idChk(User user) throws Exception {
        int result = memberService.idChk(user);
        return result;
    }

    // 비밀번호 중복 체크
    @ResponseBody
    @RequestMapping(value="/nickChk", method = RequestMethod.POST)
    public int nickChk(User user) throws Exception {
        int result = memberService.nickChk(user);
        return result;
    }


}


