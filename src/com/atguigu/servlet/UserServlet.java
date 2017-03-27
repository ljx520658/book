package com.atguigu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.Parameter2Bean;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

/**用户登录和注册的方法 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	private Gson gson = new Gson();
	public UserServlet() {
		super();
	}
	
	/**使用ajax 验证用户名是否存在*/
	protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取用户名
		String username = request.getParameter("username");
		User checkUser = userService.checkUser(username);
		Map<String, Object> map = new HashMap<String, Object>();
		if (checkUser == null) {
			// 可以注册
			map.put("isExist", true);
		} else {
			// 用户名已存在
			map.put("isExist", false);
		}
		String jsonMap = gson.toJson(map);
		response.getWriter().write(jsonMap);
	}
	

	/** 注销用户登录 */
	protected void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.getSession().invalidate();
		request.getSession().removeAttribute("username");
		request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
//		response.sendRedirect("/book/pages/user/login.jsp");
	}
	/**用户登录的方法login*/
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("查看登录服务器的请求IP地址");
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemoteAddr());
		System.out.println("=======================================================================");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().setAttribute("username", username);
		Cookie cookie = new Cookie("logCook", username);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);
		User logUser = userService.login(username, password);
//		boolean logB = userService.login(username, password);
		//判断是否登录成功
		if (logUser != null) {
			request.getSession().setAttribute("logUser", logUser);
			boolean adminB = logUser.getUsername().startsWith("admin1");
			request.getSession().setAttribute("adminB", adminB);
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "用户名或密码错误！");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}
	}
	
	
	/**用户注册的方法 regist*/
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");*/
		
		//获取页面输入框中的验证码
		String code = request.getParameter("code");
		
		User user  = new User();
		Parameter2Bean.copyParam2Bean(user, request.getParameterMap());
		User checkUser = userService.checkUser(user.getUsername());
		
		//获取谷歌生成的验证码
		String sessionToken = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//获取之后 移除session中的验证码
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (sessionToken == null) 	sessionToken = "";
		
		//先判断验证码是否正确 正确则允许注册 
		if (sessionToken.equals(code)) {
			// 判断用户名是否存在  查不到用户就可以注册
			if (checkUser == null) {
				int regist = userService.regist(user);
				if (regist > 0 ) {
					//注册成功 跳转页面//验证码正确  成功注册
					request.getSession().setAttribute("username", user.getUsername());
					request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
					
				} else {
					//注册失败 返回注册页面
					request.setAttribute("msg", "注册失败，请重新输入！");
					request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
				}
			} else {
				//用户名 已存在 
				request.setAttribute("msg", "对不起，用户名已存在！");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		} else {
			//验证码 错误 
			request.setAttribute("msg", "验证码错误，请重新输入！");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}

	}
	
	
	/***/
	/***/
	
	
	
	
	
}
