package com.my.tang.service.mypage;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;

public interface HistoryService {
    List<HistoryDto> getList() throws Exception;
    List<ProductDto> getPage(Map map) throws Exception;
    User getPoint(String id) throws Exception;
    User read(String id) throws Exception;
    List<ProductDto> selectProduct(Integer p_num) throws Exception;
    int updateClassify(ProductDto productDto) throws Exception;
    int updateCurrent(String id) throws Exception;
}
