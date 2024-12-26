package src.jobSystem.com.jobs.staffView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.util.Staff;

public class StaffChangePwdView extends JDialog {
	Staff staff = new Staff ();
	
	public StaffChangePwdView() {}
	public StaffChangePwdView(StaffView staffView,Staff staff) {
		super(staffView,"人才市场管理系统",true);
		this.staff = staff;
		this.createWindow();
	}
	
	Container container=getContentPane();   //创建一个容器	
	JLabel title=new JLabel("修改密码");	
	JLabel staffIdJLabel = new JLabel("帐      号：");
	JLabel staffNameJLabel = new JLabel("姓      名：");
	JLabel staffPwdJLabel = new JLabel("旧密码：");
	JLabel staffNewPwdJLabel = new JLabel("新密码：");
	JLabel staffRepwdJLabel = new JLabel("再次输入：");
	
	JLabel staffIdJTextField = null;
	JLabel staffNameJTextField = null;
	JTextField staffPwdJTextField = new JTextField("",30);
	JTextField staffNewPwdJTextField = new JTextField("",30);
	JTextField staffRepwdJTextField = new JTextField("",30);
	
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
		
		staffIdJLabel.setBounds(80,50,60,30);
		staffIdJTextField = new JLabel(staff.getStaffId());
		staffIdJTextField.setBounds(150,50,150,30);
		
		staffNameJLabel.setBounds(80,100,60,30);
		staffNameJTextField = new JLabel(staff.getStaffName());
		staffNameJTextField.setBounds(150,100,150,30);
			
		staffPwdJLabel.setBounds(75,150,100,30);
		staffPwdJTextField.setBounds(150,150,150,30);	
		staffNewPwdJLabel.setBounds(80,200,60,30);
		staffNewPwdJTextField.setBounds(150,200,150,30);	
		staffRepwdJLabel.setBounds(75,250,80,30);
		staffRepwdJTextField.setBounds(150,250,150,30);		
		changeBtn.setBounds(70,330,80,30);
		cancelBtn.setBounds(230,330,80,30);
				
		container.add(title);
		container.add(staffIdJLabel);
		container.add(staffIdJTextField);
		container.add(staffNameJLabel);
		container.add(staffNameJTextField);
		container.add(staffPwdJLabel);
		container.add(staffPwdJTextField);
		container.add(staffNewPwdJLabel);
		container.add(staffNewPwdJTextField);
		container.add(staffRepwdJLabel);
		container.add(staffRepwdJTextField);		

		//修改
		container.add(changeBtn);		
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(staffPwdJTextField.getText().equals("")||staffNewPwdJTextField.getText().equals("")||staffRepwdJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(StaffChangePwdView.this, "信息不能为空","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					if(staffPwdJTextField.getText().equals(staff.getStaffPwd()) && staffNewPwdJTextField.getText().equals(staffRepwdJTextField.getText())) {
						StaffDao staffDao = new StaffDao();
						if(staffDao.changeStaffPwd(staff.getStaffId(), staffPwdJTextField.getText(), staffNewPwdJTextField.getText())) {
							StaffChangePwdView.this.dispose();
							staff.setStaffPwd(staffNewPwdJTextField.getText());
							JOptionPane.showMessageDialog(StaffChangePwdView.this, "修改成功","提示",JOptionPane.INFORMATION_MESSAGE);	
						}else {
							JOptionPane.showMessageDialog(StaffChangePwdView.this, "修改失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(StaffChangePwdView.this, "密码输入有误","提示",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});	
		
		//重置
		container.add(cancelBtn);		
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				staffPwdJTextField.setText("");
				staffNewPwdJTextField.setText("");
				staffRepwdJTextField.setText("");
			}
		});		
		
	}

}
