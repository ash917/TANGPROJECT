package com.my.tang.dao.auction;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.auction.ProductMapper.";

    public int insert(ProductDto productDto) throws Exception {
       return session.insert(namespace+"insert", productDto);
    }

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } // T selectOne(String statement)

    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

//    @Override
//    public ProductDto selectClone(Map map) throws Exception {
//        return session.selectOne(namespace+"selectClone", map);
//    } // List<E> selectList(String statement, Object parameter)

    public ProductDto select(int p_num) throws Exception {
        return session.selectOne(namespace+"select", p_num);
    } // T selectOne(String statement, Object parameter)

    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    } // int delete(String statement)

    @Override
    public int delete(Integer p_num, String m_id) throws Exception {
        Map map = new HashMap();
        map.put("p_num", p_num);
        map.put("m_id", m_id);
        return session.delete(namespace+"delete", map);
    } // int delete(String statement, Object parameter)

    @Override
    public int update(ProductDto productDto) throws Exception {
        return session.update(namespace+"update", productDto);
    } // int update(String statement, Object parameter)

    public String selectPdate(Integer p_num) throws Exception {
        return session.selectOne(namespace+"selectPdate", p_num);
    } // T selectOne(String statement, Object parameter)

    public Integer selectPnum(Integer p_num) throws Exception {
        return session.selectOne(namespace+"selectPnum", p_num);
    } // T selectOne(String statement, Object parameter)

}