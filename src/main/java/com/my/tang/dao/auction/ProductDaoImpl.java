package com.my.tang.dao.auction;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
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

    @Override
    public int insert(ProductDto productDto) throws Exception {
       return session.insert(namespace+"insert", productDto);
    }

    @Override
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
    @Override
    public ProductDto select(int p_num) throws Exception {
        return session.selectOne(namespace+"select", p_num);
    } // T selectOne(String statement, Object parameter)

    @Override
    public ProductDto selectList(int p_num) throws Exception {
        return session.selectOne(namespace+"selectList", p_num);
    } // T selectOne(String statement, Object parameter)

    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    } // int delete(String statement)

    @Override
    public int delete2(Integer p_num, String customer_id) throws Exception {
        Map map = new HashMap();
        map.put("p_num", p_num);
        map.put("m_id", customer_id);
        return session.delete(namespace+"delete2", map);
    } // int delete(String statement, Object parameter)

    @Override
    public int update(ProductDto productDto) throws Exception {
        return session.update(namespace+"update", productDto);
    } // int update(String statement, Object parameter)

    @Override
    public String selectPdate(Integer p_num) throws Exception {
        return session.selectOne(namespace+"selectPdate", p_num);
    } // T selectOne(String statement, Object parameter)

    @Override
    public Integer selectPnum(Integer p_num) throws Exception {
        return session.selectOne(namespace+"selectPnum", p_num);
    } // T selectOne(String statement, Object parameter)

    @Override
    public List<ProductDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"searchSelectPage", sc);
    } // List<E> selectList(String statement, Object parameter)

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace+"searchResultCnt", sc);
    } // T selectOne(String statement)

    @Override
    public List<ProductDto> searchSelectPageList(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"searchSelectPageList", sc);
    } // List<E> selectList(String statement, Object parameter)

    @Override
    public int searchResultCntList(SearchCondition sc) throws Exception {
        return session.selectOne(namespace+"searchResultCntList", sc);
    } // T selectOne(String statement)

    @Override
    public User selectUser(String id) throws Exception {
        return session.selectOne(namespace+"selectUser", id);
    } // int update(String statement, Object parameter)

    @Override
    public int updatePoint(int m_point, String id) throws Exception {
        Map map = new HashMap();
        map.put("m_point", m_point);
        map.put("id", id);
        return session.update(namespace+"updatePoint", map);
    } // int update(String statement, Object parameter)

    @Override
    public int updateFlag(Boolean p_plus_flag, Integer p_num) throws Exception {
        Map map = new HashMap();
        map.put("p_plus_flag", p_plus_flag);
        map.put("p_num", p_num);
        return session.update(namespace+"updateFlag", map);
    } // int update(String statement, Object parameter)

    @Override
    public int updateClassify(ProductDto productDto) throws Exception {
        return session.update(namespace+"updateClassify", productDto);
    } // int update(String statement, Object parameter)

}