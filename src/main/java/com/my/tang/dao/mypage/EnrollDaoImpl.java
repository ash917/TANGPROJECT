package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EnrollDaoImpl implements EnrollDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.mypage.EnrollMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } // T selectOne(String statement)

    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    public  List<ProductDto> select(String m_id) throws Exception {
        return session.selectList(namespace+"select", m_id);
    } // T selectOne(String statement, Object parameter)

}