package com.my.tang.service.main;


import com.my.tang.dao.main.IndexDao;
import com.my.tang.dao.mypage.MypageDao;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
import com.my.tang.service.mypage.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexDao indexDao;

    @Override
    public List<ProductDto> getPage(Map map) throws Exception {
        return indexDao.selectPage(map);
    }

    @Override
    public int modify(ProductDto productDto) throws Exception {
        return indexDao.update(productDto);
    }

    @Override
    public User getNick(String nick) throws Exception {
        return indexDao.selectNick(nick);
    }

    @Override
    public User getPoint(String id) throws Exception {
        return indexDao.selectPoint(id);
    }

}
