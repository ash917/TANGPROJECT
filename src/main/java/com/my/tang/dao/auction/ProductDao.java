package com.my.tang.dao.auction;


import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    int insert(ProductDto productDto) throws Exception;
    int count() throws Exception;
    List<ProductDto> selectPage(Map map) throws Exception;
//    ProductDto selectClone(Map map) throws Exception;
    ProductDto select(int p_num) throws Exception;
    ProductDto selectList(int p_num) throws Exception;
    int delete2(Integer p_num, String customer_id) throws Exception;
    int deleteAll() throws Exception;
    int update(ProductDto productDto) throws Exception;
    String selectPdate(Integer p_num) throws Exception;
    Integer selectPnum(Integer p_num) throws Exception;
    List<ProductDto> searchSelectPage(SearchCondition sc) throws Exception;
    int searchResultCnt(SearchCondition sc) throws Exception;
    List<ProductDto> searchSelectPageList(SearchCondition sc) throws Exception;
    int searchResultCntList(SearchCondition sc) throws Exception;
    User selectUser(String id) throws Exception;
    int updatePoint(int m_point, String id) throws Exception;
    int updateFlag(Boolean p_plus_flag, Integer p_num) throws Exception;
    int updateClassify(ProductDto productDto) throws Exception;






}