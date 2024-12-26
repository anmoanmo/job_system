package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.companyDao.CompanyTableModel;

public class PositionHistoryView extends JDialog {
	src.jobSystem.com.jobs.companyView.PositionView positionView = null;
	String companyId;
	
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;	

	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;	
	
	//南部组件
	JPanel southPanel = null;
	JButton regainBtn = null;
	JButton deleteBtn = null;
	
	public PositionHistoryView() {}
	public PositionHistoryView(src.jobSystem.com.jobs.companyView.PositionView positionView, String companyId) {
		super(positionView,"人才市场管理系统",true);
		this.positionView = positionView;
		this.companyId = companyId;
		this.createWindow();
	}
	
	public void createWindow() {
		this.setTitle("人才市场管理系统");
		this.setSize(1200,500);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变
		
		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("公司单位发布的招聘信息");
		title.setFont(new Font("宋体",Font.PLAIN,25));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);		
		
		//中部布局
		centerPanel = new JPanel(new BorderLayout());		
		table = new JTable(new CompanyTableModel(companyId,1));	
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
			
		//南边布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		regainBtn = new JButton("恢复");
		regainBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionHistoryView.this, "请先选中要恢复的信息！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(PositionHistoryView.this, "确认要恢复选中的信息吗？","提示",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.regainPositionMessage(companyId,companyNumber)) {
							refreshTableModel();
							positionView.refreshTableModel();
							JOptionPane.showMessageDialog(PositionHistoryView.this, "恢复成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(PositionHistoryView.this, "恢复失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});		
		
		deleteBtn = new JButton("永久性删除");
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(PositionHistoryView.this, "请先选中要删除的信息！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(PositionHistoryView.this, "确认要删除选中的信息吗？删除后将不可恢复！","提示",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						CompanyDao companyDao = new CompanyDao();
						if(companyDao.deletePositionMessage(companyId,companyNumber)) {
							refreshTableModel();
							positionView.refreshTableModel();
							JOptionPane.showMessageDialog(PositionHistoryView.this, "删除成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(PositionHistoryView.this, "删除失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});		
		
		southPanel.add(regainBtn);
		southPanel.add(deleteBtn);		
		this.add(southPanel,BorderLayout.SOUTH);
		
	}
	
	//刷新表格
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(companyId,1));
	}		
	
}

