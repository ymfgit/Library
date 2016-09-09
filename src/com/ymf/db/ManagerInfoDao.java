package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ymf.dao.DBHelper;


//查询管理员编号和密码
public class ManagerInfoDao extends DBHelper{
	public Map<String,Object> userLogin(String mno,String mpwd){
		String sql="select * from manager where mno=? and mpwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(mno);
		params.add(mpwd);
		
		List<Map<String,Object>> list=this.find(sql, params);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	//查询所有用户的信息
	public List<Map<String,Object>> findall(){
		String sql="select * from manager order by mno desc";
		return this.find(sql, null);
	}
	  
//	public int addUserInfo(String uname,String pwd,String sex,String age,String tel,byte[] photo){
//		String sql="insert into userInfo values(seq_userInfo_usid.Nextval,?,?,?,?,?,?)";
//		List<Object> params=new ArrayList<Object>();
//		params.add(uname);
//		params.add(pwd);
//		params.add(sex);
//		params.add(age);
//		params.add(tel);
//		params.add(photo);
//		return this.update(sql, params);
//	}
}
