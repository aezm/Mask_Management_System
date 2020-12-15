package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import dao.UserDao;
import model.User;
import util.DBUtil;
import util.StringUtil;
import util.TimeUtil;

import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class UserMessageFrame extends JFrame {

	private UserDao userDao = new UserDao();
	private DBUtil dbUtil = new DBUtil();
	
	private JPanel contentPane;
	private JTextField phone_textField;
	private JPasswordField oldpwd_passwordField;
	private JPasswordField newpwd_passwordField;
	private JTextField name_textField;
	private JTextField id_textField;
	private JTextField time_textField;
	private JPasswordField pwd_passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserMessageFrame frame = new UserMessageFrame();
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
	public UserMessageFrame(User user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMessageFrame.class.getResource("/images/mask2.png")));
		setTitle("\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7528\u6237\u4FE1\u606F", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 46, 659, 367);
		contentPane.add(panel);
		
		phone_textField = new JTextField();
		phone_textField.setBounds(268, 45, 237, 24);
		phone_textField.setEditable(false);
		phone_textField.setColumns(10);
		
		JLabel phone_Label = new JLabel("\u8D26\u53F7\uFF1A");
		phone_Label.setBounds(140, 45, 75, 22);
		phone_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JLabel pwd_Label = new JLabel("\u5BC6\u7801\uFF1A");
		pwd_Label.setBounds(140, 87, 59, 22);
		pwd_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		oldpwd_passwordField = new JPasswordField();
		oldpwd_passwordField.setBounds(268, 129, 237, 24);
		
		newpwd_passwordField = new JPasswordField();
		newpwd_passwordField.setBounds(268, 171, 237, 24);
		
		JLabel oldpwd_Label = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
		oldpwd_Label.setBounds(140, 129, 75, 22);
		oldpwd_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JLabel newpwd_Label = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		newpwd_Label.setBounds(140, 171, 75, 22);
		newpwd_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		name_textField = new JTextField();
		name_textField.setBounds(268, 213, 237, 24);
		name_textField.setColumns(10);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setBounds(140, 213, 86, 22);
		name_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		id_textField = new JTextField();
		id_textField.setBounds(268, 255, 237, 24);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		id_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		id_Label.setBounds(140, 255, 118, 22);
		
		time_textField = new JTextField();
		time_textField.setBounds(268, 297, 237, 24);
		time_textField.setEditable(false);
		time_textField.setColumns(10);
		
		JLabel time_Label = new JLabel("\u4E0A\u6B21\u9884\u7EA6\u65F6\u95F4\uFF1A");
		time_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		time_Label.setBounds(140, 297, 118, 22);
		panel.setLayout(null);
		panel.add(name_textField);
		panel.add(name_Label);
		panel.add(id_textField);
		panel.add(id_Label);
		panel.add(time_textField);
		panel.add(time_Label);
		panel.add(newpwd_Label);
		panel.add(oldpwd_Label);
		panel.add(phone_Label);
		panel.add(pwd_Label);
		panel.add(phone_textField);
		panel.add(oldpwd_passwordField);
		panel.add(newpwd_passwordField);
		
		pwd_passwordField = new JPasswordField();
		pwd_passwordField.setEditable(false);
		pwd_passwordField.setBounds(268, 87, 237, 24);
		panel.add(pwd_passwordField);
		
		JButton change_Button = new JButton("\u4FEE\u6539");
		change_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				changeMessage(user);
			}
		});
		change_Button.setBounds(263, 475, 113, 39);
		contentPane.add(change_Button);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				new UserFrame(user).setVisible(true);
			}
		});
		return_Button.setBounds(440, 475, 113, 39);
		contentPane.add(return_Button);
		
		fillblanks(user);
	}
	
	private void fillblanks(User user)
	{
		this.phone_textField.setText(user.getUser_phone());
		this.pwd_passwordField.setText(user.getUser_pwd());
		this.name_textField.setText(user.getUser_name());
		this.id_textField.setText(user.getId());
		this.time_textField.setText(TimeUtil.outTheTime(user.getLast_order_time()));   // 按格式输出时间
	}
	
	private void changeMessage(User user)
	{
		User usertemp = new User();
		
		// 密码更改判断
		String pwd = new String(this.pwd_passwordField.getPassword());
		String oldpwd = new String(this.oldpwd_passwordField.getPassword());
		String newpwd = new String(this.newpwd_passwordField.getPassword());
		if(StringUtil.isNotEmpty(oldpwd))   // 判断旧密码框是否为空
		{
			if(oldpwd.equals(pwd))   // 判断旧密码是否和原密码相同 
			{
				if(StringUtil.isNotEmpty(newpwd))   // 判断新密码框是否为空
				{
					if(newpwd.length() >= 6)   // 判断新密码长度是否大于6
					{
						usertemp.setUser_pwd(newpwd);
					}
					else
					{
						// 设置按钮显示效果
						UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
						// 设置文本显示效果
						UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
						// 消息对话框
						JOptionPane.showMessageDialog(null, "密码长度不能少于6位！");
						return;
					}
				}
				else
				{
					// 设置按钮显示效果
					UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
					// 设置文本显示效果
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
					// 消息对话框
					JOptionPane.showMessageDialog(null, "请输入新密码！");
					return;
				}
			}
			else
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "密码不正确！");
				return;
			}
		}
		else
		{
			usertemp.setUser_pwd(new String(this.pwd_passwordField.getPassword()));
		}
		
		// 姓名更改判断
		String name = this.name_textField.getText();
		if(StringUtil.isNotEmpty(name))
		{
			usertemp.setUser_name(name);
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入姓名！");
			return;
		}
		
		// 身份证号修改判断
		String id = this.id_textField.getText();
		if(StringUtil.isNotEmpty(id))
		{
			usertemp.setId(id);
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入身份证号！");
			return;
		}
		
		usertemp.setUser_phone(this.phone_textField.getText());
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = userDao.updateMessage(con, usertemp);
			if(modifyNum == 1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.dispose();
				user.setUser_pwd(usertemp.getUser_pwd());
				user.setUser_name(usertemp.getUser_name());
				user.setId(usertemp.getId());
				new UserFrame(user).setVisible(true);
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
				new UserFrame(user).setVisible(true);
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
