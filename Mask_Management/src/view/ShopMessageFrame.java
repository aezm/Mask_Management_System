package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import dao.ShopDao;
import dao.StaffDao;
import model.Shop;
import model.Staff;
import model.User;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.UIManager;

public class ShopMessageFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private Shop shop = new Shop();
	private ShopDao shopDao = new ShopDao();
	private StaffDao staffDao = new StaffDao();
	
	private JPanel shopmf_Pane;
	private JTextField id_textField;
	private JTextField pro_textField;
	private JTextField city_textField;
	private JTextField dis_textField;
	private JTextField detail_textField;
	private JTextField phone_textField;
	private JTextField name_textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShopMessageFrame frame = new ShopMessageFrame();
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
	public ShopMessageFrame(Staff staff) {
		
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopMessageFrame.class.getResource("/images/mask2.png")));
		setTitle("\u9886\u53D6\u70B9\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		shopmf_Pane = new JPanel();
		shopmf_Pane.setBackground(Color.WHITE);
		shopmf_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(shopmf_Pane);
		shopmf_Pane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u9886\u53D6\u70B9\u4FE1\u606F", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 46, 659, 367);
		shopmf_Pane.add(panel);
		panel.setLayout(null);
		
		id_textField = new JTextField();
		id_textField.setBounds(268, 45, 237, 24);
		id_textField.setEditable(false);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u7F16\u53F7\uFF1A");
		id_Label.setBounds(151, 46, 75, 22);
		id_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JLabel name_Label = new JLabel("\u540D\u79F0\uFF1A");
		name_Label.setBounds(151, 87, 59, 22);
		name_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		pro_textField = new JTextField();
		pro_textField.setBounds(268, 129, 237, 24);
		pro_textField.setEditable(false);
		
		city_textField = new JTextField();
		city_textField.setBounds(268, 171, 237, 24);
		city_textField.setEditable(false);
		
		JLabel pro_Label = new JLabel("\u7701\u4EFD/\u5730\u533A\uFF1A");
		pro_Label.setBounds(151, 129, 100, 22);
		pro_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JLabel city_Label = new JLabel("\u5E02\uFF1A");
		city_Label.setBounds(151, 171, 75, 22);
		city_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		dis_textField = new JTextField();
		dis_textField.setBounds(268, 213, 237, 24);
		dis_textField.setEditable(false);
		dis_textField.setColumns(10);
		
		JLabel dis_Label = new JLabel("\u53BF/\u533A\uFF1A");
		dis_Label.setBounds(151, 213, 86, 22);
		dis_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		detail_textField = new JTextField();
		detail_textField.setBounds(268, 255, 237, 24);
		detail_textField.setColumns(10);
		
		JLabel detail_Label = new JLabel("\u8BE6\u7EC6\u5730\u5740\uFF1A");
		detail_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		detail_Label.setBounds(151, 255, 105, 22);
		
		phone_textField = new JTextField();
		phone_textField.setBounds(268, 297, 237, 24);
		phone_textField.setColumns(10);
		
		JLabel phone_Label = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		phone_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		phone_Label.setBounds(151, 297, 105, 22);
		panel.setLayout(null);
		panel.add(dis_textField);
		panel.add(dis_Label);
		panel.add(detail_textField);
		panel.add(detail_Label);
		panel.add(phone_textField);
		panel.add(phone_Label);
		panel.add(city_Label);
		panel.add(pro_Label);
		panel.add(id_Label);
		panel.add(name_Label);
		panel.add(id_textField);
		panel.add(pro_textField);
		panel.add(city_textField);
		
		name_textField = new JTextField();
		name_textField.setBounds(268, 87, 237, 24);
		panel.add(name_textField);
		
		JButton change_Button = new JButton("\u4FEE\u6539");
		change_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				changeMessage(staff, shop);
			}
		});
		change_Button.setBounds(263, 475, 113, 39);
		shopmf_Pane.add(change_Button);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new StaffFrame(staff).setVisible(true);
			}
		});
		return_Button.setBounds(440, 475, 113, 39);
		shopmf_Pane.add(return_Button);
		
		fillblanks(shop);
	}

	private void fillblanks(Shop shop)
	{
		this.id_textField.setText(shop.getShop_id() + "");
		this.name_textField.setText(shop.getShop_name());
		this.pro_textField.setText(shop.getAddr_pro());
		this.city_textField.setText(shop.getAddr_city());
		this.dis_textField.setText(shop.getAddr_dis());
		this.detail_textField.setText(shop.getAddr_detail());
		this.phone_textField.setText(shop.getShop_phone());
	}
	
	private void changeMessage(Staff staff, Shop shop)
	{
		Shop shoptemp = new Shop();
		
		// 领取点名称修改判断
		String name = this.name_textField.getText();
		if(StringUtil.isNotEmpty(name))
		{
			shoptemp.setShop_name(name);
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
		
		// 领取点详细地址修改判断
		String detail = this.detail_textField.getText();
		if(StringUtil.isNotEmpty(detail))
		{
			shoptemp.setAddr_detail(detail);
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
		
		//领取点联系电话修改判断
		String phone = this.phone_textField.getText();
		if(StringUtil.isNotEmpty(phone))
		{
			shoptemp.setShop_phone(phone);
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
		
		shoptemp.setShop_id(Integer.parseInt(this.id_textField.getText()));
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = shopDao.updateMessage(con, shoptemp);
			if(modifyNum == 1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.dispose();
				new StaffFrame(staff).setVisible(true);
				return;
			}
			else
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "修改失败！");
				this.dispose();
				new StaffFrame(staff).setVisible(true);
				return;
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
