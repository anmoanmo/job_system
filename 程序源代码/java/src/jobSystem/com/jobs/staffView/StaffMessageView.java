package src.jobSystem.com.jobs.staffView;

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

import src.jobSystem.com.jobs.staffDao.StaffDao;
import src.jobSystem.com.jobs.util.Staff;

public class StaffMessageView extends JDialog {
	Staff staff = new Staff();  //记录登陆的职员对象	
	
	public StaffMessageView() {}
	public StaffMessageView(JFrame staffView,Staff staff) {
		super(staffView,"人才市场管理系统",true);
		this.staff = staff;
		this.createWindow();
	}
	
	Container container=getContentPane();   //创建一个容器
	JLabel title=new JLabel("个人信息");     //标签文本
	JLabel staffIdJLabel = new JLabel("帐      号：");
	JLabel staffNameJLabel = new JLabel("姓      名：");
	JLabel staffCardJLabel = new JLabel("身份证号：");
	JLabel staffAddressJLabel = new JLabel("地      址：");
	JLabel staffPhoneJLabel = new JLabel("手机号：");
	JLabel staffLearnJLabel = new JLabel("学      历：");
	JLabel staffJobJLabel = new JLabel("职      称：");
	
	JLabel staffIdJTextField = null;
	JTextField staffNameJTextField = new JTextField("",30);
	JTextField staffCardJTextField = new JTextField("",30);
	JTextField staffAddressJTextField = new JTextField("",30);
	JTextField staffPhoneJTextField = new JTextField("",30);
	JTextField staffLearnJTextField = new JTextField("",30);
	JTextField staffJobJTextField = new JTextField("",30);

	JButton changeBtn = new JButton("修改");
	JButton cancelBtn = new JButton("重置");	
	
	public void createWindow() {
		this.setTitle("个人信息");
		this.setSize(400,500);
		this.setLocationRelativeTo(null);   //使窗口显示在屏幕中央
		this.setResizable(false);           //设置窗口的大小为不可变
		this.getContentPane().setLayout(null);
		
		title.setFont(new Font("宋体",Font.PLAIN,25));
		title.setBounds(140, 10, 300, 25);
		
		staffIdJLabel.setBounds(80,50,60,30);
		staffIdJTextField = new JLabel(staff.getStaffId());
		staffIdJTextField.setBounds(150,50,150,30);
		
		staffNameJLabel.setBounds(80,100,60,30);
		staffNameJTextField.setBounds(150,100,150,30);
		staffNameJTextField.setText(staff.getStaffName());
		
		staffCardJLabel.setBounds(75,150,100,30);
		staffCardJTextField.setBounds(150,150,150,30);
		staffCardJTextField.setText(staff.getStaffCard());
		
		staffAddressJLabel.setBounds(80,200,60,30);
		staffAddressJTextField.setBounds(150,200,150,30);
		staffAddressJTextField.setText(staff.getStaffAddress());
		
		staffPhoneJLabel.setBounds(80,250,60,30);
		staffPhoneJTextField.setBounds(150,250,150,30);
		staffPhoneJTextField.setText(staff.getStaffPhone());
		staffLearnJLabel.setBounds(80,300,60,30);
		staffLearnJTextField.setBounds(150,300,150,30);
		staffLearnJTextField.setText(staff.getStaffLearn());
		staffJobJLabel.setBounds(80,350,60,30);
		staffJobJTextField.setBounds(150,350,150,30);
		staffJobJTextField.setText(staff.getStaffJob());
		changeBtn.setBounds(70,400,80,30);
		cancelBtn.setBounds(230,400,80,30);
		
		container.add(title);
		container.add(staffIdJLabel);
		container.add(staffNameJLabel);
		container.add(staffCardJLabel);
		container.add(staffAddressJLabel);
		container.add(staffPhoneJLabel);
		container.add(staffLearnJLabel);
		container.add(staffJobJLabel);	
		container.add(staffIdJTextField);
		container.add(staffNameJTextField);
		container.add(staffCardJTextField);
		container.add(staffAddressJTextField);
		container.add(staffPhoneJTextField);
		container.add(staffLearnJTextField);
		container.add(staffJobJTextField);		
		
		//修改
		container.add(changeBtn);
		changeBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(staffPhoneJTextField.getText().length() == 11) {
					if(staffNameJTextField.getText().equals("")||staffCardJTextField.getText().equals("")||staffAddressJTextField.getText().equals("")
							||staffPhoneJTextField.getText().equals("")||staffLearnJTextField.getText().equals("")||staffJobJTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(StaffMessageView.this, "信息不能为空！","提示",JOptionPane.WARNING_MESSAGE);
					}else {
						staff.setStaffName(staffNameJTextField.getText());
						staff.setStaffCard(staffCardJTextField.getText());
						staff.setStaffAddress(staffAddressJTextField.getText());
						staff.setStaffPhone(staffPhoneJTextField.getText());
						staff.setStaffLearn(staffLearnJTextField.getText());
						staff.setStaffJob(staffJobJTextField.getText());							
						StaffDao staffDao = new StaffDao();
						if(staffDao.changeStaffMessage(staff)) {
							StaffMessageView.this.dispose();
							JOptionPane.showMessageDialog(StaffMessageView.this, "修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(StaffMessageView.this, "修改失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(StaffMessageView.this, "手机号输入有误！","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//重置
		container.add(cancelBtn);
		cancelBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				staffNameJTextField.setText("");
				staffCardJTextField.setText("");
				staffAddressJTextField.setText("");
				staffPhoneJTextField.setText("");
				staffLearnJTextField.setText("");
				staffJobJTextField.setText("");
			}
		});
		
	}
	
}
