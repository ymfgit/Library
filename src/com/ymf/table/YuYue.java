package com.ymf.table;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
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

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.ReaderInfoDao;
import com.ymf.db.YuYueDao;
import com.ymf.utils.DataInfo;

public class YuYue extends Composite {
	private static Table table;
	private static Combo combo;
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private static YuYueDao yuYueDao=new YuYueDao();
	private int count=0;
	private int count1=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public YuYue(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(YuYue.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(YuYue.class, "/images/SelectMessage.jpg"));
		composite.setBounds(36, 36, 749, 428);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(55, 36, 177, 33);
		label.setText("书籍预约信息");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(416, 41, 80, 22);
		label_1.setText("读者编号：");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showInfo();
			}
		});
		combo.setBounds(502, 38, 184, 25);
		add();
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(54, 86, 646, 264);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(91);
		tableColumn.setText("    书籍编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(178);
		tableColumn_1.setText("书籍名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(127);
		tableColumn_2.setText("书籍类别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(142);
		tableColumn_3.setText("出版社");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(103);
		tableColumn_4.setText("书籍价格");
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(95, 380, 184, 27);
		button.setText("借出预约的书籍");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(516, 380, 107, 27);
		button_1.setText("退 出");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		//借阅
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rno=combo.getText().trim();
				List<Map<String,Object>> list=yuYueDao.findcount(rno);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				
				
				
				
				if("".equals(rno)||rno==null){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("请先根据读者编号查询是否有预订信息...");
					mb.open();
				}else if(count==0){
					MessageBox mb=new MessageBox(getShell());
					mb.setText("温馨提示");
					mb.setMessage("对不起，该读者没有预订信息...");
					mb.open();
				}else{
					TableItem[] tis=table.getItems();
					TableItem ti;
					boolean bl=true;
					
					List<Map<String, Object>> lists=yuYueDao.finding(rno);
					for(Map<String,Object> map:lists){
						String ldate=String.valueOf(map.get("应归还日期"));
						if(MessageDialog.openConfirm(getShell(), "确定提醒","确定借出预约的书籍吗?")){
							for(int i=0;i<tis.length;i++){
								ti=tis[i];
								if(yuYueDao.insertBookBorrow(rno,ti.getText(0), ldate, ti.getText(0), ti.getText(0))>0){
									if(yuYueDao.update(rno)>0){
										bl=true;
									}
								}else{
									bl=false;
								}
							}
							if(bl){
								if(yuYueDao.updates(rno)>0){
									YuYue.showInfo();
									MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
									mg.setText("提示信息");
									mg.setMessage("图书借阅成功....");
									mg.open();
									showInfo();
									table.removeAll();
									combo.setText("");
								}


							}else{
								MessageBox mg=new MessageBox(getShell(),SWT.ERROR);
								mg.setText("提示信息");
								mg.setMessage("图书借阅失败....");
								mg.open();
							}
						}
					}
				}
					
			}
		});
		//退出
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
				DataInfo.yuYue.setVisible(false);
				}
			}
		});
	}
	
	//显示该读者的预约信息
	public static void showInfo(){
		table.removeAll();
		String rno=combo.getText().trim();
		List<Map<String,Object>> list=yuYueDao.find(rno);
		for(Map<String,Object> map:list){
			TableItem ti;
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BTYPE"),
					(String) map.get("PUBLISH"),(String) map.get("PRICE")});
			
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
