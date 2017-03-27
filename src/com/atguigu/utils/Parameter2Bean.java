package com.atguigu.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * ^_^ 2017年1月13日 ^_^ 下午6:45:16 ^_^
 *1、把Request请求对象的注入到Bean实体类对象中 2、把map中每个key的值， 通过对应的set方法，实现实体类的注入与封装
 */
public class Parameter2Bean {

	/**把Request请求对象的注入到Bean实体类对象中*/
	public static void copyParam2Bean(Object target, Map src) {
		
		try {
			//把map中每个key的值， 通过对应的set方法，实现实体类的注入与封装
			BeanUtils.populate(target, src);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
}
