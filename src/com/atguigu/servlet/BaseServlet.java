package com.atguigu.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 由于后期考虑到了事务的原子性，故此代码需要修改 ，该程序的代码中 一定要把所有的异常，
 * 都使用RuntimeException 包装起来往外抛，这样Filter里才能收到异常，否则异常只会到BaseServlet 就会终止，而不会继续往外抛
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BaseServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	doPost(request, response);
    }

	/**通过反射判断调用某个方法*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("baseServlet 当前线程名---" + Thread.currentThread().getName());
		response.setContentType("text/html; charset=UTF-8");
		//设置post 乱码字符集
		request.setCharacterEncoding("UTF-8");
		//获取action参数 
		String action = request.getParameter("action");
		try {
			//根据action参数 获取要执行的方法的对象
			Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
				//调用方法
			method.invoke(this, request, response);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
