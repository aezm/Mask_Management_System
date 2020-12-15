package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import dao.ListDao;
import dao.MakeDao;
import dao.UserDao;
import model.List;
import model.Shop;
import model.User;
import util.DBUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class UserListFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private List list = new List();
	private ListDao listDao = new ListDao();
	private MakeDao makeDao = new MakeDao();
	private UserDao userDao = new UserDao();
	
	private JPanel contentPane;
	private JTable list_table;
	private JTable mask_table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserListFrame frame = new UserListFrame();
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
	public UserListFrame(User user) {
		setTitle("\u8BA2\u5355\u67E5\u8BE2");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserListFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 717);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane list_ScrollPane = new JScrollPane();
		
		JLabel tableTitle1_Label = new JLabel("\u8BA2\u5355\u5217\u8868");
		tableTitle1_Label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JScrollPane mask_scrollPane = new JScrollPane();
		
		JLabel tableTitle2_Label = new JLabel("\u8BA2\u5355\u8BE6\u60C5");
		tableTitle2_Label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("\u53D6\u6D88\u8BA2\u5355");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				changeState(user);
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new UserFrame(user).setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(425)
							.addComponent(tableTitle1_Label, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(list_ScrollPane, GroupLayout.PREFERRED_SIZE, 782, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(mask_scrollPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
									.addGap(96)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(280)
							.addComponent(tableTitle2_Label, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(tableTitle1_Label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list_ScrollPane, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(tableTitle2_Label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mask_scrollPane, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(104)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		
		mask_table = new JTable();
		mask_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7C7B\u578B", "\u578B\u53F7", "\u6570\u91CF"
			}
		));
		mask_table.getColumnModel().getColumn(1).setPreferredWidth(131);
		mask_table.getColumnModel().getColumn(2).setPreferredWidth(130);
		mask_table.setBackground(Color.WHITE);
		mask_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		mask_scrollPane.setViewportView(mask_table);
		
		list_table = new JTable();
		list_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u65F6\u95F4", "\u72B6\u6001", "\u9886\u53D6\u70B9\u540D\u79F0", "\u9886\u53D6\u70B9\u5730\u5740", "\u8054\u7CFB\u7535\u8BDD "
			}
		));
		list_table.getColumnModel().getColumn(1).setPreferredWidth(100);
		list_table.getColumnModel().getColumn(2).setPreferredWidth(100);
		list_table.getColumnModel().getColumn(3).setPreferredWidth(110);
		list_table.getColumnModel().getColumn(4).setPreferredWidth(250);
		list_table.getColumnModel().getColumn(5).setPreferredWidth(104);
		list_table.setBackground(Color.WHITE);
		list_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
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
		list_ScrollPane.setViewportView(list_table);
		contentPane.setLayout(gl_contentPane);
		
		fillList_table(user);
	}
	
	/**
	 * 初始化订单表格
	 * @param user
	 */
	private void fillList_table(User user)
	{
		DefaultTableModel dtm = (DefaultTableModel) list_table.getModel();
		dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			ResultSet rs = listDao.list(con, user);
			while(rs.next())
			{
				Vector v = new Vector();
				v.add(rs.getString("list_id"));
				v.add(rs.getString("list_date"));
				v.add(rs.getString("list_state"));
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

	private void changeState(User user)
	{
		int count = this.list_table.getSelectedRow();
		List listtemp = new List();
		listtemp.setList_id(Integer.parseInt((String)this.list_table.getValueAt(count, 0)));
		listtemp.setList_state("已取消");
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			listDao.updateState(con, listtemp);
			userDao.updateDate(con, user);
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "取消成功！");
			this.dispose();
			new UserFrame(user).setVisible(true);
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
}


