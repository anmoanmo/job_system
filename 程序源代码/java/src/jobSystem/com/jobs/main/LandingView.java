package src.jobSystem.com.jobs.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.companyView.CompanyView;
import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.manageView.ManageView;
import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.staffView.StaffView;
import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.Manage;
import src.jobSystem.com.jobs.util.Staff;

public class LandingView implements ActionListener {
	
	//-------------登陆界面---------------------------------------------
	JFrame landingView=new JFrame();              //登陆界面
	JLabel title=new JLabel("人才市场管理系统");   //标签文本
	JLabel photoJLabel = new JLabel(new ImageIcon("D:\\学校\\课程\\java课程设计\\java数据库课设\\程序源代码\\java\\src\\img\\photo.png"));
	JLabel IdJLabel=new JLabel("帐号：");
	JLabel pwdJLabel=new JLabel("密码：");
	JTextField IdJTextField=new JTextField("",30);               //手机号框
	JPasswordField pwdJPasswordField=new JPasswordField("",30);  //密码框
	ButtonGroup group = new ButtonGroup();
	JRadioButton manage = new JRadioButton("管理员登陆");          //公司登陆单选按钮
	JRadioButton company = new JRadioButton("公司登陆");          //公司登陆单选按钮
	JRadioButton staff = new JRadioButton("个人登陆",true);       //个人登陆单选按钮，默认选择
	JButton LoginJButton=new JButton("注册");
	JButton LangJButton=new JButton("登陆");
		
	public void LandingWindow() {
		landingView.setTitle("人才市场管理系统");
		landingView.setSize(500,600);
		landingView.setLocationRelativeTo(null);      //使窗口显示在屏幕中央
		landingView.setResizable(false);              //设置窗口的大小为不可变
		landingView.getContentPane().setLayout(null); //空布局
		landingView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置窗口的关闭方式
				
		title.setFont(new Font("宋体",Font.PLAIN,35));
		title.setBounds(100, 40, 300, 35);
		photoJLabel.setBounds(60, 290, 385, 255);
		IdJLabel.setBounds(130, 100, 50, 30);
		IdJTextField.setBounds(180, 100, 150, 30);
		pwdJLabel.setBounds(130, 150, 50, 30);
		pwdJPasswordField.setBounds(180, 150, 150, 30);
		manage.setBounds(90, 190, 90, 30);
		company.setBounds(200, 190, 90, 30);
		staff.setBounds(300, 190, 90, 30);
		LoginJButton.setBounds(150, 230, 80, 30);
		LangJButton.setBounds(250, 230, 80, 30);
		group.add(manage);
		group.add(company);
		group.add(staff);			
		landingView.add(title);
		landingView.add(photoJLabel);
		landingView.add(IdJLabel);
		landingView.add(pwdJLabel);
		landingView.add(IdJTextField);
		landingView.add(pwdJPasswordField);
		landingView.add(manage);
		landingView.add(company);
		landingView.add(staff);
		landingView.add(LoginJButton);
		landingView.add(LangJButton);
		
		LoginJButton.addActionListener(this);
		LangJButton.addActionListener(this);
		landingView.setVisible(true);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if(buttonName.equals("登陆")) {
			String id = IdJTextField.getText();
			String pwd = new String(pwdJPasswordField.getPassword());
			if(manage.isSelected()) {
				if(id != null && !id.equals("") && pwd != null && !pwd.equals("")) {
					ManageDao manageDao = new ManageDao();
					Manage manage = manageDao.manageLanding(id, pwd);
					if(manage != null) {
						if(manage.getManageSign() != 1) {
							//登陆成功
							landingView.dispose();
							JOptionPane.showMessageDialog(landingView, "登陆成功！","提示",JOptionPane.INFORMATION_MESSAGE);
							ManageView manageView = new ManageView(manage);   //管理员登陆
							manageView.ManageWindow();
							
						}else {
							JOptionPane.showMessageDialog(landingView, "该帐号涉嫌违规，已被注销！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(landingView, "帐号或密码错误！","提示",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(landingView, "请输入账号和密码！","提示",JOptionPane.WARNING_MESSAGE);
				}	
			}else if(company.isSelected()) {
				if(id != null && !id.equals("") && pwd != null && !pwd.equals("")) {
					CompanyDao manageDao = new CompanyDao();
					Company company = manageDao.companyLanding(id, pwd);
					if(company != null) {
						if(company.getCompanySign() != 1) {
							//登陆成功
							landingView.dispose();
							JOptionPane.showMessageDialog(landingView, "登陆成功！","提示",JOptionPane.INFORMATION_MESSAGE);
							CompanyView companyView = new CompanyView(company);   //公司登陆
							companyView.CompanyWindow();
							
						}else {
							JOptionPane.showMessageDialog(landingView, "该帐号涉嫌违规，已被注销！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(landingView, "帐号或密码错误！","提示",JOptionPane.ERROR_MESSAGE);
					}					
				}else {
					JOptionPane.showMessageDialog(landingView, "请输入账号和密码！","提示",JOptionPane.WARNING_MESSAGE);
				}
			}else if(staff.isSelected()) {
				if(id != null && !id.equals("") && pwd != null && !pwd.equals("")) {
					StaffDao staffDao = new StaffDao();
					Staff staff = staffDao.staffLanding(id, pwd);
					if(staff != null) {
						if(staff.getStaffSign() != 1) {
							//登陆成功
							landingView.dispose();
							JOptionPane.showMessageDialog(landingView, "登陆成功！","提示",JOptionPane.INFORMATION_MESSAGE);
							StaffView staffView = new StaffView(staff);   //职员登陆
							staffView.StaffWindow();

						}else {
							JOptionPane.showMessageDialog(landingView, "该帐号涉嫌违规，已被注销！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(landingView, "帐号或密码错误！","提示",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(landingView, "请输入账号和密码！","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}else if(buttonName.equals("注册")){
			//使对话框窗体可见，实现了当用户单击该按钮后将弹出注册对话框的功能
			new src.jobSystem.com.jobs.main.LoginView(landingView).setVisible(true);

		}
	}

}

