package com.my.tang.controller.list;

import com.my.tang.domain.auction.ProductDto;


import static com.my.tang.controller.list.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

public class TalkSelectDetailService {
	public ArrayList<ProductDto> selectItemExhibiting(String strangerID) {
		ArrayList<ProductDto> isSuccess = null;
		
		Connection con = getConnection();
		TalkDAO talkDAO = TalkDAO.getInstance();
		talkDAO.setConnection(con);

		isSuccess = talkDAO.getItemExhibiting(strangerID);

		if(isSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}
	

}
