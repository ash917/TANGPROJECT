package com.my.tang.controller.list;

import static com.my.tang.controller.list.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.my.tang.domain.auction.ProductDto;

public class ItemDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private static ItemDAO instance;

	private ItemDAO() {
	}

	public static ItemDAO getInstance() {
		if (instance == null) {
			instance = new ItemDAO();
		}

		return instance;
	}

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}


//	private Integer ;
//	private String ;
//	private String p_title;
//	private String ; //카테고리명
//	private int ; //시작가
//	private int p_eprice; //즉구가
//	private Date p_sdate; //경매시작일
//	private Date ; //경매종료일
//	// private Date p_rdate; //남은시간
//	private String p_rdate;
//	private String ;
//	private String p_img2;
//	private String p_img3;
//	private String p_img4;
//	private String p_img5;
//	private String p_detail;
//	private String classify; //분류(즉시구매, 판매수입, 입찰 등)
//	private boolean p_status;
//	private int a_nprice; //입찰가
//	private int a_count; //입찰횟수
//	private int ; //현재가
	
	 //상품 정보 가져오기
	public ProductDto selectArticle(int no) {
		ProductDto article = null;

		try {
			sql = "SELECT * FROM product WHERE p_num = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new ProductDto();
				article.setP_num(rs.getInt("p_num"));
				article.setM_id(rs.getString("m_id"));
				article.setCustomer_id(rs.getString("customer_id"));
				article.setP_title(rs.getString("p_title"));
				article.setP_ca(rs.getString("p_ca"));
				article.setP_sdate(rs.getDate("p_sdate"));
				article.setP_date(rs.getString("p_date").substring(0, 16));
				article.setP_sprice(rs.getInt("p_sprice"));
				article.setA_price(rs.getInt("a_price"));
				article.setP_detail(rs.getString("p_detail"));
				article.setP_img1(rs.getString("p_img1"));
				article.setP_img2(rs.getString("p_img2"));
				article.setP_img3(rs.getString("p_img3"));
				article.setP_img4(rs.getString("p_img4"));
				article.setP_img5(rs.getString("p_img5"));
				article.setClassify(rs.getString("classify"));
				article.setP_status(rs.getInt("p_status"));
				article.setA_nprice(rs.getInt("a_nprice"));
				article.setA_count(rs.getInt("a_count"));
				article.setP_eprice(rs.getInt("p_eprice"));
				article.setP_plus(rs.getInt("p_plus"));
				article.setFlag_1(rs.getString("flag_1"));
				article.setFlag_2(rs.getString("flag_2"));
				article.setFlag_3(rs.getString("flag_3"));
				article.setFlag_4(rs.getString("flag_4"));
				article.setFlag_5(rs.getString("flag_5"));

			}

			//System.out.println("1" + article.getP_plus());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return article;
	}

	public int selectOrderArticle(int no) {
		int orderarticle = 0;

		try {
			sql = "SELECT count(*) FROM product WHERE p_num = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				orderarticle=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return orderarticle;
	}

}
