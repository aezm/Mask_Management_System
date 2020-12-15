package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import dao.ShopDao;
import model.Mask;
import model.Shop;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import java.awt.Toolkit;

public class ShopManageFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private ShopDao shopDao = new ShopDao();
	
	private JComboBox province_cb;   // 省级下拉框
	private JComboBox city_cb;       // 市级下拉框
	private JComboBox district_cb;   // 区级下拉框
	String value;   // 省级下拉框数据
	
	private JPanel contentPane;
	private JTable shop_table;
	private JTextField id_textField;
	private JTextField name_textField;
	private JTextField phone_textField;
	private JTextField pro_textField;
	private JTextField city_textField;
	private JTextField dis_textField;
	private JTextField detail_textField;
	private JLabel city_Label1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopManageFrame frame = new ShopManageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopManageFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopManageFrame.class.getResource("/images/mask2.png")));
		setTitle("\u9886\u53D6\u70B9\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane shop_scrollPane = new JScrollPane();
		shop_scrollPane.setBounds(50, 142, 712, 198);
		
		JPanel update_panel = new JPanel();
		update_panel.setBounds(50, 353, 712, 158);
		update_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u66F4\u65B0", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		update_panel.setBackground(Color.WHITE);
		
		JButton update_Button = new JButton("\u66F4\u65B0");
		update_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				updateAction();
			}
		});

		update_Button.setBounds(364, 524, 83, 27);

		
		JButton reset_Button = new JButton("\u91CD\u7F6E");
		reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				reset();
			}
		});

		reset_Button.setBounds(193, 524, 83, 27);

		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new AdminFrame().setVisible(true);
			}
		});

		return_Button.setBounds(535, 524, 83, 27);

		update_panel.setLayout(null);
		
		id_textField = new JTextField();
		id_textField.setEditable(false);
		id_textField.setBounds(97, 42, 107, 24);
		update_panel.add(id_textField);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u7F16\u53F7\uFF1A");
		id_Label.setBounds(38, 45, 45, 18);
		update_panel.add(id_Label);
		
		name_textField = new JTextField();
		name_textField.setColumns(10);
		name_textField.setBounds(301, 42, 107, 24);
		update_panel.add(name_textField);
		
		JLabel name_Label = new JLabel("名称：");
		name_Label.setBounds(242, 45, 45, 18);
		update_panel.add(name_Label);
		
		phone_textField = new JTextField();
		phone_textField.setColumns(10);
		phone_textField.setBounds(539, 42, 129, 24);
		update_panel.add(phone_textField);
		
		JLabel phone_Label = new JLabel("联系电话：");
		phone_Label.setBounds(451, 45, 84, 18);
		update_panel.add(phone_Label);
		
		shop_table = new JTable();
		shop_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u540D\u79F0", "\u7701\u4EFD/\u5730\u533A", "\u5E02", "\u53BF/\u533A", "\u8BE6\u7EC6\u5730\u5740", "\u8054\u7CFB\u7535\u8BDD"
			}
		));
		shop_table.getColumnModel().getColumn(1).setPreferredWidth(100);
		shop_table.getColumnModel().getColumn(2).setPreferredWidth(85);
		shop_table.getColumnModel().getColumn(3).setPreferredWidth(85);
		shop_table.getColumnModel().getColumn(4).setPreferredWidth(85);
		shop_table.getColumnModel().getColumn(5).setPreferredWidth(215);
		shop_table.getColumnModel().getColumn(6).setPreferredWidth(94);
		shop_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		shop_table.setBackground(Color.WHITE);
		shop_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//.rowAtPoint();
				int rowAtPoint = shop_table.rowAtPoint(e.getPoint());// 选中行
				fillTextField(rowAtPoint);
			}
		});
		shop_scrollPane.setViewportView(shop_table);
		
		contentPane.setLayout(null);
		contentPane.add(reset_Button);
		contentPane.add(update_Button);
		contentPane.add(return_Button);
		contentPane.add(update_panel);
		
		pro_textField = new JTextField();
		pro_textField.setBounds(97, 102, 73, 24);
		update_panel.add(pro_textField);
		pro_textField.setColumns(10);
		
		city_textField = new JTextField();
		city_textField.setBounds(233, 102, 73, 24);
		update_panel.add(city_textField);
		city_textField.setColumns(10);
		
		dis_textField = new JTextField();
		dis_textField.setBounds(383, 102, 73, 24);
		update_panel.add(dis_textField);
		dis_textField.setColumns(10);
		
		detail_textField = new JTextField();
		detail_textField.setBounds(552, 102, 146, 24);
		update_panel.add(detail_textField);
		detail_textField.setColumns(10);
		
		JLabel pro_Label = new JLabel("省份/地区：");
		pro_Label.setBounds(14, 105, 84, 18);
		update_panel.add(pro_Label);
		
		JLabel city_Label;
		city_Label = new JLabel("市：");
		city_Label.setBounds(194, 105, 37, 18);
		update_panel.add(city_Label);
		
		JLabel dis_Label = new JLabel("县/区：");
		dis_Label.setBounds(321, 105, 59, 18);
		update_panel.add(dis_Label);
		
		JLabel detail_Label = new JLabel("详细地址：");
		detail_Label.setBounds(465, 105, 84, 18);
		update_panel.add(detail_Label);
		contentPane.add(shop_scrollPane);
		contentPane.add(shop_scrollPane);
		
		JPanel search_panel = new JPanel();
		search_panel.setBorder(new TitledBorder(null, "\u67E5\u627E", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		search_panel.setBackground(Color.WHITE);
		search_panel.setBounds(50, 27, 712, 91);
		contentPane.add(search_panel);
		search_panel.setLayout(null);
		
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
		province_cb.setBounds(131, 43, 96, 24);
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
		city_cb.setBounds(287, 43, 96, 24);
		search_panel.add(city_cb);
		
		district_cb = new JComboBox();
		district_cb.setBounds(457, 43, 96, 24);
		search_panel.add(district_cb);
		
		JLabel province_Label = new JLabel("\u7701\u4EFD/\u5730\u533A:");
		province_Label.setBounds(39, 46, 78, 18);
		search_panel.add(province_Label);
		
		JLabel city = new JLabel("\u5E02:");
		city.setBounds(247, 46, 26, 18);
		search_panel.add(city);
		
		JLabel district_Label = new JLabel("\u53BF/\u533A:");
		district_Label.setBounds(397, 46, 49, 18);
		search_panel.add(district_Label);
		
		JButton search_Button = new JButton("\u67E5\u8BE2");
		search_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				shopSearchActionPerformed();
			}
		});
		search_Button.setBounds(601, 42, 78, 27);
		search_panel.add(search_Button);
		
		filltable();
		fillpro();
	}

	private void filltable()
	{
		DefaultTableModel dtm = (DefaultTableModel) shop_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = shopDao.listSelect(con);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("shop_id"));
				v.add(rs.getString("shop_name"));
				v.add(rs.getString("addr_pro"));
				v.add(rs.getString("addr_city"));
				v.add(rs.getString("addr_dis"));
				v.add(rs.getString("addr_detail"));
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
	
	private void reset()
	{
		this.id_textField.setText("");
		this.name_textField.setText("");
		this.phone_textField.setText("");
		this.pro_textField.setText("");
		this.city_textField.setText("");
		this.dis_textField.setText("");
		this.detail_textField.setText("");
	}
	
	private void fillTextField(int rowAtPoint)
	{
		this.id_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 0)));
		this.name_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 1)));
		this.pro_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 2)));
		this.city_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 3)));
		this.dis_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 4)));
		this.detail_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 5)));
		this.phone_textField.setText(((String)this.shop_table.getValueAt(rowAtPoint, 6)));
	}
	
	private void updateAction()
	{
		int shop_id = -1;
		if(StringUtil.isNotEmpty(this.id_textField.getText()))
		{
			shop_id = Integer.parseInt(this.id_textField.getText());
		}
		
		String name;
		if(StringUtil.isNotEmpty(this.name_textField.getText()))
		{
			name = this.name_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入名称！");
			return;
		}
		
		String pro;
		if(StringUtil.isNotEmpty(this.pro_textField.getText()))
		{
			pro = this.pro_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入省份/区域！");
			return;
		}
		
		String city;
		if(StringUtil.isNotEmpty(this.city_textField.getText()))
		{
			city = this.city_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入市！");
			return;
		}
		
		String dis;
		if(StringUtil.isNotEmpty(this.dis_textField.getText()))
		{
			dis = this.dis_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入县/区！");
			return;
		}
		
		String detail;
		if(StringUtil.isNotEmpty(this.detail_textField.getText()))
		{
			detail = this.detail_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入详细地址！");
			return;
		}
		
		String phone;
		if(StringUtil.isNotEmpty(this.phone_textField.getText()))
		{
			phone = this.phone_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入联系电话！");
			return;
		}
		
		Shop shoptemp = new Shop();
		shoptemp.setShop_id(shop_id);
		shoptemp.setShop_name(name);
		shoptemp.setAddr_pro(pro);
		shoptemp.setAddr_city(city);
		shoptemp.setAddr_dis(dis);
		shoptemp.setAddr_detail(detail);
		shoptemp.setShop_phone(phone);
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = shopDao.update(con, shoptemp);
			if(modifyNum == 1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "更新成功！");
				filltable();
				return;
			}
			else
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "更新失败！");
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

	private void shopSearchActionPerformed()
	{
		String pro = (String)this.province_cb.getSelectedItem();
		String city = (String)this.city_cb.getSelectedItem();
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
				v.add(rs.getString("addr_pro"));
				v.add(rs.getString("addr_city"));
				v.add(rs.getString("addr_dis"));
				v.add(rs.getString("addr_detail"));
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
}
