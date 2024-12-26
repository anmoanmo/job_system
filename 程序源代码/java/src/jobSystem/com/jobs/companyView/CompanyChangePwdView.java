package src.jobSystem.com.jobs.companyView;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.util.Company;

public class CompanyChangePwdView extends JDialog {
	Company company = new Company();
	
	public CompanyChangePwdView() {}
	public CompanyChangePwdView(JFrame companyView,Company company) {
		super(companyView,"人才市场管理系统",true);
		this.company = company;
		this.createWindow();
	}

	Container container=getContentPane();   //创建一个容器
	JLabel title=new JLabel("修改密码");
	JLabel companyIdJLabel = new JLabel("帐      号：");
	JLabel companyNameJLabel = new JLabel("姓      名：");
	JLabel companyPwdJLabel = new JLabel("旧密码：");
	JLabel companyNewPwdJLabel = new JLabel("新密码：");
	JLabel companyRepwdJLabel = new JLabel("再次输入：");
	
	JLabel companyIdJTextField = null;
	JLabel companyNameJTextField = null;
	JTextField companyPwdJTextField = new JTextField("",30);
	JTextField companyNewPwdJTextField = new JTextField("",30);
	JTextField companyRepwdJTextField = new JTextField("",30);
	
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
		companyIdJLabel.setBounds(80,50,60,30);
		companyIdJTextField = new JLabel(company.getCompanyId());
		companyIdJTextField.setBounds(150,50,150,30);
		companyNameJLabel.setBounds(80,100,60,30);
		companyNameJTextField = new JLabel(company.getCompanyName());
		companyNameJTextField.setBounds(150,100,150,30);
		companyPwdJLabel.setBounds(75,150,100,30);
		companyPwdJTextField.setBounds(150,150,150,30);	
		companyNewPwdJLabel.setBounds(80,200,60,30);
		companyNewPwdJTextField.setBounds(150,200,150,30);	
		companyRepwdJLabel.setBounds(75,250,80,30);
		companyRepwdJTextField.setBounds(150,250,150,30);		
		changeBtn.setBounds(70,330,80,30);
		cancelBtn.setBounds(230,330,80,30);
				
		container.add(title);
		container.add(companyIdJLabel);
		container.add(companyIdJTextField);
		container.add(companyNameJLabel);
		container.add(companyNameJTextField);
		container.add(companyPwdJLabel);
		container.add(companyPwdJTextField);
		container.add(companyNewPwdJLabel);
		container.add(companyNewPwdJTextField);
		container.add(companyRepwdJLabel);
		container.add(companyRepwdJTextField);

		//修改
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(companyPwdJTextField.getText().equals("")||companyNewPwdJTextField.getText().equals("")||companyRepwdJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(CompanyChangePwdView.this, "信息不能为空！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					if(companyPwdJTextField.getText().equals(company.getCompanyPwd()) && companyNewPwdJTextField.getText().equals(companyRepwdJTextField.getText())) {
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.changeCompanyPwd(company.getCompanyId(), companyPwdJTextField.getText(), companyNewPwdJTextField.getText())) {
							CompanyChangePwdView.this.dispose();
							company.setCompanyPwd(companyNewPwdJTextField.getText());
							JOptionPane.showMessageDialog(CompanyChangePwdView.this, "修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);	
						}else {
							JOptionPane.showMessageDialog(CompanyChangePwdView.this, "修改失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(CompanyChangePwdView.this, "密码输入有误！","提示",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		//重置
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				companyPwdJTextField.setText("");
				companyNewPwdJTextField.setText("");
				companyRepwdJTextField.setText("");
			}
		});
	}

}
