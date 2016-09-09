package com.ymf.table;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.db.ReaderInfoDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;
/**
 * 读者相关信息添加
 *
 */
public class ReaderAdd extends Composite {
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private static ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private static Combo combo;
	private String rno="";
	//private ReaderUpdate readerUpdate;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ReaderAdd(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(ReaderAdd.class, "/images/大background1.jpg"));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(ReaderAdd.class, "/images/读者信息添加.png"));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBounds(34, 35, 721, 537);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("读者姓名：");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(182, 101, 80, 22);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("身份证号：");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(182, 170, 80, 22);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("联系方式：");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(182, 240, 80, 22);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("读者等级：");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(426, 170, 80, 22);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("读者性别：");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(426, 101, 80, 22);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("登记日期：");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(426, 301, 80, 22);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("借书数量：");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setBounds(426, 240, 80, 22);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("管理员编号：");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_8.setBounds(165, 301, 96, 22);
		
		final Label label_9 = new Label(composite, SWT.NONE);
		label_9.setVisible(false);
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setBounds(184, 212, 215, 22);
		label_9.setText("请输入一个11位的联系方式");
		
		final Label label_10 = new Label(composite, SWT.NONE);
		label_10.setVisible(false);
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_10.setBounds(182, 142, 205, 22);
		label_10.setText("请输入一个18位的身份证号");

		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(268, 102, 137, 24);
		
		text_2 = new Text(composite, SWT.BORDER);
		
		text_2.setBounds(268, 170, 137, 24);
		
		text_3 = new Text(composite, SWT.BORDER);
		
		
		text_3.setBounds(268, 241, 137, 24);
		
		Group group = new Group(composite, SWT.NONE);
		group.setBounds(536, 87, 115, 36);
		
		Button button = new Button(group, SWT.RADIO);
		button.setText("男");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(10, 10, 46, 20);
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setText("女");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setBounds(62, 10, 43, 20);
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setEnabled(false);
		text_4.setBounds(536, 302, 131, 24);
		text_4.setText(sdf.format(new Date()));
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setEnabled(false);
		text_5.setBounds(536, 241, 131, 24);
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(268, 302, 137, 24);
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(536, 172, 115, 25);
		add();
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setText("添 加");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setBounds(307, 354, 80, 27);
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setText("退 出");
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_3.setBounds(501, 354, 80, 27);
		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(0, 0, 255));
		label.setFont(SWTResourceManager.getFont("楷体", 30, SWT.NORMAL));
		label.setBounds(242, 35, 339, 47);
		label.setText("读者相关信息添加");
		
		//自动获取登录用户的账号
		text_6.setText(Login.mno);
		//关闭
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "关闭提醒","确定退出吗?")){
					DataInfo.readerAdd.setVisible(false);
				}				
			}
		});
		//判断身份证号码是否正确为18位
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int length=text_2.getText().length();
				if(length!=18){
					label_10.setVisible(true);
				}else{
					label_10.setVisible(false);
				}
			}
		});
		
		//判断联系方式是否正确为11位
		text_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int length=text_3.getText().length();
				if(length!=11){
					label_9.setVisible(true);
				}else{
					label_9.setVisible(false);
				}
			}
		});
	
		
		//添加
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rname=text_1.getText().trim();
				String rcardno=text_2.getText().trim();
				String rtel=text_3.getText().trim();
				int length=text_3.getText().length();
				String rbookNum=text_5.getText().trim();
				String rlevel=combo.getText().trim();
				String ldate=text_4.getText().trim();
				String mno=text_6.getText().trim();
				String rsex="男";
				
				if(button_1.getSelection()){
					rsex="女";
				}else{
					rsex="男";
				}
				if(rname==null||"".equals(rname)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("读者姓名不能为空....");
					mg.open();
				}else if(rtel==null||"".equals(rtel)||length!=11 ){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("请输入正确的联系方式...");
					mg.open();
				}else if(ldate==null||"".equals(ldate)){
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("警告提示");
					mg.setMessage("登记时间不能为空....");
					mg.open();
				}else if(readerInfoDao.addReader(rname, rsex, rcardno, rtel, rbookNum, rlevel, ldate, mno)>0){
					//刷新表格中的数据
					ReaderUpdate.showInfo();
					List<Map<String,Object>> list=readerInfoDao.readerno(rname);
					for(Map<String,Object> map:list){
						rno=(String) map.get("RNO");
					}
					
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
					mg.setText("提示信息");
					mg.setMessage("读者信息添加成功\n读者登录时登录名为"+rno+"\n初始密码为aaaa....");
					mg.open();
					BookBorrow.remove();
					BookBorrow.add();
					BookBack.remove();
					BookBack.add();
					YuYue.remove();
					YuYue.add();
					text_3.setText("");
					text_1.setText("");
					text_2.setText("");
				}else {
					MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
					mg.setText("失败提示");
					mg.setMessage("读者信息添加失败....");
					mg.open();
				}
			}
		});
		//根剧等级自动填写最大借书数量
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rlevel=combo.getText().trim();
				List<Map<String,Object>> list=readerInfoDao.find2(rlevel);
				for(Map<String,Object> map:list){
					text_5.setText(String.valueOf(map.get("MAXNUM")));
				}
			}
		});
		//自动获取登录用户的账号
		text_6.setText(Login.mno);
		
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
