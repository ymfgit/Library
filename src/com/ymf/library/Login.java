package com.ymf.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;
import com.ymf.dao.DBHelper;
import com.ymf.db.BookBorrowDao;
import com.ymf.db.ManagerInfoDao;
import com.ymf.db.ReaderInfoDao;
import com.ymf.db.RegisterUtil;
import com.ymf.table.Library;
import com.ymf.utils.ShellUtils;

/**
 * 登录界面
 * @author 
 *
 */
public class Login {
	private Button button_2; 
	protected Shell shell;
	private Text text_2;
	private ShellUtils shellUtils=new ShellUtils();
	private Display display;
	private Combo combo ;
	private Button button;
	private Button button_1; 
	private Label label_8 ;
	public static String mno;
	public static String mpwd;
	private ManagerInfoDao managerInfoDao=new ManagerInfoDao();
	private RegisterUtil registerUtil=new RegisterUtil();
	private ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	final Map<String,String> map=registerUtil.getRecord();
	private static BookBorrowDao bookBorrowDao=new BookBorrowDao();
    private int count=0;
    private DBHelper db;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(new DBHelper().getConnection());
		try {
			Login window = new Login();
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
		shell.setImage(SWTResourceManager.getImage(Login.class, "/images/4.jpg"));
		shell.setSize(519, 300);
		shell.setText("");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shellUtils.center(shell);
		
		shellUtils.tray(display, shell);   //启动托盘
		Composite composite = new Composite(shell, SWT.NONE);
		
		shellUtils.shellMove(composite, shell);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(21, 179, 61, 26);
		label_5.setText("用户名：");
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(21, 215, 61, 26);
		label_6.setText("密   码：");
		
		text_2 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(100, 215, 146, 23);
		
		Group group = new Group(composite, SWT.NONE);
		group.setBounds(267, 166, 222, 40);
		
		button = new Button(group, SWT.RADIO);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(10, 12, 62, 22);
		button.setText("管理员");
		
		button_1 = new Button(group, SWT.RADIO);
		button_1.setSelection(true);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setBounds(78, 12, 58, 22);
		button_1.setText("读者");
		
		Button button_3 = new Button(group, SWT.RADIO);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_3.setBounds(142, 12, 52, 21);
		button_3.setText("教师");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setToolTipText("");
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/login.jpg"));
		composite_1.setBounds(0, 0, 574, 149);
		shellUtils.shellMove(composite_1, shell);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(0, 0, 180, 25);
		label_1.setFont(SWTResourceManager.getFont("楷体", 15, SWT.NORMAL));
		label_1.setText("图书馆管理系统登录");
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(445, 4, 28, 25);
		label_2.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_mini_normal.png"));
		
		shellUtils.minOp(shell, label_2);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBounds(468, 4, 39, 25);
		label_3.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_normal.png"));
		
		shellUtils.closeOp(shell, label_3);
		
		label_8 = new Label(composite_1, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setBounds(89, 122, 278, 27);
		
		Label label = new Label(composite, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(Login.class, "/images/登录.png"));
		label.setBounds(72, 258, 82, 30);
		loginOp(shell,label);
		
		final Label label_4 = new Label(composite, SWT.NONE);	
		label_4.setImage(SWTResourceManager.getImage(Login.class, "/images/取消.png"));
		label_4.setBounds(185, 258, 78, 30);
		//取消，关闭登录界面
		closeOp(shell,label_4);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setImage(SWTResourceManager.getImage(Login.class, "/images/帮助.png"));
		label_7.setBounds(297, 258, 82, 30);
		helpOp(shell,label_7);
		
		combo = new Combo(composite, SWT.NONE);
		combo.setBounds(100, 180, 146, 25);
		
		button_2 = new Button(composite, SWT.CHECK);
		
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_2.setBounds(301, 216, 78, 21);
		button_2.setText("记住密码");
		
		//从注册表中读取保存的账号信息
		
		if(map!=null){
			Set<String> keys=map.keySet();
			for(String key:keys){
				combo.add(key);   //将所有的账号循环添加到下拉列表中
			}
			combo.select(0);  //默认选中第一个
			
		//	System.out.println(map.get(combo.getText().trim()));
			//获取当前账号的密码
			text_2.setText(map.get(combo.getText().trim())); //map.get(map.keySet().toArray()[0])
			
			//默认勾住记住密码
			button_2.setSelection(true);
		}
		
		//当值发生改变时，获取对应账号的密码
		combo.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				if("".equals(map.get(combo.getText().trim()))){
					text_2.setText(map.get(combo.getText().trim()));
				}else{
					text_2.setText("");
				}
				Set<String> keys=map.keySet();
				boolean b1=false;
				String pwd1="";
				String acount="";
				for(String key:keys){
					if(combo.getText().trim().equals(key)){
						b1=true;
						pwd1=map.get(key);
						text_2.setText(pwd1);
						acount=key;
						break;
					}
				}
			}
		});
		
		combo.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e){
				label_8.setText("");
			}
		});
		
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_8.setText("");
			}
		});

	}
	
	//取消，关闭登录界面
	public void closeOp(final Shell shell,final Label label){//窗口关闭键
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/取消.png"));
			}
			
			//鼠标松开时
			public void mouseUp(MouseEvent e){
				if(MessageDialog.openConfirm(shell, "关闭提醒","确定退出吗?")){
					shell.dispose();//关闭窗口
					System.exit(0);//退出
				}
			}
		});
		

		
	}
	//帮助
	public void helpOp(final Shell shell,final Label label){//窗口关闭键
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/帮助.png"));
			}
			
			//鼠标松开时
			public void mouseUp(MouseEvent e){
				Help help=new Help();
				help.open();
			}
		});
	}
	
	
	
	//登录到主界面
	public void loginOp(final Shell shell,final Label label){//窗口关闭键
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/登录.png"));
			}
			
			//鼠标松开时
			public void mouseUp(MouseEvent e){
				mno=combo.getText().trim();
				mpwd=text_2.getText().trim();
				String status="读者";         //身份
				String rno=mno;
				List<Map<String,Object>> list =bookBorrowDao.findcount(rno);//查询逾期未还的
				for(Map<String,Object> map:list){
					count=Integer.valueOf((String) map.get("COUNT(*)"));
				}
				if(button.getSelection()){
					status="管理员";
				}else{
					status="读者";
				}
				if(mno.equals("Admin")&&"".equals(mpwd)&&status=="读者"){
					shell.dispose();
					Library lb=new Library();
					lb.open();
				}else if(mno==null||"".equals(mno)){
					label_8.setText("请输入账号...");
				}else if(mpwd==null||"".equals(mpwd)){
					label_8.setText("请输入密码...");
				}else if(count>0){
					MessageBox mb=new MessageBox(shell);
					mb.setText("温馨提示");
					mb.setMessage("对不起，您有书籍逾期未还，账号已被冻结。\n请及时到管理员处交还逾期罚金和归还书籍，才能重新登录");
					mb.open();
				}else{
					Map<String,Object> userInfo=managerInfoDao.userLogin(mno, mpwd);
					if(userInfo!=null&&status=="管理员"){
						Set<String> keys=map.keySet();
						for(String key:keys){
							if(mno==key){
								text_2.setText(map.get(combo.getText().trim()));
							}
						}
						//判断用户是否选择了保存密码
						if(button_2.getSelection()){ //记住当前账号
							Map<String,String> map=new HashMap<String,String>();
							map.put(mno, mpwd);
							registerUtil.addRecord(map);
						}	

						shell.dispose();
						Library lb=new Library();
						lb.open();
					}else{
						label_8.setText("用户或密码或身份错误...");
					}

					Map<String,Object> userInfo1=readerInfoDao.userLogin(mno, mpwd);
					if(userInfo1!=null&&status=="读者"){
						Set<String> keys=map.keySet();
						for(String key:keys){
							if(mno==key){
								text_2.setText(map.get(combo.getText().trim()));
							}
						}
						//判断用户是否选择了保存密码
						if(button_2.getSelection()){ //记住当前账号
							Map<String,String> map=new HashMap<String,String>();
							map.put(mno, mpwd);
							registerUtil.addRecord(map);
						}	

						shell.dispose();
						Library lb=new Library();
						lb.open();
					}else{
						label_8.setText("用户或密码或身份错误...");
					}
				}
			}
		});
	}
}


