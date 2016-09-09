package com.ymf.db;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Text;

import com.ymf.dao.DBHelper;
import com.ymf.library.Login;

public class PwdInfoDao extends DBHelper{
	//判断输入的密码与用户登录的密码是否相同
	public static boolean samePWD(Text text){
		String pwdok=Login.mpwd;
		String pwd=text.getText();
		if(pwdok.equals(pwd)){
			return true;
		}else{
			return false;
		}
	}
	
	//修改管理员的密码
	public boolean updateManagerPwd(Text text){
		String sql="update manager set mpwd=? where mno=?";
		List<Object> params=new ArrayList<Object>();
		String newpwd=text.getText().trim();
		String mno=Login.mno; //获取登录用户的账号
		params.add(newpwd);
		params.add(mno);
		if(this.update(sql, params)>0){
			return true;
		}else{
			return false;
		}
	}
	//修改读者密码
	public boolean updateReaderPwd(Text text){
		String sql="update reader set rpwd=? where rno=?";
		List<Object> params=new ArrayList<Object>();
		String newpwd=text.getText().trim();
		String mno=Login.mno; //获取登录用户的账号
		params.add(newpwd);
		params.add(mno);
		if(this.update(sql, params)>0){
			return true;
		}else{
			return false;
		}
	}
}
