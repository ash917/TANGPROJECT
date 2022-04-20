package com.my.tang.service.mypage;


import com.my.tang.dao.mypage.DepositDao;
import com.my.tang.dao.mypage.MypageDao;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    DepositDao depositDao;

    @Override
    public int setPoint(String id) throws Exception {
        return depositDao.setPoint(id);
    }

    @Override
    public int setPointTwo(String id) throws Exception {
        return depositDao.setPointTwo(id);
    }

    @Override
    public int setPointThree(String id) throws Exception {
        return depositDao.setPointThree(id);
    }

    @Override
    public int setPointFour(String id) throws Exception {
        return depositDao.setPointFour(id);
    }

    @Override
    public int setPointFive(String id) throws Exception {
        return depositDao.setPointFive(id);
    }

    @Override
    public int setPointSix(String id) throws Exception {
        return depositDao.setPointSix(id);
    }

    @Override
    public User getPoint(String id) throws Exception {
        User user = depositDao.getPoint(id);

        return user;
    }



}
