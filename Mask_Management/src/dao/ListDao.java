package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.List;
import model.Shop;
import model.User;
import util.StringUtil;

public class ListDao {

	/**
	 * 订单添加
	 * @param con
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int addList(Connection con, List list) throws Exception
	{
		String sql = "insert into list(list_state, list_date) values (?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, list.getList_state());
		pstmt.setLong(2, list.getList_date());
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
	
	/**
	 * 订单信息查询(领取点详细信息版)
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, User user) throws Exception
	{
		String sql = "select * from list_order_shop_view where user_phone = " + user.getUser_phone();
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		return pstmt.executeQuery(sql);
	}
	
	/**
	 * 更新订单状态
	 * @param con
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int updateState(Connection con, List list) throws Exception
	{
		String sql = "update list set list_state = ? where list_id = ? ";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, list.getList_state());
		pstmt.setString(2, list.getList_id() + "");
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 订单信息查询(无详细信息版)
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet listNon(Connection con, Shop shop) throws Exception
	{
		String sql = "select list_id, user_phone, list_state, list_date from list_order_shop_view where list_state = '待领取' and shop_id = " + shop.getShop_id();
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		return pstmt.executeQuery(sql);
	}
	
	/**
	 * 模糊匹配查找
	 * @param con
	 * @param shop
	 * @param user
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ResultSet listSelect(Connection con, Shop shop, User user, List list) throws Exception
	{
		StringBuffer sb = new StringBuffer("select list_id, user_phone, list_state, list_date from list_order_shop_view where list_state = '待领取' and shop_id = " + shop.getShop_id());
		if(StringUtil.isNotEmpty(user.getUser_phone()))
		{
			sb.append(" and user_phone like '%" + user.getUser_phone() + "%'");
		}
		if(list.getList_id() >= 0)
		{
			sb.append(" and list_id like '%" + list.getList_id() + "%'");
		}
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
}
