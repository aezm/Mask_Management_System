package view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import dao.MaskDao;
import model.Mask;
import model.Shop;
import util.DBUtil;
import util.StringUtil;

import javax.swing.UIManager;
import java.awt.Toolkit;

public class MaskManageFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private MaskDao maskDao = new MaskDao();
	
	private JPanel contentPane;
	private JTable mask_table;
	private JTextField id_textField;
	private JTextField type_textField;
	private JTextField size_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaskManageFrame frame = new MaskManageFrame();
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
	public MaskManageFrame() {
		setTitle("口罩管理");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MaskManageFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane mask_scrollPane = new JScrollPane();
		mask_scrollPane.setBounds(93, 33, 634, 267);
		
		JPanel panel = new JPanel();
		panel.setBounds(93, 349, 634, 132);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u66F4\u65B0", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		
		JButton update_Button = new JButton("\u66F4\u65B0");
		update_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				updateAction();
			}
		});
		update_Button.setBounds(364, 514, 83, 27);

		
		JButton reset_Button = new JButton("\u91CD\u7F6E");
		reset_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				reset();
			}
		});
		reset_Button.setBounds(193, 514, 83, 27);

		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new AdminFrame().setVisible(true);
			}
		});
		return_Button.setBounds(535, 514, 83, 27);

		panel.setLayout(null);
		
		id_textField = new JTextField();
		id_textField.setEditable(false);
		id_textField.setBounds(97, 62, 107, 24);
		panel.add(id_textField);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u7F16\u53F7\uFF1A");
		id_Label.setBounds(38, 65, 45, 18);
		panel.add(id_Label);
		
		type_textField = new JTextField();
		type_textField.setColumns(10);
		type_textField.setBounds(301, 62, 107, 24);
		panel.add(type_textField);
		
		JLabel type_Label = new JLabel("\u7C7B\u578B\uFF1A");
		type_Label.setBounds(242, 65, 45, 18);
		panel.add(type_Label);
		
		size_textField = new JTextField();
		size_textField.setColumns(10);
		size_textField.setBounds(500, 62, 107, 24);
		panel.add(size_textField);
		
		JLabel size_Label = new JLabel("\u578B\u53F7\uFF1A");
		size_Label.setBounds(441, 65, 45, 18);
		panel.add(size_Label);
		
		mask_table = new JTable();
		mask_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7C7B\u578B", "\u578B\u53F7"
			}
		));
		mask_table.getColumnModel().getColumn(1).setPreferredWidth(130);
		mask_table.getColumnModel().getColumn(2).setPreferredWidth(130);
		mask_table.setRowHeight(25);   // 设置行高
		//shop_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);    // 左右滚动条设置
		mask_table.setBackground(Color.WHITE);
		mask_table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//.rowAtPoint();
				int rowAtPoint = mask_table.rowAtPoint(e.getPoint());// 选中行
				fillTextField(rowAtPoint);
			}
		});
		mask_scrollPane.setViewportView(mask_table);
		
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(reset_Button);
		contentPane.add(update_Button);
		contentPane.add(return_Button);
		contentPane.add(panel);
		contentPane.add(mask_scrollPane);
		contentPane.add(mask_scrollPane);
		
		filltable();
	}

	private void filltable()
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
	}
	
	private void updateAction()
	{
		int mask_id = -1;
		if(StringUtil.isNotEmpty(this.id_textField.getText()))
		{
			mask_id = Integer.parseInt(this.id_textField.getText());
		}
		
		String type;
		if(StringUtil.isNotEmpty(this.type_textField.getText()))
		{
			type = this.type_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入类型！");
			return;
		}
		
		String size;
		if(StringUtil.isNotEmpty(this.size_textField.getText()))
		{
			size = this.size_textField.getText();
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入型号！");
			return;
		}
		
		Mask masktemp = new Mask();
		masktemp.setMask_id(mask_id);
		masktemp.setType(type);
		masktemp.setSize(size);
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = maskDao.update(con, masktemp);
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
}
