package com.ymf.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookTypeDao;
import com.ymf.utils.DataInfo;
/**
 * 图书类别添加
 *
 */
public class BookTypeInsert extends Composite {
	private static Text text;
	private static Table table;
	private static BookTypeDao bookTypeDao=new BookTypeDao();
	private int count=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookTypeInsert(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BookTypeInsert.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(36, 36, 573, 412);
		composite.setBackgroundImage(SWTResourceManager.getImage(BookTypeInsert.class, "/images/bookTypeInsert.jpg"));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(34, 68);
		table.setSize(500, 152);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(248);
		tableColumn.setText("              图书类别编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(248);
		tableColumn_1.setText("                   图书类别名称");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLocation(34, 237);
		composite_1.setSize(500, 95);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(69, 10, 112, 24);
		label.setText("图书类别名称：");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(199, 11, 264, 23);
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(111, 58, 80, 27);
		button.setText("添  加");
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_1.setBounds(324, 58, 80, 27);
		button_1.setText("退 出");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_1.setFont(SWTResourceManager.getFont("楷体", 24, SWT.NORMAL));
		label_1.setBounds(159, 34, 250, 34);
		label_1.setText("图书类   别添加");
		showInfo();
		//添加图书类型
		button.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				String btype=text.getText().trim();
				List<Map<String,Object>> list=bookTypeDao.findname(btype);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				if(btype==null||"".equals(btype)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("请输入您要添加的图书类型....");
					mg.open();
					//根据输入的图书类别名判断图书类别表是否已存在此类别
				}else if(count>0){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("对不起，此类别名称已经存在....");
					text.setText("");
					mg.open();
				}
				else if(bookTypeDao.addBookType(btype)>0){
					//刷新表格中的数据
					showInfo();
					BookTypeUpdate.showInfo();
					NewBookInsert.remove();
					NewBookInsert.add();
					BookUpdate.remove();
					BookUpdate.add();
					BookSelect.remove();
					BookSelect.add();
					BookSelect1.remove();
					BookSelect1.add();
					text.setText("");
				}else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("图书类别添加失败....");
					mg.open();
				}
			}
		});
		//关闭
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.bookTypeInsert.setVisible(false);
				}				
			}
		});
	}
	//在界面显示所有图书类型信息
	public static void showInfo(){
		table.removeAll();//移除表格中原有的数据
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=bookTypeDao.findall();
		
		TableItem ti;
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("BTNO"),(String) map.get("BTYPE")});
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
