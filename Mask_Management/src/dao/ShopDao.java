package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Mask;
import model.Shop;
import model.Staff;
import util.StringUtil;

public class ShopDao {
	
	/**
	 * 领取点地址查询
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Shop shop) throws Exception
	{
		StringBuffer sb = new StringBuffer("select * from shop");
		if(StringUtil.isNotEmpty(shop.getAddr_pro()))
		{
			sb.append(" and addr_pro = '" + shop.getAddr_pro() + "'");
		}
		if(StringUtil.isNotEmpty(shop.getAddr_city()))
		{
			sb.append(" and addr_city = '" + shop.getAddr_city() + "'");
		}
		if(StringUtil.isNotEmpty(shop.getAddr_dis()))
		{
			sb.append(" and addr_dis = '" + shop.getAddr_dis() + "'");
		}
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * 省级查询
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet list_pro(Connection con, Shop shop) throws Exception
	{
		String sql = "select distinct addr_pro from shop";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	/**
	 * 市级查询
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet list_city(Connection con, Shop shop) throws Exception
	{
		String sql = "select distinct addr_city from shop where addr_pro = '" + shop.getAddr_pro() + "'";   // 这里注意查询条件要用引号括起来
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	/**
	 * 区级查询
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet list_dis(Connection con, Shop shop) throws Exception
	{
		StringBuffer sb = new StringBuffer("select distinct addr_dis from shop");
		if(StringUtil.isNotEmpty(shop.getAddr_pro()))
		{
			sb.append(" and addr_pro = '" + shop.getAddr_pro() + "'");
		}
		if(StringUtil.isNotEmpty(shop.getAddr_city()))
		{
			sb.append(" and addr_city = '" + shop.getAddr_city() + "'");
		}
		
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));		
		return pstmt.executeQuery();
	}

	/**
	 * 领取点信息修改
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public int updateMessage(Connection con, Shop shop) throws Exception
	{
		String sql = "update shop set shop_name = ?, addr_detail = ?, shop_phone = ? where shop_id = ? ";
		
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setString(1, shop.getShop_name());
		pstmt.setString(2, shop.getAddr_detail());
		pstmt.setString(3, shop.getShop_phone());
		pstmt.setInt(4, shop.getShop_id());
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Shop shop) throws Exception
	{
		int f = 0;
		
		String sqls = "select shop_id from shop";
		PreparedStatement pstmts = con.prepareStatement(sqls);
		ResultSet rss = pstmts.executeQuery(sqls);
		while(rss.next())
		{
			if(rss.getInt("shop_id") == shop.getShop_id())
			{
				f = 1;
				break;
			}
		}
		
		if(f == 1)
		{
			String sqlu = "update shop set shop_name = ?, addr_pro = ?, addr_city = ?, addr_dis = ?, addr_detail = ?, shop_phone = ? where shop_id = ? ";
			
			PreparedStatement pstmtu = con.prepareStatement(sqlu);
			pstmtu.setString(1, shop.getShop_name());
			pstmtu.setString(2, shop.getAddr_pro());
			pstmtu.setString(3, shop.getAddr_city());
			pstmtu.setString(4, shop.getAddr_dis());
			pstmtu.setString(5, shop.getAddr_detail());
			pstmtu.setString(6, shop.getShop_phone());
			pstmtu.setInt(7, shop.getShop_id());
			
			return pstmtu.executeUpdate();
		}
		else
		{
			String sqli = "insert into shop(shop_name, addr_pro, addr_city, addr_dis, addr_detail, shop_phone) values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmti = con.prepareStatement(sqli);
			pstmti.setString(1, shop.getShop_name());
			pstmti.setString(2, shop.getAddr_pro());
			pstmti.setString(3, shop.getAddr_city());
			pstmti.setString(4, shop.getAddr_dis());
			pstmti.setString(5, shop.getAddr_detail());
			pstmti.setString(6, shop.getShop_phone());	
			
			return pstmti.executeUpdate();
		}
	}
	
	public ResultSet listSelect(Connection con) throws Exception
	{
		String sql = "select * from shop";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
