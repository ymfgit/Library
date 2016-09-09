package com.ymf.table;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookInfoDao;
import com.ymf.db.BookTypeDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;
/**
 * 图书信息修改与删除
 *
 */
public class BookUpdate extends Composite {
	private static Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private static BookInfoDao bookInfoDao=new BookInfoDao();
	private static Combo combo;
	private static BookTypeDao bookTypeDao=new BookTypeDao();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookUpdate(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(BookUpdate.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(BookUpdate.class, "/images/bookUpdate.jpg"));
		composite.setBounds(36, 40, 752, 500);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(32, 79, 687, 208);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(63);
		tableColumn.setText("图书编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(67);
		tableColumn_1.setText("图书名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(59);
		tableColumn_2.setText("作者");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(67);
		tableColumn_3.setText("图书类别");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(68);
		tableColumn_4.setText("出版社");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(87);
		tableColumn_5.setText("出版日期");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(70);
		tableColumn_6.setText("书籍价格");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(54);
		tableColumn_7.setText("库存");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(80);
		tableColumn_8.setText("入库日期");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(74);
		tableColumn_9.setText("管理员编号");
		
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		//点击一行时将对应的值显示到下面对应位置
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				text.setText(tableCursor.getRow().getText(0));
				text_1.setText(tableCursor.getRow().getText(1));
				text_2.setText(tableCursor.getRow().getText(2));
				combo.setText(tableCursor.getRow().getText(3));
				text_5.setText(tableCursor.getRow().getText(6));
				text_4.setText(tableCursor.getRow().getText(8));
				text_7.setText(tableCursor.getRow().getText(7));
				text_3.setText(tableCursor.getRow().getText(4));
			}
		});
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("图书编号：");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(51, 304, 75, 22);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("图书名称：");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(51, 338, 75, 22);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("作       者：");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(51, 371, 75, 22);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("图书类别：");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(281, 304, 75, 22);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("出  版  社：");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(281, 338, 75, 22);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("入库日期：");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(281, 372, 75, 22);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("图书价格：");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(516, 304, 75, 22);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("图书库存：");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setBounds(516, 338, 75, 22);
		
		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setText("管理员号：");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_10.setBounds(516, 373, 75, 22);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(140, 304, 91, 23);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(140, 338, 91, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(140, 371, 91, 23);
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(362, 304, 92, 25);
		add();
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(363, 338, 91, 23);
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(363, 372, 91, 23);
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setBounds(597, 304, 91, 23);
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(597, 373, 91, 23);
		
		text_7 = new Text(composite, SWT.BORDER);
		text_7.setBounds(597, 338, 91, 23);
		text_6.setText(Login.mno);
		Label label_11 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_11.setBounds(27, 402, 693, 2);
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("修  改");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(407, 446, 80, 27);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setText("退 出");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setBounds(572, 446, 80, 27);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setText("删除");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setBounds(151, 446, 80, 27);
		showInfo();
		//关闭
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.bookUpdate.setVisible(false);
					text.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_5.setText("");
					text_7.setText("");
					combo.setText("");
				}				
			}
		});
		//删除
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bno=text.getText().trim();
				if(bno==null||"".equals(bno)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("提示信息");
					mg.setMessage("请输入您要删除的图书编号....");
					mg.open();
				}else if(MessageDialog.openConfirm(getShell(), "图书删除提醒","确定删除吗?")){
					bookInfoDao.delBookInfo(bno);
						//刷新表格中的数据
						showInfo();
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
						mg.setText("提示信息");
						mg.setMessage("图书信息删除成功....");
						mg.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_7.setText("");
						combo.setText("");
					
				}
//				else{
//					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
//					mg.setText("失败提示");
//					mg.setMessage("对不起,图书信息删除失败........");
//					mg.open();
//				}
			}
		});
		
		//修改
		//修改
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bno=text.getText().trim();
				String bname=text_1.getText().trim();
				String binfo=text_2.getText().trim();
				String btype=combo.getText().trim();
				String publish=text_3.getText().trim();;
				String price=text_5.getText().trim();;
				String bnum=text_7.getText().trim();;
				String ldate=text_4.getText().trim();
				String mno=text_6.getText().trim();
				MessageBox mb=new MessageBox(getShell());
				if(bno==null||"".equals(bno)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书编号不能为空....");
					mg.open();
				}else if(bnum==null||"".equals(bnum)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书库存不能为空....");
					mg.open();
				}else if(bname==null||"".equals(bname)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书名称不能为空....");
					mg.open();
				}else if(ldate==null||"".equals(ldate)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("入库时间不能为空....");
					mg.open();
				}else if(isDouble(price)&&isInteger(bnum)){
					if(Double.parseDouble(price)<0){
						mb.setText("错误提示");
						mb.setMessage("请输入合理的价格...");
						text_5.setText("");
						mb.open();
					}else if(Integer.parseInt(bnum)<0){
						mb.setText("错误提示");
						mb.setMessage("请输入合理的库存...");
						text_7.setText("");
						mb.open();
					}
					else if(bookInfoDao.updateBookInfo(bno, bname, binfo, btype, publish, price, bnum, ldate, mno)>0){
						//刷新表格中的数据
						showInfo();
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
						mg.setText("提示信息");
						mg.setMessage("图书信息修改成功....");
						mg.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_7.setText("");

						combo.setText("");

					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
						mg.setText("失败提示");
						mg.setMessage("对不起,图书信息修改失败....");
						mg.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_7.setText("");

						combo.setText("");
					}
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
					(String) map.get("BNUM"),(String) map.get("LDATE"),(String) map.get("MNO")});
			
		}
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
	@Override
	protected void checkSubclass(){
		// Disable the check that prevents subclassing of SWT components
	}

}
