package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ymf.dao.DBHelper;



/**
 * 图书信息查询
 *
 */
public class BookInfoDao extends DBHelper{
	//查询所有书籍的信息
	public List<Map<String,Object>> findall(){
		String sql="select * from bookInfo where sfxs='1' order by bno desc";
		return this.find(sql, null);
	}
	
	//删除书籍信息
	public int delBookInfo(String bno){
		String sql="update bookInfo set sfxs='0' where bno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.update(sql, params);
	}
	
	//修改书籍信息
	public int updateBookInfo(String bno,String bname,String binfo,String btype,String publish,
			String price,String bnum,String ldate,String mno){
		String sql="update bookInfo set bname=?,binfo=?,btype=?,publish=?," +
				"price=?,bnum=?,ldate=?,mno=? where bno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bname);
		params.add(binfo);
		params.add(btype);
		params.add(publish);
		params.add(price);
		params.add(bnum);
		params.add(ldate);
		params.add(mno);
		params.add(bno);
		return this.update(sql, params);
	}
	//添加书籍信息
	public int addBookInfo(String bname,String binfo,String btype,String publish,
			String pdate,String price,String bnum,String ldate,String mno){
		String sql="insert into bookInfo(bno,bname,binfo,btype,publish,pdate,price,bnum,ldate,mno) " +
				"values(seq_bookInfo_bno.Nextval,?,?,?,?,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(bname);
		params.add(binfo);
		params.add(btype);
		params.add(publish);
		params.add(pdate);
		params.add(price);
		params.add(bnum);
		params.add(ldate);
		params.add(mno);
		return this.update(sql, params);
	}
	//根据条件查询图书信息
	public List<Map<String,Object>> find1(String bno){
		String sql="select * from bookInfo where sfxs='1' and bno=? order by bno desc";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.find(sql,params);
	}
	public List<Map<String,Object>> find2(String bname){
		String sql="select * from bookInfo where sfxs='1' and bname=? order by bno desc";
		List<Object> params=new ArrayList<Object>();
		params.add(bname);
		return this.find(sql,params);
	}
	public List<Map<String,Object>> find3(String binfo){
		String sql="select * from bookInfo where sfxs='1' and binfo=? order by bno desc";
		List<Object> params=new ArrayList<Object>();
		params.add(binfo);
		return this.find(sql,params);
	}
	public List<Map<String,Object>> find4(String btype){
		String sql="select * from bookInfo where sfxs='1' and btype=? order by bno desc";
		List<Object> params=new ArrayList<Object>();
		params.add(btype);
		return this.find(sql,params);
	}
	public List<Map<String,Object>> find5(String publish){
		String sql="select * from bookInfo where sfxs='1' and publish=? order by bno desc";
		List<Object> params=new ArrayList<Object>();
		params.add(publish);
		return this.find(sql,params);
	}
	//将借阅的书籍插入借阅信息表中
	public int addBookBorrow(String bno,String odate,String rdate,String rno,String mno,String l1,String l2){
		String sql="insert into loan(rno,bno,odate,rdate,mno,l1,l2)values(?,?,?,?,?,null,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		params.add(bno);
		params.add(odate);
		params.add(rdate);
		params.add(mno);
		params.add(l1);
		params.add(l2);
		return this.update(sql, params);
	}
}
