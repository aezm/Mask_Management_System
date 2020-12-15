package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import dao.UserDao;
import model.User;
import util.DBUtil;
import util.StringUtil;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class NewPwdFrame extends JFrame {

	private UserDao userDao = new UserDao();
	private DBUtil dbUtil = new DBUtil();
	
	private JPanel contentPane;
	private JPasswordField pwd_passwordField;
	private JPasswordField confpwd_passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NewPwdFrame frame = new NewPwdFrame();
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
	public NewPwdFrame(User user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewPwdFrame.class.getResource("/images/mask2.png")));
		setTitle("\u5BC6\u7801\u8BBE\u7F6E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 401);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pwd_passwordField = new JPasswordField();
		pwd_passwordField.setBounds(237, 95, 178, 24);
		contentPane.add(pwd_passwordField);
		
		confpwd_passwordField = new JPasswordField();
		confpwd_passwordField.setBounds(237, 161, 178, 24);
		contentPane.add(confpwd_passwordField);
		
		JLabel pwd_Label = new JLabel("\u5BC6\u7801\uFF1A");
		pwd_Label.setBounds(133, 98, 72, 18);
		contentPane.add(pwd_Label);
		
		JLabel conf_Label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		conf_Label.setBounds(133, 164, 90, 18);
		contentPane.add(conf_Label);
		
		JButton confirm_Button = new JButton("\u786E\u8BA4");
		confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				confirm(user);
			}
		});
		confirm_Button.setBounds(156, 267, 90, 27);
		contentPane.add(confirm_Button);
		
		JButton return_Button = new JButton("\u53D6\u6D88");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		return_Button.setBounds(305, 267, 90, 27);
		contentPane.add(return_Button);
	}
	
	private void confirm(User user)
	{
		// 密码判断
		String pwd = new String(this.pwd_passwordField.getPassword());
		String confpwd = new String(this.confpwd_passwordField.getPassword());
		// 确认密码判断
		if(StringUtil.isNotEmpty(pwd))   // 判断密码框是否为空
		{
			if(pwd.length() >= 6)   // 判断密码长度是否大于6
			{
				if(confpwd.equals(pwd))   // 判断确认密码是否与密码相同
				{
					user.setUser_pwd(pwd);
				}
				else
				{
					// 设置按钮显示效果
					UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
					// 设置文本显示效果
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
					// 消息对话框
					JOptionPane.showMessageDialog(null, "请确认密码！");
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
				JOptionPane.showMessageDialog(null, "密码长度不能小于6！");
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
			JOptionPane.showMessageDialog(null, "请输入密码！");
			return;
		}
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = userDao.updatePwd(con, user);
			if(modifyNum == 1)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "密码更新成功！");
				this.dispose();
				new LoginFrame().setVisible(true);
				return;
			}
			else
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "密码更新失败！");
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
