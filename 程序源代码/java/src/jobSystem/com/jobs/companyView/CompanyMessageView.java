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

public class CompanyMessageView extends JDialog {
	Company company = new Company();   //记录传递过来的登陆的公司对象
	
	public CompanyMessageView () {}
	public CompanyMessageView (JFrame companyView,Company company) {
		super(companyView,"人才市场管理系统",true);
		this.company = company;
		this.createWindow();
	}
	
	Container container=getContentPane();   //创建一个容器
	JLabel title=new JLabel("个人信息");     //标签文本
	JLabel companyIdJLabel = new JLabel("帐      号：");
	JLabel companyNameJLabel = new JLabel("姓      名：");
	JLabel companyIdJTextField = null;
	JTextField companyNameJTextField = new JTextField("",30);	
	
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
		
		companyIdJLabel.setBounds(80,80,60,30);
		companyIdJTextField = new JLabel(company.getCompanyId());
		companyIdJTextField.setBounds(150,80,150,30);
		
		companyNameJLabel.setBounds(80,150,60,30);
		companyNameJTextField.setBounds(150,150,150,30);
		companyNameJTextField.setText(company.getCompanyName());
		changeBtn.setBounds(70,250,80,30);
		cancelBtn.setBounds(230,250,80,30);
		
		container.add(title);
		container.add(companyIdJLabel);
		container.add(companyIdJTextField);
		container.add(companyNameJLabel);
		container.add(companyNameJTextField);	
		
		//修改
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(companyNameJTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(CompanyMessageView.this, "信息不能为空","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					company.setCompanyName(companyNameJTextField.getText());
					CompanyDao companyDao = new CompanyDao();
					if(companyDao.changeCompanyMessage(company)) {
						CompanyMessageView.this.dispose();
						JOptionPane.showMessageDialog(CompanyMessageView.this, "修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(CompanyMessageView.this, "修改失败！","提示",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});		
		
		//重置
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				companyNameJTextField.setText("");
			}
		});
		
	}

}
