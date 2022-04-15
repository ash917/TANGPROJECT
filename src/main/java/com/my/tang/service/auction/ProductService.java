package com.my.tang.service.auction;


import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;

import java.util.List;
import java.util.Map;


public interface ProductService {
    int write(ProductDto productDto) throws Exception;
    int getCount() throws Exception;
    List<ProductDto> getPage(Map map) throws Exception;
//    ProductDto getClone(Map map) throws Exception;
    ProductDto read(int p_num) throws Exception;
    int remove(Integer p_num, String m_id) throws Exception;
    int modify(ProductDto productDto) throws Exception;
    String selectPdate(Integer p_num) throws Exception;
    Integer selectPnum(Integer p_num) throws Exception;

}