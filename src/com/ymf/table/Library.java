package com.ymf.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.swtdesigner.SWTResourceManager;
import com.ymf.library.Login;
import com.ymf.utils.DataInfo;
import com.ymf.utils.ShellUtils;
import org.eclipse.swt.widgets.Label;


public class Library {

	protected Shell shell;
	private Display display;
	private ShellUtils shellUtils=new ShellUtils();
	private SimpleDateFormat sdf=new SimpleDateFormat(" yyyy年MM月dd日\nHH:mm:ss EEE ");
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Library window = new Library();
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
		shellUtils.center(shell);
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
		shell = new Shell(SWT.MIN);
		shell.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		shell.setText("欢迎使用图书馆管理系统");
		shell.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/大background1.jpg"));
		shell.setImage(SWTResourceManager.getImage(Library.class, "/images/4.jpg"));
		shell.setSize(1350, 720);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		tabItem.setText("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		
		
		final Tree tree = new Tree(composite_3, SWT.NONE);
		tree.setLocation(0, 0);
		tree.setSize(166,550);
		tree.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/tree.png"));
		
		final TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		treeItem.setText("\u65B0\u4E66\u7BA1\u7406");
		
		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/大background1.jpg"));
		composite_4.setLayout(new StackLayout());
		
		CTabItem tabItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tabItem_1.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		tabItem_1.setText("读 者 管 理");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		
		
		final Tree tree_1 = new Tree(composite_5, SWT.NONE);
		tree_1.setLocation(0, 0);
		tree_1.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/背景2.png"));
		tree_1.setSize(148, 547);
		
		
		TreeItem treeItem_16 = new TreeItem(tree_1, SWT.NONE);
		treeItem_16.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		treeItem_16.setText("读者密码修改");
		
		TreeItem treeItem_17 = new TreeItem(tree_1, SWT.NONE);
		treeItem_17.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		treeItem_17.setText("借阅信息查看");
		treeItem_17.setExpanded(true);
		
		TreeItem treeItem_18 = new TreeItem(tree_1, SWT.NONE);
		treeItem_18.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		treeItem_18.setText("书籍查询");
		
		TreeItem treeItem_20 = new TreeItem(tree_1, SWT.NONE);
		treeItem_20.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		treeItem_20.setText("已预约书籍");
		
		final Label label_1 = new Label(composite_5, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("楷体", 14, SWT.NORMAL));
		label_1.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/背景3.png"));
		label_1.setBounds(0, 546, 148, 113);
		
		label_1.setText("现在是北京时间：\n"+sdf.format(new Date()));
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(!shell.isDisposed()){
					display.asyncExec(new Runnable(){
						public void run(){
							label_1.setText("现在是北京时间:\n\n"+sdf.format(new Date()));
						}
					});
					try {
						Thread.sleep(1000);  //以毫秒计算
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}		
		}).start();
		
		Composite composite_6 = new Composite(sashForm_1, SWT.NONE);
		composite_6.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/背景1.png"));
		composite_6.setLayout(new StackLayout());
		if(Login.mno.startsWith("M")){
			tabItem_1.dispose();	
		}else if(Login.mno.startsWith("R")){
			tabItem.dispose();	
		}
		
		NewBookInsert newBookInsert = new NewBookInsert(composite_4,SWT.NONE);
		
		DataInfo.newBookInsert=newBookInsert;
		DataInfo.newBookCheck=new NewBookCheck(composite_4,SWT.NONE);
		DataInfo.bookBack=new BookBack(composite_4,SWT.NONE);
		DataInfo.bookBorrow=new BookBorrow(composite_4,SWT.NONE);
		DataInfo.bookUpdate=new BookUpdate(composite_4,SWT.NONE);
		DataInfo.bookSelect=new BookSelect(composite_4,SWT.NONE);
		DataInfo.bookTypeInsert=new BookTypeInsert(composite_4,SWT.NONE);
		DataInfo.bookTypeUpdate=new BookTypeUpdate(composite_4,SWT.NONE);
		DataInfo.pwdUpdate=new PwdUpdate(composite_4,SWT.NONE);
		DataInfo.readerAdd=new ReaderAdd(composite_4,SWT.NONE);
		DataInfo.readerUpdate=new ReaderUpdate(composite_4,SWT.NONE);
		DataInfo.level=new Level(composite_4,SWT.NONE);
		DataInfo.yuYue=new YuYue(composite_4,SWT.NONE);
		
		DataInfo.stackLayout.topControl=DataInfo.newBookInsert;
		ReaderPwdUpdate readerPwdUpdate= new ReaderPwdUpdate(composite_6,SWT.NONE);
		readerPwdUpdate.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/背景1.png"));
		DataInfo.readerPwdUpdate=readerPwdUpdate;
		DataInfo.selectMessage=new SelectMessage(composite_6,SWT.NONE);
		DataInfo.bookSelect1=new BookSelect1(composite_6,SWT.NONE);
		DataInfo.yuYue1=new YuYue1(composite_6,SWT.NONE);
		sashForm_1.setWeights(new int[] {148, 1187});
		DataInfo.stackLayout.topControl=DataInfo.readerPwdUpdate;
		TreeItem tri;
		
		
		
		tri=new TreeItem(treeItem,SWT.NONE);
		tri.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		tri.setText("新书订购");
		
		TreeItem treeItem_1 = new TreeItem(treeItem, SWT.NONE);
		treeItem_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_1.setText("\u4E66\u7C4D\u9A8C\u6536");
		
		final TreeItem treeItem_15 = new TreeItem(tree, SWT.NONE);
		treeItem_15.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		treeItem_15.setText("图书信息管理");
		
		TreeItem treeItem_6 = new TreeItem(treeItem_15, SWT.NONE);
		treeItem_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_6.setText("图书信息修改与删除");
		
		final TreeItem treeItem_2 = new TreeItem(tree, SWT.NONE);
		treeItem_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		treeItem_2.setText("图书借还管理");
		
		TreeItem treeItem_3 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_3.setText("图书归还管理");
		
		TreeItem treeItem_4 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_4.setText("图书借阅管理");
		
		TreeItem treeItem_5 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_5.setText("书籍查询");
		
		TreeItem treeItem_19 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_19.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_19.setText("读者预约情况");
		
		final TreeItem treeItem_7 = new TreeItem(tree, SWT.NONE);
		treeItem_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		treeItem_7.setText("管理员信息管理");
		
		TreeItem treeItem_8 = new TreeItem(treeItem_7, SWT.NONE);
		treeItem_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_8.setText("更改密码");
		
		final TreeItem treeItem_9 = new TreeItem(tree, SWT.NONE);
		treeItem_9.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		treeItem_9.setText("读者信息管理");
		
		TreeItem treeItem_10 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_10.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_10.setText("读者信息添加");
		
		TreeItem treeItem_11 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_11.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_11.setText("读者信息修改与删除");
		
		TreeItem treeItem_21 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_21.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_21.setText("读者等级信息修改");
		treeItem_9.setExpanded(false);
		
		final TreeItem treeItem_12 = new TreeItem(tree, SWT.NONE);
		treeItem_12.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		treeItem_12.setText("图书类别管理");
		
		TreeItem treeItem_13 = new TreeItem(treeItem_12, SWT.NONE);
		treeItem_13.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_13.setText("图书类别添加");
		
		TreeItem treeItem_14 = new TreeItem(treeItem_12, SWT.NONE);
		treeItem_14.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		treeItem_14.setText("图书类别修改");
		
		final Label label = new Label(composite_3, SWT.NONE);
		label.setBackgroundImage(SWTResourceManager.getImage(Library.class, "/images/time.png"));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("楷体", 14, SWT.NORMAL));
		label.setBounds(0, 550, 166, 109);
		label.setText("现在是北京时间：\n"+sdf.format(new Date()));
		sashForm.setWeights(new int[] {163, 1198});
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(!shell.isDisposed()){
					display.asyncExec(new Runnable(){
						public void run(){
							label.setText("现在是北京时间:\n\n"+sdf.format(new Date()));
						}
					});
					try {
						Thread.sleep(1000);  //以毫秒计算
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}		
		}).start();
		
		
		//获取节点值
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] trees=tree.getSelection();
				
				String name=trees[0].getText().trim();
				if("新书管理".equals(name)){
					treeItem.setExpanded(true);
					treeItem_15.setExpanded(false);
					treeItem_2.setExpanded(false);
					treeItem_7.setExpanded(false);
					treeItem_9.setExpanded(false);
					treeItem_12.setExpanded(false);
			}else if("图书信息管理".equals(name)){
					treeItem_15.setExpanded(true);
					treeItem.setExpanded(false);
					treeItem_2.setExpanded(false);
					treeItem_7.setExpanded(false);
					treeItem_9.setExpanded(false);
					treeItem_12.setExpanded(false);
			}else if("图书借还管理".equals(name)){
				treeItem_15.setExpanded(false);
				treeItem.setExpanded(false);
				treeItem_2.setExpanded(true);
				treeItem_7.setExpanded(false);
				treeItem_9.setExpanded(false);
				treeItem_12.setExpanded(false);
			}else if("管理员信息管理".equals(name)){
				treeItem_15.setExpanded(false);
				treeItem.setExpanded(false);
				treeItem_2.setExpanded(false);
				treeItem_7.setExpanded(true);
				treeItem_9.setExpanded(false);
				treeItem_12.setExpanded(false);
			}else if("读者信息管理".equals(name)){
				treeItem_15.setExpanded(false);
				treeItem.setExpanded(false);
				treeItem_2.setExpanded(false);
				treeItem_7.setExpanded(false);
				treeItem_9.setExpanded(true);
				treeItem_12.setExpanded(false);
			}else if("图书类别管理".equals(name)){
				treeItem_15.setExpanded(false);
				treeItem.setExpanded(false);
				treeItem_2.setExpanded(false);
				treeItem_7.setExpanded(false);
				treeItem_9.setExpanded(false);
				treeItem_12.setExpanded(true);
			}else if("新书订购".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.newBookInsert;
					DataInfo.newBookInsert.setVisible(true);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("书籍验收".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.newBookCheck;
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(true);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("图书信息修改与删除".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.bookUpdate;
					DataInfo.bookUpdate.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("图书归还管理".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.bookBack;
					DataInfo.bookBack.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
					
				}else if("图书借阅管理".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.bookBorrow;
					DataInfo.bookBorrow.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
					//BookBorrow.showInfo();
					
				}else if("书籍查询".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.bookSelect;
					DataInfo.bookSelect.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("更改密码".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.pwdUpdate;
					DataInfo.pwdUpdate.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("读者信息添加".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.readerAdd;
					DataInfo.readerAdd.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("读者信息修改与删除".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.readerUpdate;
					DataInfo.readerUpdate.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("图书类别添加".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.bookTypeInsert;
					DataInfo.bookTypeInsert.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("图书类别修改".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.bookTypeUpdate;
					DataInfo.bookTypeUpdate.setVisible(true);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(false);
				}else if("读者预约情况".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.yuYue;
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(true);
					DataInfo.level.setVisible(false);
				}else if("读者等级信息修改".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.level;
					DataInfo.bookTypeUpdate.setVisible(false);
					DataInfo.newBookInsert.setVisible(false);
					DataInfo.newBookCheck.setVisible(false);
					DataInfo.bookBack.setVisible(false);
					DataInfo.bookBorrow.setVisible(false);
					DataInfo.bookUpdate.setVisible(false);
					DataInfo.bookSelect.setVisible(false);
					DataInfo.bookTypeInsert.setVisible(false);
					DataInfo.pwdUpdate.setVisible(false);
					DataInfo.readerAdd.setVisible(false);
					DataInfo.readerUpdate.setVisible(false);
					DataInfo.yuYue.setVisible(false);
					DataInfo.level.setVisible(true);
					
				}
			}
		});
		
		tree_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] tree=tree_1.getSelection();
				
				String name=tree[0].getText().trim();
				if("读者密码修改".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.readerPwdUpdate;
					DataInfo.readerPwdUpdate.setVisible(true);
					DataInfo.selectMessage.setVisible(false);
					DataInfo.bookSelect1.setVisible(false);
					DataInfo.yuYue1.setVisible(false);
				}else if("借阅信息查看".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.readerPwdUpdate;
					DataInfo.selectMessage.setVisible(true);
					DataInfo.readerPwdUpdate.setVisible(false);
					DataInfo.bookSelect1.setVisible(false);
					DataInfo.yuYue1.setVisible(false);
				}else if("书籍查询".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.readerPwdUpdate;
					DataInfo.selectMessage.setVisible(false);
					DataInfo.readerPwdUpdate.setVisible(false);
					DataInfo.bookSelect1.setVisible(true);
					DataInfo.yuYue1.setVisible(false);
				}else if("已预约书籍".equals(name)){
					DataInfo.stackLayout.topControl=DataInfo.yuYue1;
					DataInfo.yuYue1.setVisible(true);
					DataInfo.selectMessage.setVisible(false);
					DataInfo.readerPwdUpdate.setVisible(false);
					DataInfo.bookSelect1.setVisible(false);
				}
			}
		});
	}
}
