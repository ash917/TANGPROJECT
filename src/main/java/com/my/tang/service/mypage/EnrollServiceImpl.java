package com.my.tang.service.mypage;

import com.my.tang.dao.mypage.EnrollDao;
import com.my.tang.domain.auction.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnrollServiceImpl implements EnrollService {
    @Autowired
    EnrollDao enrollDao;

    @Override
    public int getCount() throws Exception {
        return enrollDao.count();
    }

    @Override
    public List<ProductDto> getPage(Map map) throws Exception {
        return enrollDao.selectPage(map);
    }

    @Override
    public  List<ProductDto> read(String m_id) throws Exception {
        List<ProductDto> productDto = enrollDao.select(m_id);
        return productDto;
    }


}
