package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import dao.ListDao;
import dao.MakeDao;
import dao.MaskDao;
import dao.OrderDao;
import dao.StoreDao;
import model.List;
import model.Shop;
import model.Store;
import model.User;
import util.DBUtil;
import util.StringUtil;
import util.TimeUtil;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderFrame extends JFrame {
	
	DBUtil dbUtil = new DBUtil();
	MaskDao maskDao = new MaskDao();
	Store store = new Store();
	StoreDao storeDao = new StoreDao();
	List list = new List();
	ListDao listDao = new ListDao();
	MakeDao makeDao = new MakeDao();
	OrderDao orderDao = new OrderDao();
	
	private JTable mask_order_table;
	private JTextField num_textField;	
	private JPanel contentPane;
	private JComboBox id_comboBox;
	
	private int total = 0;   // 统计口罩总数量
	private int count = 0;   // 表格中行数计数

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OrderFrame frame = new OrderFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * 构造方法
	 * @param shop
	 */
	public OrderFrame(Shop shop, User user) {
		
		final User innerUser = user;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u9884\u7EA6");
		setResizable(true);
		getContentPane().setBackground(Color.WHITE);
		
		JScrollPane list_scrollPane = new JScrollPane();
		
		JLabel table_Label = new JLabel("\u9884\u7EA6\u5217\u8868");
		table_Label.setBackground(Color.WHITE);
		
		id_comboBox = new JComboBox();
		
		num_textField = new JTextField();
		num_textField.setColumns(10);
		
		JButton add_Button = new JButton("\u6DFB\u52A0");
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				add(shop);
			}
		});
		
		JLabel id_Label = new JLabel("\u7F16\u53F7\uFF1A");
		
		JLabel num_Label = new JLabel("\u6570\u91CF\uFF1A");
		
		JButton del_Button = new JButton("\u5220\u9664");
		del_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				del();
			}
		});
		
		JButton confirmList_Button = new JButton("\u786E\u8BA4\u8BA2\u5355");
		confirmList_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				innerUser.setLast_order_time(confirmList(shop, innerUser));
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("\u5355\u6B21\u8BA2\u5355\u53E3\u7F69\u603B\u6570\u8BF7\u52FF\u8D85\u8FC720\uFF0C\u8C22\u8C22\uFF01");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(list_scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(76)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(num_Label)
													.addComponent(id_Label))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(id_comboBox, 0, 109, Short.MAX_VALUE)
													.addComponent(num_textField, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(add_Button)
												.addGap(34)
												.addComponent(del_Button, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
										.addGap(62))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(85)
										.addComponent(confirmList_Button, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(8))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(123)
							.addComponent(table_Label)))
					.addGap(16))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(105)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(id_Label)
								.addComponent(id_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(num_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(num_Label))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(add_Button)
								.addComponent(del_Button))
							.addGap(31)
							.addComponent(confirmList_Button)
							.addGap(27)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(table_Label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list_scrollPane, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		mask_order_table = new JTable();
		mask_order_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6570\u91CF"
			}
		));
		mask_order_table.setBackground(Color.WHITE);
		list_scrollPane.setViewportView(mask_order_table);
		getContentPane().setLayout(groupLayout);
		setBounds(100, 100, 589, 451);
		this.setLocationRelativeTo(null);   // 窗体居中
		
		fillcb(shop);
	}

	private Long confirmList(Shop shop, User user)
	{
		final User usertemp = user;
		
		List newlist = new List();
		newlist.setList_date(TimeUtil.getTheTime());
		newlist.setList_state("待领取");   // 预约成功的订单状态显示“待领取” （待领取->已完成/已取消）
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			
			int list_id = listDao.addList(con, newlist);
			if(list_id == -1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "预约失败！");
			}
			else
			{
				newlist.setList_id(list_id);
			}
			
		    for(int row = 0; row < mask_order_table.getRowCount(); row++)
		    {
		        DefaultTableModel model = (DefaultTableModel)mask_order_table.getModel();
		        int mask_id = Integer.parseInt(model.getValueAt(row, 0).toString());
		        int num = Integer.parseInt(model.getValueAt(row, 1).toString());
		        makeDao.addMake(con, mask_id, newlist.getList_id(), num);
		        storeDao.updateStore_num(con, (-1)*num, shop, mask_id);
		    }
		    orderDao.addOrder(con, user, shop, newlist);
		    usertemp.setLast_order_time(newlist.getList_date());
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "预约成功！");
		    return usertemp.getLast_order_time();
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
		
		return usertemp.getLast_order_time();
	}

	/**
	 * 删除
	 */
	private void del()
	{
		DefaultTableModel dtm = (DefaultTableModel) mask_order_table.getModel();
		
	    //取得所选行数组长度;
        int numrow = mask_order_table.getSelectedRows().length;
        for(int i = 0; i < numrow; i++)
        {
            //删除所选行;
        	int index = mask_order_table.getSelectedRow();
        	total -= (int) mask_order_table.getValueAt(index, 1);   // 控制口罩总数
            dtm.removeRow(index);
        }
	}

	/**
	 * 填充口罩编号下拉框
	 * @param shop
	 */
	private void fillcb(Shop shop)
	{
		Connection con = null;
		
		try
		{
			con = dbUtil.getCon();
			
			ResultSet rs = maskDao.listId(con, shop);
			while(rs.next())
			{
				this.id_comboBox.addItem(rs.getInt("mask_id"));
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
	 * 预添加口罩
	 * @param shop
	 */
	private void add(Shop shop)
	{
		DefaultTableModel dtm = (DefaultTableModel) mask_order_table.getModel();
		//dtm.setRowCount(0);   // 设置成0行
		
		Connection con = null;

		if(StringUtil.isNotEmpty(this.num_textField.getText()) && StringUtil.isNotEmpty(this.id_comboBox.getSelectedItem().toString()))
		{
			try
			{
				con = dbUtil.getCon();
				int mask_id = Integer.parseInt(this.id_comboBox.getSelectedItem().toString());
				int num = Integer.parseInt(this.num_textField.getText());
				
				if(storeDao.limit(con, shop, mask_id, num))
				{
					total += num;
					if(total <= 20)   // 一次订单口罩总数量不得超过20
					{
						Vector v = new Vector();
						v.add(mask_id);
						v.add(num);
						dtm.addRow(v);
						count++;
					}
					else
					{
						total -= num;   // 总数回退
						
						// 设置按钮显示效果
						UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
						// 设置文本显示效果
						UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
						// 消息对话框
						JOptionPane.showMessageDialog(null, "超过限制数量！");
					}
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
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入编号及数量！");				
		}
	}
}
