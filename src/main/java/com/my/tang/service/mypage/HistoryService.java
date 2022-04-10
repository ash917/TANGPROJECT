package com.my.tang.service.mypage;

import com.my.tang.domain.mypage.HistoryDto;
import com.my.tang.domain.member.User;

import java.util.List;
import java.util.Map;

public interface HistoryService {
    List<HistoryDto> getList() throws Exception;
    List<HistoryDto> getPage(Map map) throws Exception;
    User getPoint(String id) throws Exception;
}
