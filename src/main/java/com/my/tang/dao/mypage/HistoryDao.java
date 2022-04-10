package com.my.tang.dao.mypage;

import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;

public interface HistoryDao {
    HistoryDto select(Integer in_num) throws Exception;
    List<HistoryDto> selectPage(Map map) throws Exception;
    List<HistoryDto> selectAll() throws Exception;
    User selectPoint(String id) throws Exception;
}
