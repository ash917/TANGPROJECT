package com.my.tang.domain.member;

import java.util.Date;
import java.util.Objects;

public class User {
    private String id;
    private String pwd;
    private String pwd2;
    private String name;
    private String email;
    private String nick;
    private String hp;
    private int m_point;
    private Date reg_date;

    public User() {}

    public User(String id, String pwd, String pwd2, String name, String email, String nick, String hp, int m_point, Date reg_date) {
        this.id = id;
        this.pwd = pwd;
        this.pwd2 = pwd2;
        this.name = name;
        this.email = email;
        this.nick = nick;
        this.hp = hp;
        this.m_point = m_point;
        this.reg_date = reg_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public int getM_point() {
        return m_point;
    }

    public void setM_point(int m_point) {
        this.m_point = m_point;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
}