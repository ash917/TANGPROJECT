package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;

public interface HistoryDao {
    User select(String id) throws Exception;
    List<ProductDto> selectPage(Map map) throws Exception;
    List<HistoryDto> selectAll() throws Exception;
    User selectPoint(String id) throws Exception;
    List<ProductDto> selectProduct(Integer p_num) throws Exception;
    int updateClassify(ProductDto dto) throws Exception;
    int updateCurrent(String id) throws Exception;
}
