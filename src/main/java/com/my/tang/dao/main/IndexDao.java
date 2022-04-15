package com.my.tang.dao.main;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;


public interface IndexDao {
    List<ProductDto> selectPage(Map map) throws Exception;
    int update(ProductDto productDto) throws Exception;
}
