package com.my.tang.service.notice;


import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.notice.NoticeDto;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    int getCount() throws Exception;
    int remove(Integer bno, String writer) throws Exception;
    int write(NoticeDto noticeDto) throws Exception;
    List<NoticeDto> getList() throws Exception;
    NoticeDto read(Integer bno) throws Exception;
    List<NoticeDto> getPage(Map map) throws Exception;
    int modify(NoticeDto boardDto) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;
    List<NoticeDto> getSearchResultPage(SearchCondition sc) throws Exception;
}