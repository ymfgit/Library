package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ymf.dao.DBHelper;
public class YuYueDao extends DBHelper{
	//根据读者编号查询预约信息
	public List<Map<String,Object>> find(String rno){
		String sql="select b.bno,bname,btype,publish,price from yuyue y," +
				"bookInfo b where b.bno=y.bno and rno=? and yuyue='是'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//根据书籍编号插入借阅表
	public int insertBookBorrow(String rno,String bno,String ldate,String bno1,String bno2){
		String sql="insert into loan(rno,bno,odate,ldate,l1,l2)values(?,?,to_char(sysdate,'yyyy-MM-dd'),"+
				"?,(select b1 from bookInfo where bno=?),(select b2 from bookInfo where bno=?))";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		params.add(bno);
		params.add(ldate);
		params.add(bno1);
		params.add(bno2);
		return this.update(sql, params);
	}
	//根据读者编号查询该读者是否预订过书籍
	public List<Map<String,Object>> findcount(String rno){
		String sql="select count(*) from yuyue where rno=? and yuyue='是'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//根据读者编号查询该读者是否预订过书籍
	public List<Map<String,Object>> findYJWH(String bno,String rno){
		String sql="select count(*) from loan where bno=? and rno=? and whether='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		params.add(rno);
		return this.find(sql,params);
	}

	
	//根据读者编号更新预订过的书籍
	public int update(String rno){
		String sql="update yuyue set yuyue='否' where rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.update(sql,params);
	}
	//借出书籍，更新借阅表
	public int updates(String rno){
		String sql="update loan set whether1='是' where whether1='否' and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.update(sql,params);
	}
	
	//根据读者编号查询应归还日期
	public List<Map<String, Object>> finding(String rno){
		String sql="select to_char(sysdate+TO_NUMBER(days),'yyyy-MM-dd') 应归还日期  from reader r,rlevel rl where r.rlevel=rl.rlevel and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	//根据书籍编号查询此图书是否已经预约
	public List<Map<String, Object>> findbook(String bno,String rno) {
		String sql="select count(*) from yuyue where bno=? and yuyue='是' and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		params.add(rno);
		return this.find(sql,params);
	}
	//根据书籍编号和读者编号插入预约表
	public int insertyuyue(String rno,String bno){
		String sql="insert into yuyue(rno,bno) values(?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		params.add(bno);
		return this.update(sql, params);
	}
	//根据书籍编号取消预约
	public int delyuyue(String bno){
		String sql="delete from yuyue where bno=? and yuyue='是'";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.update(sql, params);
	}
	////查询预约表中whether1='是'的语句条数
	public List<Map<String,Object>> findcounts(String rno){
		String sql="select count(*) from yuyue where rno=? and yuyue='是'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql, params);
	}
}
