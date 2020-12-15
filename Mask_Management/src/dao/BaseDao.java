package dao;

import java.sql.Connection;
import java.sql.SQLException;

import util.DBUtil;

public class BaseDao
{
	// 数据库连接
	public Connection con = new DBUtil().getCon();


	/**
	 * 关闭数据库连接
	 */
	public void closeDao() {
		try {
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
