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
	 * serialVersionUID��Ϲд��
	 */
	private static final long serialVersionUID = 1L;
	private JPanel LoginPane;
	private JTextField nameTextField;
	private JPasswordField pswPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FontUtil.initGobalFont(new Font("΢���ź�", Font.PLAIN, 14));
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
		this.setLocationRelativeTo(null);   // �������
		LoginPane = new JPanel();
		LoginPane.setBackground(Color.WHITE);
		LoginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(LoginPane);
		LoginPane.setLayout(null);
				
		JRadioButton userjrb = new JRadioButton("�û�");
		userjrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		userjrb.setBackground(Color.WHITE);
		userjrb.setLocation(282, 420);
		userjrb.setSize(80, 30);
		JRadioButton staffjrb = new JRadioButton("ְ��");
		staffjrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		staffjrb.setBackground(Color.WHITE);
		staffjrb.setSize(80, 30);
		staffjrb.setLocation(385, 420);
		JRadioButton adminjrb = new JRadioButton("����");
		adminjrb.setFont(new Font("΢���ź�", Font.PLAIN, 15));
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
		loginBtn.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		loginBtn.setBounds(170, 480, 113, 39);
		LoginPane.add(loginBtn);
		
		JButton registerBtn = new JButton("\u6CE8\u518C");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new RegisterFrame().setVisible(true);
			}
		});
		registerBtn.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		registerBtn.setBounds(552, 480, 113, 39);
		LoginPane.add(registerBtn);
		
		JButton forgetBtn = new JButton("\u5FD8\u8BB0\u5BC6\u7801");
		forgetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new ForgetFrame().setVisible(true);
			}
		});
		forgetBtn.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		forgetBtn.setBounds(361, 480, 113, 39);
		LoginPane.add(forgetBtn);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/mask2.png")));
		iconLabel.setBounds(315, 32, 196, 168);
		LoginPane.add(iconLabel);
		
		JLabel TitleLabel = new JLabel("\u53E3\u7F69\u9884\u7EA6\u7CFB\u7EDF");
		TitleLabel.setFont(new Font("΢���ź�", Font.BOLD, 33));
		TitleLabel.setBounds(314, 203, 204, 44);
		LoginPane.add(TitleLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("����", Font.PLAIN, 16));
		nameTextField.setBounds(288, 282, 284, 39);
		LoginPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		pswPasswordField = new JPasswordField();
		pswPasswordField.setFont(new Font("����", Font.PLAIN, 16));
		pswPasswordField.setBounds(288, 349, 284, 39);
		LoginPane.add(pswPasswordField);
		
		JLabel zhanghaoLabel = new JLabel("\u8D26   \u53F7");
		zhanghaoLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		zhanghaoLabel.setBounds(222, 285, 66, 31);
		LoginPane.add(zhanghaoLabel);
		
		JLabel mimaLabel = new JLabel("\u5BC6   \u7801");
		mimaLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		mimaLabel.setBounds(222, 353, 58, 29);
		LoginPane.add(mimaLabel);

	}
	  
	/**
	 * �û���¼����ʵ��
	 * @param evt
	 * @throws Exception 
	 */
	private void loginActionPerformed(ActionEvent evt, String id_type) throws Exception
	{
		String name = this.nameTextField.getText();
		String psw = new String(this.pswPasswordField.getPassword());
		
		// �˺�Ϊ��ʱ
		if(StringUtil.isEmpty(name))
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�");
			return;
		}
		
		// ����Ϊ��ʱ
		if(StringUtil.isEmpty(psw))
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			return;
		}
		
		// �û���¼		
		if("user".equals(id_type))
		{
			User user = new User();
			UserDao userdao = new UserDao();
			
			user.setUser_phone(name);
			user.setUser_pwd(psw);
			user = userdao.login(user);
	
			if(user == null)
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "�˺Ż��������");
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
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "�˺Ż��������");
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
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "�˺Ż��������");
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
