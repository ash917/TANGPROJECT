package com.my.tang.dao.mypage;

import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;


public interface DepositDao {
    int setPoint(String id) throws Exception;
    int setPointTwo(String id) throws Exception;
    int setPointThree(String id) throws Exception;
    int setPointFour(String id) throws Exception;
    int setPointFive(String id) throws Exception;
    int setPointSix(String id) throws Exception;
    User getPoint(String id) throws Exception;
}
