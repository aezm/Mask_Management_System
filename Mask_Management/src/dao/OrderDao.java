package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.List;
import model.Shop;
import model.User;
import util.TimeUtil;

public class OrderDao {

	public int addOrder(Connection con, User user, Shop shop, List list) throws Exception
	{
		String sql = "insert into `order` values (?, ?, ?)";   // order是mysql保留字，需要加反引号加以区分
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUser_phone());
		pstmt.setInt(2, shop.getShop_id());
		pstmt.setInt(3, list.getList_id());

		return pstmt.executeUpdate();
	}
}
