package com.ymf.table;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookBorrowDao;
import com.ymf.db.ReaderInfoDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Combo;

/**
 * 图书归还管理
 *
 */
public class BookBack extends Composite {
	private static Table table;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private static BookBorrowDao bookBorrowDao=new BookBorrowDao();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private String bno="";
	private String str="";
	private static String rno="";
	private static Combo combo;
	private String y="";
	private String d="";
	private int count=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookBack(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BookBack.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		composite_1.setBackgroundImage(SWTResourceManager.getImage(BookBack.class, "/images/bg1.png"));
		composite_1.setBounds(36, 36, 845, 587);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setBounds(52, 46, 745, 241);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("读者编号：");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(425, 27, 80, 22);
		
		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 72, 745, 167);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(66);
		tableColumn.setText("书籍编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(60);
		tableColumn_1.setText("书籍名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(61);
		tableColumn_2.setText("书籍类别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(60);
		tableColumn_3.setText("读者姓名");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(84);
		tableColumn_5.setText("借书时间");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(62);
		tableColumn_7.setText("可借天数");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(86);
		tableColumn_6.setText("应归还时间");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(74);
		tableColumn_8.setText("是否已归还");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("逾期罚金(元/日)");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("  丢失图书罚金");
		
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				bno=tableCursor.getRow().getText(0);
				str=tableCursor.getRow().getText(7);
				d=tableCursor.getRow().getText(9);
				text_1.setText(tableCursor.getRow().getText(4));
				text_2.setText(tableCursor.getRow().getText(5));
				String str1=text_6.getText().trim();        //当前日期
				String str2=text_1.getText().trim();        //借书时日期
				//将其转换成date类型
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date1= sdf.parse(str1);
					Date date2= sdf.parse(str2);
					int num1=(int) getTwoDay(date2,date1);
					int num2=Integer.parseInt(tableCursor.getRow().getText(5));
					int num3=num1-num2;
					if(num3<=0){
						num3=0;
					}
					text_3.setText(String.valueOf(num1));    //实际天数
					text_4.setText(String.valueOf(num3));    //超出天数
					float num4=Float.parseFloat(tableCursor.getRow().getText(8));
					float num5=num4*num3;
					DecimalFormat df = new DecimalFormat("#.##");   //保留两位小数
					text_5.setText(String.valueOf(df.format(num5)));     //罚金
					y=String.valueOf(df.format(num5));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		Group group_3 = new Group(group, SWT.NONE);
		group_3.setBounds(138, 13, 208, 48);
		
		final Button button_2 = new Button(group_3, SWT.RADIO);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_2.setBounds(10, 14, 33, 27);
		button_2.setText("是");
		
		final Button button_4 = new Button(group_3, SWT.RADIO);
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_4.setBounds(64, 14, 33, 27);
		button_4.setText("否");
		
		Button button_5 = new Button(group_3, SWT.RADIO);
		button_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_5.setBounds(111, 14, 87, 27);
		button_5.setText("全部显示");
			
		combo = new Combo(group, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if(button_2.getSelection()){
					showInfo1();
				}else if(button_4.getSelection()){
					showInfo2();
				}else{
					showInfo();
				}
				
				TableItem[] tit=table.getItems();
				for(int j=0;j<tit.length;j++){
					if(tit[j].getText(7).equals("否")){
						Color a=new Color(null, 77, 166, 255);
						tit[j].setBackground(a);
					}
				}

			}
		});
		combo.setBounds(535, 26, 119, 25);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_10.setBounds(36, 27, 96, 22);
		label_10.setText("是否已归还：");
		
		add();
		
		Group group_1 = new Group(composite_1, SWT.NONE);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group_1.setBounds(62, 293, 321, 254);
		
		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setText(" 罚款信息");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_1.setFont(SWTResourceManager.getFont("楷体", 16, SWT.NORMAL));
		label_1.setBounds(10, 10, 119, 24);
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setText("借书日期：");
		label_2.setBounds(23, 50, 79, 24);
		
		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setEnabled(false);
		text_1.setBounds(108, 47, 179, 27);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setText("规定天数：");
		label_3.setBounds(25, 89, 77, 27);
		
		text_2 = new Text(group_1, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setEnabled(false);
		text_2.setBounds(108, 89, 179, 27);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setText("实际天数：");
		label_4.setBounds(25, 136, 77, 24);
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setEnabled(false);
		text_3.setBounds(108, 133, 179, 27);
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setText("超出天数：");
		label_5.setBounds(25, 176, 77, 20);
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setEnabled(false);
		text_4.setBounds(108, 175, 179, 27);
		
		Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setText("罚款金额：");
		label_6.setBounds(25, 220, 77, 24);
		
		text_5 = new Text(group_1, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_5.setEnabled(false);
		text_5.setBounds(108, 217, 179, 27);
		
		Group group_2 = new Group(composite_1, SWT.NONE);
		group_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group_2.setBounds(399, 293, 389, 254);
		
		Label label_7 = new Label(group_2, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("楷体", 16, SWT.NORMAL));
		label_7.setText(" 系统信息");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_7.setBounds(10, 10, 126, 29);
		
		Label label_8 = new Label(group_2, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setText("当前日期：");
		label_8.setBounds(50, 45, 78, 29);
		
		text_6 = new Text(group_2, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_6.setEnabled(false);
		text_6.setBounds(176, 42, 141, 30);
		text_6.setText(sdf.format(new Date()));
		
		Label label_9 = new Label(group_2, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setText("操作员编号：");
		label_9.setBounds(50, 88, 100, 29);
		
		text_7 = new Text(group_2, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_7.setEnabled(false);
		text_7.setBounds(175, 85, 142, 30);
		Button button = new Button(group_2, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setText("图书归还");
		button.setBounds(56, 201, 94, 29);
		
		Button button_1 = new Button(group_2, SWT.NONE);
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setText("退 出");
		button_1.setBounds(237, 201, 80, 29);
		
		Button button_3 = new Button(group_2, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_3.setBounds(56, 145, 94, 30);
		button_3.setText("图书丢失");
		
		Label label_13 = new Label(composite_1, SWT.NONE);
		label_13.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_13.setBounds(327, 10, 144, 30);
		label_13.setText("图书归还管理");
		label_13.setFont(SWTResourceManager.getFont("楷体", 17, SWT.NORMAL));
		text_7.setText(Login.mno);

		//关闭
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.bookBack.setVisible(false);
					table.removeAll();
					combo.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_5.setText("");
				}			
			}
		});
		
		//图书丢失
		button_3.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if("".equals(text_1.getText().trim())){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("请选中要提交的图书信息...");
					mg.open();
				}else if(str=="是(逾期)"||"是(逾期)".equals(str)||str=="是(正常)"||"是(正常)".equals(str)||str=="是(丢失)"||"是(丢失)".equals(str)
){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("错误提示");
					mg.setMessage("此图书已归还或您已提交图书丢失罚金....");
					mg.open();
					combo.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_5.setText("");
				}else if(MessageDialog.openConfirm(getShell(), "图书丢失罚金提交提醒","确定提交罚金"+d+"元吗?")){
					String yqfj="0";
					String dsfj=d;
					if(bookBorrowDao.updateloan1(yqfj,dsfj,bno,rno)>0){
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
						mg.setText("提示信息");
						mg.setMessage("图书丢失罚金提交成功....");
						mg.open();
						showInfo();
						combo.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ERROR);
						mg.setText("提示信息");
						
						mg.setMessage("图书丢失罚金提交失败....");
						mg.open();
					}
				}
			}
		});
		//图书归还
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rno_1=combo.getText().trim();
				if(rno_1!=null&&!"".equals(rno_1)){
					rno=rno_1;
				}
				List<Map<String,Object>> list =bookBorrowDao.findcount(rno);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				if("".equals(text_1.getText().trim())){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("请选中要提交的图书信息...");
					mg.open();
				}else if(str=="是(逾期)"||"是(逾期)".equals(str)||str=="是(正常)"||"是(正常)".equals(str)||str=="是(丢失)"||"是(丢失)".equals(str)
){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("错误提示");
					mg.setMessage("此图书已归还或您已提交图书丢失罚金....");
					mg.open();
					combo.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_5.setText("");
				}else if(y.equals("0") || y=="0"){
					if(MessageDialog.openConfirm(getShell(), "图书归还提醒","确定归还吗?")){
						if(bookBorrowDao.updateBookInfo(bno)>0){
							String yqfj=text_5.getText().trim();
							String dsfj="0";
							if(count<=0&&bookBorrowDao.updateloan2(yqfj,dsfj,bno,rno)>0){
								MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
								mg.setText("提示信息");
								mg.setMessage("图书归还成功....");
								mg.open();
								showInfo();
								BookUpdate.showInfo();
								combo.setText("");
								text_1.setText("");
								text_2.setText("");
								text_3.setText("");
								text_4.setText("");
								text_5.setText("");
							}else if(bookBorrowDao.updateloan(yqfj,dsfj,bno,rno)>0){
								MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
								mg.setText("提示信息");
								mg.setMessage("图书归还成功....");
								mg.open();
								showInfo();
								BookUpdate.showInfo();
								combo.setText("");
								text_1.setText("");
								text_2.setText("");
								text_3.setText("");
								text_4.setText("");
								text_5.setText("");
							}
							else{
								MessageBox mg=new MessageBox(getShell(),SWT.ERROR);
								mg.setText("提示信息");
								mg.setMessage("图书归还失败....");
								mg.open();
							}
						}
					}
				}else if(!y.equals("0") || y!="0"){
					if(MessageDialog.openConfirm(getShell(), "图书归还提醒","确定归还并提交提交逾期罚金"+y+"元吗?")){
						if(bookBorrowDao.updateBookInfo(bno)>0){
							String yqfj=text_5.getText().trim();
							String dsfj="0";
							if(count>0&&bookBorrowDao.updateloan(yqfj,dsfj,bno,rno)>0){
								MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
								mg.setText("提示信息");
								mg.setMessage("图书归还成功....");
								mg.open();
								showInfo();
								BookUpdate.showInfo();
								combo.setText("");
								text_1.setText("");
								text_2.setText("");
								text_3.setText("");
								text_4.setText("");
								text_5.setText("");
							}else{
								MessageBox mg=new MessageBox(getShell(),SWT.ERROR);
								mg.setText("提示信息");
								mg.setMessage("图书归还失败....");
								mg.open();
							}
						}
					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ERROR);
						mg.setText("提示信息");
						mg.setMessage("图书归还失败....");
						mg.open();
					}
				}
			}
		});
	}
	//显示该读者的借阅信息
	public static void showInfo(){
		table.removeAll();
		String rno_1=combo.getText().trim();
		if(rno_1!=null&&!"".equals(rno_1)){
			rno=rno_1;
		}
		List<Map<String,Object>> list=bookBorrowDao.find(rno);
		for(Map<String,Object> map:list){
			TableItem ti;
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BTYPE"),
					(String) map.get("RNAME"),(String) map.get("ODATE"),(String) map.get("DAYS"),
					(String) map.get("TO_DATE(ODATE,'YYYY-MM-DD')+TO_NUMBER(DAYS)"),(String) map.get("WHETHER"),
					(String) map.get("B1"),(String) map.get("B2")});
			
		}
	}
	//显示该读者的已归还的借阅信息
	public static void showInfo1(){
		table.removeAll();
		String rno_1=combo.getText().trim();
		if(rno_1!=null&&!"".equals(rno_1)){
			rno=rno_1;
		}
		List<Map<String,Object>> list=bookBorrowDao.find1(rno);
		for(Map<String,Object> map:list){
			TableItem ti;
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BTYPE"),
					(String) map.get("RNAME"),(String) map.get("ODATE"),(String) map.get("DAYS"),
					(String) map.get("TO_DATE(ODATE,'YYYY-MM-DD')+TO_NUMBER(DAYS)"),(String) map.get("WHETHER"),
					(String) map.get("B1"),(String) map.get("B2")});
			
		}
	}
	//显示该读者的未归还的借阅信息
	public static void showInfo2(){
		table.removeAll();
		String rno_1=combo.getText().trim();
		if(rno_1!=null&&!"".equals(rno_1)){
			rno=rno_1;
		}
		List<Map<String,Object>> list=bookBorrowDao.find2(rno);
		for(Map<String,Object> map:list){
			TableItem ti;
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BTYPE"),
					(String) map.get("RNAME"),(String) map.get("ODATE"),(String) map.get("DAYS"),
					(String) map.get("TO_DATE(ODATE,'YYYY-MM-DD')+TO_NUMBER(DAYS)"),(String) map.get("WHETHER"),
					(String) map.get("B1"),(String) map.get("B2")});
			
		}
	}
	//日期相减的方法

	 public long getTwoDay(Date begin_date, Date end_date) {
	  long day = 0;
	  try {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String sdate = format.format(Calendar.getInstance().getTime());
	   
	   if (begin_date == null) {
	    begin_date = format.parse(sdate);
	   }
	   if (end_date == null) {
	    end_date = format.parse(sdate);
	   }
	   day = (end_date.getTime() - begin_date.getTime())
	     / (24 * 60 * 60 * 1000);
	  } catch (Exception e) {
	   return -1;
	  }
	  return day;
	 }
	
	//将读者编号添加到下拉框
	public static void add(){
			List<Map<String,Object>> list=readerInfoDao.finds();
			
			for(Map<String,Object> map:list){
				combo.add((String) map.get("RNO"));
				
			}
		}
	//将图书类型从下拉框删除
	public static void remove(){
		combo.removeAll();
	}
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

