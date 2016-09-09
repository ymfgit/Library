package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ymf.dao.DBHelper;

public class NewBookDao extends DBHelper{
	//查询所有新书订购信息
	public List<Map<String,Object>> findall(){
	//	String sql="select * from odbook order by nbno desc";
		String sql="select nbno,nbname,nbtype,price*discount*0.1,nbdate,nbinfo,nbnum,publish,mno,discount,whether,o1  from odbook order by whether";

		return this.find(sql, null);
	}
	//验收新书
	
	public int updateBookInfo(String nbname,String whether){
		String sql="update odbook set whether=? where nbname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(whether);
		params.add(nbname);
		return this.update(sql, params);
	}
	//新书订购
	public int addOdbook(String nbname,String nbtype,String publish,String price,
			String nbinfo,String nbdate,String mno,String discount,String nbnum,String whether,String o1){
		String sql="insert into odbook(nbno,nbname,nbtype,publish,price,nbinfo,nbdate,mno,discount,nbnum,whether,o1) " +
				"values(seq_odbook_nbno.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(nbname);
		params.add(nbtype);
		params.add(publish);
		params.add(price);
		params.add(nbinfo);
		params.add(nbdate);
		params.add(mno);
		params.add(discount);
		params.add(nbnum);
		params.add(whether);
		params.add(o1);
		return this.update(sql, params);
	}
}
