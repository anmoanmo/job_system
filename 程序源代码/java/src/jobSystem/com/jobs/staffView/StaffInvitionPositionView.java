package src.jobSystem.com.jobs.staffView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import src.jobSystem.com.jobs.companyDao.CompanyTableModel;

public class StaffInvitionPositionView extends JDialog {
	int staffNumber;
	
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;
				
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
	
	public StaffInvitionPositionView() {}
	public StaffInvitionPositionView(StaffView staffView,int staffNumber) {
		super(staffView,"人才市场管理系统",true);
		this.staffNumber = staffNumber;
		this.createWindow();
	}		
	
	public void createWindow() {
		this.setTitle("人才市场管理系统");
		this.setSize(1200, 560);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变		

		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("收到邀请的招聘信息");
		title.setFont(new Font("宋体",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);		

		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyTableModel(staffNumber,1));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);		
		
	}	
	
}
