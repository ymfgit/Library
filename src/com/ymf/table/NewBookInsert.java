package com.ymf.table;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookInfoDao;
import com.ymf.db.BookTypeDao;
import com.ymf.db.NewBookDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;
/**新书订购
 *
 */
public class NewBookInsert extends Composite {
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private NewBookDao newBookDao=new NewBookDao();
	private static BookInfoDao bookInfoDao=new BookInfoDao();
	private static BookTypeDao bookTypeDao=new BookTypeDao();
	private static Combo combo;
	private CalendarCombo calendarCombo;
	private CalendarCombo calendarCombo_1;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NewBookInsert(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(NewBookInsert.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(NewBookInsert.class, "/images/新书订购.png"));
		composite.setBounds(36, 36, 619, 445);
		
		Group group = new Group(composite, SWT.NONE);
		group.setBounds(48, 43, 527, 186);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("书籍信息");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_4.setFont(SWTResourceManager.getFont("楷体", 16, SWT.NORMAL));
		label_4.setBounds(28, 10, 95, 29);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setText("书籍名称：");
		label_6.setBounds(28, 48, 83, 23);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(117, 48, 128, 23);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setText("书籍类别：");
		label_7.setBounds(28, 103, 83, 22);
		
		combo = new Combo(group, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.setBounds(117, 100, 128, 25);
		add();
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setText("出版社：");
		label_8.setBounds(308, 45, 61, 25);
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(375, 45, 112, 23);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setText("书籍价格：");
		label_9.setBounds(28, 154, 83, 20);
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setBounds(117, 151, 128, 23);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_10.setText("编著者：");
		label_10.setBounds(308, 97, 61, 22);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setBounds(375, 97, 112, 23);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(294, 151, 75, 23);
		label_2.setText("出版日期：");
		
		calendarCombo = new CalendarCombo(group, SWT.READ_ONLY);
		calendarCombo.setBounds(375, 151, 112, 25);
		
		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setBounds(48, 235, 527, 129);
		
		Label label_11 = new Label(group_1, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("楷体", 16, SWT.NORMAL));
		label_11.setText("订购信息");
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_11.setBounds(21, 17, 90, 30);
		
		Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_12.setText(" 订 购 日 期：");
		label_12.setBounds(23, 53, 88, 23);
		
		Label label_13 = new Label(group_1, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_13.setText(" 订购数量 ：");
		label_13.setBounds(276, 53, 83, 23);
		
		text_5 = new Text(group_1, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_5.setBounds(365, 53, 113, 23);
		
		Label label_14 = new Label(group_1, SWT.NONE);
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_14.setText("操作员编号：");
		label_14.setBounds(23, 96, 90, 28);
		
		text_6 = new Text(group_1, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_6.setBounds(119, 96, 128, 23);
		text_6.setText(Login.mno);
		
		Label label_16 = new Label(group_1, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_16.setText("   折  扣  ：");
		label_16.setBounds(286, 96, 73, 20);
		
		text_7 = new Text(group_1, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_7.setBounds(365, 96, 113, 23);
		
		calendarCombo_1 = new CalendarCombo(group_1, SWT.READ_ONLY);
		calendarCombo_1.setBounds(117, 51, 128, 25);
		
		final Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(173, 17, 161, 23);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("楷体", 12, SWT.NORMAL));
		button_2.setText("订 购");
		button_2.setBounds(169, 375, 80, 27);
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("楷体", 12, SWT.NORMAL));
		button_3.setText(" 退 出");
		button_3.setBounds(368, 375, 80, 27);
		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label.setFont(SWTResourceManager.getFont("楷体", 18, SWT.NORMAL));
		label.setBounds(256, 10, 107, 27);
		label.setText("新书订购");
		//关闭
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.newBookInsert.setVisible(false);
				}				
			}
		});
		//订购
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nbname=text_1.getText().trim();
				String nbtype=combo.getText().trim();
				String publish=text_2.getText().trim();
				String price=text_3.getText().trim();
				String nbinfo=text_4.getText().trim();
				String nbdate=calendarCombo_1.getDateAsString();
				String mno=text_6.getText().trim();
				String discount=text_7.getText().trim();
				String nbnum=text_5.getText().trim();
				String o1=calendarCombo.getDateAsString();
				String whether="否";
				MessageBox mb=new MessageBox(getShell());
				double discount1=Double.parseDouble(text_7.getText().trim());
//				if(button.getSelection()){
//					whether="是";
//					if(nbname==null||"".equals(nbname)){
//						mb.setText("错误提示");
//						mb.setMessage("新书名称不能为空...");
//						text_5.setText("");
//						mb.open();
//					}else if(price==null||"".equals(price)){
//						mb.setText("错误提示");
//						mb.setMessage("新书价格不能为空...");
//						text_5.setText("");
//						mb.open();
//					}else if(nbdate==null||"".equals(nbdate)){
//						mb.setText("错误提示");
//						mb.setMessage("订购时间不能为空...");
//						text_5.setText("");
//						mb.open();
//					}else if(bookInfoDao.addBookInfo(nbname, nbinfo, nbtype, publish, o1, price, nbnum, nbdate,mno)>0){
//						//刷新表格中的数据
//						BookUpdate.showInfo();
//					}else{
//						MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
//						mg.setText("失败提示");
//						mg.setMessage("新书订购失败....");
//						mg.open();
//					}
//				}else{
//					whether="否";
//				}
				
				if(nbname==null||"".equals(nbname)){
					mb.setText("错误提示");
					mb.setMessage("新书名称不能为空...");
					text_5.setText("");
					mb.open();
				}else if(price==null||"".equals(price)){
					mb.setText("错误提示");
					mb.setMessage("新书价格不能为空...");
					text_5.setText("");
					mb.open();
				}else if(nbdate==null||"".equals(nbdate)){
					mb.setText("错误提示");
					mb.setMessage("订购时间不能为空...");
					text_5.setText("");
					mb.open();
				}else if(!isDouble(price)){
					mb.setText("错误提示");
					mb.setMessage("请输入合理的价格...");
					text_3.setText("");
					mb.open();
				}else if(!isInteger(nbnum)){
					mb.setText("错误提示");
					mb.setMessage("请输入合理的订购数量...");
					text_5.setText("");
					mb.open();
				}else if(!isDouble(discount)|| discount1>10 || discount1<0){
					mb.setText("错误提示");
					mb.setMessage("请输入合理的折扣...");
					text_7.setText("");
					mb.open();
				}
				else if(nbdate.compareTo(o1)<0){
					mb.setText("错误提示");
					mb.setMessage("您输入的时间不符合实际....");
					calendarCombo_1.setText("");
					mb.open();
				}else if(isDouble(price)&&isInteger(nbnum)){
					if(Double.parseDouble(price)<0){
						mb.setText("错误提示");
						mb.setMessage("请输入合理的价格...");
						text_3.setText("");
						mb.open();
					}else if(Integer.parseInt(nbnum)<0){
						mb.setText("错误提示");
						mb.setMessage("请输入合理的订购数量...");
						text_5.setText("");
						mb.open();
					}else if(newBookDao.addOdbook(nbname, nbtype, publish, price, nbinfo, nbdate,mno, discount, nbnum, whether,o1)>0){
						//刷新表格中的数据
						NewBookCheck.showInfo();
						MessageBox mg=new MessageBox(getShell(),SWT.RIGHT);
						mg.setText("提示信息");
						mg.setMessage("新书订购成功....");
						mg.open();
						text_3.setText("");
						text_1.setText("");
						text_2.setText("");
						text_4.setText("");
						text_7.setText("");
						calendarCombo_1.setText("");
						text_5.setText("");
						calendarCombo.setText("");
						combo.setText("");
						
					}
				}
				else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("新书订购失败....");
					mg.open();
				}
			}
		});
	}
	//将图书类型添加到下拉框
	public static void add(){
			List<Map<String,Object>> list=bookTypeDao.finds();
			
			for(Map<String,Object> map:list){
				combo.add((String) map.get("BTYPE"));
				
			}
		}
	//将图书类型从下拉框删除
	public static void remove(){
		combo.removeAll();
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	//判断是否书籍订购数量为整数
	 public static boolean isInteger(String value) {
		  try {
		   Integer.parseInt(value);
		   return true;
		  } catch (NumberFormatException e) {
		   return false;
		  }
	}
	 //判断是否书籍价格为double型
	 public static boolean isDouble(String value) {
		  try {
		   Double.parseDouble(value);
		   return true;
		  } catch (NumberFormatException e) {
		   return false;
		  }
	}
}
