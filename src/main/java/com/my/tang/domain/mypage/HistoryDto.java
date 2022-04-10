package com.my.tang.domain.mypage;

import java.util.Date;

public class HistoryDto {
    private int in_num;
    private String  m_id;
    private String classification; //분류(입찰 , 유찰 , 즉시구매 등)
    private int     in_point;  //변동 포인트
    private int     in_info;
    private Date    in_date;

    public HistoryDto() {}

    public HistoryDto(int in_num, String m_id, String classification, int in_point, int in_info, Date in_date) {
        this.in_num = in_num;
        this.m_id = m_id;
        this.classification = classification;
        this.in_point = in_point;
        this.in_info = in_info;
        this.in_date = in_date;
    }

    public int getIn_num() {
        return in_num;
    }

    public void setIn_num(int in_num) {
        this.in_num = in_num;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getIn_point() {
        return in_point;
    }

    public void setIn_point(int in_point) {
        this.in_point = in_point;
    }

    public int getIn_info() {
        return in_info;
    }

    public void setIn_info(int in_info) {
        this.in_info = in_info;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }
}
