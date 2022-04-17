package com.my.tang.service.main;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;


public interface IndexService {
    List<ProductDto> getPage(Map map) throws Exception;
    int modify(ProductDto productDto) throws Exception;
    User getNick(String nick) throws Exception;
    User getPoint(String id) throws Exception;
}
