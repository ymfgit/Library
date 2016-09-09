package com.ymf.dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ymf.utils.LogUtil;
import com.ymf.utils.ShellUtils;


public class DBHelper {
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private ShellUtils shellUtils=new ShellUtils();
	private LogUtil logUtil=new LogUtil();
	
	static {
		try {
			Class.forName(ReadProperties.getPro().getProperty("driver"));
		} catch (ClassNotFoundException e) { 
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 获取连接
	 * @return
	 */
	public Connection getConnection(){
		try {
			//注意：db.properties中必须是user和password 否则就不能使用这种方式，只能单独获取用户名和密码
			con=DriverManager.getConnection(ReadProperties.getPro().getProperty("url"),ReadProperties.getPro().getProperty("user"),ReadProperties.getPro().getProperty("password"));
			System.out.println("进来了"+con);
			
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());;
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public void CloseAll(Connection con,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) { 
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
		}
		
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 给占位符赋值的方法
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	public void setValue(PreparedStatement pstmt,List<Object> params) throws SQLException{
		//给sql语句中的占位符赋值
		if(params!=null && params.size()>0 ){//说明要执行的sql语句中有占位符
			Object obj=null;
			String type="";
			for(int i=0;i<params.size();i++){
				obj=params.get(i);
				if(obj!=null){
					type=obj.getClass().getName();
					if("[B".equals(type)){
						pstmt.setBytes(i+1, (byte[]) obj);
					}else{
						pstmt.setString(i+1, String.valueOf(obj));

					}
				}else{
					pstmt.setString(i+1, String.valueOf(obj));
				}
			}
		}
	}
	
	/*
	 * 更新操作
	 * @param sql:要执行的sql语句
	 * @param params:要执行的sql语句中，对应?（占位符）的值，如果为null，则说明sql语句中没有占位符
	 */
	public int update(String sql,List<Object> params){
		int result=0;
		
		try {
			con=this.getConnection();//获取连接
			pstmt=con.prepareStatement(sql);//预编译要执行的sql语句
			
			this.setValue(pstmt, params);
			
			//执行语句
			result=pstmt.executeUpdate();
		} catch (SQLException e) { 
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}finally{
			this.CloseAll(con, pstmt, null);
		}
		return result;
	}
	
	public List<Map<String,Object>> find(String sql,List<Object> params){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		
		try {
			con=this.getConnection();//获取连接
			pstmt=con.prepareStatement(sql);//预编译要执行的sql语句
			
			this.setValue(pstmt, params);
			
			rs=pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();//获取源数据
			//从源数据中获取列的信息
			String[] colNames=new String[ rsmd.getColumnCount() ];
			for(int i=0;i<colNames.length;i++){
				colNames[i]=rsmd.getColumnName(i+1);
			}
			
			while(rs.next()){
				map=new HashMap<String,Object>();
				Object obj=null;
				String type;
				if(colNames!=null && colNames.length>0){
					for(String s:colNames){
						obj= rs.getObject(s);
						if(obj!=null){
							type=obj.getClass().getName();
							if("oracle.sql.BLOB".equals(type)){
								Blob blob=rs.getBlob(s);
								byte[] bt=null;
								InputStream is=blob.getBinaryStream();
								if(is!=null){
									bt=new byte[ (int) blob.length() ];
									try {
										is.read(bt);
									} catch (IOException e) {
										e.printStackTrace();
									}finally{
										if(is!=null){
											try {
												is.close();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
									}
									map.put(s, bt);
								}else{
									map.put(s,null);
								}
							}else{
								map.put(s,String.valueOf(obj));
							}
						}else{
						map.put(s,null);
						}
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}finally{
			this.CloseAll(con,pstmt,rs);
			
		}
		return list;
	}
	
	/**
	 * 聚合查询
	 * @param sql：要执行的查询语句
	 * @param params：要执行的sql语句中，对应?（占位符）的值，如果为null，则说明sql语句中没有占位符
	 * @return
	 */
	public double findPloymer(String sql,List<Object> params){
		double result=0;
		con=this.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				
				result=rs.getDouble(1);
			}
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}finally{
			this.CloseAll(con, pstmt, rs);
		}
		return result;
	}
	
	public List<Double> findPloymers(String sql,List<Object> params){
		List<Double> result=new ArrayList<Double>();
		con=this.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				int count=rs.getMetaData().getColumnCount();
				for(int i=0;i<count;i++){
					result.add(rs.getDouble(i+1));
					
				}
			}
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}finally{
			this.CloseAll(con, pstmt, rs);	
		}
		return result;
	}
	
	public boolean createOp(String sql){
		boolean bl=false;
		
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			bl=pstmt.execute();
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			//e.printStackTrace();
		}finally{
			this.CloseAll(con, pstmt, rs);	
		}
		return bl;
	}
}

