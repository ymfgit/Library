package com.ymf.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ymf.utils.LogUtil;

//单例模式
@SuppressWarnings("serial")
public class ReadProperties extends Properties{
	private static ReadProperties instance=new ReadProperties();
	
	private ReadProperties(){
		InputStream is=null;
		//读取配置文件
		try {
			is=this.getClass().getResourceAsStream("/db.properties");
			this.load(is);
		} catch (IOException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					LogUtil.log.error(e.toString());
					e.printStackTrace();
				}
			}
		}	
	}
	public static ReadProperties getPro(){
		return instance;
	}
}
