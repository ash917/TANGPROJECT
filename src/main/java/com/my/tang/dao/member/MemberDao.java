package com.my.tang.dao.member;

import com.my.tang.domain.member.User;

public interface MemberDao {
    // 아이디 중복체크
    public int idChk(User user) throws Exception;

    // 닉네임 중복체크
    public int nickChk(User user) throws Exception;

    // 회원가입
    public void register(User user) throws Exception;
}
