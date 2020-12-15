package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Admin;
import model.List;
import model.User;

public class AdminDao extends BaseDao{

	/**
	 * 管理员登陆
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public Admin login(Admin admin) throws Exception
	{
		Admin resultAdmin = null;
		String sql = "select * from `admin` where admin_id = ? and admin_pwd = ?";
		
		try
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin.getAdmin_id());
			pstmt.setString(2, admin.getAdmin_pwd());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				resultAdmin = new Admin();
				resultAdmin.setAdmin_id(Integer.parseInt(rs.getString("admin_id")));
				resultAdmin.setAdmin_pwd(rs.getString("admin_pwd"));
				resultAdmin.setAdmin_name(rs.getString("admin_name"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultAdmin;
	}
	
	/**
	 * 添加管理员
	 * @param con
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public int addAdmin(Connection con, Admin admin) throws Exception
	{
		String sql = "insert into admin(admin_name, admin_pwd) values (?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, admin.getAdmin_name());
		pstmt.setString(2, admin.getAdmin_pwd());
		pstmt.executeUpdate();
		
		ResultSet rs = pstmt.getGeneratedKeys();   // 获取结果   
		if(rs.next())
		{
			int autoIncKey = rs.getInt(1);   // 取得ID
			System.out.println(autoIncKey);
			return autoIncKey;
		}
		else
		{
			return -1;
		}
	}
}
