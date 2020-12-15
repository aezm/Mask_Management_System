package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;
import model.List;
import model.Shop;
import model.User;
import util.StringUtil;
import util.TimeUtil;

public class UserDao extends BaseDao
{
	/**
	 * �û���¼
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(User user) throws Exception
	{
		User resultUser = null;
		String sql = "select * from `user` where user_phone = ? and user_pwd = ?";
		
		try
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_phone());
			pstmt.setString(2, user.getUser_pwd());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				resultUser = new User();
				resultUser.setUser_phone(rs.getString("user_phone"));
				resultUser.setUser_pwd(rs.getString("user_pwd"));
				resultUser.setUser_name(rs.getString("user_name"));
				resultUser.setId(rs.getString("id"));
				resultUser.setLast_order_time(rs.getLong("last_order_time"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultUser;
	}

	/**
	 * �ж�ԤԼ�ʸ�
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean check(Connection con, User user) throws Exception
	{
		if((user.getLast_order_time() == 0) || ((TimeUtil.getTheTime() - user.getLast_order_time()) > 432000000))   // ����һ��ԤԼ���ܳ���5��(5�� = 432,000,000ms)
		{
			System.out.println(TimeUtil.getTheTime() - user.getLast_order_time());
			return true;
		}
		else
		{
			System.out.println("false");
			return false;
		}
	}
	
	/**
	 * �����û���Ϣ
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateMessage(Connection con, User user) throws Exception
	{
		String sql = "update `user` set user_pwd = ?, user_name = ?, id = ? where user_phone = ? ";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_pwd());
		pstmt.setString(2, user.getUser_name());
		pstmt.setString(3, user.getId());
		pstmt.setString(4, user.getUser_phone());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * �������ԤԼʱ��
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateDate(Connection con, User user) throws Exception
	{
		String sql = "update `user` set last_order_time = (select max(list_date) from list where list_state = '����ȡ' and list_state = '�����') where user_phone = ? ";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_phone());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * ��ѯȫ���û���Ϣ
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con) throws Exception
	{
		String sql = "select * from `user`";

		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	/**
	 * ģ��ƥ�����
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet listSelect(Connection con, User user) throws Exception
	{
		StringBuffer sb = new StringBuffer("select * from `user`");
		if(StringUtil.isNotEmpty(user.getUser_phone()))
		{
			sb.append(" and user_phone like '%" + user.getUser_phone() + "%'");
		}
		if(StringUtil.isNotEmpty(user.getUser_name()))
		{
			sb.append(" and user_name like '%" + user.getUser_name() + "%'");
		}
		if(StringUtil.isNotEmpty(user.getId()))
		{
			sb.append(" and id like '%" + user.getId() + "%'");
		}
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * ɾ��
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, User user) throws Exception
	{
		String sql = "delete from `user` where user_phone = ?";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_phone());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * ���
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int addUser(Connection con, User user) throws Exception
	{
		int f = 1;
		
		String sqls = "select user_phone from `user`";
		PreparedStatement pstmts = con.prepareStatement(sqls);
		ResultSet rss = pstmts.executeQuery(sqls);
		while(rss.next())
		{
			if(rss.getString("user_phone").equals(user.getUser_phone()))
			{
				f = 0;
				break;
			}
		}
		
		if(f == 1)
		{
			String sqli = "insert into `user`(user_phone, user_pwd, user_name, id) values (?, ?, ?, ?)";
			
			PreparedStatement pstmti = con.prepareStatement(sqli);
			pstmti.setString(1, user.getUser_phone());
			pstmti.setString(2, user.getUser_pwd());
			pstmti.setString(3, user.getUser_name());
			pstmti.setString(4, user.getId());
			
			return pstmti.executeUpdate();
		}
		else
		{
			return -1;
		}
	}
	
	/**
	 * ��������
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User ForgetSelect(Connection con, User user) throws Exception
	{
		String sql = "select * from `user` where user_phone = ? and user_name = ? and id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_phone());
		pstmt.setString(2, user.getUser_name());
		pstmt.setString(3, user.getId());
		
		ResultSet rs = pstmt.executeQuery();
		User resultUser = null;
		if(rs.next())
		{
			resultUser = new User();
			resultUser.setUser_phone(rs.getString("user_phone"));
			resultUser.setUser_pwd(rs.getString("user_pwd"));
			resultUser.setUser_name(rs.getString("user_name"));
			resultUser.setId(rs.getString("id"));
			resultUser.setLast_order_time(rs.getLong("last_order_time"));
		}
		
		return resultUser;
	}
	
	/**
	 * ��������
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(Connection con, User user) throws Exception
	{
		String sql = "update `user` set user_pwd = ? where user_phone = ? ";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_pwd());
		pstmt.setString(2, user.getUser_phone());
		
		return pstmt.executeUpdate();
	}
}
