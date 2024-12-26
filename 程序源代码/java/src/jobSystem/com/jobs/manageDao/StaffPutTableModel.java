package src.jobSystem.com.jobs.manageDao;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import src.jobSystem.com.jobs.util.Staff;

public class StaffPutTableModel extends AbstractTableModel {
	ManageDao manageDao = new ManageDao();
	String[] columnNames = {"编号","账号","姓名","身份证号","地址","电话","学历","职称"};
	ArrayList<Staff> staffs =null;
	Object[][] rowData = null;
	
	//构造方法，获取某个特定公司发布招聘信息被投递的职员信息，构造表格
	public StaffPutTableModel(int flag) {
		if(flag == 0) {
			staffs = manageDao.getStaffsList(0);
		}else if(flag == 1) {
			staffs = manageDao.getStaffsList(1);
		}
		
		rowData = new Object[staffs.size()][columnNames.length];
		for(int i=0;i<staffs.size();i++) {
			rowData[i][0] = staffs.get(i).getStaffNumber();
			rowData[i][1] = staffs.get(i).getStaffId();
			rowData[i][2] = staffs.get(i).getStaffName();
			rowData[i][3] = staffs.get(i).getStaffCard();
			rowData[i][4] = staffs.get(i).getStaffAddress();
			rowData[i][5] = staffs.get(i).getStaffPhone();
			rowData[i][6] = staffs.get(i).getStaffLearn();
			rowData[i][7] = staffs.get(i).getStaffJob();
		}
	}

	//获取列名
	public String getColumnName(int column) {
		return columnNames[column];
	}	

	//获取行数
	public int getRowCount() {
		return staffs.size();
	}

	//获取列数
	public int getColumnCount() {
		return columnNames.length;
	}

	//获取单元值
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rowData[rowIndex][columnIndex];
	}	
	
}
