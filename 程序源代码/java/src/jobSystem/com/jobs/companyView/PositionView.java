package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.companyDao.CompanyTableModel;
import src.jobSystem.com.jobs.util.Company;

public class PositionView extends JFrame {
	Company company = new Company();
	CompanyView companyView;
	
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;
		
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
		
	//西部组件
	JPanel westPanel = null;
	JButton addBtn = null;     //发布招聘信息按钮
	JButton delBtn = null;     //删除招聘信息按钮
	JButton changeBtn = null;  //修改招聘信息按钮
	JButton HistoryBtn = null; //历史招聘信息按钮
	JButton returnBtn = null;  //退出按钮
	
	//构造方法
	public PositionView() {}
	public PositionView(Company company,CompanyView companyView) {
		this.company = company;
		this.companyView = companyView;   //将主界面传过来用于 打开、关闭和刷新界面
	}	
	
	public void createWindow() {
		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("公司单位发布的招聘信息");
		title.setFont(new Font("宋体",Font.PLAIN,25));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyTableModel(company.getCompanyId(),0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//西部布局
		westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,40));  //创建一个新的流布局管理器，它具有指定的对齐方式以及指定的水平和垂直间隙。
		westPanel.setPreferredSize(new Dimension(150, 400));              //设置JPanel的大小	
		addBtn = new JButton("发布招聘");
		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				new CompanyAddMessageView(PositionView.this,company).setVisible(true);
			}
		});
		
		delBtn = new JButton("删除招聘");
		delBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionView.this, "请先选中要删除的信息！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(PositionView.this, "确认要删除选中的信息吗？","提示",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.delePositionMessage(company.getCompanyId(),companyNumber)) {
							refreshTableModel();
							JOptionPane.showMessageDialog(PositionView.this, "删除成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(PositionView.this, "删除失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		changeBtn = new JButton("修改招聘");
		changeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionView.this, "请先选中要修改的招聘信息！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int id = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
					new CompanyChangeMessageView(PositionView.this,company.getCompanyId(),id).setVisible(true);
				}
			}
		});
		
		HistoryBtn = new JButton("历史招聘");
		HistoryBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				new PositionHistoryView(PositionView.this,company.getCompanyId()).setVisible(true);
			}
		});
		
		returnBtn = new JButton("退      出");
		returnBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PositionView.this.dispose();
				companyView.refreshTableModel();
				companyView.setVisible(true);
			}
		});
		
		westPanel.add(addBtn);
		westPanel.add(delBtn);
		westPanel.add(changeBtn);
		westPanel.add(HistoryBtn);
		westPanel.add(returnBtn);
		this.add(westPanel,BorderLayout.WEST);
		
		//主界面
		this.setTitle("人才市场管理系统");
		this.setSize(1500, 560);
		this.setLocationRelativeTo(null); //使窗口显示在屏幕中央
		this.setResizable(false);         //设置窗口的大小为不可变
		this.setVisible(true);            //设置容器可视化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置容器的关闭方式
		
	}
	
	//刷新表格
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(company.getCompanyId(),0));
	}
	
}

