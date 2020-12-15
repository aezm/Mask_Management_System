package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
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

import dao.UserDao;
import model.List;
import model.Shop;
import model.User;
import util.DBUtil;
import util.StringUtil;
import util.TimeUtil;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;

public class UserMamageFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
	
	private JPanel contentPane;
	private JTextField phone_textField;
	private JTextField name_textField;
	private JTable user_table;
	private JTable mask_table;
	private JTextField id_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMamageFrame frame = new UserMamageFrame();
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
	public UserMamageFrame() {
		setTitle("\u7528\u6237\u7BA1\u7406");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMamageFrame.class.getResource("/images/mask2.png")));
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
		
		JScrollPane user_scrollPane = new JScrollPane();
		user_scrollPane.setBounds(99, 206, 735, 314);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new AdminFrame().setVisible(true);
			}
		});
		return_Button.setBounds(591, 562, 101, 32);
		
		user_table = new JTable();
		user_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8D26\u53F7", "\u59D3\u540D", "\u8EAB\u4EFD\u8BC1\u53F7", "\u4E0A\u6B21\u9884\u7EA6\u65F6\u95F4"
			}
		));
		user_table.getColumnModel().getColumn(0).setPreferredWidth(140);
		user_table.getColumnModel().getColumn(1).setPreferredWidth(140);
		user_table.getColumnModel().getColumn(2).setPreferredWidth(200);
		user_table.getColumnModel().getColumn(3).setPreferredWidth(140);
		user_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		user_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//.rowAtPoint();
				int rowAtPoint = user_table.rowAtPoint(e.getPoint());// 选中行
				del(rowAtPoint);
			}
		});
		user_scrollPane.setViewportView(user_table);
		panel.setLayout(null);
		
		
		phone_textField = new JTextField();
		phone_textField.setBounds(106, 38, 113, 24);
		panel.add(phone_textField);
		phone_textField.setColumns(10);
		
		name_textField = new JTextField();
		name_textField.setColumns(10);
		name_textField.setBounds(288, 38, 113, 24);
		panel.add(name_textField);
		
		JLabel phone_Label = new JLabel("\u8D26\u53F7\uFF1A");
		phone_Label.setBounds(50, 41, 52, 18);
		panel.add(phone_Label);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setBounds(233, 41, 52, 18);
		panel.add(name_Label);
		
		JButton search_Button = new JButton("\u641C\u7D22");
		search_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				searchAction();
			}
		});
		search_Button.setBounds(640, 37, 63, 27);
		panel.add(search_Button);
		
		JButton reset_Button = new JButton("\u91CD\u7F6E");
		reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				filltable();
			}
		});
		reset_Button.setBounds(420, 562, 101, 32);
		
		JButton del_Button = new JButton("\u5220\u9664");
		del_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		del_Button.setBounds(249, 562, 101, 32);
		
		JLabel title_Label = new JLabel("\u7528\u6237\u4FE1\u606F");
		title_Label.setBounds(428, 182, 83, 18);
		
		contentPane.setLayout(null);
		contentPane.add(user_scrollPane);
		contentPane.add(panel);
		
		JLabel id_Label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		id_Label.setBounds(415, 41, 82, 18);
		panel.add(id_Label);
		
		id_textField = new JTextField();
		id_textField.setColumns(10);
		id_textField.setBounds(501, 38, 113, 24);
		panel.add(id_textField);
		contentPane.add(return_Button);
		contentPane.add(reset_Button);
		contentPane.add(del_Button);
		contentPane.add(title_Label);	
		
		filltable();
	}

	/**
	 * 初始化表格数据
	 */
	private void filltable()
	{
		DefaultTableModel dtm = (DefaultTableModel) user_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = userDao.list(con);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("user_phone"));
				v.add(rs.getString("user_name"));
				v.add(rs.getString("id"));
				v.add(TimeUtil.outTheTime(rs.getLong("last_order_time")));
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
	 * 搜索
	 */
	private void searchAction()
	{
		String phone = this.phone_textField.getText();
		String name = this.name_textField.getText();
		String id = this.id_textField.getText();
		
		User usertemp = new User();
		usertemp.setUser_phone(phone);
		usertemp.setUser_name(name);
		usertemp.setId(id);
		
		DefaultTableModel dtm = (DefaultTableModel) user_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = userDao.listSelect(con, usertemp);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("user_phone"));
				v.add(rs.getString("user_name"));
				v.add(rs.getString("id"));
				v.add(TimeUtil.outTheTime(rs.getLong("last_order_time")));
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
	 * 删除
	 * @param rowAtPoint
	 */
	private void del(int rowAtPoint)
	{
		User usertemp = new User();
		usertemp.setUser_phone((String) this.user_table.getValueAt(rowAtPoint, 0));   // 需要强制类型转换一下(Object->String)
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int ModifyNum = userDao.delete(con, usertemp);
			if(ModifyNum == 1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "删除成功！");
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
				JOptionPane.showMessageDialog(null, "删除失败！");
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
