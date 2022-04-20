package com.my.tang.service.mypage;

import com.my.tang.dao.mypage.HistoryDao;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDao historyDao;

    @Override
    public List<HistoryDto> getList() throws Exception {
        return historyDao.selectAll();
    }

    @Override
    public List<ProductDto> getPage(Map map) throws Exception {
        return historyDao.selectPage(map);
    }

    @Override
    public User getPoint(String id) throws Exception {
        return historyDao.selectPoint(id);
    }

    @Override
    public User read(String id) throws Exception {
        User user = historyDao.select(id);
        return user;
    }

    @Override
    public List<ProductDto> selectProduct(Integer p_num) throws Exception {
        List<ProductDto> productDto = historyDao.selectProduct(p_num);
        return productDto;
    }

    @Override
    public int updateClassify(ProductDto productDto) throws Exception {
        return historyDao.updateClassify(productDto);
    }

    @Override
    public int updateCurrent(String id) throws Exception {
        return historyDao.updateCurrent(id);
    }


}
