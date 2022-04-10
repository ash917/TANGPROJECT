package com.my.tang.domain.auction;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDto {
    private int p_num;
    private String m_id;
    private String p_title;
    private String p_ca; //카테고리명
    private int p_sprice; //시작가
    private int p_eprice; //즉구가
    private Date p_sdate; //경매시작일
    private Date p_date; //경매종료일
    private Date p_rdate; //남은시간
    private String p_img1;
    private String p_img2;
    private String p_img3;
    private String p_img4;
    private String p_img5;
    private String p_detail;
    private String classify; //분류(즉시구매, 판매수입, 입찰 등)
    private boolean p_status;
    private int a_nprice; //입찰가
    private int a_count; //입찰횟수
    private int a_price; //현재가


    public int getP_num() {
        return p_num;
    }

    public void setP_num(int p_num) {
        this.p_num = p_num;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getP_ca() {
        return p_ca;
    }

    public void setP_ca(String p_ca) {
        this.p_ca = p_ca;
    }

    public int getP_sprice() {
        return p_sprice;
    }

    public void setP_sprice(int p_sprice) {
        this.p_sprice = p_sprice;
    }

    public int getP_eprice() {
        return p_eprice;
    }

    public void setP_eprice(int p_eprice) {
        this.p_eprice = p_eprice;
    }

    public Date getP_sdate() {
        return p_sdate;
    }

    public Date getP_date() {
        return p_date;
    }

    public Date getP_rdate() {
        return p_rdate;
    }

    public void setP_sdate(String p_sdate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.p_sdate = f.parse(p_sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setP_date(String p_date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.p_date = f.parse(p_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setP_rdate(String p_rdate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.p_rdate = f.parse(p_rdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getP_img1() {
        return p_img1;
    }

    public void setP_img1(String p_img1) {
        this.p_img1 = p_img1;
    }

    public String getP_img2() {
        return p_img2;
    }

    public void setP_img2(String p_img2) {
        this.p_img2 = p_img2;
    }

    public String getP_img3() {
        return p_img3;
    }

    public void setP_img3(String p_img3) {
        this.p_img3 = p_img3;
    }

    public String getP_img4() {
        return p_img4;
    }

    public void setP_img4(String p_img4) {
        this.p_img4 = p_img4;
    }

    public String getP_img5() {
        return p_img5;
    }

    public void setP_img5(String p_img5) {
        this.p_img5 = p_img5;
    }

    public String getP_detail() {
        return p_detail;
    }

    public void setP_detail(String p_detail) {
        this.p_detail = p_detail;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public boolean getP_status() {
        return p_status;
    }

    public boolean isP_status() {
        return p_status;
    }

    public void setP_status(boolean p_status) {
        this.p_status = p_status;
    }

    public int getA_nprice() {
        return a_nprice;
    }

    public void setA_nprice(int a_nprice) {
        this.a_nprice = a_nprice;
    }

    public int getA_count() {
        return a_count;
    }

    public void setA_count(int a_count) {
        this.a_count = a_count;
    }

    public int getA_price() {
        return a_price;
    }

    public void setA_price(int a_price) {
        this.a_price = a_price;
    }
}