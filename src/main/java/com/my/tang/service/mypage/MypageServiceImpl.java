package com.my.tang.service.mypage;


import com.my.tang.dao.mypage.MypageDao;
import com.my.tang.domain.member.User;
import com.my.tang.domain.mypage.MypageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MypageServiceImpl implements MypageService {
    @Autowired
    MypageDao mypageDao;

    @Override
    public User getNick(String nick) throws Exception {
        return mypageDao.selectNick(nick);
    }

    @Override
    public User getPoint(String id) throws Exception {
        return mypageDao.selectPoint(id);
    }


}
