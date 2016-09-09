package com.ymf.table;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
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
import com.ymf.db.BookInfoDao;
import com.ymf.db.BookTypeDao;
import com.ymf.db.YuYueDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;

/**
 * 书籍查询
 *
 */
public class BookSelect1 extends Composite {
	private static Text text;
	private static BookInfoDao bookInfoDao=new BookInfoDao();
	private static YuYueDao yuYueDao=new YuYueDao();
	private static Table table;
	private static Combo combo;
	private static Combo combo_1;
	private static BookTypeDao bookTypeDao=new BookTypeDao();
	public static List<Map<String,Object>> lm; //根据书号查询出来的数据
	private static BookBorrowDao bookBorrowDao=new BookBorrowDao();
	public int count=0;
	public int counts=0;
	private int count1=0;
	public int num=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookSelect1(Composite parent, final int style) {
		
		
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(BookSelect1.class, "/images/背景1.png"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(BookSelect1.class, "/images/图书查询.png"));
		composite.setBounds(36, 36, 838, 489);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_1.setBounds(49, 46, 751, 413);
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setBounds(10, 10, 731, 68);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("请选择查询项目");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label_1.setFont(SWTResourceManager.getFont("楷体", 15, SWT.NORMAL));
		label_1.setBounds(29, 0, 145, 22);
		combo_1 = new Combo(group, SWT.NONE);
		combo_1.setVisible(false);
		combo_1.setBounds(227, 28, 166, 29);
		combo_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		add();
		combo = new Combo(group, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText()=="显示所有图书信息"||"显示所有图书信息".equals(combo.getText())){
					text.setText("");
					combo_1.setVisible(false);
					text.setEnabled(false);
					
				}
					else if(combo.getText()=="图书类别"||"图书类别".equals(combo.getText())){
						combo_1.setText("");
						combo_1.setVisible(true);
						text.setRedraw(false);
						
					}
				else{
					combo_1.setVisible(false);
					text.setText("");
					text.setRedraw(true);
					text.setEnabled(true);
				}
			}
		});
		
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo.setBounds(39, 28, 166, 29);
		combo.add("图书编号");
		combo.add("图书名称");
		combo.add("作者");
		combo.add("图书类别");
		combo.add("出版社");
		combo.add("显示所有图书信息");
		
		text = new Text(group, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text.setBounds(227, 28, 166, 29);
		
		Button button = new Button(group, SWT.NONE);
		button.setBounds(426, 28, 80, 27);
		button.setText("查   询");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label label = new Label(group, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(523, 28, 166, 29);
		label.setText("请根据您所知道的信息查询");
		
		//点击查询时
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText()=="显示所有图书信息"||"显示所有图书信息".equals(combo.getText())){
					showInfo();
				}else if(combo.getText()=="图书编号"||"图书编号".equals(combo.getText())){
					showInfo1();
				}else if(combo.getText()=="图书名称"||"图书名称".equals(combo.getText())){
					showInfo2();
				}else if(combo.getText()=="作者"||"作者".equals(combo.getText())){
					showInfo3();
				}else if(combo.getText()=="图书类别"||"图书类别".equals(combo.getText())){
					showInfo4();
				}else if(combo.getText()=="出版社"||"出版社".equals(combo.getText())){
					showInfo5();
				}
				
			}
		});
		
		Group group_1 = new Group(composite_1, SWT.NONE);
		group_1.setBounds(10, 97, 731, 273);
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setText("查询结果显示");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label_2.setFont(SWTResourceManager.getFont("楷体", 15, SWT.NORMAL));
		label_2.setBounds(23, 0, 126, 22);
		
		table = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 35, 721, 228);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(60);
		tableColumn.setText("图书编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(69);
		tableColumn_1.setText("图书名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(67);
		tableColumn_2.setText("作者");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(64);
		tableColumn_3.setText("图书类别");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(57);
		tableColumn_4.setText("出版社");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(63);
		tableColumn_5.setText("出版日期");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(60);
		tableColumn_6.setText("书籍价格");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(43);
		tableColumn_7.setText("库存");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(60);
		tableColumn_8.setText("入库日期");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(72);
		tableColumn_9.setText("管理员编号");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.CENTER);
		tableColumn_10.setWidth(103);
		tableColumn_10.setText("逾期罚金(元/日)");
		
		TableColumn tableColumn_11 = new TableColumn(table, SWT.CENTER);
		tableColumn_11.setWidth(92);
		tableColumn_11.setText("丢失图书罚金");
		
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("预约");
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setText("退   出");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.setBounds(608, 376, 80, 27);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_4.setText("书籍查询");
		label_4.setFont(SWTResourceManager.getFont("楷体", 17, SWT.NORMAL));
		label_4.setBounds(386, 10, 102, 25);
		final String rno=Login.mno;
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index=table.getSelectionIndex();
				TableItem[] ti=table.getItems();
				String bno=ti[index].getText(); //书籍编号
				String str=ti[index].getText(7); //库存
				List<Map<String,Object>> list=yuYueDao.findbook(bno,rno);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				List<Map<String,Object>> lists=yuYueDao.findcounts(rno);
				for(Map<String,Object> map:lists){
					counts=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				
				//int index=table.getSelectionIndex();
				//TableItem[] ti1=table.getItems();
				//String bno=ti1[index].getText(0);
				
				List<Map<String,Object>> list1=yuYueDao.findYJWH(bno,rno);
				for(Map<String,Object> map:list1){
					count1=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				if("0".equals(str)|| Integer.parseInt(str)<0){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("对不起，此书籍暂时库存不足...");
					mb.open();
				}else if(count1>0){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("您借了此书还没还，还请还了再借...");
					mb.open();
				}else if(count>0){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("对不起，相同书籍只能预约一本...");
					mb.open();
				}else{
					List<Map<String, Object>> listss=bookBorrowDao.finding(rno);
					for(Map<String,Object> map:listss){
						num=Integer.parseInt(String.valueOf(map.get("MAXNUM")));
					}
					if(counts>=num){
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
						mg.setText("警告信息");
						mg.setMessage("您可预约的最大书籍量为"+num+"本，\n当前所预约的书籍数量已达最大书籍量,\n请在已预约书籍模块取消部分书籍....");
						mg.open();
					}else{
						if(yuYueDao.insertyuyue(rno, bno)>0){
							if(bookBorrowDao.updateBookInfo1(bno)>0){
								YuYue1.showInfo();
								BookUpdate.showInfo();
							//	showInfo();
								MessageBox mb=new MessageBox(getShell());
								mb.setText("温馨提示");
								mb.setMessage("预约成功...");
								mb.open();
							}
						}else{
							MessageBox mb=new MessageBox(getShell(),SWT.ICON_ERROR);
							mb.setText("温馨提示");
							mb.setMessage("对不起，预约失败...");
							mb.open();
						}

					}
				}
			}
		});
		
		//关闭
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.bookSelect1.setVisible(false);
					text.setText("");
					combo.setText("");
					table.removeAll();
				}				
			}
		});
		
	}
	
	//显示所有书籍信息
	public static void showInfo(){
		table.removeAll();
		List<Map<String,Object>> list=bookInfoDao.findall();
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BINFO"),
					(String) map.get("BTYPE"),(String) map.get("PUBLISH"),(String) map.get("PDATE"),(String) map.get("PRICE"),
					(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO"),(String) map.get("B1"),(String) map.get("B2")});
		}
	}
	//根据编号查询显示图书信息
	public static void showInfo1(){
		table.removeAll();
		String bno=text.getText().trim();
		List<Map<String,Object>> list=bookInfoDao.find1(bno);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BINFO"),
					(String) map.get("BTYPE"),(String) map.get("PUBLISH"),(String) map.get("PDATE"),(String) map.get("PRICE"),
					(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO"),(String) map.get("B1"),(String) map.get("B2")});
		}
	}
	
	public static void showInfo2(){
		table.removeAll();
		String bname=text.getText().trim();
		List<Map<String,Object>> list=bookInfoDao.find2(bname);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BINFO"),
					(String) map.get("BTYPE"),(String) map.get("PUBLISH"),(String) map.get("PDATE"),(String) map.get("PRICE"),
					(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO"),(String) map.get("B1"),(String) map.get("B2")});
		}
	}
	public static void showInfo3(){
		table.removeAll();
		String binfo=text.getText().trim();
		List<Map<String,Object>> list=bookInfoDao.find3(binfo);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BINFO"),
					(String) map.get("BTYPE"),(String) map.get("PUBLISH"),(String) map.get("PDATE"),(String) map.get("PRICE"),
					(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO"),(String) map.get("B1"),(String) map.get("B2")});
		}
	}
	public static void showInfo4(){
		table.removeAll();
		String btype=combo_1.getText().trim();
		List<Map<String,Object>> list=bookInfoDao.find4(btype);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BINFO"),
					(String) map.get("BTYPE"),(String) map.get("PUBLISH"),(String) map.get("PDATE"),(String) map.get("PRICE"),
					(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO"),(String) map.get("B1"),(String) map.get("B2")});
		}
	}
	public static void showInfo5(){
		table.removeAll();
		String publish=text.getText().trim();
		List<Map<String,Object>> list=bookInfoDao.find5(publish);
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BINFO"),
			(String) map.get("BTYPE"),(String) map.get("PUBLISH"),(String) map.get("PDATE"),(String) map.get("PRICE"),
			(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO"),(String) map.get("B1"),(String) map.get("B2")});
		}
	}
	//将图书类型添加到下拉框
	public static void add(){
			List<Map<String,Object>> list=bookTypeDao.finds();
			
			for(Map<String,Object> map:list){
				combo_1.add((String) map.get("BTYPE"));
				
			}
		}
	//将图书类型从下拉框删除
	public static void remove(){
		combo_1.removeAll();
	}
	@Override
	protected void checkSubclass() {
		
	}

	public static void table() {
		// TODO Auto-generated method stub
		
	}


}
