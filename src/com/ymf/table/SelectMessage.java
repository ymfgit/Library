package com.ymf.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;

public class SelectMessage extends Composite {
	private static Text text;
	private static Table table;
	private static BookBorrowDao bookBorrowDao=new BookBorrowDao();
    private int count=0;
    private int days=0;
    private String day="";
    public  SelectTime time=new SelectTime(getShell(),SWT.APPLICATION_MODAL);
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SelectMessage(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(SelectMessage.class, "/images/背景1.png"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(SelectMessage.class, "/images/SelectMessage.jpg"));
		composite.setBounds(36, 36, 748, 429);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(76, 331, 78, 26);
		label.setText("读者编号：");
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(385, 329, 80, 27);
		button.setText("查询信息");
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setEnabled(false);
		text.setBounds(160, 332, 148, 23);
		text.setText(Login.mno);
		
		Group group = new Group(composite, SWT.NONE);
		group.setBounds(35, 68, 679, 243);
		
		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(10, 55);
		table.setSize(659, 178);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(65);
		tableColumn.setText("书籍编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(67);
		tableColumn_1.setText("书籍名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(90);
		tableColumn_2.setText("借阅日期");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(91);
		tableColumn_3.setText("应还日期");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(87);
		tableColumn_7.setText("归还日期");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(77);
		tableColumn_4.setText("是否已归还");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(97);
		tableColumn_5.setText("提交的逾期罚金");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(88);
		tableColumn_6.setText("丢失图书罚金");
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_1.setFont(SWTResourceManager.getFont("楷体", 16, SWT.NORMAL));
		label_1.setBounds(24, 0, 186, 28);
		label_1.setText("书籍借阅信息如下：");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("楷体", 12, SWT.NORMAL));
		label_3.setBounds(216, 23, 295, 25);
		label_3.setText("是否要查找某个时间段自己看过的书籍：");
		
		Group group_1 = new Group(group, SWT.NONE);
		group_1.setBounds(517, 12, 90, 35);
		
		final Button button_2 = new Button(group_1, SWT.RADIO);
		button_2.setBounds(10, 10, 33, 17);
		button_2.setText("是");
		
		final Button button_3 = new Button(group_1, SWT.RADIO);
		button_3.setSelection(true);
		button_3.setBounds(49, 10, 31, 17);
		button_3.setText("否");
		
		//选中"是"的时候弹出时间框
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_2.getSelection()){
					time.open();
				}
			}
		});
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_2.setFont(SWTResourceManager.getFont("楷体", 20, SWT.BOLD));
		label_2.setBounds(432, 35, 188, 34);
		label_2.setText("借阅信息查看");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setBounds(552, 329, 80, 27);
		button_1.setText("退 出");
		//退出
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					table.removeAll();
					DataInfo.selectMessage.setVisible(false);
				}
			}
		});
		
		String rno=text.getText().trim();
		List<Map<String,Object>> list =bookBorrowDao.findbnum(rno);
		for(Map<String,Object> map:list){
			count=Integer.valueOf((String) map.get("COUNT(*)"));
		}
		
		List<Map<String,Object>> list1 =bookBorrowDao.findDays(rno);
		
		for(Map<String,Object> map:list1){
			if("".equals(map.get("天数")) || map.get("天数")!=null){
				days=Integer.valueOf((String)map.get("天数"));
				if(days>0){
					day=String.valueOf(days)+"天后";
				}else if(days==0){
					day="今天";
				}
				if(rno.startsWith("R")&&count>0){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_INFORMATION);
					mg.setText("温馨提示");
					mg.setMessage("您有未归还书籍"+count+"本\n"+"有一本书在"+day+"将超过应归还日期，请您尽快归还！");
					mg.open();	
				}
			}else{
				if(rno.startsWith("R")&&count>0){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_INFORMATION);
					mg.setText("温馨提示");
					mg.setMessage("您有未归还书籍"+count+"本,请及时归还...");
					mg.open();
				}
			}
		}

		//查询对应读者的借阅信息
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(button_2.getSelection()){
					//showInfo();
				}else if(button_3.getSelection()){
					showInfo1();
				}
					//然后循环table表，把表中是否归还等于否的字体改为蓝色
					TableItem[] tit=table.getItems();
					for(int j=0;j<tit.length;j++){
						if(tit[j].getText(5).equals("否")){
							Color a=new Color(null, 77, 166, 255);
							tit[j].setBackground(a);
						}
					}
			}
		});
	}
	//显示该读者的借阅信息
	public static void showInfo1(){
		table.removeAll();
		String rno=text.getText().trim();
		List<Map<String,Object>> list=bookBorrowDao.findWH(rno);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("ODATE"),
					(String) map.get("TO_DATE(ODATE,'YYYY-MM-DD')+TO_NUMBER(DAYS)"),(String) map.get("RDATE"),(String) map.get("WHETHER"),
					(String) map.get("YQFJ"),(String) map.get("DSFJ")});
		}
	}
	//显示读者在借阅那天到应还那天借阅的书籍
	public static void showInfo(){
		table.removeAll();
		String rno=text.getText().trim();
		List<Map<String,Object>> list=bookBorrowDao.finds(rno);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			
			//判断应还时间和借阅时间是否在输入的两个时间段内
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			
			try {
				String yhsj=(String) map.get("TO_DATE(ODATE,'YYYY-MM-DD')+TO_NUMBER(DAYS)");
				String jysj=(String) map.get("ODATE");
				Date yhsjdate=sdf.parse(yhsj);
				Date jysjdate=sdf.parse(jysj);
				
				if(SelectTime.sj1.trim()==null || "".equals(SelectTime.sj1.trim()) ){
					System.out.println(SelectTime.sj1);
					continue;
				}else{
					Date sj1date=sdf.parse(SelectTime.sj1);
					Date sj2date=sdf.parse(SelectTime.sj2);
					
					if(yhsjdate.after(sj1date) && jysjdate.before(sj1date)&&sj1date.before(sj2date)){
						
						ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("ODATE"),
								(String) map.get("TO_DATE(ODATE,'YYYY-MM-DD')+TO_NUMBER(DAYS)"),(String) map.get("RDATE"),(String) map.get("WHETHER"),
								(String) map.get("YQFJ"),(String) map.get("DSFJ")});
						//然后循环table表，把表中是否归还等于否的字体改为蓝色
						TableItem[] tit=table.getItems();
						for(int j=0;j<tit.length;j++){
							if(tit[j].getText(5).equals("否")){
								Color a=new Color(null, 77, 166, 255);
								tit[j].setBackground(a);
							}
						}
					}else{
						ti.dispose();
					}
				}
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
		}
	}
	@Override
	protected void checkSubclass(){
		// Disable the check that prevents subclassing of SWT components
	}
}
