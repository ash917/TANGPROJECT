package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BidDaoImpl implements BidDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.mypage.BidMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } // T selectOne(String statement)

    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    public  List<ProductDto> select(Map map) throws Exception {
//        String m_id = null;
//        Integer p_num = null;
//        map = new HashMap();
//        map.put("m_id", m_id);
//        map.put("p_num", p_num);
        return session.selectList(namespace+"select", map);
    } // T selectOne(String statement, Object parameter)

}