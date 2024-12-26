package src.jobSystem.com.jobs.companyDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.jobSystem.com.jobs.util.Company;
import src.jobSystem.com.jobs.util.JDBCUtil;
import src.jobSystem.com.jobs.util.Position;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.ClientPreparedStatement;


public class CompanyDao {

	//登陆方法
	public Company companyLanding(String id,String pwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "select * from userTable where userRole=2 and userId=? and userPwd=?";
			//创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？占位符赋值
			ps.setString(1, id);
			ps.setString(2, pwd);
			//执行sql 返回结果集
			rs = ps.executeQuery();
			//查找账户、密码和权限是否正确
			if(rs.next()) {
				Company company = new Company();         //新建公司单位实例，用于存放登陆实例
				company.setCompanyName(rs.getString("userName"));
				company.setCompanyId(rs.getString("userId"));
				company.setCompanyPwd(rs.getString("userPwd"));
				company.setCompanyRole(rs.getInt("userRole"));
				company.setCompanySign(rs.getInt("userSign"));
				return company;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return null;
	}

	//查找注册ID是否存在
	public boolean selectID(String id) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "select userId from userTable where userId = ?";
			//创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给sql参数赋值
			ps.setString(1, id);
			//执行sql 返回结果集
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//公司单位注册
	public boolean companyLogin(String Name,String Id,String Pwd,int Role) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into userTable(userName,userId,userPwd,userRole,userSign) values(?,?,?,?,?)";
			//预编译语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setString(1, Name);
			ps.setString(2, Id);
			ps.setString(3, Pwd);
			ps.setInt(4, Role);
			ps.setInt(5, 0);
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

	//查找的全部招聘信息 或 根据条件查找
	public ArrayList<Position> getPositionList(src.jobSystem.com.jobs.companyDao.CompanyPageModel companyPageModel) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			if(companyPageModel.getSearchText()!=null && !companyPageModel.getSearchText().equals("")) {
				String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyPosition like ? or companyRequest like ? limit ?,? ";
				//创建ClientPreparedStatement语句对象
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, "%"+companyPageModel.getSearchText()+"%");
				ps.setString(2, "%"+companyPageModel.getSearchText()+"%");
				ps.setInt(3, (companyPageModel.getCompanyPageNo()-1)*companyPageModel.getCompanyPageSize());
				ps.setInt(4, companyPageModel.getCompanyPageSize());
			}else {
				String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView limit ?,? ";
				//创建ClientPreparedStatement语句对象
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, (companyPageModel.getCompanyPageNo()-1)*companyPageModel.getCompanyPageSize());
				ps.setInt(2, companyPageModel.getCompanyPageSize());
			}
			//执行sql 返回结果集
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
				position.setCompanyNumber(rs.getInt("companyNumber"));
				position.setCompanyName(rs.getString("companyName"));
				position.setCompanyPosition(rs.getString("companyPosition"));
				position.setCompanyPerson(rs.getString("companyPerson"));
				position.setCompanyPhone(rs.getString("companyPhone"));
				position.setCompanyCount(rs.getString("companyCount"));
				position.setCompanyLearn(rs.getString("companyLearn"));
				position.setCompanyRequest(rs.getString("companyRequest"));
				position.setCompanyMoney(rs.getString("companyMoney"));
				position.setCompanyAddress(rs.getString("companyAddress"));
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//查找一共有多少条招聘信息 或 根据条件查找一共有多少条招聘信息
	public long getPositionTotal(String searchText) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		long totalCount = 0;
		try {
			conn = JDBCUtil.getConnection();
			//准备sql语句，分情况执行sql语句
			if(searchText != null && !searchText.equals("")) {
				String sql = "select count(companyNumber) from PositionView where companyPosition like ? or companyRequest like ?";
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, "%"+searchText+"%");
				ps.setString(2, "%"+searchText+"%");
			}else {
				String sql = "select count(companyNumber) from PositionView";
				ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				totalCount = rs.getLong(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return totalCount;
	}

	//查找某个特定的公司发布的招聘信息 或 删除的招聘信息，按公司id查找  flag=0 发布的招聘信息，flag=1 删除的招聘信息
	public ArrayList<Position> getPutPositionList(String companyId,int flag){
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		String sql = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			if(flag==0) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyId = ?";
			}else if(flag==1) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from companyTable where companyId = ? and companySign = 1";
			}
			//创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setString(1, companyId);
			//执行sql 返回结果集
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
				position.setCompanyNumber(rs.getInt("companyNumber"));
				position.setCompanyName(rs.getString("companyName"));
				position.setCompanyPosition(rs.getString("companyPosition"));
				position.setCompanyPerson(rs.getString("companyPerson"));
				position.setCompanyPhone(rs.getString("companyPhone"));
				position.setCompanyCount(rs.getString("companyCount"));
				position.setCompanyLearn(rs.getString("companyLearn"));
				position.setCompanyRequest(rs.getString("companyRequest"));
				position.setCompanyMoney(rs.getString("companyMoney"));
				position.setCompanyAddress(rs.getString("companyAddress"));
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//按编号查询招聘信息
	public Position findPutPositionMessageByNumber(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		Position position = null;   //标记按编号查询的招聘信息是否存在
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyId = ? and companyNumber = ?";
			//创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setString(1, companyId);
			ps.setInt(2, companyNumber);
			//执行sql 返回结果集
			rs = ps.executeQuery();
			while(rs.next()) {
				position = new Position();
				position.setCompanyNumber(rs.getInt("companyNumber"));
				position.setCompanyName(rs.getString("companyName"));
				position.setCompanyPosition(rs.getString("companyPosition"));
				position.setCompanyPerson(rs.getString("companyPerson"));
				position.setCompanyPhone(rs.getString("companyPhone"));
				position.setCompanyCount(rs.getString("companyCount"));
				position.setCompanyLearn(rs.getString("companyLearn"));
				position.setCompanyRequest(rs.getString("companyRequest"));
				position.setCompanyMoney(rs.getString("companyMoney"));
				position.setCompanyAddress(rs.getString("companyAddress"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return position;
	}

	//查找某个特定的公司发布的招聘信息 被投递 或 邀请职员 的统计情况  flag=0 被投递的招聘信息，flag=1 邀请职员的招聘信息
	public ArrayList<Position> getPositionSumMessageList(String companyId,int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag==0) {
				sql = "select companyNumber ,companyName ,companyPosition ,companyPerson ,companyPhone ,companyCount ,companyLearn ,companyRequest ,companyMoney ,companyAddress ,staffSum from PositionView join ( select PcompanyNumber ,count(PcompanyNumber) as staffSum from putTable group by PcompanyNumber ) t on companyNumber = PcompanyNumber and companyId = ?";
			}else if(flag==1) {
				sql = "select companyNumber ,companyName ,companyPosition ,companyPerson ,companyPhone ,companyCount ,companyLearn ,companyRequest ,companyMoney ,companyAddress ,staffSum from PositionView join ( select IcompanyNumber ,count(IcompanyNumber ) as staffSum from invitionTable group by IcompanyNumber  ) t on companyNumber = IcompanyNumber  and companyId = ?";
			}else if(flag==2) {
				sql = "select companyNumber ,companyName ,companyPosition ,companyPerson ,companyPhone ,companyCount ,companyLearn ,companyRequest ,companyMoney ,companyAddress ,staffSum from PositionView join ( select EcompanyNumber ,count(EcompanyNumber ) as staffSum from employTable group by EcompanyNumber  ) t on companyNumber = EcompanyNumber  and companyId = ?";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, companyId);
			//执行sql 返回结果集
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
				position.setCompanyNumber(rs.getInt("companyNumber"));
				position.setCompanyName(rs.getString("companyName"));
				position.setCompanyPosition(rs.getString("companyPosition"));
				position.setCompanyPerson(rs.getString("companyPerson"));
				position.setCompanyPhone(rs.getString("companyPhone"));
				position.setCompanyCount(rs.getString("companyCount"));
				position.setCompanyLearn(rs.getString("companyLearn"));
				position.setCompanyRequest(rs.getString("companyRequest"));
				position.setCompanyMoney(rs.getString("companyMoney"));
				position.setCompanyAddress(rs.getString("companyAddress"));
				position.setStaffSum(rs.getInt("staffSum"));
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//查找某个特定职员投递的招聘信息
	public ArrayList<Position> getPutStaffList(int staffNumber,int flag) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Position> positions =null;
		String sql = null;
		try {
			conn = JDBCUtil.getConnection();
			if(flag == 0) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyNumber in (select PcompanyNumber from putTable where PstaffNumber = ?)";
			}else if(flag == 1) {
				sql = "select companyNumber,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress from PositionView where companyNumber in (select IcompanyNumber from invitionTable where IstaffNumber = ?)";
			}
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			rs = ps.executeQuery();
			if(!rs.wasNull()) {
				positions =new ArrayList<Position>();
			}
			while(rs.next()) {
				Position position = new Position();
				position.setCompanyNumber(rs.getInt("companyNumber"));
				position.setCompanyName(rs.getString("companyName"));
				position.setCompanyPosition(rs.getString("companyPosition"));
				position.setCompanyPerson(rs.getString("companyPerson"));
				position.setCompanyPhone(rs.getString("companyPhone"));
				position.setCompanyCount(rs.getString("companyCount"));
				position.setCompanyLearn(rs.getString("companyLearn"));
				position.setCompanyRequest(rs.getString("companyRequest"));
				position.setCompanyMoney(rs.getString("companyMoney"));
				position.setCompanyAddress(rs.getString("companyAddress"));
				positions.add(position);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return positions;
	}

	//修改个人信息
	public boolean changeCompanyMessage(Company company) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update userTable set userName=? where userId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, company.getCompanyName());
			ps.setString(2, company.getCompanyId());
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
	public boolean changeCompanyPwd(String id,String pwd,String newpwd) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//1、获取数据库连接
			conn = JDBCUtil.getConnection();
			//2、准备sql语句
			String sql = "update userTable set userPwd = ? where userId = ? and  userPwd = ?";
			//3、创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//4、给sql参数赋值
			ps.setString(1, newpwd);
			ps.setString(2, id);
			ps.setString(3, pwd);
			//5、执行sql
			int count = ps.executeUpdate();
			if(count != 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//6、释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//发布招聘信息
	public boolean addPositionMessage(Position position) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "insert into companyTable (companyId,companyName,companyPosition,companyPerson,companyPhone,companyCount,companyLearn,companyRequest,companyMoney,companyAddress,companySign,companyFlag) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			//预编译语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setString(1, position.getCompanyId());
			ps.setString(2, position.getCompanyName());
			ps.setString(3, position.getCompanyPosition());
			ps.setString(4, position.getCompanyPerson());
			ps.setString(5, position.getCompanyPhone());
			ps.setString(6, position.getCompanyCount());
			ps.setString(7, position.getCompanyLearn());
			ps.setString(8, position.getCompanyRequest());
			ps.setString(9, position.getCompanyMoney());
			ps.setString(10, position.getCompanyAddress());
			ps.setInt(11, position.getCompanySign());
			ps.setInt(12, position.getCompanyFlag());
			//执行sql
			int counts = ps.executeUpdate();   //count用于记录是否发布成功
			if(counts > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//逻辑性删除招聘信息
	public boolean delePositionMessage(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companySign = ? where companyNumber = ? and  companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, companyNumber);
			ps.setString(3, companyId);
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

	//永久性删除招聘信息
	public boolean deletePositionMessage(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "delete from companyTable where companyId = ? and companyNumber = ?";
			//创建ClientPreparedStatement语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给sql参数赋值
			ps.setString(1, companyId);
			ps.setInt(2, companyNumber);
			//执行sql
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//修改招聘信息
	public boolean changePositionMessage(Position position) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "update companyTable set companyName=?,companyPosition=?,companyPerson=?,companyPhone=?,companyCount=?,companyLearn=?,companyRequest=?,companyMoney=?,companyAddress=? where companyId = ? and companyNumber = ?";
			//预编译语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setString(1, position.getCompanyName());
			ps.setString(2, position.getCompanyPosition());
			ps.setString(3, position.getCompanyPerson());
			ps.setString(4, position.getCompanyPhone());
			ps.setString(5, position.getCompanyCount());
			ps.setString(6, position.getCompanyLearn());
			ps.setString(7, position.getCompanyRequest());
			ps.setString(8, position.getCompanyMoney());
			ps.setString(9, position.getCompanyAddress());
			ps.setString(10, position.getCompanyId());
			ps.setInt(11, position.getCompanyNumber());
			//执行sql
			int counts = ps.executeUpdate();
			if(counts > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//恢复招聘信息
	public boolean regainPositionMessage(String companyId,int companyNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update companyTable set companySign = ? where companyNumber = ? and  companyId = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, companyNumber);
			ps.setString(3, companyId);
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

	//查找是否重复邀请职员
	public boolean selectInvitionMessage(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select IstaffNumber from invitionTable where IcompanyNumber = ? and IstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			rs = ps.executeQuery();
			//判断录用的信息是否存在
			while(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//按职员的编号邀请职员面试
	public boolean invitionStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			//获取数据库连接
			conn = JDBCUtil.getConnection();
			//准备sql语句
			String sql = "insert into invitionTable(IcompanyNumber ,IstaffNumber ) values(?,?)";
			//预编译语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//执行sql
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//按职员的编号删除邀请信息
	public boolean deleteInvitionStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from invitionTable where IcompanyNumber = ? and IstaffNumber = ?";
			//预编译语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//执行sql
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//查找是否重复录用职员
	public boolean selectEmployMessage(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select EstaffNumber from employTable where EcompanyNumber = ? and EstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			rs = ps.executeQuery();
			//判断录用的信息是否存在
			while(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//查找该职员是否被其它公司录用
	public boolean selectEmployStaff(int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select EstaffNumber from employTable where EstaffNumber = ?";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, staffNumber);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//按职员的编号招聘录用
	public boolean employStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into employTable(EcompanyNumber ,EstaffNumber ) values(?,?)";
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//执行sql
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

	//按职员的编号删除录用信息
	public boolean delStaff(int companyNumber,int staffNumber) {
		Connection conn = null;
		ClientPreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from employTable where EcompanyNumber = ? and EstaffNumber = ?";
			//预编译语句对象
			ps = (ClientPreparedStatement) conn.prepareStatement(sql);
			//给？号赋值
			ps.setInt(1, companyNumber);
			ps.setInt(2, staffNumber);
			//执行sql
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConn((com.mysql.cj.jdbc.ConnectionImpl)conn, ps, null);
		}
		return false;
	}

}

