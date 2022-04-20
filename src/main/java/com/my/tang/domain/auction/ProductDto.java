package com.my.tang.domain.auction;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class ProductDto  {
    private Integer p_num;
    private String m_id;
    private String customer_id;
    private String current_id;
    private String flag_1; //입찰한 고유 customer_id 저장
    private String flag_2;
    private String flag_3;
    private String flag_4;
    private String flag_5;
    private String p_title;
    private String p_ca; //카테고리명
    private int p_sprice; //시작가
    private int p_eprice; //즉구가
    private int p_plus;//더한값
    private boolean p_plus_flag;
    private Date p_sdate; //경매시작일
    private String p_date; //경매종료일
    // private Date p_rdate; //남은시간
//    private String p_rdate;
    private String p_img1;
    private String p_img2;
    private String p_img3;
    private String p_img4;
    private String p_img5;
    private String p_detail;
    private String classify_buy; //분류(즉시구매, 판매수입, 입찰 등)
    private String classify_sell;
    private int a_nprice; //입찰가
    private int a_count; //입찰횟수
    private int a_price; //현재가
    private boolean bid_checked; //입찰 여부 체크
    private int m_point;
    private Date reg_date;
    private Date mod_reg_date;
    private int in_point_buy;//변동 포인트
    private int in_point_sell;
    private int immediate_flag;
    private int successful_flag;

    public int getImmediate_flag() {
        return immediate_flag;
    }

    public void setImmediate_flag(int immediate_flag) {
        this.immediate_flag = immediate_flag;
    }

    public int getSuccessful_flag() {
        return successful_flag;
    }

    public void setSuccessful_flag(int successful_flag) {
        this.successful_flag = successful_flag;
    }

    public String getCurrent_id() {
        return current_id;
    }

    public void setCurrent_id(String current_id) {
        this.current_id = current_id;
    }

    public String getClassify_buy() {
        return classify_buy;
    }

    public void setClassify_buy(String classify_buy) {
        this.classify_buy = classify_buy;
    }

    public String getClassify_sell() {
        return classify_sell;
    }

    public void setClassify_sell(String classify_sell) {
        this.classify_sell = classify_sell;
    }

    public int getIn_point_buy() {
        return in_point_buy;
    }

    public void setIn_point_buy(int in_point_buy) {
        this.in_point_buy = in_point_buy;
    }

    public int getIn_point_sell() {
        return in_point_sell;
    }

    public void setIn_point_sell(int in_point_sell) {
        this.in_point_sell = in_point_sell;
    }

    public boolean isP_plus_flag() {
        return p_plus_flag;
    }

    public void setP_plus_flag(boolean p_plus_flag) {
        this.p_plus_flag = p_plus_flag;
    }

    public Date getMod_reg_date() {
        return mod_reg_date;
    }

    public void setMod_reg_date(Date mod_reg_date) {
        this.mod_reg_date = mod_reg_date;
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

    public boolean isBid_checked() {
        return bid_checked;
    }

    public void setBid_checked(boolean bid_checked) {
        this.bid_checked = bid_checked;
    }

    public String getFlag_1() {
        return flag_1;
    }

    public void setFlag_1(String flag_1) {
        this.flag_1 = flag_1;
    }

    public String getFlag_2() {
        return flag_2;
    }

    public void setFlag_2(String flag_2) {
        this.flag_2 = flag_2;
    }

    public String getFlag_3() {
        return flag_3;
    }

    public void setFlag_3(String flag_3) {
        this.flag_3 = flag_3;
    }

    public String getFlag_4() {
        return flag_4;
    }

    public void setFlag_4(String flag_4) {
        this.flag_4 = flag_4;
    }

    public String getFlag_5() {
        return flag_5;
    }

    public void setFlag_5(String flag_5) {
        this.flag_5 = flag_5;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getP_plus() {
        return p_plus;
    }

    public void setP_plus(int p_plus) {
        this.p_plus = p_plus;
    }

    public Integer getP_num() {
        return p_num;
    }

    public void setP_num(Integer p_num) {
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

    public String getP_date() {
        return p_date;
    }

//    public String getP_rdate() {
//        return p_rdate;
//    }

    public void setP_sdate(Date p_sdate) {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            this.p_sdate = f.parse(p_sdate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        this.p_sdate = p_sdate;
    }

    public void setP_date(String p_date) {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            this.p_date = f.parse(p_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        this.p_date = p_date;

    }

//    public void setP_rdate(String p_rdate) {
//        /*
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            this.p_rdate = f.parse(p_rdate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }*/
//        this.p_rdate = p_rdate;
//    }

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