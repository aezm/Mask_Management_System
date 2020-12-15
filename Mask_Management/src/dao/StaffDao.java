package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Shop;
import model.Staff;
import model.User;

public class StaffDao extends BaseDao{

	/**
	 * 职工登录
	 * @param staff
	 * @return
	 * @throws Exception
	 */
	public Staff login(Staff staff) throws Exception
	{
		Staff resultStaff = null;
		String sql = "select * from staff where staff_id = ? and staff_pwd = ?";
		
		try
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, staff.getStaff_id()+"");   // Int转换为String
			pstmt.setString(2, staff.getStaff_pwd());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				resultStaff = new Staff();
				resultStaff.setStaff_id(rs.getInt("staff_id"));
				resultStaff.setStaff_pwd(rs.getString("staff_pwd"));
				resultStaff.setStaff_name(rs.getString("staff_name"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultStaff;
	}
	
	/**
	 * 职工信息修改
	 * @param con
	 * @param staff
	 * @return
	 * @throws Exception
	 */
	public int updateMessage(Connection con, Staff staff) throws Exception
	{
		String sql = "update staff set staff_pwd = ?, staff_name = ? where staff_id = ? ";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, staff.getStaff_pwd());
		pstmt.setString(2, staff.getStaff_name());
		pstmt.setInt(3, staff.getStaff_id());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查找职工所工作的领取点
	 * @param con
	 * @param staff
	 * @return
	 * @throws Exception
	 */
	public Shop findshop(Connection con, Staff staff) throws Exception
	{
		Shop resultShop = null;
		String sql = "select shop.shop_id, shop_name, addr_pro, addr_city, addr_dis, addr_detail, shop_phone from work, shop, staff where staff.staff_id = ? and staff.staff_id = work.staff_id and work.shop_id = shop.shop_id";
		
		try
		{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, staff.getStaff_id());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				resultShop = new Shop();
				resultShop.setShop_id(rs.getInt("shop_id"));
				resultShop.setShop_name(rs.getString("shop_name"));
				resultShop.setAddr_pro(rs.getString("addr_pro"));
				resultShop.setAddr_city(rs.getString("addr_city"));
				resultShop.setAddr_dis(rs.getString("addr_dis"));
				resultShop.setAddr_detail(rs.getString("addr_detail"));
				resultShop.setShop_phone(rs.getString("shop_phone"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultShop;		
	}
	
	/**
	 * 添加职工以及相关工作信息
	 * @param con
	 * @param staff
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Staff staff, Shop shop) throws Exception
	{
		// 添加职工
		String sqls = "insert staff(staff_pwd, staff_name) values (?, ?)";
		
		PreparedStatement pstmts = con.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
		pstmts.setString(1, staff.getStaff_pwd());
		pstmts.setString(2, staff.getStaff_name());
		pstmts.executeUpdate();
		
		int autoIncKey = -1;
		ResultSet rs = pstmts.getGeneratedKeys();   // 获取结果   
		if(rs.next())
		{
			autoIncKey = rs.getInt(1);   // 取得ID
		}
		
		// 添加工作信息
		if(autoIncKey != -1)
		{
			String sqlw = "insert work(staff_id, shop_id) values (?, ?)";
			PreparedStatement pstmtw = con.prepareStatement(sqlw);
			pstmtw.setInt(1, autoIncKey);
			pstmtw.setInt(2, shop.getShop_id());
			int f = pstmtw.executeUpdate();
			if(f == 1)
			{
				return autoIncKey;
			}
			else
			{
				return -1;
			}
		}
		else
		{
			return -1;
		}
	}
}
