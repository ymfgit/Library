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
import com.ymf.db.ReaderInfoDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;
import org.eclipse.swt.widgets.Combo;
/**
 * 读者信息修改与删除
 * @author Administrator
 *
 */
public class ReaderUpdate extends Composite {
	private static Table table;
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_4;
	private Text text_5;
	private Text text_7;
	private Text text_6;
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private Button button;
	private Button button_1; 
	private static Combo combo;
	private int count=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ReaderUpdate(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(ReaderUpdate.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(ReaderUpdate.class, "/images/readerupdate.png"));
		composite.setBounds(36, 36, 719, 540);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(10, 46);
		table.setSize(699, 199);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(64);
		tableColumn_8.setText("读者编号");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(62);
		tableColumn.setText("读者姓名");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(42);
		tableColumn_1.setText("性别");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(118);
		tableColumn_2.setText("身份证号");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(96);
		tableColumn_3.setText("联系方式");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(75);
		tableColumn_4.setText("最大借书量");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(69);
		tableColumn_5.setText("等级");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(74);
		tableColumn_6.setText("登记日期");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(94);
		tableColumn_7.setText("操作员编号");
		
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		//点击一行时将对应的值显示到下面对应位置
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				text_2.setText(tableCursor.getRow().getText(0));
				text.setText(tableCursor.getRow().getText(1));
				if("男".equals(tableCursor.getRow().getText(2))){
					button.setSelection(true);
					button_1.setSelection(false);
				}else{
					button.setSelection(false);
					button_1.setSelection(true);
				}

				text_1.setText(tableCursor.getRow().getText(3));
				text_5.setText(tableCursor.getRow().getText(4));
				text_4.setText(tableCursor.getRow().getText(5));
				combo.setText(tableCursor.getRow().getText(6));
				text_6.setText(tableCursor.getRow().getText(7));
				//text_7.setText(tableCursor.getRow().getText(8));
				
			}
		});
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setLocation(10, 251);
		composite_1.setSize(699, 279);
		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(41, 60, 94, 20);
		label_5.setText("     姓    名 ：");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(141, 60, 180, 23);
		
		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(412, 22, 53, 20);
		label_6.setText(" 性 别：");
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setBounds(471, 10, 158, 32);
		
		button = new Button(group, SWT.RADIO);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button.setBounds(10, 10, 55, 17);
		button.setText("男");
		
		button_1 = new Button(group, SWT.RADIO);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_1.setBounds(75, 10, 44, 17);
		button_1.setText("女");
		
		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(37, 99, 94, 20);
		label_7.setText(" 身 份 证 号：");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setEnabled(false);
		text_1.setBounds(141, 99, 180, 24);
		
		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(41, 21, 86, 20);
		label_8.setText("读 者 编 号：");
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setEnabled(false);
		text_2.setBounds(141, 21, 180, 23);
		
		Label label_9 = new Label(composite_1, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setBounds(412, 61, 53, 20);
		label_9.setText("  等级：");
		
		Label label_10 = new Label(composite_1, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_10.setBounds(375, 100, 90, 20);
		label_10.setText("最大借书量：");
		
		text_4 = new Text(composite_1, SWT.BORDER);
		text_4.setEnabled(false);
		text_4.setBounds(473, 99, 156, 23);
		
		Label label_11 = new Label(composite_1, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_11.setBounds(37, 143, 94, 20);
		label_11.setText(" 联 系 方 式：");
		
		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_5.setBounds(141, 143, 180, 24);
		
		Label label_12 = new Label(composite_1, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_12.setBounds(388, 147, 77, 20);
		label_12.setText("登记日期：");
		
		Label label_13 = new Label(composite_1, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_13.setBounds(37, 187, 94, 23);
		label_13.setText(" 操作员编号：");
		
		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_7.setEnabled(false);
		text_7.setBounds(141, 186, 180, 24);
		text_7.setText(Login.mno);
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setBounds(141, 231, 80, 27);
		button_2.setText("删  除");
		
		Button button_3 = new Button(composite_1, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		
		button_3.setBounds(528, 231, 80, 27);
		button_3.setText("退  出");
		
		Button button_4 = new Button(composite_1, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_4.setBounds(324, 231, 80, 27);
		button_4.setText("修  改");
		
		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(473, 143, 156, 24);
		
		combo = new Combo(composite_1, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.setBounds(473, 57, 156, 28);
		add();
		//根剧等级自动填写最大借书数量
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rlevel=combo.getText().trim();
				List<Map<String,Object>> list=readerInfoDao.find2(rlevel);
				for(Map<String,Object> map:list){
					text_4.setText(String.valueOf(map.get("MAXNUM")));
				}
			}
		});
		
		//关闭
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.readerUpdate.setVisible(false);
				}				
			}
		});
		//删除
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rno=text_2.getText().trim();
				List<Map<String,Object>> list=readerInfoDao.findcount(rno);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				if(rno==null||"".equals(rno)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("请输入您要删除的读者编号....");
					mg.open();
				}else if(MessageDialog.openConfirm(getShell(), "读者删除提醒","确定删除吗?")){
					if(count>0){
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
						mg.setText("提示信息");
						mg.setMessage("对不起,该读者有未还的书籍,无法删除....");
						mg.open();
					}else if(readerInfoDao.delReader(rno)>0){
						//刷新表格中的数据
						showInfo();
						BookBorrow.remove();
						BookBorrow.add();
						BookBack.remove();
						BookBack.add();
						YuYue.remove();
						YuYue.add();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						
					}	
				}else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("对不起，本系统中无此读者信息,读者信息删除失败........");
					mg.open();
				}
			}
		});
		//修改
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rno=text_2.getText().trim();
				String rname=text.getText().trim();
				String rsex="男";
				if(button_1.getSelection()){
					rsex="女";
				}else{
					rsex="男";
				}
				String rcardno=text_1.getText().trim();
				String rtel=text_5.getText().trim();;
				String rbookNum=text_4.getText().trim();;
				String rlevel=combo.getText().trim();;
				String ldate=text_6.getText().trim();
				String mno=text_7.getText().trim();
				
				
				if(rno==null||"".equals(rno)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("读者编号不能为空....");
					mg.open();
				}else if(rtel==null||"".equals(rtel)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("联系方式不能为空....");
					mg.open();
				}else if(rname==null||"".equals(rname)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("读者姓名不能为空....");
					mg.open();
				}else if(ldate==null||"".equals(ldate)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("登记时间不能为空....");
					mg.open();
				}else if(readerInfoDao.updateReader(rno, rname, rsex, rcardno, rtel, rbookNum, rlevel, ldate, mno)>0){
					if(text_7.getText().trim().equals(Login.mno)){
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_INFORMATION);
						mg.setText("提示信息");
						mg.setMessage("修改成功....");
						mg.open();
						showInfo();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						
						
					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
						mg.setText("提示信息");
						mg.setMessage("操作失败\n请输入您自己的操作员编号....");
						mg.open();
					}
			}
				else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("对不起，本系统中无此读者信息,读者信息修改失败....");
					mg.open();
			}
			}
			
		});
		showInfo();
	}
	//显示所有书籍信息
	public static void showInfo(){
		table.removeAll();
		List<Map<String,Object>> list=readerInfoDao.findall();
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("RNO"),(String) map.get("RNAME"),(String) map.get("RSEX"),
					(String) map.get("RCARDNO"),(String) map.get("RTEL"),(String) map.get("MAXNUM"),(String) map.get("RLEVEL"),
					(String) map.get("LDATE"),(String) map.get("MNO")});
			
		}
	}
	//将读者等级添加到下拉框
	public static void add(){
			List<Map<String,Object>> list=readerInfoDao.find1();
			
			for(Map<String,Object> map:list){
				combo.add((String) map.get("RLEVEL"));
				
			}
		}
	//将读者等级从下拉框删除
	public static void remove(){
		combo.removeAll();
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
