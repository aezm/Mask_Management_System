package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Mask;
import model.Shop;
import util.StringUtil;

public class MaskDao {

	/**
	 * 查询领取点库存信息
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Shop shop) throws Exception
	{
		String sql = "select mask_id, type, size, store_num from mask_store_view where shop_id = " + shop.getShop_id();

		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery(sql);
	}
	
	/**
	 * 查询领取点库存口罩编号
	 * @param con
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ResultSet listId(Connection con, Shop shop) throws Exception
	{
		String sql = "select mask_id from mask_store_view where shop_id = " + shop.getShop_id();

		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery(sql);
	}
	
	/**
	 * 查询口罩信息
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ResultSet listMask(Connection con) throws Exception
	{
		String sql = "select * from mask";

		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery(sql);
	}
	
	/**
	 * 更新
	 * @param con
	 * @param mask
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Mask mask) throws Exception
	{
		int f = 0;
		
		String sqls = "select mask_id from mask";
		PreparedStatement pstmts = con.prepareStatement(sqls);
		ResultSet rss = pstmts.executeQuery(sqls);
		while(rss.next())
		{
			if(rss.getInt("mask_id") == mask.getMask_id())
			{
				f = 1;
				break;
			}
		}
		
		if(f == 1)
		{
			String sqlu = "update mask set type = ?, size = ? where mask_id = ? ";
			
			PreparedStatement pstmtu = con.prepareStatement(sqlu);
			pstmtu.setString(1, mask.getType());
			pstmtu.setString(2, mask.getSize());
			pstmtu.setInt(3, mask.getMask_id());
			
			return pstmtu.executeUpdate();
		}
		else
		{
			String sqli = "insert into mask(size, type) values (?, ?)";
			
			PreparedStatement pstmti = con.prepareStatement(sqli);
			pstmti.setString(1, mask.getSize());
			pstmti.setString(2, mask.getType());		
			
			return pstmti.executeUpdate();
		}
	}
}
