package com.my.tang.service.member;


import com.my.tang.dao.member.MemberDao;
import com.my.tang.domain.member.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    // 아이디 중복 체크
    @Override
    public int idChk(User user) throws Exception {
        int result = memberDao.idChk(user);
        return result;
    }

    // 닉네임 중복 체크
    @Override
    public int nickChk(User user) throws Exception {
        int result = memberDao.nickChk(user);
        return result;
    }

    @Override
    public void register(User user) throws Exception {
        memberDao.register(user);
    }
}
