package view;
import util.DBUtil;
import util.FontUtil;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MaskDao;
import dao.ShopDao;
import dao.UserDao;
import model.Mask;
import model.Shop;
import model.User;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;

public class MaskOrderFrame extends JFrame {

	private JPanel orderPane;
	private JTable shop_table;
	
	private JComboBox province_cb;   // 省级下拉框
	private JComboBox city_cb;       // 市级下拉框
	private JComboBox district_cb;   // 区级下拉框
	String value;   // 省级下拉框数据
	
	private DBUtil dbUtil = new DBUtil();
	private Shop shop = new Shop();
	private ShopDao shopDao = new ShopDao();
	private Mask mask = new Mask();
	private MaskDao maskDao = new MaskDao();
	private UserDao userDao = new UserDao();
	private JTable mask_table;
 
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MaskOrderFrame frame = new MaskOrderFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**
	 * Create the frame.
	 */
	public MaskOrderFrame(User user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MaskOrderFrame.class.getResource("/images/mask2.png")));
		setTitle("口罩预约");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 717);
		this.setLocationRelativeTo(null);   // 窗体居中
		orderPane = new JPanel();
		orderPane.setBackground(Color.WHITE);
		orderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(orderPane);
		
		JScrollPane shop_scrollPane = new JScrollPane();
		
		JPanel search_panel = new JPanel();
		search_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		search_panel.setToolTipText("");
		search_panel.setBackground(Color.WHITE);
		
		JScrollPane mask_scrollPane = new JScrollPane();
		
		JButton confirm_Button = new JButton("\u9884\u7EA6");
		confirm_Button.addActionListener(new ActionListener() {
			
			// 弹出预约具体信息窗体，传递参数shop, user(获取shop_id, user_phone)
			public void actionPerformed(ActionEvent evt) {
				//System.out.println(shop.getShop_id());
				
				Connection con = null;
				try
				{
					con = dbUtil.getCon();
					if(userDao.check(con, user))
					{
						new OrderFrame(shop, user).setVisible(true);		
					}
					else
					{
						// 设置按钮显示效果
						UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
						// 设置文本显示效果
						UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
						// 消息对话框
						JOptionPane.showMessageDialog(null, "预约过于频繁！");
						return;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("-在库口罩-");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JButton refresh_Button = new JButton("\u91CD\u7F6E");
		refresh_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				// 初始化领取点表格
				filltable(new Shop());
			}
		});
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				// 退出当前页面
				dispose();
				new UserFrame(user).setVisible(true);
			}
		});
		GroupLayout gl_orderPane = new GroupLayout(orderPane);
		gl_orderPane.setHorizontalGroup(
			gl_orderPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPane.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_orderPane.createParallelGroup(Alignment.LEADING)
						.addComponent(shop_scrollPane, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
						.addComponent(search_panel, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
						.addGroup(gl_orderPane.createSequentialGroup()
							.addComponent(mask_scrollPane, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addGroup(gl_orderPane.createParallelGroup(Alignment.LEADING)
								.addComponent(confirm_Button, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(return_Button, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(refresh_Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
					.addGap(63))
				.addGroup(gl_orderPane.createSequentialGroup()
					.addGap(342)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(508, Short.MAX_VALUE))
		);
		gl_orderPane.setVerticalGroup(
			gl_orderPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_orderPane.createSequentialGroup()
					.addGap(22)
					.addComponent(search_panel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(shop_scrollPane, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_orderPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(mask_scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_orderPane.createSequentialGroup()
							.addGap(16)
							.addComponent(confirm_Button, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addComponent(return_Button, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(12)))
					.addGap(51))
		);
		
		mask_table = new JTable();
		mask_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7C7B\u578B", "\u578B\u53F7", "\u6570\u91CF"
			}
		));
		mask_table.getColumnModel().getColumn(0).setPreferredWidth(80);
		mask_table.getColumnModel().getColumn(1).setPreferredWidth(135);
		mask_table.getColumnModel().getColumn(2).setPreferredWidth(135);
		mask_table.getColumnModel().getColumn(3).setPreferredWidth(80);
		mask_table.setBackground(Color.WHITE);
		mask_table.setRowHeight(25);   // 设置行高
		//mask_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		mask_scrollPane.setViewportView(mask_table);
		search_panel.setLayout(null);

		
		JLabel province_Label = new JLabel("\u7701\u4EFD/\u5730\u533A:");
		province_Label.setBounds(59, 35, 78, 18);
		search_panel.add(province_Label);
		
		JLabel city_Label = new JLabel("\u5E02:");
		city_Label.setBounds(299, 35, 26, 18);
		search_panel.add(city_Label);
		
		JLabel district_Label = new JLabel("\u53BF/\u533A:");
		district_Label.setBounds(483, 35, 49, 18);
		search_panel.add(district_Label);
		
		JButton search_Button = new JButton("\u67E5\u8BE2");
		search_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				shopSearchActionPerformed();
			}
		});
		search_Button.setBounds(700, 31, 78, 27);
		search_panel.add(search_Button);
		
		province_cb = new JComboBox();
		province_cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt_pro)
			{
				// 根据省级下拉框选择的变化动态更新市级下拉框
				if(evt_pro.getStateChange() == ItemEvent.SELECTED)
				{
					city_cb.removeAllItems();
					district_cb.removeAllItems();
					value = province_cb.getSelectedItem().toString();
					fillcity(value);
				}
			}
		});
		province_cb.setBounds(138, 32, 120, 24);
		search_panel.add(province_cb);
		
		city_cb = new JComboBox();
		city_cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt_city)
			{
				// 根据市级下拉框选择的变化动态更新区级下拉框
				if(evt_city.getStateChange() == ItemEvent.SELECTED)
				{
					district_cb.removeAllItems();
					String city = city_cb.getSelectedItem().toString();
					filldis(value, city);
				}				
			}
		});
		city_cb.setBounds(329, 32, 120, 24);
		search_panel.add(city_cb);
		
		district_cb = new JComboBox();
		district_cb.setBounds(534, 32, 120, 24);
		search_panel.add(district_cb);
		
		shop_table = new JTable();
		shop_table.setBackground(Color.WHITE);
		shop_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u540D\u79F0", "\u5730\u5740", "\u8054\u7CFB\u7535\u8BDD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		shop_table.getColumnModel().getColumn(0).setPreferredWidth(55);
		shop_table.getColumnModel().getColumn(1).setPreferredWidth(159);
		shop_table.getColumnModel().getColumn(2).setPreferredWidth(364);
		shop_table.getColumnModel().getColumn(3).setPreferredWidth(150);
		shop_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		shop_scrollPane.setViewportView(shop_table);
		orderPane.setLayout(gl_orderPane);
		// 获取领取点表格选中行的值并填充在库口罩表
		shop_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//.rowAtPoint();
				int rowAtPoint = shop_table.rowAtPoint(e.getPoint());// 选中行
				shop.setShop_id(Integer.parseInt((String) shop_table.getValueAt(rowAtPoint, 0)));   // 需要强制类型转换一下(Object->String->Int)
				// System.out.println(shop_table.getValueAt(rowAtPoint, 0));
				// System.out.println("*方法二:\t" + table.getValueAt(rowrowAtPoint, 0) + "\t" + table.getValueAt(row, 1));
				fillmask(shop);
			}
		});
		
		this.fillpro();
		this.filltable(new Shop());
	}
	
	/**
	 * 搜索领取点并填充领取点表格
	 */
	private void shopSearchActionPerformed() {
		// TODO Auto-generated method stub

		String pro = (String)this.province_cb.getSelectedItem();
		String city =(String)this.city_cb.getSelectedItem();
		String dis = (String)this.district_cb.getSelectedItem();		
		if("请选择...".equals(pro))
		{
			return;
		}
		
		DefaultTableModel dtm = (DefaultTableModel) shop_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Shop shop = new Shop();
		shop.setAddr_pro(pro);
		shop.setAddr_city(city);
		shop.setAddr_dis(dis);
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = shopDao.list(con, shop);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("shop_id"));
				v.add(rs.getString("shop_name"));
				v.add(rs.getString("addr_pro") + rs.getString("addr_city") + rs.getString("addr_dis") + rs.getString("addr_detail"));
				v.add(rs.getString("shop_phone"));
				dtm.addRow(v);				
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}

	/**
	 * 初始化表格数据
	 * @param shop
	 */
	private void filltable(Shop shop)
	{
		DefaultTableModel dtm = (DefaultTableModel) shop_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = shopDao.list(con, shop);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("shop_id"));
				v.add(rs.getString("shop_name"));
				v.add(rs.getString("addr_pro") + rs.getString("addr_city") + rs.getString("addr_dis") + rs.getString("addr_detail"));
				v.add(rs.getString("shop_phone"));
				dtm.addRow(v);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 填充省级下拉框
	 */
	private void fillpro()
	{
		Connection con = null;
		Shop shopTemp = null;
		
		try
		{
			con = dbUtil.getCon();
			
			shopTemp = new Shop();
			shopTemp.setAddr_pro("请选择...");
			shopTemp.setShop_id(-1);
			this.province_cb.addItem(shopTemp.getAddr_pro());
				
			ResultSet rs = shopDao.list_pro(con, shopTemp);
			while(rs.next())
			{
				shopTemp = new Shop();
				shopTemp.setAddr_pro(rs.getString("addr_pro"));
				this.province_cb.addItem(shopTemp.getAddr_pro());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				dbUtil.closeCon(con);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 填充市级下拉框
	 * @param pro
	 */
	private void fillcity(String pro)
	{
		Connection con = null;
		Shop shopTemp = null;
		
		try
		{
			con = dbUtil.getCon();
			
			shopTemp = new Shop();
			shopTemp.setAddr_pro(pro);
			
			ResultSet rs = shopDao.list_city(con, shopTemp);
			while(rs.next())
			{
				shopTemp = new Shop();
				shopTemp.setAddr_city(rs.getString("addr_city"));
				this.city_cb.addItem(shopTemp.getAddr_city());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtil.closeCon(con);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 填充区级下拉框
	 * @param pro
	 * @param city
	 */
	private void filldis(String pro, String city)
	{
		Connection con = null;
		Shop shopTemp = null;
		
		try
		{
			con = dbUtil.getCon();
			
			shopTemp = new Shop();				
			shopTemp.setAddr_pro(pro);
			shopTemp.setAddr_city(city);
			
			ResultSet rs = shopDao.list_dis(con, shopTemp);
			while(rs.next())
			{
				shopTemp = new Shop();
				shopTemp.setAddr_dis(rs.getString("addr_dis"));
				this.district_cb.addItem(shopTemp.getAddr_dis());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtil.closeCon(con);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 填充在库口罩数据
	 * @param shop
	 */
	private void fillmask(Shop shop)
	{
		DefaultTableModel dtm = (DefaultTableModel) mask_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = maskDao.list(con, shop);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("mask_id"));
				v.add(rs.getString("type"));
				v.add(rs.getString("size"));
				v.add(rs.getString("store_num"));
				dtm.addRow(v);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

	
