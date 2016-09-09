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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookInfoDao;
import com.ymf.db.NewBookDao;
import com.ymf.utils.DataInfo;
/**
 * 书籍验收
 *
 */
public class NewBookCheck extends Composite {
	private static Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private static NewBookDao newBookDao=new NewBookDao();
	private BookInfoDao bookInfoDao=new BookInfoDao();
	private Text text_8;
	private Button button_1;
	private Button button;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NewBookCheck(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(NewBookCheck.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		composite.setBackgroundImage(SWTResourceManager.getImage(NewBookCheck.class, "/images/书籍验收.png"));
		composite.setBounds(36, 36, 836, 551);
		
		Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_3.setLocation(52, 242);
		composite_3.setSize(747, 276);
		
		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setText("订购日期：");
		label_4.setBounds(400, 10, 72, 23);
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setText("   出版社 ：");
		label_5.setBounds(400, 54, 72, 23);
		
		text = new Text(composite_3, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(478, 54, 153, 23);
		
		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setText(" 订购数量：");
		label_6.setBounds(73, 54, 78, 26);
		
		text_1 = new Text(composite_3, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(157, 54, 152, 23);
		
		Label label_7 = new Label(composite_3, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setText("书籍 名称：");
		label_7.setBounds(73, 10, 78, 23);
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setEnabled(false);
		text_2.setBounds(157, 10, 152, 23);
		
		Label label_8 = new Label(composite_3, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setText(" 书籍类别：");
		label_8.setBounds(73, 97, 76, 25);
		
		text_3 = new Text(composite_3, SWT.BORDER);
		text_3.setEnabled(false);
		text_3.setBounds(157, 97, 152, 23);
		
		Label label_9 = new Label(composite_3, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setText("      书籍价格：");
		label_9.setBounds(376, 92, 94, 23);
		
		text_4 = new Text(composite_3, SWT.BORDER);
		text_4.setEnabled(false);
		text_4.setBounds(478, 92, 153, 23);
		
		Label label_10 = new Label(composite_3, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_10.setText(" 操作员编号：");
		label_10.setBounds(57, 145, 94, 25);
		
		text_5 = new Text(composite_3, SWT.BORDER);
		text_5.setEnabled(false);
		text_5.setBounds(157, 145, 152, 23);
		
		Label label_11 = new Label(composite_3, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_11.setText("   出版日期：");
		label_11.setBounds(388, 145, 84, 23);
		
		text_6 = new Text(composite_3, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(478, 145, 153, 23);
		
		Label label_12 = new Label(composite_3, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_12.setText(" 编  著  者 ：");
		label_12.setBounds(67, 189, 84, 25);
		
		text_7 = new Text(composite_3, SWT.BORDER);
		text_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_7.setEnabled(false);
		text_7.setBounds(157, 189, 152, 25);
		
		Label label_13 = new Label(composite_3, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_13.setText("是否验收：");
		label_13.setBounds(400, 189, 72, 22);
		
		Group group = new Group(composite_3, SWT.NONE);
		group.setBounds(478, 176, 153, 38);
		
		button = new Button(group, SWT.RADIO);
		button.setText("是");
		button.setSelection(true);
		button.setBounds(27, 10, 33, 25);
		
		button_1 = new Button(group, SWT.RADIO);
		button_1.setText("否");
		button_1.setBounds(99, 10, 44, 25);
		
		Button button_2 = new Button(composite_3, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setText("确定验收");
		button_2.setBounds(188, 239, 80, 27);
		
		Button button_3 = new Button(composite_3, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_3.setText("退 出");
		button_3.setBounds(481, 239, 80, 27);
		
		text_8 = new Text(composite_3, SWT.BORDER);
		text_8.setEnabled(false);
		text_8.setBounds(478, 10, 153, 23);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackgroundMode(SWT.INHERIT_DEFAULT);
		table.setBounds(52, 47, 747, 189);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(65);
		tableColumn.setText("新书编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(73);
		tableColumn_1.setText("订购日期");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(62);
		tableColumn_2.setText("订购数量");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(72);
		tableColumn_3.setText("操作员编号");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(63);
		tableColumn_4.setText("是否验收");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(38);
		tableColumn_5.setText("折扣");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(64);
		tableColumn_6.setText("图书类别");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(68);
		tableColumn_7.setText("图书名称");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(50);
		tableColumn_8.setText("编著者");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(73);
		tableColumn_9.setText("新书价格");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.CENTER);
		tableColumn_10.setWidth(73);
		tableColumn_10.setText("出版日期");
		
		TableColumn tableColumn_11 = new TableColumn(table, SWT.CENTER);
		tableColumn_11.setWidth(70);
		tableColumn_11.setText("出版社");
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 18, SWT.NORMAL));
		label.setBounds(419, 10, 135, 31);
		label.setText("书籍验收");
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				if("是".equals(tableCursor.getRow().getText(4))){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("错误提示");
					mb.setMessage("对不起,此图书已经验收完成了...");
					mb.open();
					button.getSelection();
				}else{
					text.setText(tableCursor.getRow().getText(11));
					text_8.setText(tableCursor.getRow().getText(1));
					text_1.setText(tableCursor.getRow().getText(2));
					text_5.setText(tableCursor.getRow().getText(3));
					text_6.setText(tableCursor.getRow().getText(10));
					text_3.setText(tableCursor.getRow().getText(6));
					text_2.setText(tableCursor.getRow().getText(7));
					text_7.setText(tableCursor.getRow().getText(8));
					text_4.setText(tableCursor.getRow().getText(9));
					button_1.getSelection();
				}
				
				
			}
		});
		//关闭
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.newBookCheck.setVisible(false);
					text.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_5.setText("");
					text_6.setText("");
					text_7.setText("");
					text_8.setText("");
				}				
			}
		});
		
		//验收
		button_2.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//String nbno=text.getText().trim();
				String nbname=text_2.getText().trim();
				String nbtype=text_3.getText().trim();
				String publish=text.getText().trim();
				String price=text_4.getText().trim();
				String nbinfo=text_7.getText().trim();
				String nbdate=text_8.getText().trim();
				String mno=text_5.getText().trim();
				String nbnum=text_1.getText().trim();
				String o1=text_6.getText().trim();
				String whether="是";
				MessageBox mb=new MessageBox(getShell());
				if(button_1.getSelection()){
					whether="否";
					if(nbname==null||"".equals(nbname)){
						mb.setText("错误提示");
						mb.setMessage("新书名称不能为空...");
						mb.open();
					}else if(price==null||"".equals(price)){
						mb.setText("错误提示");
						mb.setMessage("新书价格不能为空...");
						mb.open();
					}else if(nbdate==null||"".equals(nbdate)){
						mb.setText("错误提示");
						mb.setMessage("订购时间不能为空...");
						mb.open();
					}else if(newBookDao.updateBookInfo(nbname, whether)>0){
						//刷新表格中的数据
						showInfo();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						text_7.setText("");
						text_8.setText("");

					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
						mg.setText("失败提示");
						mg.setMessage("对不起,新书验收失败....");
						mg.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						text_7.setText("");
						text_8.setText("");
					}
				}else{
					whether="是";
					if(nbname==null||"".equals(nbname)){
						mb.setText("错误提示");
						mb.setMessage("新书名称不能为空...");
						mb.open();
					}else if(price==null||"".equals(price)){
						mb.setText("错误提示");
						mb.setMessage("新书价格不能为空...");
						mb.open();
					}else if(nbdate==null||"".equals(nbdate)){
						mb.setText("错误提示");
						mb.setMessage("订购时间不能为空...");
						mb.open();
					}else if(bookInfoDao.addBookInfo(nbname, nbinfo, nbtype, publish, o1, price, nbnum, nbdate, mno)>0){
						//刷新表格中的数据
						BookUpdate.showInfo();
						if(newBookDao.updateBookInfo(nbname, whether)>0){
							//刷新表格中的数据
							showInfo();
						}
						MessageBox mg=new MessageBox(getShell(),SWT.RIGHT);
						mg.setText("提示信息");
						mg.setMessage("新书验证成功....");
						mg.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						text_7.setText("");
						text_8.setText("");
					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
						mg.setText("失败提示");
						mg.setMessage("新书验收失败....");
						mg.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						text_7.setText("");
						text_8.setText("");
					}
				}
				
			}
		});
		showInfo();
	}
	//显示所有新书订购信息
	public static void showInfo(){
		table.removeAll();
		List<Map<String,Object>> list=newBookDao.findall();
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("NBNO"),(String) map.get("NBDATE"),(String) map.get("NBNUM"),
					(String) map.get("MNO"),(String) map.get("WHETHER"),(String) map.get("DISCOUNT"),(String) map.get("NBTYPE"),
					(String) map.get("NBNAME"),(String) map.get("NBINFO"),(String) map.get("PRICE*DISCOUNT*0.1"),(String) map.get("O1"),(String) map.get("PUBLISH")});
			
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
