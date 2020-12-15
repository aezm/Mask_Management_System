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

import dao.UserDao;
import model.User;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class ForgetFrame extends JFrame {

	private UserDao userDao = new UserDao();
	private DBUtil dbUtil = new DBUtil();

	private JPanel contentPane;
	private JTextField phone_textField;
	private JTextField name_textField;
	private JTextField id_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetFrame frame = new ForgetFrame();
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
	public ForgetFrame() {
		setTitle("\u5FD8\u8BB0\u5BC6\u7801");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ForgetFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5FD8\u8BB0\u5BC6\u7801", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 46, 659, 367);
		contentPane.add(panel);
		
		phone_textField = new JTextField();
		phone_textField.setBounds(268, 102, 237, 30);
		phone_textField.setColumns(10);
		
		JLabel phone_Label = new JLabel("\u8D26\u53F7\uFF1A");
		phone_Label.setBounds(151, 105, 75, 22);
		phone_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		name_textField = new JTextField();
		name_textField.setBounds(268, 175, 237, 30);
		name_textField.setColumns(10);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setBounds(151, 178, 86, 22);
		name_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		id_textField = new JTextField();
		id_textField.setBounds(268, 248, 237, 30);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		id_Label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		id_Label.setBounds(151, 251, 111, 22);
		panel.setLayout(null);
		panel.add(name_textField);
		panel.add(name_Label);
		panel.add(id_textField);
		panel.add(id_Label);
		panel.add(phone_Label);
		panel.add(phone_textField);
		
		JButton confirm_Button = new JButton("\u786E\u5B9A");
		confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				confirm();
			}
		});
		confirm_Button.setBounds(263, 475, 113, 39);
		contentPane.add(confirm_Button);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		return_Button.setBounds(440, 475, 113, 39);
		contentPane.add(return_Button);
	}

	private void confirm()
	{
		User usertemp = new User();
		
		// 账号判断
		String phone = this.phone_textField.getText();
		if(StringUtil.isNotEmpty(phone))
		{
			usertemp.setUser_phone(phone);
		}
		else
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "请输入账号！");
			return;
		}
		
		// 姓名判断
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
		
		// 身份证号判断
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
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			usertemp = userDao.ForgetSelect(con, usertemp);
			if(usertemp == null)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "未匹配到对应用户！");
				this.dispose();
				new LoginFrame().setVisible(true);
				return;
			}
			else
			{
				dispose();
				new NewPwdFrame(usertemp).setVisible(true);
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
