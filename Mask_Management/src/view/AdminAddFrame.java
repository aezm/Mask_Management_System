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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import dao.AdminDao;
import model.Admin;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AdminAddFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private AdminDao adminDao = new AdminDao();
	
	private JPanel aaf_Pane;
	private JPasswordField pwd_passwordField;
	private JPasswordField confpwd_passwordField;
	private JTextField name_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddFrame frame = new AdminAddFrame();
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
	public AdminAddFrame() {
		setTitle("\u7BA1\u7406\u5458\u6DFB\u52A0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminAddFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		aaf_Pane = new JPanel();
		aaf_Pane.setBackground(Color.WHITE);
		aaf_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(aaf_Pane);
		aaf_Pane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7BA1\u7406\u5458\u4FE1\u606F", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 46, 659, 367);
		aaf_Pane.add(panel);
		panel.setLayout(null);
		
		pwd_passwordField = new JPasswordField();
		pwd_passwordField.setBounds(278, 123, 193, 30);
		panel.add(pwd_passwordField);
		
		confpwd_passwordField = new JPasswordField();
		confpwd_passwordField.setBounds(278, 176, 193, 30);
		panel.add(confpwd_passwordField);
		
		JLabel pwd_Label = new JLabel("\u5BC6\u7801\uFF1A");
		pwd_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		pwd_Label.setBounds(189, 125, 59, 24);
		panel.add(pwd_Label);
		
		JLabel confpwd_Label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		confpwd_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		confpwd_Label.setBounds(189, 178, 83, 24);
		panel.add(confpwd_Label);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		name_Label.setBounds(189, 231, 73, 24);
		panel.add(name_Label);
		
		name_textField = new JTextField();
		name_textField.setBounds(278, 229, 193, 30);
		panel.add(name_textField);
		name_textField.setColumns(10);
		
		JButton change_Button = new JButton("\u6DFB\u52A0");
		change_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				addAction();
			}
		});
		change_Button.setBounds(263, 475, 113, 39);
		aaf_Pane.add(change_Button);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new AdminFrame().setVisible(true);
			}
		});
		return_Button.setBounds(440, 475, 113, 39);
		aaf_Pane.add(return_Button);
	}
	
	private void reset()
	{
		this.pwd_passwordField.setText("");
		this.confpwd_passwordField.setText("");
		this.name_textField.setText("");
	}
	
	/**
	 * 添加管理员
	 */
	private void addAction()
	{
		Admin admintemp = new Admin();
		String pwd = new String(this.pwd_passwordField.getPassword());
		String confpwd = new String(this.confpwd_passwordField.getPassword());
		
		// 确认密码判断
		if(StringUtil.isNotEmpty(pwd))   // 判断密码框是否为空
		{
			if(pwd.length() >= 6)   // 判断密码长度是否大于6
			{
				if(confpwd.equals(pwd))   // 判断确认密码是否与密码相同
				{
					admintemp.setAdmin_pwd(pwd);
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
		
		
		// 姓名输入判断
		String name = this.name_textField.getText();
		if(StringUtil.isNotEmpty(name))
		{
			admintemp.setAdmin_name(name);
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
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int id = adminDao.addAdmin(con, admintemp);

			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "添加成功！\n您的管理员账号为"+ id + '！');
			reset();
			return;
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
