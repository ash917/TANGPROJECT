package com.my.tang.dao.mypage;

import com.my.tang.domain.member.User;
import com.my.tang.domain.mypage.MypageDto;

import java.util.List;
import java.util.Map;


public interface MypageDao {
    User selectNick(String nick) throws Exception;
    User selectPoint(String id) throws Exception;
}
