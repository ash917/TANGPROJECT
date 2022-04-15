package com.my.tang.controller.list;

import static com.my.tang.controller.list.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.my.tang.domain.auction.ProductDto;

public class TalkDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	private static TalkDAO instance;

	private TalkDAO() {
	}

	public static TalkDAO getInstance() {
		if (instance == null) {
			instance = new TalkDAO();
		}

		return instance;
	}

	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 상품 번호로 출품자 회원 아이디 가져오기
	public String getMemberID(int itemNum) {
		String memberID = null;

		try {
			sql = "SELECT m_id FROM product WHERE p_num = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNum);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				memberID = rs.getString("m_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return memberID;
	}
	
	
	// 현재 활성화된 톡 출품자의 출품중인 상품 가져오기 (최대 4개)
	public ArrayList<ProductDto> getItemExhibiting(String strangerID) {
		ArrayList<ProductDto> articleList = null;

		try {
			articleList = new ArrayList<ProductDto>();
			
			sql = "SELECT p_num, p_title, a_price, p_img1 FROM product WHERE m_id = ? ORDER BY p_num DESC LIMIT 4";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, strangerID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto list = new ProductDto();
					
				list.setP_num(rs.getInt("it_no"));
				list.setP_title(rs.getString("it_name"));
				list.setA_price(rs.getInt("it_start_price"));
				list.setP_img1(rs.getString("it_thumbnail"));
				
				articleList.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	

}
