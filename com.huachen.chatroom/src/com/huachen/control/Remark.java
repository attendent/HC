package com.huachen.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.service.UserService;
import com.huachen.service.Impl.UserServiceImpl;

@WebServlet("/Remark")
public class Remark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Remark() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendId = request.getParameter("friendId");
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		String remark = request.getParameter("remark");
		
		UserService userservice = new UserServiceImpl();
		if(userservice.updateRemark(userId,Integer.parseInt(friendId),remark) == true) {
			request.setAttribute("msg", "修改备注成功!");
			request.getRequestDispatcher("Index").forward(request, response);
		}else {
			request.setAttribute("msg", "修改备注失败!");
			request.getRequestDispatcher("Index").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
