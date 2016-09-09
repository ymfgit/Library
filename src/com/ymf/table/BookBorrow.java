package com.ymf.table;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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
import org.eclipse.swt.widgets.Combo;

public class BookBorrow extends Composite {
	private static Text text_1;
	private static Text text_2;
	private static Text text_3;
	private Text text_8;
	private Text text_9;
	private static Table table;
	private static BookBorrowDao bookBorrowDao=new BookBorrowDao();
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private static Text text_4;
	private int count=0;
	private int index=0;
	private int counts=0;
	private int bcounts=0;
	
	private static Combo combo;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookBorrow(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(BookBorrow.class, "/images/大background1.jpg"));
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(BookBorrow.class, "/images/书籍借阅.png"));
		composite_1.setBounds(36, 36, 717, 518);
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBounds(39, 36, 639, 441);
		
		Label label_12 = new Label(composite_2, SWT.NONE);
		label_12.setText("当前日期：");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_12.setBounds(33, 401, 80, 23);
		
		text_8 = new Text(composite_2, SWT.BORDER);
		text_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_8.setEnabled(false);
		text_8.setBounds(119, 400, 159, 28);
		text_8.setText(sdf.format(new Date()));
		
		Label label_13 = new Label(composite_2, SWT.NONE);
		label_13.setText("当前操作员编号：");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_13.setBounds(313, 244, 131, 23);
		
		text_9 = new Text(composite_2, SWT.BORDER);
		text_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_9.setEnabled(false);
		text_9.setBounds(458, 238, 149, 30);
		text_9.setText(Login.mno);
		
		Button button = new Button(composite_2, SWT.NONE);
		button.setText("借出当前书籍");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(382, 347, 188, 27);
		
		Button button_1 = new Button(composite_2, SWT.NONE);
		
		button_1.setText("清除所有记录");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.setBounds(382, 292, 188, 27);
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 10, 619, 182);
		
		final TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("   书籍编号");
		
		final TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(132);
		tableColumn_1.setText("借书日期");
		
		final TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(129);
		tableColumn_2.setText("应还日期");
		
		final TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(125);
		tableColumn_4.setText("逾期罚金(元/日)");
		
		final TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(128);
		tableColumn_5.setText("丢失图书罚金");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("取消借阅");
		
		
		Button button_2 = new Button(composite_2, SWT.NONE);
		
		button_2.setText("退 出");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_2.setBounds(426, 393, 111, 27);
		
		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setBounds(33, 198, 80, 22);
		label_4.setText("读者编号：");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label label_5 = new Label(composite_2, SWT.NONE);
		label_5.setBounds(33, 244, 80, 28);
		label_5.setText("读者姓名：");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(119, 240, 159, 28);
		text_1.setEnabled(false);
		
		Label label_7 = new Label(composite_2, SWT.NONE);
		label_7.setBounds(33, 281, 80, 22);
		label_7.setText("读者等级：");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_2 = new Text(composite_2, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(119, 280, 159, 28);
		text_2.setEnabled(false);
		
		Label label_6 = new Label(composite_2, SWT.NONE);
		label_6.setBounds(21, 321, 92, 22);
		label_6.setText("可借书数量:");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_3 = new Text(composite_2, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setBounds(119, 320, 159, 28);
		text_3.setEnabled(false);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setBounds(33, 361, 80, 23);
		label_1.setText("可借天数:");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_4 = new Text(composite_2, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setBounds(119, 360, 159, 28);
		text_4.setEnabled(false);
		
		combo = new Combo(composite_2, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rno=combo.getText().trim();
				List<Map<String,Object>> list=bookBorrowDao.findindex(rno);
				
				for(Map<String,Object> map:list){
					index=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				
				List<Map<String,Object>> lists =bookBorrowDao.findcount(rno);
				for(Map<String,Object> map:lists){
					counts=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				
				TableItem[] tis=table.getItems();
				TableItem ti;
				
				boolean b=true;
				String [] arr=new String[50];
				int j=0;
				
				for(int i=0;i<tis.length;i++){
					ti=tis[i];
					List<Map<String,Object>> list1 =bookBorrowDao.findbooks(ti.getText(0),rno);
					for(Map<String,Object> map:list1){
						bcounts=Integer.valueOf((String) map.get("COUNT(*)"));
					}
					if(bcounts>0){
						b=false;
						arr[j]=ti.getText(0);
						j++;
					}else{
						b=true;
					}
				}
				
				String str="";
				for(int i=0;i<arr.length;i++){
					if(arr[i]!=null){
						str+=arr[i]+" ";
					}else{
						break;
					}
				}
				
				if(index==0){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("错误提示");
					mb.setMessage("对不起，此读者不存在...");
					combo.setText("");
					mb.open();
				}else if(counts>0){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("对不起，该读者有书籍逾期未还，账号已被冻结。\n请提醒其交还逾期罚金和归还书籍，才能重新借阅。");
					mb.open();
					combo.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
//					if(bookBorrowDao.delBookBorrow()>0){
//						table.removeAll();
//					}
				}else if(!b){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("对不起，该读者已经借阅了书籍编号为"+str+"的书籍，并且尚未归还，请归还后再借阅.");
					mb.open();
				}
				else{
					show();
					showInfo();
					String days=text_4.getText().trim();
					if(bookBorrowDao.updateBookBorrow(rno,days)>0){
						showInfo();
					}
				}
			}
		});
		combo.setBounds(119, 198, 159, 28);
		add();
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label.setFont(SWTResourceManager.getFont("楷体", 18, SWT.NORMAL));
		label.setBounds(292, 10, 144, 29);
		label.setText("图书借阅管理");
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index=table.getSelectionIndex();
				TableItem[] ti=table.getItems();
				
				String str=ti[index].getText(0);
				bookBorrowDao.delBookBorrows(str);
				BookBorrow.showInfo();
			}
		});
		
		BookSelect.table();
		//清空操作
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					combo.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
				if(bookBorrowDao.delBookBorrow()>0){
					table.removeAll();
				}
			}
		});
		
		//关闭
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					if(bookBorrowDao.delBookBorrow()>0){
						table.removeAll();
					}
					combo.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					DataInfo.bookBorrow.setVisible(false);
				}				
			}
		});
		
		//借出当前图书
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rno=combo.getText().trim();
				List<Map<String,Object>> list=bookBorrowDao.findcount1(rno);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				
				if("".equals(text_3.getText().trim())){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("警告信息");
					mg.setMessage("请先选择您要借阅的书籍....");
					mg.open();
				}else {
					int num=Integer.parseInt(text_3.getText().trim());
					if(count>num){
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
						mg.setText("警告信息");
						mg.setMessage("您所选书籍数量大于可借数量,请取消借阅部分书籍....");
						mg.open();
					}else{
						TableItem[] tis=table.getItems();
						TableItem ti;
						boolean bl=true;
						for(int i=0;i<tis.length;i++){
							ti=tis[i];
							if(bookBorrowDao.updateBookInfo1(ti.getText(0))>0){
								bl=true;
							}else{
								bl=false;
							}
						}
						if(bl){
							bookBorrowDao.update();
							showInfo();
							MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
							mg.setText("提示信息");
							mg.setMessage("图书借阅成功....");
							mg.open();
							BookUpdate.showInfo();
							table.removeAll();
							combo.setText("");
							text_1.setText("");
							text_2.setText("");
							text_3.setText("");
							text_4.setText("");
						}else{
							MessageBox mg=new MessageBox(getShell(),SWT.ERROR);
							mg.setText("提示信息");
							mg.setMessage("图书借阅失败....");
							mg.open();
						}
					}
				}
			}
		});
	}
	

	public static List<Map<String,Object>> table(String bno){
		String sql="select * from loan where bno=?";
		return table(sql);
	}
	

	//显示所有借阅书籍信息
	public static void showInfo(){
		table.removeAll();
		List<Map<String,Object>> list=bookBorrowDao.findallOfYBJ();
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			
			ti.setText(0, String.valueOf(map.get("BNO")));
			ti.setText(1, String.valueOf(map.get("ODATE")));
			ti.setText(2, String.valueOf(map.get("LDATE")));
			ti.setText(3, String.valueOf(map.get("L1")));
			ti.setText(4, String.valueOf(map.get("L2")));
			
		}
	}
	//根据编号显示读者的部分信息
	public static void show(){
		table.removeAll();
		String  rno=combo.getText().trim();
		List<Map<String, Object>> list=bookBorrowDao.finding(rno);
		for(Map<String,Object> map:list){
			text_1.setText(String.valueOf(map.get("RNAME")));
			text_2.setText(String.valueOf(map.get("RLEVEL")));
			text_4.setText(String.valueOf(map.get("DAYS")));
			text_3.setText(String.valueOf(map.get("MAXNUM")));
		}
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
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
