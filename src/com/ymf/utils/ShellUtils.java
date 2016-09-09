package com.ymf.utils;

import java.io.InputStream;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import com.swtdesigner.SWTResourceManager;

public class ShellUtils {
	private boolean flag=false;//记录鼠标是否是按着的
	private int clickX;//鼠标点击是的x轴
	private int clickY;//鼠标点击是的y轴
	
	public void shellMove(Composite composite,final Shell shell){//面板和窗口
		composite.addMouseMoveListener(new MouseMoveListener(){//鼠标监听,没有鼠标移动监听就new一个就可以啦
			public void mouseMove(MouseEvent arg0){//鼠标移动
				if(flag){//如果是按着的
					//移动面板
					shell.setLocation(shell.getLocation().x+arg0.x-clickX,shell.getLocation().y+arg0.y-clickY);//居中显示
				}
			}

		});
		
		/**
		  *因为mouselistener里面有很多方法，而这些方法不一定都是我们所需要的，所以引入mouseAdapter 这个接口，
		 * 该接口里面有系统写好的方法（这些方法没有方法体，就是空方法），而我们只需要再重写我们需要的方法就行了
		 */
		
		composite.addMouseListener(new MouseAdapter(){//给一个接收器,接收我们所需要的
			//鼠标按下时
			public void mouseDown(MouseEvent e){//鼠标按下的e事件
				//记录当前鼠标的位置
				flag=true;
				clickX=e.x;
				clickY=e.y;
			}
			
			//当鼠标松开时
			public void mouseUp(MouseEvent e){//鼠标松开的事件
				flag=false;
			}
		});
	}
	
	//关闭操作
	public void closeOp(final Shell shell,final Label label){//窗口关闭键
		label.addMouseTrackListener(new MouseTrackAdapter(){//添加鼠标踪迹监听，new一个接收器
			//鼠标移开时
			public void mouseExit(MouseEvent arg0){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_close_normal.png"));
			}
			
			//鼠标移上时
			public void mouseHover(MouseEvent arg0){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_close_highlight.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_close_down.png"));
			}
			
			//鼠标松开时
			public void mouseUp(MouseEvent e){
				shell.dispose();//关闭窗口
				//System.exit(0);//退出
			}
		});
	}
	
	//最小化操作
	public void minOp(final Shell shell,final Label label){
		label.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			public void mouseExit(MouseEvent arg0){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_mini_normal.png"));
			}
			
			//鼠标移上时
			public void mouseHover(MouseEvent arg0){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_mini_highlight.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_mini_down.png"));
			}
			
			//鼠标松开时
			public void mouseUp(MouseEvent e){
				shell.setMinimized(true);
			}
		});
	}
	
	public void maxOp(final Shell shell,final Label label){
		label.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			public void mouseExit(MouseEvent arg0){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_max_normal.png"));
			}
			
			//鼠标移上时
			public void mouseHover(MouseEvent arg0){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_max_highlight.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e){
				label.setImage(SWTResourceManager.getImage(ShellUtils.class,"/images/btn_max_down.png"));
			}
			
			int temp=2;
			//鼠标松开时
			public void mouseUp(MouseEvent e){
				//shell.setMaximized(true);
				if(temp%2==0){
					shell.setMaximized(true);
					temp++;
				}else{
					shell.setSize(1000, 700);
					temp++;
				}
			}
		});
	}
	
	/**
	 * 图片自适应
	 * @param
	 * @param path
	 * @return
	 */
	public Image imageSize(InputStream is,int width,int height){
		ImageData data=new ImageData(is);
		data=data.scaledTo(width,height);
		return new Image(null,data);
	}
	


	//居中
	public void center(final Shell shell){
		shell.setLocation((shell.getDisplay().getClientArea().width-shell.getSize().x)/2,(shell.getDisplay().getClientArea().height-shell.getSize().y)/2);
	}

	public void tray(Display display,final Shell shell){
		Tray tray=display.getSystemTray();//获取系统托盘
		TrayItem item;
		
		if(tray==null){
			MessageDialog.openError(shell,"错误信息","当前操作不支持托盘");
		}else{
			item=new TrayItem(tray,SWT.NONE);
			item.setToolTipText("图书馆管理系统");//当前鼠标放到托盘图标上是，显示的信息
			
			//设置托盘图标
			item.setImage(shell.getImage());
			
			//最大化最小化应用程序
			item.addListener(SWT.Selection,new Listener(){

				public void handleEvent1(Event arg0) {
					if(shell.getMinimized()){//如果当前应用程序最小化
						shell.setMinimized(false);
					}else{
						if(shell.getVisible()){//如果是显示的
							shell.setVisible(false);
						}else{
							shell.setVisible(true);
						}
					}
				}

				@Override
				public void handleEvent(Event arg0) {
					
				}
				
			});
			
			
			final Menu menu=new Menu(shell,SWT.POP_UP);//右击弹出菜单//SWT.POP_UP    鼠标右键弹出菜单
			
			//普通型
			MenuItem maxItem=new MenuItem(menu,SWT.PUSH);//
			maxItem.setText("最大化");
			maxItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					shell.setVisible(true);//先将可见性设为true
					shell.setMaximized(true);
				}
			});
			MenuItem minItem=new MenuItem(menu,SWT.PUSH);//普通型
			minItem.setText("最小化");
			minItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					shell.setVisible(true);//先将可见性设为true
					shell.setMinimized(true);
				}
			});
				
			//分割线
			new MenuItem(menu,SWT.SEPARATOR);
			
			MenuItem exitItem=new MenuItem(menu,SWT.PUSH);//普通型
			exitItem.setText("退出");
			exitItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					Display.getCurrent().close();
					System.exit(0);
				}
			});
			
			item.addListener(SWT.MenuDetect,new Listener(){
				public void handleEvent(Event arg0){
					menu.setVisible(true);
				}
			});
			
			
			
			
		}
		
	}
	
	

	
	
}
