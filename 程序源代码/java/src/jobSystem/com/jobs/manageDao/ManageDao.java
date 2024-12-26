package src.jobSystem.com.jobs.manageDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.JDBCUtil;
import src.jobSystem.com.jobs.util.Manage;
import src.jobSystem.com.jobs.util.Staff;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.ClientPreparedStatement;

public class ManageDao {

	//登陆方法
	public Manage manageLanding(String id,String pwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "select * from userTable where userRole=1 and userId=? and userPwd=?";
			//创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？占位符赋值
			ps.setString(1, id);
			ps.setString(2, pwd);
			//执行sql 返回结果集
			rs = ps.executeQuery();
			//查找账户、密码和权限是否正确
			if(rs.next()) {
				Manage manage = new Manage();         //新建公司单位实例，用于存放登陆实例
				manage.setManageName(rs.getString("userName"));
				manage.setManageId(rs.getString("userId"));
				manage.setManagePwd(rs.getString("userPwd"));
				manage.setManageRole(rs.getInt("userRole"));
				manage.setManageSign(rs.getInt("userSign"));
				return manage;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return null;
	}

	//修改个人信息
	public boolean changeManage(Manage manage) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userName=? where userId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, manage.getManageName());
			ps.setString(2, manage.getManageId());
			//执行sql
			int count = ps.executeUpdate();   //count用于记录是否注册成功
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//修改个人密码
	public boolean changeManagePwd(String id,String pwd,String newpwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userPwd = ? where userId = ? and  userPwd = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, newpwd);
			ps.setString(2, id);
			ps.setString(3, pwd);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//查找所有的公司信息
	public ArrayList<Company> getCompanysList(int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Company> companys = null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select userNumber,userName,userId from companyView";
			}else if(flag == 1) {
				sql = "select userNumber,userName,userId from userTable where userRole = 2 and userSign = 1";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				companys =new ArrayList<Company>();
			}
			while(rs.next()) {
				Company company = new Company();
				company.setCompanyNumber(rs.getInt("userNumber"));
				company.setCompanyName(rs.getString("userName"));
				company.setCompanyId(rs.getString("userId"));
				companys.add(company);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return companys;
	}

	//查找所有的职员信息
	public ArrayList<Staff> getStaffsList(int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Staff> staffs = null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select staffNumber,staffId,staffName,staffCard,staffAddress,staffPhone,staffLearn,staffJob from staffView";
			}else if(flag == 1) {
				sql = "select staffNumber,staffId,staffName,staffCard,staffAddress,staffPhone,staffLearn,staffJob from staffTable where staffSign = 1";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				staffs =new ArrayList<Staff>();
			}
			while(rs.next()) {
				Staff staff = new Staff();
				staff.setStaffNumber(rs.getInt("staffNumber"));
				staff.setStaffId(rs.getString("staffId"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffCard(rs.getString("staffCard"));
				staff.setStaffAddress(rs.getString("staffAddress"));
				staff.setStaffPhone(rs.getString("staffPhone"));
				staff.setStaffLearn(rs.getString("staffLearn"));
				staff.setStaffJob(rs.getString("staffJob"));
				staffs.add(staff);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return staffs;
	}

	//逻辑性删除职员信息
	public boolean deleStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update staffTable set staffSign = ? where staffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, staffNumber);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//永久性删除职员信息
	public boolean deleteStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from staffTable where staffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//恢复职员账号
	public boolean regainStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update staffTable set staffSign = 0 where staffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//逻辑性删除公司账号
	public boolean deleCompany(int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userSign = 1 where userNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//逻辑性删除公司的招聘信息
	public boolean deleCompanyPosition(String companyId) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companyFlag = 1 where companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//永久性删除公司账号
	public boolean deleteCompany(int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from userTable where userNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//永久性删除公司的招聘信息
	public boolean deleteCompanyPosition(String companyId) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from companyTable where companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//恢复公司账号
	public boolean regainCompany(int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userSign = 0 where userNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//恢复公司的招聘信息
	public boolean regainCompanyPosition(String companyId) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companyFlag = 0 where companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

}
