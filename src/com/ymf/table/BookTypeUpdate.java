package com.ymf.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import com.swtdesigner.SWTResourceManager;
import com.ymf.db.BookTypeDao;
import com.ymf.utils.DataInfo;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class BookTypeUpdate extends Composite {
	private static Table table;
	private Text text;
	private Text text_1;
	private static BookTypeDao bookTypeDao=new BookTypeDao();
	private int count=0;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookTypeUpdate(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(BookTypeUpdate.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(BookTypeUpdate.class, "/images/bookTypeInsert.jpg"));
		composite.setBounds(36, 36, 573, 412);
	
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(34, 68);
		table.setSize(500, 151);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(248);
		tableColumn.setText("                    图书类别编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(248);
		tableColumn_1.setText("                       图书类别名称");
		
		final TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		tableCursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				text.setText(tableCursor.getRow().getText(0));
				text_1.setText(tableCursor.getRow().getText(1));
			}
		});
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLocation(34, 237);
		composite_1.setSize(500, 95);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(24, 10, 70, 22);
		label.setText("类别编号:");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(244, 11, 73, 23);
		label_1.setText("类别名称:");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text = new Text(composite_1, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(104, 11, 117, 23);
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(324, 11, 150, 23);
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(83, 58, 80, 27);
		button.setText(" 修  改");
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setBounds(356, 58, 80, 27);
		button_1.setText(" 退  出");
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setBounds(216, 58, 80, 27);
		button_2.setText("删 除");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		label_2.setFont(SWTResourceManager.getFont("楷体", 25, SWT.NORMAL));
		label_2.setBounds(104, 34, 376, 34);
		label_2.setText("图书类别    修改与删除");
		showInfo();
		//关闭
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.bookTypeUpdate.setVisible(false);
				}				
			}
		});
		
		//修改
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String btno=text.getText().trim();
				String btype=text_1.getText().trim();
				List<Map<String,Object>> list=bookTypeDao.findname(btype);
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				if(btno==null||"".equals(btno)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书类别编号不能为空....");
					mg.open();
				}else if(btype==null||"".equals(btype)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书类别名称不能为空....");
					mg.open();
				}else if(count>0){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WARNING);
					mg.setText("提示信息");
					mg.setMessage("对不起，此类别名称已经存在....");
					text_1.setText("");
					mg.open();
				}else if(bookTypeDao.updateBookType(btno, btype)>0){
						//刷新表格中的数据
						showInfo();
						BookTypeInsert.showInfo();
						NewBookInsert.remove();
						NewBookInsert.add();
						BookUpdate.remove();
						BookUpdate.add();
						BookSelect.remove();
						BookSelect.add();
						BookSelect1.remove();
						BookSelect1.add();
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
						mg.setText("成功提示");
						mg.setMessage("图书类别修改成功....");
						mg.open();
						text.setText("");
						text_1.setText("");
						
			}
				else{
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("对不起,图书类别修改失败....");
					mg.open();
					text.setText("");
					text_1.setText("");
			}
			}
			
		});
		//类别删除
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String btno=text.getText().trim();
				String btype=text_1.getText().trim();
				if(btno==null||"".equals(btno)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书类别编号不能为空....");
					mg.open();
				}else if(btype==null||"".equals(btype)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("图书类别名称不能为空....");
					mg.open();
				}else if(bookTypeDao.delbtype(btno, btype)>0){
					//刷新表格中的数据
					showInfo();
					BookTypeInsert.showInfo();
					NewBookInsert.remove();
					NewBookInsert.add();
					BookUpdate.remove();
					BookUpdate.add();
					BookSelect.remove();
					BookSelect.add();
					BookSelect1.remove();
					BookSelect1.add();
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
					mg.setText("成功提示");
					mg.setMessage("图书类别删除成功....");
					mg.open();
					text.setText("");
					text_1.setText("");
					text.setText("");
					text_1.setText("");
					
		}
			else{
				MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
				mg.setText("失败提示");
				mg.setMessage("对不起,图书类别删除失败....");
				mg.open();
				text.setText("");
				text_1.setText("");
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
