package com.my.tang.service.mypage;

import com.my.tang.dao.mypage.BidDao;
import com.my.tang.dao.mypage.EnrollDao;
import com.my.tang.domain.auction.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    BidDao bidDao;

    @Override
    public int getCount() throws Exception {
        return bidDao.count();
    }

    @Override
    public List<ProductDto> getPage(Map map) throws Exception {
        return bidDao.selectPage(map);
    }

    @Override
    public  List<ProductDto> read(Map map) throws Exception {
        List<ProductDto> productDto = bidDao.select(map);
        return productDto;
    }

    @Override
    public int modify(ProductDto productDto) throws Exception {
        return bidDao.update(productDto);
    }
}
