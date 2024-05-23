package guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.GuestbookDao;
import guestbook.vo.GuestbookVo;




public class GuestbookServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");
		if("add".equals(action)) {
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			String regDate = request.getParameter("reg_date");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			vo.setRegDate(regDate);
			
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);

			response.sendRedirect(request.getContextPath()+"/gb");
			
		} else if ("deleteform".equals(action)) {
			String no = request.getParameter("no");
	        request.setAttribute("no", no);
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
	        rd.forward(request, response);
			
		} else if("delete".equals(action)){
			long no = Long.parseLong(request.getParameter("no"));
	    	String password = request.getParameter("password");
	    	GuestbookDao dao = new GuestbookDao();
	    	dao.deleteByNoAndPassword(no,password);
	    	
	    	response.sendRedirect(request.getContextPath()+"/gb");
		}
		else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			request.setAttribute("list", list);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
