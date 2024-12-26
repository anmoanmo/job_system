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

public class ManageChangePwdView extends JDialog {
	Manage manage = new Manage ();
	
	public ManageChangePwdView() {}
	public ManageChangePwdView(ManageView manageView,Manage manage) {
		super(manageView,"人才市场管理系统",true);
		this.manage = manage;
		this.createWindow();
	}
	
	Container container=getContentPane();   //创建一个容器
	JLabel title=new JLabel("修改密码");
	JLabel manageIdJLabel = new JLabel("帐      号：");
	JLabel manageNameJLabel = new JLabel("姓      名：");
	JLabel managePwdJLabel = new JLabel("旧密码：");
	JLabel manageNewPwdJLabel = new JLabel("新密码：");
	JLabel manageRepwdJLabel = new JLabel("再次输入：");
	
	JLabel manageIdJTextField = null;
	JLabel manageNameJTextField = null;
	JTextField managePwdJTextField = new JTextField("",30);
	JTextField manageNewPwdJTextField = new JTextField("",30);
	JTextField manageRepwdJTextField = new JTextField("",30);
	
	JButton changeBtn = new JButton("修改");
	JButton cancelBtn = new JButton("重置");	
	
	public void createWindow() {
		this.setTitle("修改个人密码");
		this.setSize(400,500);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变
		this.getContentPane().setLayout(null);			
		
		title.setFont(new Font("宋体",Font.PLAIN,25));
		title.setBounds(140, 10, 300, 25);
		manageIdJLabel.setBounds(80,50,60,30);
		manageIdJTextField = new JLabel(manage.getManageId());
		manageIdJTextField.setBounds(150,50,150,30);
		manageNameJLabel.setBounds(80,100,60,30);
		manageNameJTextField = new JLabel(manage.getManageName());
		manageNameJTextField.setBounds(150,100,150,30);
		managePwdJLabel.setBounds(75,150,100,30);
		managePwdJTextField.setBounds(150,150,150,30);	
		manageNewPwdJLabel.setBounds(80,200,60,30);
		manageNewPwdJTextField.setBounds(150,200,150,30);	
		manageRepwdJLabel.setBounds(75,250,80,30);
		manageRepwdJTextField.setBounds(150,250,150,30);		
		changeBtn.setBounds(70,330,80,30);
		cancelBtn.setBounds(230,330,80,30);
				
		container.add(title);
		container.add(manageIdJLabel);
		container.add(manageIdJTextField);
		container.add(manageNameJLabel);
		container.add(manageNameJTextField);
		container.add(managePwdJLabel);
		container.add(managePwdJTextField);
		container.add(manageNewPwdJLabel);
		container.add(manageNewPwdJTextField);
		container.add(manageRepwdJLabel);
		container.add(manageRepwdJTextField);
		
		//修改
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(managePwdJTextField.getText().equals("")||manageNewPwdJTextField.getText().equals("")||manageRepwdJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(ManageChangePwdView.this, "信息不能为空！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					if(managePwdJTextField.getText().equals(manage.getManagePwd()) && manageNewPwdJTextField.getText().equals(manageRepwdJTextField.getText())) {
						ManageDao manageDao = new ManageDao();
						if(manageDao.changeManagePwd(manage.getManageId(), managePwdJTextField.getText(), manageNewPwdJTextField.getText())) {
							ManageChangePwdView.this.dispose();
							manage.setManagePwd(manageNewPwdJTextField.getText());
							JOptionPane.showMessageDialog(ManageChangePwdView.this, "修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);	
						}else {
							JOptionPane.showMessageDialog(ManageChangePwdView.this, "修改失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(ManageChangePwdView.this, "密码输入有误！","提示",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});		
		
		//重置
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				managePwdJTextField.setText("");
				manageNewPwdJTextField.setText("");
				manageRepwdJTextField.setText("");
			}
		});
		
	}

}
