package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.List;
import model.User;

public class MakeDao {

	/**
	 * 组成数据插入
	 * @param con
	 * @param mask_id
	 * @param list_id
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public int addMake(Connection con, int mask_id, int list_id, int num) throws Exception
	{
		String sql = "insert into make(mask_id, list_id, make_num) values (?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, mask_id);
		pstmt.setInt(2, list_id);
		pstmt.setInt(3, num);
		
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询订单详情
	 * @param con
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, List list) throws Exception
	{
		String sql = "select mask_id, type, size, make_num from list_make_mask_view where list_id = " + list.getList_id();
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		return pstmt.executeQuery(sql);
	}
}
