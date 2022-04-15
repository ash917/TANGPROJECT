package com.my.tang.controller.list;

import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.PageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/item")
public class ItemFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
	            if(command.equals("/item/view.it")) {
			action = new ItemViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) { // Redirect 방식 (주소 변경 O, request 객체 공유 X)
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식 (주소 변경 X, request 객체 공유 O)
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doProcess(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doProcess(request, response);
//	}

	@RequestMapping(value = "/view.it", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Integer p_num, ProductDto productDto, Integer page, Integer pageSize, Model m, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rattr) {


		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		action = new ItemViewAction();

		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(page==null) page=1;
		if(pageSize==null) pageSize=10;

		try {

//			int totalCnt = productService.getCount();
//			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);

//			int no = Integer.parseInt(request.getParameter("no"));

			ItemViewService itemViewService = new ItemViewService();
			ProductDto article = itemViewService.getArticle(8);

//			List<ProductDto> list = productService.getPage(map);

			//List<Integer> list2 = productService.selectPnum();

			//System.out.println(productDto);
			//System.out.println(p_num);
			//ProductDto dto = productService.read(p_num);
			//String p_date = productService.selectPdate(p_num);

			//m.addAttribute("dto", dto);
			//m.addAttribute("p_date", p_date);
			//m.addAttribute("list2", list2);
//			m.addAttribute("no", no);
//			request.setAttribute("no", no);
			m.addAttribute("article", article);
			m.addAttribute("dto", productDto);
//			m.addAttribute("list", list);
//			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "list/view";
	}

	@RequestMapping(value = "/activation.it", method= {RequestMethod.GET, RequestMethod.POST})
	public String activation(Integer p_num, ProductDto productDto, Integer page, Integer pageSize, Model m, HttpSession session) {
		String m_id = (String)session.getAttribute("id");

		TalkSelectDetailService tdss = new TalkSelectDetailService();
		ArrayList<ProductDto> selectItemExhibiting = tdss.selectItemExhibiting(m_id);
		m.addAttribute("selectItemExhibiting", selectItemExhibiting);



		return "list/activation";
	}

	}
