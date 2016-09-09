package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ymf.dao.DBHelper;

/**
 * 图书类型更新
 * @author Administrator
 *
 */
public class BookTypeDao extends DBHelper {
	//查询所有书籍的类型
	public List<Map<String,Object>> findall(){
		String sql="select * from  booktype order by btno desc";
		return this.find(sql, null);
	}
	public List<Map<String,Object>> finds(){
		String sql="select btype from  booktype order by btno desc";
		return this.find(sql, null);
	}
	
	//根据输入的图书类别名查询图书类别表看是否已存在此类别
	public List<Map<String,Object>> findname(String btype){
		String sql="select count(*) from  booktype where btype=?";
		List<Object> params=new ArrayList<Object>();
		params.add(btype);
		return this.find(sql, params);
	}
	
	//添加书籍类型
	public int addBookType(String btype){
		String sql="insert into booktype(btno,btype) " +
				"values(seq_booktype_btno.nextval,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(btype);
		
		return this.update(sql, params);
	}
	//修改图书类型
	public int updateBookType(String btno,String btype){
		String sql="update booktype set btype=? where btno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(btype);
		params.add(btno);
		return this.update(sql, params);
	}
	//删除图书类别
	public int delbtype(String btno,String btype){
		String sql="delete from booktype where btno=? and btype=?";
		List<Object> params=new ArrayList<Object>();
		params.add(btno);
		params.add(btype);
		return this.update(sql, params);
	}
}
