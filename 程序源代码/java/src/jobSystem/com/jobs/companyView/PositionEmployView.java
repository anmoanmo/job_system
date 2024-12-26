package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import src.jobSystem.com.jobs.companyDao.PositionEmployTableModel;
import src.jobSystem.com.jobs.util.Company;

public class PositionEmployView extends JFrame {
	Company company = new Company();
	CompanyView companyView;
	
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;
	
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
		
	//南部组件
	JPanel southPanel = null;
	JButton showBtn = null;
	JButton returnBtn = null;
	
	public PositionEmployView() {}
	public PositionEmployView(Company company,CompanyView companyView) {
		this.company = company;
		this.companyView = companyView;
	}

	public void createWindow() {
		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("全部招聘信息的录用情况");
		title.setFont(new Font("宋体",Font.PLAIN,25));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new PositionEmployTableModel(company.getCompanyId()));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);	
		
		//南部布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		showBtn = new JButton("查看详情");
		showBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionEmployView.this, "请先选中要查看的招聘信息！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int id = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
					new StaffEmployView(PositionEmployView.this,company.getCompanyId(),id).setVisible(true);
				}
			}
		});	
		
		returnBtn = new JButton("退      出");
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PositionEmployView.this.dispose();
				companyView.refreshTableModel();
				companyView.setVisible(true);
			}
		});	
					
		southPanel.add(showBtn);
		southPanel.add(returnBtn);
		this.add(southPanel,BorderLayout.SOUTH);
		
		//主界面
		this.setTitle("人才市场管理系统");
		this.setSize(1200, 560);
		this.setLocationRelativeTo(null); //使窗口显示在屏幕中央
		this.setResizable(false);         //设置窗口的大小为不可变
		this.setVisible(true);            //设置容器可视化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	//刷新表格
	public void refreshTableModel() {
		table.setModel(new PositionEmployTableModel(company.getCompanyId()));
	}		

}

