package com.my.tang.service.mypage;

import com.my.tang.domain.member.User;
import com.my.tang.domain.mypage.MypageDto;

import java.util.List;
import java.util.Map;


public interface MypageService {
    User getNick(String nick) throws Exception;
    User getPoint(String id) throws Exception;
}
