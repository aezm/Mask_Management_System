package util;

import java.sql.*;

public class DBUtil
{
	private String driverName="com.mysql.cj.jdbc.Driver";
	private String dbURL="jdbc:mysql://localhost:3306/mask_manage?useSSL=false&serverTimezone=PRC&useUnicode=true&characterEncoding=utf8";

	private String dbuserName="root";   // �û���
	private String dbuserPwd="Mrs0.0.0225";   // ����

	/**
	 * ��ȡ���ݿ�����
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
	 * �ر����ݿ�����
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
			System.out.println("�������ݿ�ɹ�");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("����ʧ�ܣ�������");
		}
	}
}