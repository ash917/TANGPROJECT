package com.my.tang.dao.auction;


import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    int insert(ProductDto productDto) throws Exception;
    int count() throws Exception;
    List<ProductDto> selectPage(Map map) throws Exception;
//    ProductDto selectClone(Map map) throws Exception;
    ProductDto select(int p_num) throws Exception;
    int delete(Integer p_num, String m_id) throws Exception;
    int deleteAll() throws Exception;
    int update(ProductDto productDto) throws Exception;
    String selectPdate(Integer p_num) throws Exception;
    Integer selectPnum(Integer p_num) throws Exception;
}