package com.my.tang.dao.mypage;

import com.my.tang.domain.auction.ProductDto;

import java.util.List;
import java.util.Map;

public interface BidDao {
    int count() throws Exception;
    List<ProductDto> selectPage(Map map) throws Exception;
    List<ProductDto> select(Map map) throws Exception;

}
