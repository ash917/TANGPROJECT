package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class HistoryDaoImpl implements HistoryDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.mypage.HistoryMapper.";

    @Override
    public List<HistoryDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    } // List<E> selectList(String statement)

    @Override
    public User select(String id) throws Exception {
        return session.selectOne(namespace + "select", id);
    } // T selectOne(String statement, Object parameter)

    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    @Override
    public User selectPoint(String id) throws Exception {
        return session.selectOne(namespace+"selectPoint", id);
    }

    @Override
    public List<ProductDto> selectProduct(Integer p_num) throws Exception {
        return session.selectList(namespace + "selectProduct", p_num);
    } // T selectOne(String statement, Object parameter)

    @Override
    public int updateClassify(ProductDto dto) throws Exception {
        return session.update(namespace+"updateClassify", dto);
    } // int update(String statement, Object parameter)

    @Override
    public int updateCurrent(String id) throws Exception {
        return session.update(namespace+"updateCurrent", id);
    } // int update(String statement, Object parameter)


}
