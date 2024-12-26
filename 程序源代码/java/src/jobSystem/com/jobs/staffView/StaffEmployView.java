package src.jobSystem.com.jobs.staffView;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.jobSystem.com.jobs.util.Position;

public class StaffEmployView extends JDialog {
	Position position = null;
	
	
	public StaffEmployView() {}
	public StaffEmployView(StaffView staffView,Position position) {
		super(staffView,"人才市场管理系统",true);
		this.position = position;
		this.createWindow();
	}
	
	Container container=getContentPane();        //创建一个容器
	JLabel title=new JLabel("录取的招聘信息");      //标签文本
	JLabel companyNameJLabel = new JLabel("公司名称：");
	JLabel jobNameJLabel = new JLabel("职位名称：");
	JLabel personJLabel = new JLabel("联系人：");
	JLabel phoneJLabel = new JLabel("手机号：");
	JLabel countJLabel = new JLabel("招聘人数：");
	JLabel learnJLabel = new JLabel("学      历：");
	JLabel requestJLabel = new JLabel("职位要求：");
	JLabel moneyJLabel = new JLabel("薪      资：");
	JLabel AddressJLabel = new JLabel("公司地址：");
	
	JLabel companyName = null;
	JLabel jobName = null;
	JLabel person = null;
	JLabel phone = null;
	JLabel count = null;
	JLabel learn = null;
	JLabel request = null;
	JLabel money = null;
	JLabel address = null;
	
	
	public void createWindow() {
		this.setTitle("人才市场管理系统");
		this.setSize(500,650);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变
		this.getContentPane().setLayout(null);				

		title.setFont(new Font("宋体",Font.PLAIN,25));
		title.setBounds(140, 20, 300, 25);	
		
		companyNameJLabel.setBounds(80,70,70,30);
		companyName = new JLabel(position.getCompanyName());
		companyName.setBounds(150,70,200,30);
				
		jobNameJLabel.setBounds(80,120,70,30);
		jobName = new JLabel(position.getCompanyPosition());
		jobName.setBounds(150,120,200,30);
		
		personJLabel.setBounds(80,170,60,30);
		person = new JLabel(position.getCompanyPerson());
		person.setBounds(150,170,200,30);
		
		phoneJLabel.setBounds(75,220,100,30);
		phone = new JLabel(position.getCompanyPhone());
		phone.setBounds(150,220,200,30);
		
		countJLabel.setBounds(80,270,70,30);
		count = new JLabel(position.getCompanyCount());
		count.setBounds(150,270,200,30);
		
		learnJLabel.setBounds(80,320,60,30);
		learn = new JLabel(position.getCompanyLearn());
		learn.setBounds(150,320,200,30);
		
		requestJLabel.setBounds(80,370,70,30);
		request = new JLabel(position.getCompanyRequest());
		request.setBounds(150,370,200,30);
		
		moneyJLabel.setBounds(80,420,60,30);
		money = new JLabel(position.getCompanyMoney());
		money.setBounds(150,420,200,30);
		
		AddressJLabel.setBounds(80,470,70,30);
		address = new JLabel(position.getCompanyAddress());
		address.setBounds(150,470,200,30);
		
		container.add(title);
		container.add(companyNameJLabel);
		container.add(companyName);
		container.add(jobNameJLabel);
		container.add(jobName);
		container.add(personJLabel);
		container.add(person);
		container.add(phoneJLabel);
		container.add(phone);
		container.add(countJLabel);
		container.add(count);
		container.add(learnJLabel);
		container.add(learn);
		container.add(requestJLabel);
		container.add(request);
		container.add(moneyJLabel);
		container.add(money);	
		container.add(AddressJLabel);
		container.add(address);
		
	}

}
