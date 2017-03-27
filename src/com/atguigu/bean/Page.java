package com.atguigu.bean;

import java.util.List;

/**
 * ^_^ 2017年1月16日 ^_^ 下午8:52:26 ^_^ 分页使用 泛型
 */
public class Page<T> {

	public static final long PAGE_SIZE = 4;

	/** 数据的总记录数：可以通过查询数据库，使用count(*) 来统计 */
	private long totalCount;
	/** 每页显示的记录数：可以事先设定。 */
	private long pageSize = PAGE_SIZE;
	/** 总的页数：可以通过 总记录数 / 每页显示的记录数 求出 */
	private long pageTotal;
	/** 当前页码：是根据每次访问的分页参数可以获得 */
	private long pageNo;
	/** 当前页需要显示的数据集合：可以通过 数据库 select 语句 加上 limit begin , size 来获取一页数据 */
	private List<T> list;
	
	/**为分数设置URL*/
	private String url;

	/** 在 limit begin ， size 子句中。 */
	/** begin： 可以使用公式 ( 当前页码 - 1 ) * 每页显示的数量求出 */
	/** size： 值即是每页显示的记录数。 */
	
	/**全参构造器*/
	public Page(long pageNo, long totalCount, long pageTotal, long pageSize, List<T> list) {
		super();
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageTotal = pageTotal;
		this.pageNo = pageNo;
		this.list = list;
	}

	public Page() {
		super();
	}
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageNo(long pageNo) {
		if (pageNo < 1)		 pageNo = 1;
		if (pageNo > pageTotal) 	pageNo = pageTotal;
		this.pageNo = pageNo;
	}

	public long getPageNo() {
		return pageNo;
	}

	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(long pageTotal) {
		this.pageTotal = pageTotal;
	}


	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", pageTotal=" + pageTotal + ", pageNo=" + pageNo + ", list="
				+ list + "]";
	}

}
