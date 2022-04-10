package com.my.tang.service.member;


import com.my.tang.domain.member.User;

public interface MemberService {
    public int idChk(User user) throws Exception;
    public int nickChk(User user) throws Exception;
    public void register(User user) throws Exception;
}