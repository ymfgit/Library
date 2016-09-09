package com.ymf.library;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.SWTResourceManager;
import com.ymf.utils.ShellUtils;

public class Help {

	protected Shell shell;
	private ShellUtils shellUtils=new ShellUtils();
	private Display display;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Help window = new Help();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(Help.class, "/images/4.jpg"));
		shell.setSize(450, 317);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shellUtils.center(shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackgroundMode(SWT.INHERIT_DEFAULT);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(Help.class, "/images/login.jpg"));
		shellUtils.shellMove(composite_2, shell);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(0, 0, 77, 20);
		label_1.setText("帮助模块");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setBounds(383, 0, 28, 20);
		label_2.setImage(SWTResourceManager.getImage(Help.class, "/images/btn_mini_normal.png"));
		shellUtils.minOp(shell, label_2);
		
		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setBounds(409, 0, 39, 20);
		label_3.setImage(SWTResourceManager.getImage(Help.class, "/images/btn_close_normal.png"));
		shellUtils.closeOp(shell, label_3);
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		shellUtils.shellMove(composite_3, shell);

		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_4.setBounds(10, 0, 381, 41);
		label_4.setText("   欢迎访问图书馆管理系统！！！！");
		
		Label lbladmin = new Label(composite_3, SWT.NONE);
		lbladmin.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		lbladmin.setText("初次使用的用户：用户名为Admin，密码为空，以读者或教师身份，点击登录即可。");
		lbladmin.setBounds(10, 47, 416, 24);
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setBounds(10, 77, 428, 24);
		label_5.setText("想借阅书籍的用户：可以在图书管理模块进行注册，然后再重新登录，即可借阅。");
		
		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setBounds(10, 107, 416, 17);
		label_6.setText("管理员：通过用户名，密码，以管理员身份进入主界面可以进行各模块的管理。");
		
		Button button = new Button(composite_3, SWT.NONE);
		button.setBounds(176, 142, 80, 27);
		button.setText("返回登录界面");
		sashForm.setWeights(new int[] {124, 165});
		
		//返回登录界面
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}
}
