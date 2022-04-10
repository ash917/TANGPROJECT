package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;

import java.util.List;
import java.util.Map;

public interface EnrollDao {
    int count() throws Exception;
    List<ProductDto> selectPage(Map map) throws Exception;
    List<ProductDto> select(String m_id) throws Exception;

}
