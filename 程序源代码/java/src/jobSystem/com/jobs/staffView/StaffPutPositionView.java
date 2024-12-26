package src.jobSystem.com.jobs.staffView;

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

import src.jobSystem.com.jobs.companyDao.CompanyTableModel;
import src.jobSystem.com.jobs.staffDao.StaffDao;

public class StaffPutPositionView extends JDialog {
	int staffNumber;
	
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;
				
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
				
	//南部组件
	JPanel southPanel = null;
	JButton delPutBtn = null;
	
	public StaffPutPositionView() {}
	public StaffPutPositionView(StaffView staffView,int staffNumber) {
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
		title=new JLabel("全部投递的招聘信息");
		title.setFont(new Font("宋体",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);		

		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new CompanyTableModel(staffNumber,0));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//南部布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		delPutBtn = new JButton("删除投递");
		delPutBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(StaffPutPositionView.this, "请先选中要删除的信息","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int flag = JOptionPane.showConfirmDialog(StaffPutPositionView.this, "确认要删除选中的信息吗","提示",JOptionPane.YES_NO_OPTION);
					if(flag == 0) {
						int companyNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
						StaffDao staffDao = new StaffDao();
						if(staffDao.delPutMessage(companyNumber, staffNumber)) {
							refreshTableModel();
							JOptionPane.showMessageDialog(StaffPutPositionView.this, "删除成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(StaffPutPositionView.this, "删除失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		southPanel.add(delPutBtn);
		this.add(southPanel,BorderLayout.SOUTH);	
		
	}
	
	//刷新表格
	public void refreshTableModel() {
		table.setModel(new CompanyTableModel(staffNumber,0));
	}	

}
