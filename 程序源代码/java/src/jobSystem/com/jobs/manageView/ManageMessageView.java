package src.jobSystem.com.jobs.manageView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.util.Manage;

public class ManageMessageView extends JDialog {
	Manage manage = new Manage ();           //记录登陆的管理员对象
	
	public ManageMessageView() {}
	public ManageMessageView(ManageView manageView,Manage manage) {
		super(manageView,"人才市场管理系统",true);
		this.manage = manage;
		this.createWindow();
	}
	
	Container container=getContentPane();   //创建一个容器
	JLabel title=new JLabel("个人信息");     //标签文本
	JLabel manageIdJLabel = new JLabel("帐      号：");
	JLabel manageNameJLabel = new JLabel("姓      名：");
	JLabel manageIdJTextField = null;
	JTextField manageNameJTextField = new JTextField("",30);	
	
	JButton changeBtn = new JButton("修改");
	JButton cancelBtn = new JButton("重置");		
	
	public void createWindow() {
		this.setTitle("个人信息");
		this.setSize(400,450);
		this.setLocationRelativeTo(null);   //使窗口显示在屏幕中央
		this.setResizable(false);           //设置窗口的大小为不可变
		this.getContentPane().setLayout(null);		
		
		title.setFont(new Font("宋体",Font.PLAIN,25));
		title.setBounds(140, 20, 300, 25);
		
		manageIdJLabel.setBounds(80,80,60,30);
		manageIdJTextField = new JLabel(manage.getManageId());
		manageIdJTextField.setBounds(150,80,150,30);
		
		manageNameJLabel.setBounds(80,150,60,30);
		manageNameJTextField.setBounds(150,150,150,30);
		manageNameJTextField.setText(manage.getManageName());
		changeBtn.setBounds(70,250,80,30);
		cancelBtn.setBounds(230,250,80,30);
		
		container.add(title);
		container.add(manageIdJLabel);
		container.add(manageIdJTextField);
		container.add(manageNameJLabel);
		container.add(manageNameJTextField);			
	
		//修改
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(manageNameJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(ManageMessageView.this, "信息不能为空","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					manage.setManageName(manageNameJTextField.getText());
					ManageDao manageDao = new ManageDao();
					if(manageDao.changeManage(manage)) {
						ManageMessageView.this.dispose();
						JOptionPane.showMessageDialog(ManageMessageView.this, "修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(ManageMessageView.this, "修改失败！","提示",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		//重置
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				manageNameJTextField.setText("");
			}
		});		
	}
	
}
