package com.my.tang.dao.member;

import com.my.tang.domain.member.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{
    @Autowired
    private SqlSession session;
    private static String namespace = "com.my.tang.dao.member.MemberMapper.";

    // 아이디 중복 체크
    @Override
    public int idChk(User user) throws Exception {
        int result = session.selectOne(namespace+"idChk", user);
        return result;
    }

    // 닉네임 중복 체크
    @Override
    public int nickChk(User user) throws Exception {
        int result = session.selectOne(namespace+"nickChk", user);
        return result;
    }

    @Override
    public void register(User user) throws Exception {
        session.insert(namespace+"register", user);
    }
}
