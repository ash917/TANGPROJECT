package com.my.tang.dao.main;

import com.my.tang.dao.mypage.MypageDao;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class IndexDaoImpl implements IndexDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.main.IndexMapper.";

    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    @Override
    public int update(ProductDto productDto) throws Exception {
        return session.update(namespace+"update", productDto);
    } // int update(String statement, Object parameter)

    @Override
    public User selectNick(String nick) throws Exception {
        return session.selectOne(namespace+"selectNick", nick);
    }

    @Override
    public User selectPoint(String id) throws Exception {
        return session.selectOne(namespace+"selectPoint", id);
    }


}
