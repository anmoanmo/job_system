package src.jobSystem.com.jobs.companyView;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import src.jobSystem.com.jobs.companyDao.CompanyDao;
import src.jobSystem.com.jobs.staffDao.StaffTableModel;
import src.jobSystem.com.jobs.util.Position;

public class StaffPutView extends JDialog {
	int companyNumber;
	String companyId;
	
	//北部组件
	JPanel northPanel = null;
	JLabel title= null;
			
	//中部组件
	JPanel centerPanel = null;
	JScrollPane jScrollPane = null;
	JTable table = null;
	
	//西部组件
	JPanel westPanel = null;
	JLabel companyNameJLabel = new JLabel("公司名称：");
	JLabel jobNameJLabel = new JLabel("职位名称： ");
	JLabel personJLabel = new JLabel(" 联  系  人： ");
	JLabel phoneJLabel = new JLabel(" 手  机  号 ：");
	JLabel countJLabel = new JLabel("招聘人数： ");
	JLabel learnJLabel = new JLabel(" 学        历： ");
	JLabel requestJLabel = new JLabel("职位要求： ");
	JLabel moneyJLabel = new JLabel(" 薪        资： ");
	JLabel AddressJLabel = new JLabel("公司地址： ");
	
	JTextField companyNameJTextField = new JTextField("",20);
	JTextField jobNameJTextField = new JTextField("",20);
	JTextField personJTextField = new JTextField("",20);
	JTextField phoneJTextField = new JTextField("",20);
	JTextField countJTextField = new JTextField("",20);
	JTextField learnJTextField = new JTextField("",20);
	JTextField requestJTextField = new JTextField("",20);
	JTextField moneyJTextField = new JTextField("",20);
	JTextField AddressJTextField = new JTextField("",20);	
			
	//南部组件
	JPanel southPanel = null;
	JButton invitionBtn = null;
	JButton returnBtn = null;
	
	public StaffPutView() {}
	public StaffPutView(PositionPutView positionPutView,String companyId,int companyNumber) {
		super(positionPutView,"人才市场管理系统",true);
		this.companyId = companyId;
		this.companyNumber = companyNumber;
		this.createWindow();
	}
	
	public void createWindow() {
		CompanyDao companyDao = new CompanyDao();
		Position position = companyDao.findPutPositionMessageByNumber(companyId, companyNumber);
		this.setTitle("人才市场管理系统");
		this.setSize(1100,500);
		this.setLocationRelativeTo(null);  //使窗口显示在屏幕中央
		this.setResizable(false);          //设置窗口的大小为不可变
		
		//北边布局
		northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		title=new JLabel("求职人员的详情信息");
		title.setFont(new Font("宋体",Font.PLAIN,20));
		northPanel.add(title);
		this.add(northPanel,BorderLayout.NORTH);
		
		//中部布局
		centerPanel = new JPanel(new BorderLayout());
		table = new JTable(new StaffTableModel(companyNumber));
		table.setRowHeight(30);
		jScrollPane = new JScrollPane(table);
		this.add(jScrollPane,BorderLayout.CENTER);
		
		//西部布局
		westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		westPanel.setPreferredSize(new Dimension(300, 10));         //设置JPanel的大小	
		companyNameJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		companyNameJTextField.setText(position.getCompanyName());
		jobNameJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		jobNameJTextField.setText(position.getCompanyPosition());
		personJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		personJTextField.setText(position.getCompanyPerson());
		phoneJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		phoneJTextField.setText(position.getCompanyPhone());
		countJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		countJTextField.setText(position.getCompanyCount());
		learnJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		learnJTextField.setText(position.getCompanyLearn());
		requestJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		requestJTextField.setText(position.getCompanyRequest());
		moneyJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		moneyJTextField.setText(position.getCompanyMoney());
		AddressJTextField.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
		AddressJTextField.setText(position.getCompanyAddress());
		
		westPanel.add(companyNameJLabel);
		westPanel.add(companyNameJTextField);
		westPanel.add(jobNameJLabel);
		westPanel.add(jobNameJTextField);
		westPanel.add(personJLabel);
		westPanel.add(personJTextField);
		westPanel.add(phoneJLabel);
		westPanel.add(phoneJTextField);
		westPanel.add(countJLabel);
		westPanel.add(countJTextField);
		westPanel.add(learnJLabel);
		westPanel.add(learnJTextField);
		westPanel.add(requestJLabel);
		westPanel.add(requestJTextField);
		westPanel.add(moneyJLabel);
		westPanel.add(moneyJTextField);	
		westPanel.add(AddressJLabel);
		westPanel.add(AddressJTextField);
		this.add(westPanel,BorderLayout.WEST);
		
		//南部布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		invitionBtn = new JButton("邀请面试");
		invitionBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				CompanyDao companyDao = new CompanyDao();
				int rowIndex = table.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(StaffPutView.this, "请先选中要邀请的职员！","提示",JOptionPane.WARNING_MESSAGE);
				}else {
					int staffNumber = ((Integer)table.getModel().getValueAt(rowIndex, 0)).intValue();
					if(companyDao.selectInvitionMessage(companyNumber, staffNumber)) {
						JOptionPane.showMessageDialog(StaffPutView.this, "已邀请，请勿再次邀请！","提示",JOptionPane.WARNING_MESSAGE);
					}else {
						if(companyDao.invitionStaff(companyNumber, staffNumber)) {
							JOptionPane.showMessageDialog(StaffPutView.this, "邀请成功！","提示",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(StaffPutView.this, "邀请失败！","提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});			
		southPanel.add(invitionBtn);	
		this.add(southPanel,BorderLayout.SOUTH);
	}

}
