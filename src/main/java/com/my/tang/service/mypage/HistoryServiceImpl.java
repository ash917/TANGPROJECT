package com.my.tang.service.mypage;

import com.my.tang.dao.mypage.HistoryDao;
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
    public List<HistoryDto> getPage(Map map) throws Exception {
        return historyDao.selectPage(map);
    }

    @Override
    public User getPoint(String id) throws Exception {
        return historyDao.selectPoint(id);
    }


}
