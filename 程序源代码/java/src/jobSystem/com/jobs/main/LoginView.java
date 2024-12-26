package src.jobSystem.com.jobs.main;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.staffDao.StaffDao;

public class LoginView extends JDialog implements ActionListener {
	
	public LoginView() {}
	public LoginView(JFrame landingView) {
		super(landingView,"人才市场管理系统",true);
		this.LoginWindow();
	}
	
	//-------------注册界面---------------------------------------------
	Container container=getContentPane();  //创建一个容器
	JLabel title=new JLabel("注册界面");    //标签文本
	JLabel NameLabel=new JLabel("姓    名：");
	JLabel IdJLabel=new JLabel("手机号：");
	JLabel pwdJLabel=new JLabel("密    码：");
	JTextField NameJTextField=new JTextField("",30);             //姓名框
	JTextField IdJTextField=new JTextField("",30);               //手机号框
	JPasswordField pwdJPasswordField=new JPasswordField("",30);  //密码框
	ButtonGroup group = new ButtonGroup();
	JRadioButton company = new JRadioButton("公司注册");
	JRadioButton staff = new JRadioButton("个人注册",true);
	JButton LoginJButton=new JButton("确认");
		
	public void LoginWindow() {
		
		this.setTitle("注册界面");
		this.setSize(430,340);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变
		this.getContentPane().setLayout(null);
		
		title.setFont(new Font("华文行楷",Font.PLAIN,40));
		title.setBounds(120, 30, 300, 40);
		NameLabel.setBounds(85, 90, 60, 30);
		IdJLabel.setBounds(85, 130, 60, 30);
		pwdJLabel.setBounds(85, 170, 60, 30);
		NameJTextField.setBounds(140, 90, 150, 30);
		IdJTextField.setBounds(140, 130, 150, 30);
		pwdJPasswordField.setBounds(140, 170, 150, 30);
		company.setBounds(120, 200, 90, 30);
		staff.setBounds(220, 200, 90, 30);
		LoginJButton.setBounds(160, 240, 80, 30);
		group.add(company);
		group.add(staff);
		LoginJButton.addActionListener(this);
		container.add(title);
		container.add(NameLabel);
		container.add(IdJLabel);
		container.add(pwdJLabel);
		container.add(NameJTextField);
		container.add(IdJTextField);
		container.add(pwdJPasswordField);
		container.add(company);
		container.add(staff);
		container.add(LoginJButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CompanyDao companyDao = new CompanyDao();
		StaffDao staffDao = new StaffDao();
		String Name = NameJTextField.getText();
		String Id = IdJTextField.getText();
		String Pwd = new String(pwdJPasswordField.getPassword());	
		if(company.isSelected()) {
			if(Name != null && !Name.equals("") && Id != null && !Id.equals("") && Pwd != null && !Pwd.equals("")) {
				if(Id.length() == 11) {
					if(companyDao.selectID(Id) || staffDao.selectID(Id)) {
						JOptionPane.showMessageDialog(LoginView.this, "该账户已存在,不可重复注册！","提示",JOptionPane.WARNING_MESSAGE);
					}else {
						if(companyDao.companyLogin(Name,Id, Pwd,2)) {
							JOptionPane.showMessageDialog(LoginView.this, "注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(LoginView.this, "注册失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(LoginView.this, "手机号输入有误！","提示",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(LoginView.this, "请输入姓名、账号和密码！","提示",JOptionPane.WARNING_MESSAGE);
			}
		}else if(staff.isSelected()) {
			if(Name != null && !Name.equals("") && Id != null && !Id.equals("") && Pwd != null && !Pwd.equals("")) {
				if(Id.length() == 11) {	
					if(companyDao.selectID(Id) || staffDao.selectID(Id)) {
						JOptionPane.showMessageDialog(LoginView.this, "该账户已存在,不可重复注册！","提示",JOptionPane.WARNING_MESSAGE);
					}else {
						if(staffDao.staffLogin(Name,Id, Pwd)) {
							JOptionPane.showMessageDialog(LoginView.this, "注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(LoginView.this, "注册失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(LoginView.this, "手机号输入有误！","提示",JOptionPane.ERROR_MESSAGE);
				}					
			}else {
				JOptionPane.showMessageDialog(LoginView.this, "请输入姓名、账号和密码！","提示",JOptionPane.WARNING_MESSAGE);
			}
		}		
	}

}
