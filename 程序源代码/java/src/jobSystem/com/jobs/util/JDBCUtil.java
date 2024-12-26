package src.jobSystem.com.jobs.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;

public class JDBCUtil {

	//数据库驱动名
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	/*URL
	 * jdbc:mysql:协议
	 * localhost:本地数据库
	 * 3306端口号
	 * job:数据库名
	 */
	public static final String URL = "jdbc:mysql://localhost:3306/jobsdb";
	//用户名
	public static final String USERNAME = "root";
	//密码
	public static final String PASSWORD = "root123";

	//静态代码块，每次运行时，首先会加载此代码
	static {
		try {
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//提供一个获取连接方法
	public static ConnectionImpl getConnection() {
		ConnectionImpl conn = null;
		try {
			conn = (ConnectionImpl) DriverManager.getConnection(URL,USERNAME,PASSWORD);

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//提供一个释放连接方法
	public static void closeConn(ConnectionImpl conn, ClientPreparedStatement ps , ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
