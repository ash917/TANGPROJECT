package com.my.tang.service.mypage;

import com.my.tang.domain.auction.ProductDto;

import java.util.List;
import java.util.Map;

public interface EnrollService {
    int getCount() throws Exception;
    List<ProductDto> getPage(Map map) throws Exception;
    List<ProductDto> read(Map map) throws Exception;

}
