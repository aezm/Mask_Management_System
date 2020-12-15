package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import dao.AdminDao;
import dao.StaffDao;
import dao.UserDao;
import model.Admin;
import model.Staff;
import model.User;
import util.DBUtil;
import util.FontUtil;
import util.StringUtil;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButtonMenuItem;

public class LoginFrame extends JFrame {

	/**
	 * serialVersionUID是瞎写的
	 */
	private static final long serialVersionUID = 1L;
	private JPanel LoginPane;
	private JTextField nameTextField;
	private JPasswordField pswPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FontUtil.initGobalFont(new Font("微软雅黑", Font.PLAIN, 14));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setResizable(false);
		setTitle("\u7528\u6237\u767B\u5F55");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // 窗体居中
		LoginPane = new JPanel();
		LoginPane.setBackground(Color.WHITE);
		LoginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPane);
		LoginPane.setLayout(null);
				
		JRadioButton userjrb = new JRadioButton("用户");
		userjrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		userjrb.setBackground(Color.WHITE);
		userjrb.setLocation(282, 420);
		userjrb.setSize(80, 30);
		JRadioButton staffjrb = new JRadioButton("职工");
		staffjrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		staffjrb.setBackground(Color.WHITE);
		staffjrb.setSize(80, 30);
		staffjrb.setLocation(385, 420);
		JRadioButton adminjrb = new JRadioButton("管理");
		adminjrb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		adminjrb.setLocation(485, 420);
		adminjrb.setBackground(Color.WHITE);
		adminjrb.setSize(80, 30);
		ButtonGroup idjrbBG = new ButtonGroup();
		idjrbBG.add(userjrb);
		idjrbBG.add(staffjrb);
		idjrbBG.add(adminjrb);
		userjrb.setSelected(true);
		LoginPane.add(userjrb);
		LoginPane.add(staffjrb);
		LoginPane.add(adminjrb);
		
		JButton loginBtn = new JButton("\u767B\u5F55");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					if(userjrb.isSelected())
					{
						loginActionPerformed(evt, "user");
					}
					else if(adminjrb.isSelected())
					{
						loginActionPerformed(evt, "admin");
					}
					else if(staffjrb.isSelected())
					{
						loginActionPerformed(evt, "staff");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		loginBtn.setBounds(170, 480, 113, 39);
		LoginPane.add(loginBtn);
		
		JButton registerBtn = new JButton("\u6CE8\u518C");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new RegisterFrame().setVisible(true);
			}
		});
		registerBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		registerBtn.setBounds(552, 480, 113, 39);
		LoginPane.add(registerBtn);
		
		JButton forgetBtn = new JButton("\u5FD8\u8BB0\u5BC6\u7801");
		forgetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new ForgetFrame().setVisible(true);
			}
		});
		forgetBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		forgetBtn.setBounds(361, 480, 113, 39);
		LoginPane.add(forgetBtn);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/mask2.png")));
		iconLabel.setBounds(315, 32, 196, 168);
		LoginPane.add(iconLabel);
		
		JLabel TitleLabel = new JLabel("\u53E3\u7F69\u9884\u7EA6\u7CFB\u7EDF");
		TitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 33));
		TitleLabel.setBounds(314, 203, 204, 44);
		LoginPane.add(TitleLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("宋体", Font.PLAIN, 16));
		nameTextField.setBounds(288, 282, 284, 39);
		LoginPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		pswPasswordField = new JPasswordField();
		pswPasswordField.setFont(new Font("宋体", Font.PLAIN, 16));
		pswPasswordField.setBounds(288, 349, 284, 39);
		LoginPane.add(pswPasswordField);
		
		JLabel zhanghaoLabel = new JLabel("\u8D26   \u53F7");
		zhanghaoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		zhanghaoLabel.setBounds(222, 285, 66, 31);
		LoginPane.add(zhanghaoLabel);
		
		JLabel mimaLabel = new JLabel("\u5BC6   \u7801");
		mimaLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		mimaLabel.setBounds(222, 353, 58, 29);
		LoginPane.add(mimaLabel);

	}
	  
	/**
	 * 用户登录功能实现
	 * @param evt
	 * @throws Exception 
	 */
	private void loginActionPerformed(ActionEvent evt, String id_type) throws Exception
	{
		String name = this.nameTextField.getText();
		String psw = new String(this.pswPasswordField.getPassword());
		
		// 账号为空时
		if(StringUtil.isEmpty(name))
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "账号不能为空！");
			return;
		}
		
		// 密码为空时
		if(StringUtil.isEmpty(psw))
		{
			// 设置按钮显示效果
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 设置文本显示效果
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
			// 消息对话框
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		
		// 用户登录		
		if("user".equals(id_type))
		{
			User user = new User();
			UserDao userdao = new UserDao();
			
			user.setUser_phone(name);
			user.setUser_pwd(psw);
			user = userdao.login(user);
	
			if(user == null)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "账号或密码错误！");
				return;
			}
			else
			{
				System.out.println(user.getLast_order_time());
				dispose();
				new UserFrame(user).setVisible(true);
			}
		}
		else if("staff".equals(id_type))
		{
			Staff staff = new Staff();
			StaffDao staffdao = new StaffDao();
			
			staff.setStaff_id(Integer.parseInt(name));
			staff.setStaff_pwd(psw);
			staff = staffdao.login(staff);
	
			if(staff == null)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "账号或密码错误！");
				return;
			}
			else
			{
				dispose();
				new StaffFrame(staff).setVisible(true);
			}
		}
		else if("admin".equals(id_type))
		{
			Admin admin = new Admin();
			AdminDao admindao = new AdminDao();
			
			admin.setAdmin_id(Integer.parseInt(name));
			admin.setAdmin_pwd(psw);
			admin = admindao.login(admin);
	
			if(admin == null)
			{
				// 设置按钮显示效果
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 设置文本显示效果
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 15)));
				// 消息对话框
				JOptionPane.showMessageDialog(null, "账号或密码错误！");
				return;
			}
			else
			{
				dispose();
				new AdminFrame().setVisible(true);
			}
		}
	}
}
