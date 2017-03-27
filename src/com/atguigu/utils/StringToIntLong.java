package com.atguigu.utils;
/**
 * ^_^ 2017年1月16日 ^_^ 下午7:18:48 ^_^
 * 将获取的字符串转换为 int 或 long型数据
 */
public class StringToIntLong {

	/**将获取的字符串转换为 int,如果出现异常转换为 默认值*/
	public static int StringToInt(String id, int val) {
		try {
			return Integer.parseInt(id);
		} catch (NumberFormatException e) {
		}
		return val;
	}
	
	
	/**将获取的字符串转换为 long*/
	public static long StringToLong(String str, long val) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
		}
		return val;
	}
	
	/**将获取的字符串转换为 long*/
	public static double StringToDouble(String str, double val) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
		}
		return val;
	}
	
	/***/
	
	
	/***/
	
	
	
}
