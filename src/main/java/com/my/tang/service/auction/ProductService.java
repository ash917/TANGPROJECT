package com.my.tang.service.auction;


import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.event.BoardDto;

import java.util.List;
import java.util.Map;


public interface ProductService {
    int write(ProductDto productDto) throws Exception;
    int getCount() throws Exception;
    List<ProductDto> getPage(Map map) throws Exception;
//    ProductDto getClone(Map map) throws Exception;
    ProductDto read(int p_num) throws Exception;
    ProductDto readList(int p_num) throws Exception;
    int remove2(Integer p_num, String customer_id) throws Exception;
    int modify(ProductDto productDto) throws Exception;
    String selectPdate(Integer p_num) throws Exception;
    Integer selectPnum(Integer p_num) throws Exception;
    List<ProductDto> getSearchResultPage(SearchCondition sc) throws Exception;
    int getSearchResultCnt(SearchCondition sc) throws Exception;
    List<ProductDto> getSearchResultPageList(SearchCondition sc) throws Exception;
    int getSearchResultCntList(SearchCondition sc) throws Exception;



}