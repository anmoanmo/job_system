package src.jobSystem.com.jobs.manageView;

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

import src.jobSystem.com.jobs.manageDao.ManageDao;
import src.jobSystem.com.jobs.manageDao.StaffPutTableModel;

public class ShowStaffPutView extends JDialog {
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;
			
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
	
	//南部组件
	JPanel southPanel = null;
	JButton deleteBtn = null;
	
	public ShowStaffPutView() {}
	public ShowStaffPutView(ManageView manageView) {
		super(manageView,"人才市场管理系统",true);
		this.createWindow();
	}

	public void createWindow() {
		this.setTitle("人才市场管理系统");
		this.setSize(1200,500);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变
		
		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("所有职员的详情信息");
		title.setFont(new Font("宋体",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new StaffPutTableModel(0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//南部布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		deleteBtn = new JButton("删除职员");		
		deleteBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {		
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(ShowStaffPutView.this, "请先选中要删除的职员！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(ShowStaffPutView.this, "确认要删除选中的信息吗","提示",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int staffNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						ManageDao manageDao = new ManageDao();
						if(manageDao.deleStaff(staffNumber)) {
							refreshTableModel();
							JOptionPane.showMessageDialog(ShowStaffPutView.this, "删除成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(ShowStaffPutView.this, "删除失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		southPanel.add(deleteBtn);
		this.add(southPanel,BorderLayout.SOUTH);
		
	}
	
	//刷新表格
	public void refreshTableModel() {
		table.setModel(new StaffPutTableModel(0));
	}
	
}
