package com.my.tang.dao.mypage;

import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepositDaoImpl implements DepositDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.mypage.DepositMapper.";

    @Override
    public int setPoint(String id) throws Exception {
        return session.update(namespace+"setPoint", id);
    } // int update(String statement, Object parameter)

    @Override
    public int setPointTwo(String id) throws Exception {
        return session.update(namespace+"setPointTwo", id);
    } // int update(String statement, Object parameter)

    @Override
    public int setPointThree(String id) throws Exception {
        return session.update(namespace+"setPointThree", id);
    } // int update(String statement, Object parameter)

    @Override
    public int setPointFour(String id) throws Exception {
        return session.update(namespace+"setPointFour", id);
    } // int update(String statement, Object parameter)

    @Override
    public int setPointFive(String id) throws Exception {
        return session.update(namespace+"setPointFive", id);
    } // int update(String statement, Object parameter)

    @Override
    public int setPointSix(String id) throws Exception {
        return session.update(namespace+"setPointSix", id);
    } // int update(String statement, Object parameter)

    @Override
    public User getPoint(String id) throws Exception {
        return session.selectOne(namespace + "getPoint", id);
    } // T selectOne(String statement, Object parameter)



}
