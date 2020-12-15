package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ListDao;
import dao.MakeDao;
import dao.ShopDao;
import dao.StaffDao;
import model.List;
import model.Shop;
import model.Staff;
import model.User;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;

public class FinishListFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private Shop shop = new Shop();
	private List list = new List();
	private ListDao listDao = new ListDao();
	private StaffDao staffDao = new StaffDao();
	private MakeDao makeDao = new MakeDao();
	
	private JPanel contentPane;
	private JTextField list_textField;
	private JTextField user_textField;
	private JTable list_table;
	private JTable mask_table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FinishListFrame frame = new FinishListFrame();
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
	public FinishListFrame(Staff staff) {
		setTitle("订单领取");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FinishListFrame.class.getResource("/images/mask2.png")));
		
		// 获取职工工作的领取点信息
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			shop = staffDao.findshop(con, staff);
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 717);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(99, 68, 735, 90);
		panel.setBorder(new TitledBorder(null, "\u67E5\u627E", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		
		JScrollPane list_scrollPane = new JScrollPane();
		list_scrollPane.setBounds(99, 206, 735, 205);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new StaffFrame(staff).setVisible(true);
			}
		});
		return_Button.setBounds(668, 562, 101, 32);
		
		list_table = new JTable();
		list_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BA2\u5355\u53F7", "\u7528\u6237\u8D26\u53F7", "\u8BA2\u5355\u65E5\u671F", "\u8BA2\u5355\u72B6\u6001"
			}
		));
		list_table.getColumnModel().getColumn(1).setPreferredWidth(100);
		list_table.getColumnModel().getColumn(2).setPreferredWidth(100);
		list_table.getColumnModel().getColumn(3).setPreferredWidth(100);
		list_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		list_scrollPane.setViewportView(list_table);
		list_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//.rowAtPoint();
				int rowAtPoint = list_table.rowAtPoint(e.getPoint());// 选中行
				list.setList_id(Integer.parseInt((String) list_table.getValueAt(rowAtPoint, 0)));   // 需要强制类型转换一下(Object->String->Int)
				// System.out.println(shop_table.getValueAt(rowAtPoint, 0));
				// System.out.println("*方法二:\t" + table.getValueAt(rowrowAtPoint, 0) + "\t" + table.getValueAt(row, 1));
				fillmask(list);
			}
		});
		panel.setLayout(null);
		
		
		list_textField = new JTextField();
		list_textField.setBounds(142, 38, 113, 24);
		panel.add(list_textField);
		list_textField.setColumns(10);
		
		user_textField = new JTextField();
		user_textField.setColumns(10);
		user_textField.setBounds(398, 38, 113, 24);
		panel.add(user_textField);
		
		JLabel list_Label = new JLabel("\u8BA2\u5355\u53F7\uFF1A");
		list_Label.setBounds(56, 41, 72, 18);
		panel.add(list_Label);
		
		JLabel user_Label = new JLabel("\u7528\u6237\u8D26\u53F7\uFF1A");
		user_Label.setBounds(297, 41, 87, 18);
		panel.add(user_Label);
		
		JButton search_Button = new JButton("\u641C\u7D22");
		search_Button.setBounds(588, 37, 93, 27);
		search_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				searchAction(shop);
			}
		});
		panel.add(search_Button);
		
		JButton reset_Button = new JButton("\u91CD\u7F6E");
		reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				filltable(shop);
			}
		});
		reset_Button.setBounds(668, 517, 101, 32);
		
		JButton confirm_Button = new JButton("\u786E\u8BA4");
		confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				changeState(staff);
			}
		});
		confirm_Button.setBounds(668, 472, 101, 32);
		
		JLabel title1_Label = new JLabel("\u8BA2\u5355\u4FE1\u606F");
		title1_Label.setBounds(428, 182, 83, 18);
		
		JScrollPane mask_scrollPane = new JScrollPane();
		mask_scrollPane.setBounds(101, 457, 458, 165);
		
		mask_table = new JTable();
		mask_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7C7B\u578B", "\u578B\u53F7", "\u6570\u91CF"
			}
		));
		mask_table.getColumnModel().getColumn(1).setPreferredWidth(100);
		mask_table.getColumnModel().getColumn(2).setPreferredWidth(100);
		mask_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		mask_scrollPane.setViewportView(mask_table);
		
		contentPane.setLayout(null);
		contentPane.add(list_scrollPane);
		contentPane.add(panel);
		contentPane.add(return_Button);
		contentPane.add(reset_Button);
		contentPane.add(confirm_Button);
		contentPane.add(title1_Label);
		contentPane.add(mask_scrollPane);  	
		
		JLabel title2_Label = new JLabel("\u8BE6\u60C5");
		title2_Label.setBounds(313, 435, 72, 18);
		contentPane.add(title2_Label);
		
		filltable(shop);
	}
	
	/**
	 * 初始化表格数据
	 * @param shop
	 */
	private void filltable(Shop shop)
	{
		DefaultTableModel dtm = (DefaultTableModel) list_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = listDao.listNon(con, shop);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("list_id"));
				v.add(rs.getString("user_phone"));
				v.add(rs.getString("list_date"));
				v.add(rs.getString("list_state"));
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
	
	private void fillmask(List list)
	{
		DefaultTableModel dtm = (DefaultTableModel) mask_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = makeDao.list(con, list);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("mask_id"));
				v.add(rs.getString("type"));
				v.add(rs.getString("size"));
				v.add(rs.getString("make_num"));
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
	
	private void changeState(Staff staff)
	{
		int count = this.list_table.getSelectedRow();
		List listtemp = new List();
		listtemp.setList_id(Integer.parseInt((String)this.list_table.getValueAt(count, 0)));
		listtemp.setList_state("已完成");
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			listDao.updateState(con, listtemp);
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "领取成功！");
			this.dispose();
			new StaffFrame(staff).setVisible(true);
			return;
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
	
	private void searchAction(Shop shop)
	{
		String user_phone = this.user_textField.getText();
		int list_id;
		if(StringUtil.isNotEmpty(this.list_textField.getText()))
		{
			list_id = Integer.parseInt(this.list_textField.getText());
		}
		else
		{
			list_id = -1;
		}
		
		User usertemp = new User();
		usertemp.setUser_phone(user_phone);
		
		List listtemp = new List();
		listtemp.setList_id(list_id);
		
		DefaultTableModel dtm = (DefaultTableModel) list_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = listDao.listSelect(con, shop, usertemp, listtemp);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("list_id"));
				v.add(rs.getString("user_phone"));
				v.add(rs.getString("list_date"));
				v.add(rs.getString("list_state"));
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
