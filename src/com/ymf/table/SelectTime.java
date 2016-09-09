package com.ymf.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.widgets.Label;
import com.swtdesigner.SWTResourceManager;
import com.ymf.utils.ShellUtils;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SelectTime extends Dialog {
	public static String sj1;
	public static String sj2;
	protected Object result;
	protected Shell shell;
	private ShellUtils shellUtils=new ShellUtils();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SelectTime(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.APPLICATION_MODAL);
		shell.setSize(334, 217);
		shell.setText(getText());
		
		final CalendarCombo calendarCombo = new CalendarCombo(shell, SWT.NONE);
		calendarCombo.setBounds(36, 74, 109, 25);
		
		final CalendarCombo calendarCombo_1 = new CalendarCombo(shell, SWT.NONE);
		calendarCombo_1.setBounds(181, 74, 109, 25);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(94, 24, 144, 35);
		label.setText("时间框");
		
		Button button = new Button(shell, SWT.NONE);
		shellUtils.shellMove(shell, shell);
		
		button.setBounds(195, 165, 80, 27);
		button.setText("确   定");
		
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sj1=calendarCombo.getDateAsString();
				sj2=calendarCombo_1.getDateAsString();
				shell.dispose();
				SelectMessage.showInfo();
			}
		});

		
		int width = shell.getMonitor().getClientArea().width;
		int height = shell.getMonitor().getClientArea().height;
		int x = shell.getSize().x;
		int y = shell.getSize().y;
		if (x > width) {
		    shell.getSize().x = width;
		}
		if (y > height) {
		    shell.getSize().y = height;
		}
		shell.setLocation((width - x) / 2, (height - y) / 2);


	}
}	
	
	
	

