package com.my.tang.dao;



import com.my.tang.domain.notice.NoticeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class NoticeDaoImplTest {
    @Autowired
    private com.my.tang.dao.notice.NoticeDao NoticeDao;

    @Test
    public void insertTestData() throws Exception {
        NoticeDao.deleteAll();
        for(int i=1; i<=220; i++) {
            NoticeDto noticeDto = new NoticeDto("title" + i, "no content", "aaa");
            NoticeDao.insert(noticeDto);
        }

    }

    @Test
    public void countTest() throws Exception {
        NoticeDao.deleteAll();
        assertTrue(NoticeDao.count()==0);

        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.count()==1);

        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.count()==2);
    }

    @Test
    public void deleteAllTest() throws Exception {
        NoticeDao.deleteAll();
        assertTrue(NoticeDao.count()==0);

        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.deleteAll()==1);
        assertTrue(NoticeDao.count()==0);

        NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.deleteAll()==2);
        assertTrue(NoticeDao.count()==0);
    }

    @Test
    public void deleteTest() throws Exception {
        NoticeDao.deleteAll();
        assertTrue(NoticeDao.count()==0);

        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        Integer bno = NoticeDao.selectAll().get(0).getBno();
        assertTrue(NoticeDao.delete(bno, NoticeDto.getWriter())==1);
        assertTrue(NoticeDao.count()==0);

        assertTrue(NoticeDao.insert(NoticeDto)==1);
        bno = NoticeDao.selectAll().get(0).getBno();
        assertTrue(NoticeDao.delete(bno, NoticeDto.getWriter()+"222")==0);
        assertTrue(NoticeDao.count()==1);

        assertTrue(NoticeDao.delete(bno, NoticeDto.getWriter())==1);
        assertTrue(NoticeDao.count()==0);

        assertTrue(NoticeDao.insert(NoticeDto)==1);
        bno = NoticeDao.selectAll().get(0).getBno();
        assertTrue(NoticeDao.delete(bno+1, NoticeDto.getWriter())==0);
        assertTrue(NoticeDao.count()==1);
    }

    @Test
    public void insertTest() throws Exception {
        NoticeDao.deleteAll();
        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);

        NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.count()==2);

        NoticeDao.deleteAll();
        NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.count()==1);
    }

    @Test
    public void selectAllTest() throws Exception {
        NoticeDao.deleteAll();
        assertTrue(NoticeDao.count()==0);

        List<NoticeDto> list = NoticeDao.selectAll();
        assertTrue(list.size() == 0);

        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);

        list = NoticeDao.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(NoticeDao.insert(NoticeDto)==1);
        list = NoticeDao.selectAll();
        assertTrue(list.size() == 2);
    }

    @Test
    public void selectTest() throws Exception {
        NoticeDao.deleteAll();
        assertTrue(NoticeDao.count()==0);

        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);

        Integer bno = NoticeDao.selectAll().get(0).getBno();
        NoticeDto.setBno(bno);
        NoticeDto NoticeDto2 = NoticeDao.select(bno);
        assertTrue(NoticeDto.equals(NoticeDto2));
    }

    @Test
    public void selectPageTest() throws Exception {
        NoticeDao.deleteAll();

        for (int i = 1; i <= 10; i++) {
            NoticeDto NoticeDto = new NoticeDto(""+i, "no content"+i, "asdf");
            NoticeDao.insert(NoticeDto);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<NoticeDto> list = NoticeDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = NoticeDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = NoticeDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    public void updateTest() throws Exception {
        NoticeDao.deleteAll();
        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);

        Integer bno = NoticeDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        NoticeDto.setBno(bno);
        NoticeDto.setTitle("yes title");
        assertTrue(NoticeDao.update(NoticeDto)==1);

        NoticeDto NoticeDto2 = NoticeDao.select(bno);
        assertTrue(NoticeDto.equals(NoticeDto2));
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        NoticeDao.deleteAll();
        assertTrue(NoticeDao.count()==0);

        NoticeDto NoticeDto = new NoticeDto("no title", "no content", "asdf");
        assertTrue(NoticeDao.insert(NoticeDto)==1);
        assertTrue(NoticeDao.count()==1);

        Integer bno = NoticeDao.selectAll().get(0).getBno();
        assertTrue(NoticeDao.increaseViewCnt(bno)==1);

        NoticeDto = NoticeDao.select(bno);
        assertTrue(NoticeDto!=null);
        assertTrue(NoticeDto.getView_cnt() == 1);

        assertTrue(NoticeDao.increaseViewCnt(bno)==1);
        NoticeDto = NoticeDao.select(bno);
        assertTrue(NoticeDto!=null);
        assertTrue(NoticeDto.getView_cnt() == 2);
    }
}