package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.member.User;
import com.my.tang.domain.mypage.MypageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MypageDaoImpl implements MypageDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.mypage.MypageMapper.";

    @Override
    public User selectNick(String nick) throws Exception {
        return session.selectOne(namespace+"selectNick", nick);
    }

    @Override
    public User selectPoint(String id) throws Exception {
        return session.selectOne(namespace+"selectPoint", id);
    }

    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

}
