package src.jobSystem.com.jobs.companyDao;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import src.jobSystem.com.jobs.util.Position;

public class PositionPutTableModel extends AbstractTableModel {
	
	CompanyDao companyDao = new CompanyDao();
	String[] columnNames = {"编号","公司名称","职位名称","联系人姓名","电话","招聘人数","学历要求","职称要求","薪资","公司地址","投简历人数"};
	ArrayList<Position> positions = null;
	Object[][] rowData = null;
	
	public PositionPutTableModel(String companyId) {
		positions = companyDao.getPositionSumMessageList(companyId,0);
		rowData = new Object[positions.size()][columnNames.length];
		for(int i=0;i<positions.size();i++) {
			rowData[i][0] = positions.get(i).getCompanyNumber();
			rowData[i][1] = positions.get(i).getCompanyName();
			rowData[i][2] = positions.get(i).getCompanyPosition();
			rowData[i][3] = positions.get(i).getCompanyPerson();
			rowData[i][4] = positions.get(i).getCompanyPhone();
			rowData[i][5] = positions.get(i).getCompanyCount();
			rowData[i][6] = positions.get(i).getCompanyLearn();
			rowData[i][7] = positions.get(i).getCompanyRequest();
			rowData[i][8] = positions.get(i).getCompanyMoney();		
			rowData[i][9] = positions.get(i).getCompanyAddress();
			rowData[i][10] = positions.get(i).getStaffSum();
		}
	}
	
	public String getColumnName(int column) {
		//获取列名
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		//获取行数
		return positions.size();
	}

	@Override
	public int getColumnCount() {
		//获取列数
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//获取单元值
		return rowData[rowIndex][columnIndex];
	}

}
