package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import dao.MaskDao;
import dao.ShopDao;
import dao.StaffDao;
import dao.StoreDao;
import model.Shop;
import model.Staff;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class StoreManageFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private Shop shop = new Shop();
	private ShopDao shopDao = new ShopDao();
	private StaffDao staffDao = new StaffDao();
	private MaskDao maskDao = new MaskDao();
	private StoreDao storeDao = new StoreDao();
	
	private JPanel contentPane;
	private JTable store_table;
	private JTextField id_textField;
	private JTextField type_textField;
	private JTextField num_textField;
	private JTextField size_textField;
	private JTable mask_table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StoreManageFrame frame = new StoreManageFrame();
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
	public StoreManageFrame(Staff staff) {
		setTitle("\u5E93\u5B58\u7BA1\u7406");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StoreManageFrame.class.getResource("/images/mask2.png")));
		
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
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane store_scrollPane = new JScrollPane();
		store_scrollPane.setBounds(93, 61, 634, 156);
		
		JLabel title1_Label = new JLabel("\u5728\u5E93\u53E3\u7F69");
		title1_Label.setBounds(378, 36, 83, 18);
		
		JPanel panel = new JPanel();
		panel.setBounds(471, 243, 256, 238);
		panel.setBorder(new TitledBorder(null, "\u5E93\u5B58\u66F4\u65B0", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		
		JButton update_Button = new JButton("\u66F4\u65B0");
		update_Button.setBounds(364, 514, 83, 27);
		update_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateAction(shop);
			}
		});
		
		JButton reset_Button = new JButton("\u91CD\u7F6E");
		reset_Button.setBounds(193, 514, 83, 27);
		reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				reset();
			}
		});
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.setBounds(535, 514, 83, 27);
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new StaffFrame(staff).setVisible(true);
			}
		});
		panel.setLayout(null);
		
		id_textField = new JTextField();
		id_textField.setEditable(false);
		id_textField.setBounds(96, 36, 107, 24);
		panel.add(id_textField);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u7F16\u53F7\uFF1A");
		id_Label.setBounds(37, 39, 45, 18);
		panel.add(id_Label);
		
		type_textField = new JTextField();
		type_textField.setEditable(false);
		type_textField.setColumns(10);
		type_textField.setBounds(96, 85, 107, 24);
		panel.add(type_textField);
		
		JLabel lblNewLabel_1 = new JLabel("\u7C7B\u578B\uFF1A");
		lblNewLabel_1.setBounds(37, 88, 45, 18);
		panel.add(lblNewLabel_1);
		
		num_textField = new JTextField();
		num_textField.setColumns(10);
		num_textField.setBounds(96, 183, 107, 24);
		panel.add(num_textField);
		
		size_textField = new JTextField();
		size_textField.setEditable(false);
		size_textField.setColumns(10);
		size_textField.setBounds(96, 134, 107, 24);
		panel.add(size_textField);
		
		JLabel size_Label = new JLabel("\u578B\u53F7\uFF1A");
		size_Label.setBounds(37, 137, 45, 18);
		panel.add(size_Label);
		
		JLabel num_Label = new JLabel("\u6570\u91CF\uFF1A");
		num_Label.setBounds(37, 186, 45, 18);
		panel.add(num_Label);
		
		store_table = new JTable();
		store_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7C7B\u578B", "\u578B\u53F7", "\u6570\u91CF"
			}
		));
		store_table.getColumnModel().getColumn(1).setPreferredWidth(130);
		store_table.getColumnModel().getColumn(2).setPreferredWidth(130);
		store_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		store_table.setBackground(Color.WHITE);
		store_scrollPane.setViewportView(store_table);
		
		JScrollPane mask_scrollPane = new JScrollPane();
		mask_scrollPane.setBounds(93, 267, 327, 214);
		
		JLabel title2_Label = new JLabel("进货");
		title2_Label.setBounds(239, 243, 48, 18);
		
		mask_table = new JTable();
		mask_table.setBackground(Color.WHITE);
		mask_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7C7B\u578B", "\u578B\u53F7"
			}
		));
		mask_table.getColumnModel().getColumn(0).setPreferredWidth(90);
		mask_table.getColumnModel().getColumn(1).setPreferredWidth(90);
		mask_table.getColumnModel().getColumn(2).setPreferredWidth(90);
		mask_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		// 获取进货表格选中行的值并填充口罩信息
		mask_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//.rowAtPoint();
				int rowAtPoint = mask_table.rowAtPoint(e.getPoint());// 选中行
				fillTextField(rowAtPoint);
			}
		});
		mask_scrollPane.setViewportView(mask_table);
		
		contentPane.setLayout(null);
		contentPane.add(reset_Button);
		contentPane.add(update_Button);
		contentPane.add(return_Button);
		contentPane.add(panel);
		contentPane.add(store_scrollPane);
		contentPane.add(title1_Label);
		contentPane.add(mask_scrollPane);
		contentPane.add(store_scrollPane);
		contentPane.add(title1_Label);
		contentPane.add(mask_scrollPane);
		contentPane.add(title2_Label);
		
		fillstoretable(shop);
		fillmasktable();
	}
	
	/**
	 * 初始化库存表格数据
	 * @param shop
	 */
	private void fillstoretable(Shop shop)
	{
		DefaultTableModel dtm = (DefaultTableModel) store_table.getModel();
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
				v.add(rs.getString("size"));
				v.add(rs.getString("type"));
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
	
	/**
	 * 初始化口罩表格数据
	 */
	private void fillmasktable()
	{
		DefaultTableModel dtm = (DefaultTableModel) mask_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = maskDao.listMask(con);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("mask_id"));
				v.add(rs.getString("type"));
				v.add(rs.getString("size"));
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
	
	private void fillTextField(int rowAtPoint)
	{
		this.id_textField.setText(((String)this.mask_table.getValueAt(rowAtPoint, 0)));
		this.type_textField.setText(((String)this.mask_table.getValueAt(rowAtPoint, 1)));
		this.size_textField.setText(((String)this.mask_table.getValueAt(rowAtPoint, 2)));
	}
	
	private void reset()
	{
		this.id_textField.setText("");
		this.type_textField.setText("");
		this.size_textField.setText("");
		this.num_textField.setText("");
	}
	
	private void updateAction(Shop shop)
	{
		int mask_id = -1;
		if(StringUtil.isNotEmpty(this.id_textField.getText()))
		{
			mask_id = Integer.parseInt(this.id_textField.getText());
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请选择要更新的口罩！");
			return;
		}
		
		int num = 0;
		if(StringUtil.isNotEmpty(this.num_textField.getText()))
		{
			num = Integer.parseInt(this.num_textField.getText());
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入数量！");
			return;
		}
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = storeDao.update(con, num, shop, mask_id);
			if(modifyNum == 1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "更新成功！");
				fillstoretable(shop);
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
}
