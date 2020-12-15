package util;

import java.sql.*;

public class DBUtil
{
	private String driverName="com.mysql.cj.jdbc.Driver";
	private String dbURL="jdbc:mysql://localhost:3306/mask_manage?useSSL=false&serverTimezone=PRC&useUnicode=true&characterEncoding=utf8";

	private String dbuserName="root";   // 用户名
	private String dbuserPwd="Mrs0.0.0225";   // 密码

	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()
	{
		try
		{
			Class.forName(driverName);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Connection con = null;
		
		try
		{
			con = DriverManager.getConnection(dbURL, dbuserName, dbuserPwd);
		}
		catch(SQLException e)
		{	
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭数据库连接
	 * @param dbCon
	 * @throws Exception
	 */
	public void closeCon(Connection dbCon) throws Exception
	{
		if(dbCon != null)
		{
			dbCon.close();
		}
	}
	
	public static void main(String [] args)
	{
		DBUtil con = new DBUtil();

		try
		{
			con.getCon();			
			System.out.println("连接数据库成功");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("连接失败，请重试");
		}
	}
}