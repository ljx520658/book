package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.Parameter2Bean;
import com.atguigu.utils.StringToIntLong;

@WebServlet("/manager/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService;
       
    public BookServlet() {
        super();
        bookService = new BookServiceImpl();
    }

    /**分页显示图书列表*/
    protected void page(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	//获取当前页面
    	long pageNo = StringToIntLong.StringToLong(request.getParameter("pageNo"), 1);
    	//获取每页显示的记录数
    	long pageSize = StringToIntLong.StringToLong(request.getParameter("pageSize"), Page.PAGE_SIZE);
    	//根据给定的页码和每页显示的记录数  查询出需要显示的分页信息【图书信息列表】 
    	Page<Book> page = bookService.queryPages(pageNo, pageSize);
    	
    	// 为分页设置URL
    	page.setUrl("manager/BookServlet?action=page");
    	
    	//把分页模块数据 放到域对象中
    	request.setAttribute("page", page);
    	//转发到图书列表管理页面
    	request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    	
    }
    
    /**显示所有图书列表*/
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = bookService.queryAllBooks();
		request.setAttribute("books", books);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	/**新增一本图书*/
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		// 使用工具类 将获取的参数封装为 book
		Parameter2Bean.copyParam2Bean(book, request.getParameterMap());
		
		//获取分页信息的 页码
		String pageNo = request.getParameter("pageNo");
		boolean flg = bookService.saveBook(book);
		if (flg) {
			request.setAttribute("msg", "添加成功！");
//			// 保存之后。我们需要重定向到图书管理的列表页面。
			response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page&pageNo=" + pageNo);
			// book_manager.jsp该页面 不可以直接运行，因为list集合还没有数据呢，所以必须转发到list servlet中
//			response.sendRedirect(request.getContextPath() + "/manager/book_manager.jsp");
		} else {
			request.setAttribute("msg", "对不起，添加失败!");
		}
		
	}
	
	/**修改图书--获取该图书*/
	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用工具类 把参数转成为 int 的id值
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		// 查询要修改的图书信息
		Book queryBook = bookService.queryBookById(id);
		// 把需要修改的图书对象添加到Request域对象中
		request.setAttribute("book", queryBook);
		// 转发到编辑页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=list");
		
	}
	
	/**修改图书---修改该本书*/
	protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);

		Book bookUp = new Book();
		Parameter2Bean.copyParam2Bean(bookUp, request.getParameterMap());
		if (id > 0) {
			bookUp.setId(id);
			bookService.updateBook(bookUp);
			response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
		} else {
			request.setAttribute("msg", "对不起，修改失败!");
		}
		
	}
	
	/**删除图书*/
	protected void delBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		if (id > 0) {
			bookService.delBook(id);
			response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
			
		} else {
			request.setAttribute("msg", "delBook方法中id参数获取失败");
		}
	}


}
