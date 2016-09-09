package com.ymf.table;



import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import com.swtdesigner.SWTResourceManager;
import com.ymf.db.PwdInfoDao;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

/**
 * 密码修改
 *
 */
public class PwdUpdate extends Composite {
	private Text text_3;
	private Text text_1;
	private Text text_2;
	private PwdInfoDao pwdInfoDao=new PwdInfoDao();
	private Login login=new Login();
	String pwdok=Login.mpwd;
	Library library=new Library();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PwdUpdate(Composite parent, final int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(PwdUpdate.class, "/images/大background1.jpg"));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(PwdUpdate.class, "/images/pwdUpdate.jpg"));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBounds(36, 36, 479, 401);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLocation(10, 48);
		label.setSize(410, 30);
		label.setText(" 注：每个管理员只能修改自己的密码！");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("楷体", 18, SWT.NORMAL));
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLocation(32, 110);
		label_2.setSize(121, 30);
		label_2.setText("旧   密   码：");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLocation(32, 183);
		label_3.setSize(121, 30);
		label_3.setText("新   密   码：");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setLocation(32, 260);
		label_4.setSize(121, 30);
		label_4.setText("确认新密码：");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		
		Button button = new Button(composite, SWT.NONE);
		
		button.setLocation(32, 334);
		button.setSize(80, 30);
		button.setText("确   认");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Button button_1 = new Button(composite, SWT.NONE);
		
		button_1.setLocation(169, 334);
		button_1.setSize(80, 30);
		button_1.setText("重   写");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		
		text_1.setLocation(159, 107);
		text_1.setSize(190, 30);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		
		text_2 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
	
		text_2.setLocation(159, 183);
		text_2.setSize(190, 30);
		
		Button button_2 = new Button(composite, SWT.NONE);
		
		button_2.setLocation(288, 334);
		button_2.setSize(80, 30);
		button_2.setText("退   出");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		text_3 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		
		text_3.setLocation(159, 257);
		text_3.setSize(190, 30);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setImage(SWTResourceManager.getImage(PwdUpdate.class, "/images/pwd.jpg"));
		label_5.setBounds(23, 10, 52, 30);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("方正兰亭超细黑简体", 18, SWT.BOLD));
		label_6.setBounds(108, 10, 195, 30);
		label_6.setText("管理员密码修改");
		
		Label label_7 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_7.setBounds(2, 44, 427, 2);
		
		final Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_1.setBounds(355, 110, 32, 24);
		
		final Label label_8 = new Label(composite, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_8.setBounds(355, 260, 32, 24);
		
		final Label label_9 = new Label(composite, SWT.NONE);
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label_9.setBounds(25, 146, 395, 31);
		
		//输入框的 原始密码是否与登录用户的密码相同
		text_1.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if(PwdInfoDao.samePWD(text_1)){
					try {
						label_1.setText("");
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else{
					try {
						label_1.setText("");
						text_1.setText("");
						label_9.setText("密码输入错误，请重新输入");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//新密码与原密码是否相同  以及密码长度是否在4-16位之间
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newpwd=text_2.getText().trim(); //新密码
				int length=text_2.getText().length();
				if(pwdok.equals(newpwd)){
					label_9.setText("新密码不能与原密码相同!请重新输入....");
					label_8.setText("");
					text_2.setText("");
					text_3.setText("");
				}else if(length<4 || length>16 && newpwd!=null){
					label_9.setText("密码长度为4至16位!请重新输入....");
					text_2.setText("");
				}else{
					label_9.setText("");
				}
				
			}
		});
		//再次输入的密码是否与第一次输入的密码相同
		text_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newpwd=text_2.getText().trim(); //新密码
				String newpwd1=text_3.getText().trim(); //再次输入的密码
				if(newpwd1.equals(newpwd)){
					label_8.setText("");
				}else if(newpwd.equals("")||newpwd1==null){
					label_9.setText("密码不一致请重新输入...");
					label_8.setText("");
				}else{
					label_9.setText("输入错误请重新输入");
					text_2.setText("");
					text_3.setText("");
					label_8.setText("");
				}
			}
		});
		
		//点击确认后
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String newpwd=text_2.getText().trim(); //新密码
				String newpwd1=text_3.getText().trim(); //再次输入的密码
				int length=text_1.getText().length();
				int length1=text_2.getText().length();
				int length2=text_3.getText().length();
				if(length==0 ||length1==0||length2==0 ){
					label_9.setText("输入错误(新密码不一致或密码为空)请重新输入 ...");
				}else if(length!=0 &&length1!=0&&length2!=0 &&newpwd==newpwd1 || newpwd.equals(newpwd1) &&PwdInfoDao.samePWD(text_1)){
					if(pwdInfoDao.updateManagerPwd(text_2)){
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_WORKING);
						mg.setText("提示信息");
						mg.setMessage("恭喜！修改密码成功....请重新登录系统");
						mg.open();
						getShell().dispose();
						login.open();
					
					}else{
						MessageBox mg=new MessageBox(getShell(),SWT.ICON_ERROR);
						mg.setText("提示信息");
						mg.setMessage("对不起！修改密码失败....");
						mg.open();
					}
					
				}
			}
		});
		//点击重写
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_2.setText("");
				text_3.setText("");
			}
		});
		//点击退出
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(MessageDialog.openConfirm(getShell(), "退出提醒","确定退出吗?")){
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					label_1.setText("");
					label_8.setText("");
					label_9.setText("");
					
					DataInfo.pwdUpdate.setVisible(false);
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
