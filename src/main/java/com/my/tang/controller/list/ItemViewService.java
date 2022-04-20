package com.my.tang.controller.list;

import static com.my.tang.controller.list.JdbcUtil.close;
import static com.my.tang.controller.list.JdbcUtil.getConnection;

import java.sql.Connection;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.member.User;


public class ItemViewService {

	public ProductDto getArticle(int no) {

//			System.out.println("BoardDetailService - getArticle()");
//			System.out.println("board_num = " + board_num);

		ProductDto article = null;

		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

 		article = itemDAO.selectArticle(no);

//			if(article != null) {
//				int updateCount = itemDAO.updateReadcount(no);
//				if(updateCount > 0) {
//					commit(con);
//				} else {
//					rollback(con);
//				}
//			}

		close(con);

		return article;

	}

	public User getArticleUser(String id) {

//			System.out.println("BoardDetailService - getArticle()");
//			System.out.println("board_num = " + board_num);

		User article = null;

		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		article = itemDAO.selectArticleUser(id);

//			if(article != null) {
//				int updateCount = itemDAO.updateReadcount(no);
//				if(updateCount > 0) {
//					commit(con);
//				} else {
//					rollback(con);
//				}
//			}

		close(con);

		return article;

	}



	public int getOrderArticle(int no) {
		int orderarticle = 0;

		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		ItemDAO itemDAO = ItemDAO.getInstance();
		itemDAO.setConnection(con);

		orderarticle = itemDAO.selectOrderArticle(no);


		close(con);

		return orderarticle;

	}

}
