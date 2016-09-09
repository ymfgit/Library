package com.ymf.table;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookBorrowDao;
import com.ymf.db.ReaderInfoDao;
import com.ymf.db.YuYueDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class YuYue1 extends Composite {
	private static Table table;
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private static YuYueDao yuYueDao=new YuYueDao();
	private static Text text;
	private static BookBorrowDao bookBorrowDao=new BookBorrowDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public YuYue1(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(YuYue1.class, "/images/背景1.png"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(YuYue1.class, "/images/SelectMessage.jpg"));
		composite.setBounds(31, 45, 751, 428);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(83, 39, 191, 34);
		label.setText("书籍预约信息");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label.setFont(SWTResourceManager.getFont("楷体", 20, SWT.NORMAL));
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(414, 39, 88, 33);
		label_1.setText("读者编号：");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		table.setBounds(45, 79, 665, 264);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(99);
		tableColumn.setText("    书籍编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(171);
		tableColumn_1.setText("书籍名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(122);
		tableColumn_2.setText("书籍类别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(149);
		tableColumn_3.setText("出版社");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(119);
		tableColumn_4.setText("书籍价格");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		//取消预约
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index=table.getSelectionIndex();
				TableItem[] ti=table.getItems();
				
				String bno=ti[index].getText(0);
				yuYueDao.delyuyue(bno);
				bookBorrowDao.updateBookInfo(bno);
				showInfo();
				BookSelect1.showInfo();
			}
		});
		menuItem.setText("取消预约");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(496, 378, 107, 27);
		button_1.setText("退 出");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setEnabled(false);
		text.setBounds(508, 39, 148, 23);
		text.setText(Login.mno);
		showInfo();
		//退出
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.yuYue1.setVisible(false);
					}
			}
		});
	}
	//显示该读者的预约信息
	public static void showInfo(){
		table.removeAll();
		String rno=text.getText().trim();
		List<Map<String,Object>> list=yuYueDao.find(rno);
		for(Map<String,Object> map:list){
			TableItem ti;
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BNO"),(String) map.get("BNAME"),(String) map.get("BTYPE"),
					(String) map.get("PUBLISH"),(String) map.get("PRICE")});
			
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
