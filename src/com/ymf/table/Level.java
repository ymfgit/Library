package com.ymf.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import com.swtdesigner.SWTResourceManager;
import com.ymf.db.ReaderInfoDao;
import com.ymf.utils.DataInfo;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class Level extends Composite {
	private static Table table;
	private Text text;
	private Text text_1;
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private Text text_2;
	private int count=0;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Level(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Level.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		composite.setBackgroundImage(SWTResourceManager.getImage(Level.class, "/images/level.png"));
		composite.setBackground(SWTResourceManager.getColor(224, 255, 255));
		composite.setBounds(36, 36, 601, 393);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLocation(116, 10);
		label.setSize(180, 27);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		label.setText("读者等级信息");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setLocation(10, 44);
		table.setSize(392, 154);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(124);
		tableColumn.setText("        读者等级");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(140);
		tableColumn_1.setText("最大借书数量");
		
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setText("可借天数");
		tableColumn_2.setWidth(124);
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				text.setText(tableCursor.getRow().getText(0));
				text_1.setText(tableCursor.getRow().getText(1));
				text_2.setText(tableCursor.getRow().getText(2));
			}
		});
		
		
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLocation(10, 257);
		label_2.setSize(112, 23);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setText("最大借书数量：");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLocation(42, 215);
		label_1.setSize(87, 23);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setText("读者等级：");
		
		text = new Text(composite, SWT.BORDER);
		text.setLocation(157, 213);
		text.setSize(198, 26);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		
		
		 
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLocation(157, 255);
		text_1.setSize(198, 26);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		
		Button button = new Button(composite, SWT.NONE);
		
		button.setLocation(26, 355);
		button.setSize(80, 27);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setText("修  改");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setLocation(308, 355);
		button_1.setSize(80, 27);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setText("退   出");
		
		Button button_3 = new Button(composite, SWT.NONE);
		
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_3.setBounds(167, 355, 80, 27);
		button_3.setText("添   加");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(42, 306, 87, 23);
		label_3.setText("可借天数：");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(155, 303, 200, 26);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		label_4.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label_4.setText("温馨提示：\r\n如果只需要修改最大借\r\n书量和可借天数，请选\r\n择等级【修改】。如果\r\n需要修改等级名称，请\r\n选择等级【添加】。");
		label_4.setBounds(415, 10, 180, 123);
		showInfo();
		
		//退出
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.level.setVisible(false);
					text.setText("");
					text_1.setText("");
					text_2.setText("");
				}				
			}
		});
		//修改
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rlevel=text.getText().trim();
				String maxnum=text_1.getText().trim();
				String days=text_2.getText().trim();
				
				if(rlevel==null||"".equals(rlevel)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("读者等级不能为空....");
					mg.open();
				}else if(maxnum==null||"".equals(maxnum)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("最大借书数量不能为空....");
					mg.open();
				}else if(days==null||"".equals(days)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("可借天数不能为空....");
					mg.open();
				}else if(readerInfoDao.updatelevel(maxnum,days,rlevel)>0){
					showInfo();
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
					mg.setText("成功提示");
					mg.setMessage("读者等级信息修改成功....");
					mg.open();
					ReaderAdd.remove();
					ReaderAdd.add();
					ReaderUpdate.remove();
					ReaderUpdate.add();
					text.setText("");
					text_1.setText("");
					text_2.setText("");
				}else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("对不起,读者等级信息修改失败....");
					mg.open();
					text.setText("");
					text_1.setText("");
					text_2.setText("");
				}
			}
		});
		//添加等级信息
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rlevel=text.getText().trim();
				String maxnum=text_1.getText().trim();
				String days=text_2.getText().trim();
				List<Map<String,Object>> list=readerInfoDao.findname(rlevel);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				
				if(rlevel==null||"".equals(rlevel)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("读者等级不能为空....");
					mg.open();
				}else if(maxnum==null||"".equals(maxnum)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("最大借书数量不能为空....");
					mg.open();
				}else if(days==null||"".equals(days)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("可借天数不能为空....");
					mg.open();
				}else if(count>0){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("对不起，此等级名称已经存在，您可以直接进行其他数据的添加修改....");
					text.setText("");
					text_1.setText("");
					text_2.setText("");
					mg.open();
				}
				else if(readerInfoDao.addLevel(maxnum,days,rlevel)>0){
					showInfo();
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
					mg.setText("成功提示");
					mg.setMessage("读者等级信息添加成功....");
					mg.open();
					ReaderAdd.remove();
					ReaderAdd.add();
					ReaderUpdate.remove();
					ReaderUpdate.add();
					text.setText("");
					text_1.setText("");
					text_2.setText("");
				}else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("对不起,读者等级信添加失败....");
					mg.open();
					text.setText("");
					text_1.setText("");
					text_2.setText("");
				}
			}
		});
		

	}
	//在界面显示所有读者等级信息
	public static void showInfo(){
		table.removeAll();//移除表格中原有的数据
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=readerInfoDao.findlevel();
		
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("RLEVEL"),(String) map.get("MAXNUM"),(String) map.get("DAYS")});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
