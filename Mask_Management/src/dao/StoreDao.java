package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Mask;
import model.Shop;
import model.Store;

public class StoreDao {
	
	/**
	 * 控制预约数量
	 * @param con
	 * @param shop
	 * @param mask_id
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public boolean limit(Connection con, Shop shop, int mask_id, int num) throws Exception
	{
		String sql = "select store_num from mask_store_view where shop_id = " + shop.getShop_id() + " and mask_id = " + mask_id;
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		while(rs.next())
		{
			if(rs.getInt("store_num") < num)
			{
				return false;
			}
			else
			{ 
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 更新库存数量(加减)
	 * @param con
	 * @param num
	 * @param shop
	 * @param mask
	 * @return
	 * @throws Exception
	 */
	public int updateStore_num(Connection con, int num, Shop shop, int mask_id) throws Exception
	{
		String sql = "update store set store_num = store_num + ? where shop_id = ? and mask_id = ? ";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setInt(2, shop.getShop_id());
		pstmt.setInt(3, mask_id);
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新库存(可插入新类型口罩)
	 * @param con
	 * @param num
	 * @param shop
	 * @param mask_id
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, int num, Shop shop, int mask_id) throws Exception
	{
		int f = 0;
		
		String sqls = "select mask_id from `store` where shop_id = " + shop.getShop_id();
		PreparedStatement pstmts = con.prepareStatement(sqls);
		ResultSet rss = pstmts.executeQuery(sqls);
		while(rss.next())
		{
			if(rss.getInt("mask_id") == mask_id)
			{
				f = 1;
				break;
			}
		}
		
		if(f == 1)
		{
			String sqlu = "update store set store_num = store_num + ? where shop_id = ? and mask_id = ? ";
			
			PreparedStatement pstmtu = con.prepareStatement(sqlu);
			pstmtu.setInt(1, num);
			pstmtu.setInt(2, shop.getShop_id());
			pstmtu.setInt(3, mask_id);
			
			return pstmtu.executeUpdate();
		}
		else
		{
			String sqli = "insert into store values (?, ?, ?)";
			
			PreparedStatement pstmti = con.prepareStatement(sqli);
			pstmti.setInt(1, shop.getShop_id());
			pstmti.setInt(2, mask_id);
			pstmti.setInt(3, num);			
			
			return pstmti.executeUpdate();
		}
	}
}
