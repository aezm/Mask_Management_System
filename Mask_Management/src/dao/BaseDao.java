package dao;

import java.sql.Connection;
import java.sql.SQLException;

import util.DBUtil;

public class BaseDao
{
	// ���ݿ�����
	public Connection con = new DBUtil().getCon();


	/**
	 * �ر����ݿ�����
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
