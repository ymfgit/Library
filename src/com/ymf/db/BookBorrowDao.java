package com.ymf.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ymf.dao.DBHelper;

public class BookBorrowDao extends DBHelper{
	//根据编号查询读者的借阅信息
	public List<Map<String,Object>> find(String rno){
		String sql="select l.bno,bname,btype,rname,odate,days,b1,b2," +
				"to_date(odate,'yyyy-mm-dd') +TO_NUMBER(days),whether from loan l," +
				"bookInfo b,reader r,rlevel rl where l.rno=r.rno and " +
				"l.bno=b.bno and r.rlevel=rl.rlevel and l.rno=? order by whether";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	//根据编号和whether='是'查询读者的借阅信息
	public List<Map<String,Object>> find1(String rno){
		String sql="select l.bno,bname,btype,rname,odate,days,b1,b2," +
				"to_date(odate,'yyyy-mm-dd') +TO_NUMBER(days),whether from loan l," +
				"bookInfo b,reader r,rlevel rl where l.rno=r.rno and " +
				"l.bno=b.bno and r.rlevel=rl.rlevel and whether like '是%' and l.rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//根据编号和whether='否'查询读者的借阅信息
	public List<Map<String,Object>> find2(String rno){
		String sql="select l.bno,bname,btype,rname,odate,days,b1,b2," +
				"to_date(odate,'yyyy-mm-dd') +TO_NUMBER(days),whether from loan l," +
				"bookInfo b,reader r,rlevel rl where l.rno=r.rno and " +
				"l.bno=b.bno and r.rlevel=rl.rlevel and whether='否' and l.rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//根据编号查询读者的借阅信息
	public List<Map<String,Object>> finds(String rno){
		String sql="select l.bno,bname,odate,yqfj,dsfj,rdate," +
				"to_date(odate,'yyyy-mm-dd') +TO_NUMBER(days),whether from loan l," +
				"bookInfo b,reader r,rlevel rl where l.rno=r.rno and " +
				"l.bno=b.bno and r.rlevel=rl.rlevel and l.rno=? and whether like '是%'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//根据读者借书那天到应还那天的间隔段来查询归还的信息
	public List<Map<String,Object>> findWH(String rno){
		String sql="select l.bno,bname,odate,yqfj,dsfj,rdate," +
				"to_date(odate,'yyyy-mm-dd') +TO_NUMBER(days),whether from loan l," +
				"bookInfo b,reader r,rlevel rl where l.rno=r.rno and " +
				"l.bno=b.bno and r.rlevel=rl.rlevel and l.rno=? order by whether";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//更新借阅表丢失时
	public int updateloan1(String yqfj,String dsfj,String bno,String rno){
		String sql="update loan set whether='是(丢失)',rdate=to_char(sysdate, 'yyyy-mm-dd'),yqfj=?,dsfj=? where bno=? and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(yqfj);
		params.add(dsfj);
		params.add(bno);
		params.add(rno);
		return this.update(sql, params);
	}
	//更新借阅表  逾期时
	public int updateloan(String yqfj,String dsfj,String bno,String rno){
		String sql="update loan set whether='是(逾期)',rdate=to_char(sysdate, 'yyyy-mm-dd'),yqfj=?,dsfj=? where bno=? and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(yqfj);
		params.add(dsfj);
		params.add(bno);
		params.add(rno);
		return this.update(sql, params);
	}
	//更新借阅表  正常归还时
	public int updateloan2(String yqfj,String dsfj,String bno,String rno){
		String sql="update loan set whether='是(正常)',rdate=to_char(sysdate, 'yyyy-mm-dd'),yqfj=?,dsfj=? where bno=? and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(yqfj);
		params.add(dsfj);
		params.add(bno);
		params.add(rno);
		return this.update(sql, params);
	}
	
	//查询逾期未还的findcount
	
	
	//查询借阅表中whether='否'的语句条数  /没有归还的书籍本数
	public List<Map<String,Object>> findbnum(String rno){
		String sql="select count(*) from loan where rno=? and whether='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//修改书籍信息
	public int updateBookInfo(String bno){
		String sql="update bookInfo set bnum=TO_CHAR(TO_NUMBER(bnum)+1) where bno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.update(sql, params);
	}
	
	//根据书籍编号插入借阅表
	public int insertBookBorrow(String bno,String bno1,String bno2){
		String sql="insert into loan(bno,odate,ldate,l1,l2)values(?,to_char(sysdate,'yyyy-MM-dd'),"+
				"'未填',(select b1 from bookInfo where bno=?),(select b2 from bookInfo where bno=?))";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		params.add(bno1);
		params.add(bno2);
		return this.update(sql, params);
	}
	
	//借阅成功后，将借阅表中的whether1='否'修改成whether1='是'
	public int update(){
		String sql="update loan set whether1='是' where whether1='否'";
		return this.update(sql,null);
	}
	//查询whether1='否'的书籍信息
	public List<Map<String,Object>> findallOfYBJ(){
		String sql="select * from loan where whether1='否'";
		return this.find(sql, null);
	}
	//根据读者编号更新读者的相应信息
	public List<Map<String, Object>> finding(String rno){
		String sql="select rname,r.rlevel,days,maxnum from reader r,rlevel rl where r.rlevel=rl.rlevel and rno=? and sfxs='1'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	//根据读者编号更新借阅信息
	public int updateBookBorrow(String rno,String days){
		String sql="update loan set rno=?,ldate=to_char(sysdate+TO_NUMBER(?),'yyyy-MM-dd') where whether1='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		params.add(days);
		return this.update(sql, params);
	}
	//根据读者编号查询是否存在该读者
	public List<Map<String,Object>> findindex(String rno){
		String sql="select count(*) from reader where rno=? and sfxs='1'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql, params);
	}
	//删除刚插入借阅表的所有信息
	public int delBookBorrow(){
		String sql="delete from loan where whether1='否'";
		return this.update(sql, null);
	}
	//取消刚插入借阅表的信息
	public int delBookBorrows(String bno){
		String sql="delete from loan where bno=? and whether1='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.update(sql, params);
	}
	//查询借阅表中whether1='否'的语句条数
	public List<Map<String,Object>> findcount1(String rno){
		String sql="select count(*) from loan where whether='否' and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql, params);
	}

	
	//修改书籍信息
	public int updateBookInfo1(String bno){
		String sql="update bookInfo set bnum=TO_CHAR(TO_NUMBER(bnum)-1) where bno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.update(sql, params);
	}
	//根据书籍编号查询此图书是否已在借阅模块
	public List<Map<String, Object>> findbook(String bno) {
		String sql="select count(*) from loan where bno=? and whether1='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		return this.find(sql,params);
	}
	
	//查询应还书时间是否在5天之内
	public List<Map<String, Object>> findDays(String rno) {
		String sql="select min(trunc(to_date(ldate,'yyyy-mm-dd')-sysdate))天数 from loan where trunc(to_date(ldate,'yyyy-mm-dd')-sysdate)<=5 and trunc(to_date(ldate,'yyyy-mm-dd')-sysdate)>=0 and rno=? and whether='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//查询逾期未还的
	public List<Map<String, Object>> findcount(String rno) {
		String sql="select count(*) from loan where trunc(to_date(ldate,'yyyy-mm-dd')-sysdate)<0 and rno=? and whether='否'";
		List<Object> params=new ArrayList<Object>();
		params.add(rno);
		return this.find(sql,params);
	}
	
	//根据书籍编号查询此图书是否已在借阅信息表中
	public List<Map<String, Object>> findbooks(String bno,String rno) {
		String sql="select count(*) from loan where bno=? and whether='否' and rno=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bno);
		params.add(rno);
		return this.find(sql,params);
	}
}
