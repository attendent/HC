package com.huachen.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huachen.service.UserService;
import com.huachen.service.Impl.UserServiceImpl;

@WebServlet("/DelFriend")
public class DelFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DelFriend() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendId = request.getParameter("friendId");
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		
		UserService userservice = new UserServiceImpl();
		
		if(userservice.delFriend(userId,Integer.parseInt(friendId)) == true) {
			request.setAttribute("msg", "刪除好友成功");
			request.getRequestDispatcher("Index").forward(request, response);
		}else {
			request.setAttribute("msg", "刪除好友失败");
			request.getRequestDispatcher("Index").forward(request, response);
		}
	}

}
