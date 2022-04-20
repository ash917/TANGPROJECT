package com.my.tang.dao.main;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface IndexDao {
    List<ProductDto> selectPage(Map map) throws Exception;
    int update(ProductDto productDto) throws Exception;
    User selectNick(String nick) throws Exception;
    User selectPoint(String id) throws Exception;
    int updatePoint(int m_point, String id) throws Exception;
    int updateFlag(ProductDto productDto) throws Exception;
    int updateSuccess(ProductDto productDto) throws Exception;
    User selectUser(String id) throws Exception;
}
