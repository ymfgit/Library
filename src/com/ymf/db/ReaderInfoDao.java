package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ymf.dao.DBHelper;

//读者编号和密码查询
public class ReaderInfoDao extends DBHelper{
	public Map<String,Object> userLogin(String rno,String rpwd){
		String sql="select * from reader where sfxs='1'and rno=? and rpwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		params.add(rpwd);
		
		List<Map<String,Object>> list=this.find(sql, params);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	//查询所有读者的信息
	public List<Map<String,Object>> findall(){
		String sql="select r.*,maxnum from reader r,rlevel rl where sfxs='1'and r.rlevel=rl.rlevel order by rno desc";
		return this.find(sql, null);
	}
	public List<Map<String,Object>> finds(){
		String sql="select rno from reader where sfxs='1'";
		return this.find(sql, null);
	}
	
	//删除读者信息
	public int delReader(String rno){
		String sql="update reader set sfxs='0' where rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.update(sql, params);
	}
	//修改读者信息
	public int updateReader(String rno,String rname,String rsex,String rcardno,String rtel,
			String rbookNum,String rlevel,String ldate,String mno){
		String sql="update reader set rname=?,rsex=?,rcardno=?,rtel=?," +
				"rbookNum=?,rlevel=?,ldate=?,mno=? where rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rname);
		params.add(rsex);
		params.add(rcardno);
		params.add(rtel);
		params.add(rbookNum);
		params.add(rlevel);
		params.add(ldate);
		params.add(mno);
		params.add(rno);
		return this.update(sql, params);
	}
	//添加读者信息
	public int addReader(String rname,String rsex,String rcardno,String rtel,String rbookNum,
			String rlevel,String ldate,String mno){
		String sql="insert into reader(rno,rname,rsex,rcardno,rtel,rbookNum,rlevel,ldate,mno) " +
				"values('R'||substr(seq_reader_rno.nextval,2),?,?,?,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(rname);
		params.add(rsex);
		params.add(rcardno);
		params.add(rtel);
		params.add(rbookNum);
		params.add(rlevel);
		params.add(ldate);
		params.add(mno);
		return this.update(sql, params);
	}
	
	//根据读者编号查询借阅表中未归还的书籍
	public List<Map<String,Object>> findcount(String rno){
		String sql="select count(*) from loan where rno=? and whether='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql, params);
	}
	
	//根据读者姓名查询读者编号
	public List<Map<String,Object>> readerno(String rname){
		String sql="select rno from reader where rname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rname);
		return this.find(sql, params);
	}
	
	
	//修改等级信息
	public int updatelevel(String maxnum,String days,String rlevel){
		String sql="update rlevel set maxnum=?,days=? where rlevel=?";
		List<Object> params=new ArrayList<Object>();
		params.add(maxnum);
		params.add(days);
		params.add(rlevel);
		return this.update(sql, params);
	}
	
	
	//查询所有读者等级
	public List<Map<String,Object>> findlevel(){
		String sql="select * from  rlevel order by days desc";
		return this.find(sql, null);
	}
	//查询此等级是否已经存在
	public List<Map<String,Object>> findname(String rlevel){
		String sql="select count(*) from rlevel where rlevel=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rlevel);
		return this.find(sql, params);
	}
	//添加等级信息
	public int addLevel(String maxnum,String days,String rlevel){
		String sql="insert into rlevel(maxnum,days,rlevel) values (?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(maxnum);
		params.add(days);
		params.add(rlevel);
		return this.update(sql, params);
	}
	
	//查询等级和最大借书量
	public List<Map<String,Object>> find1(){
		String sql="select rlevel,maxnum from rlevel";
		return this.find(sql, null);
	}
	//根据等级查最大借书量
	public List<Map<String,Object>> find2(String rlevel){
		String sql="select maxnum from rlevel where rlevel=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rlevel);
		return this.find(sql, params);
	}

	
	
}
