package com.faceye.feature.repository.jpa;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建SQL的实体类
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年6月26日
 */
public class SQLBuilderEntity {
    /**
     * 未加入参数的原始SQL
     * SQL将会被拼装成：select * from user wher name =? and age =?类型
     */
	private String sql="";
	/**
	 * SQL中将要加入的参数值，有序
	 */
	private List<Object> paramValues=new ArrayList<Object>();
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Object> getParamValues() {
		return paramValues;
	}
	public void setParamValues(List<Object> paramValues) {
		this.paramValues = paramValues;
	}
	

	
	
	
}
