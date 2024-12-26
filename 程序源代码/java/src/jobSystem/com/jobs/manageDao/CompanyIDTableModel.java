package src.jobSystem.com.jobs.manageDao;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import src.jobSystem.com.jobs.util.Company;

public class CompanyIDTableModel extends AbstractTableModel {
	ManageDao manageDao = new ManageDao();
	String[] columnNames = {"编号","姓名","账号"};
	ArrayList<Company> companys =null;
	Object[][] rowData = null;

	public CompanyIDTableModel(int flag) {
		if(flag == 0) {
			companys = manageDao.getCompanysList(0);
		}else if(flag == 1){
			companys = manageDao.getCompanysList(1);
		}
		
		rowData = new Object[companys.size()][columnNames.length];
		for(int i=0;i<companys.size();i++) {
			rowData[i][0] = companys.get(i).getCompanyNumber();
			rowData[i][1] = companys.get(i).getCompanyName();
			rowData[i][2] = companys.get(i).getCompanyId();
		}
	}

	//获取列名
	public String getColumnName(int column) {
		return columnNames[column];
	}	

	//获取行数
	public int getRowCount() {
		return companys.size();
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
