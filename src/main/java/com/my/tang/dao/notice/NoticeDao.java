package com.my.tang.dao.notice;


import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.notice.NoticeDto;

import java.util.List;
import java.util.Map;

public interface NoticeDao {
    NoticeDto select(Integer bno) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    int insert(NoticeDto dto) throws Exception;
    int update(NoticeDto dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;

    List<NoticeDto> selectPage(Map map) throws Exception;
    List<NoticeDto> selectAll() throws Exception;
    int deleteAll() throws Exception;
    int count() throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;
    List<NoticeDto> searchSelectPage(SearchCondition sc) throws Exception;
}