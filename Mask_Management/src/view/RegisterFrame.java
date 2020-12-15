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

import dao.UserDao;
import model.User;
import util.DBUtil;
import util.StringUtil;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Color;
import javax.swing.UIManager;

public class RegisterFrame extends JFrame {
	
	private UserDao userDao = new UserDao();
	private DBUtil dbUtil = new DBUtil();

	private JPanel contentPane;
	private JTextField phone_textField;
	private JPasswordField pwd_passwordField;
	private JPasswordField confpwd_passwordField;
	private JTextField name_textField;
	private JTextField id_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setTitle("\u6CE8\u518C");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // �������
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7528\u6237\u6CE8\u518C", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 46, 659, 367);
		contentPane.add(panel);
		
		phone_textField = new JTextField();
		phone_textField.setBounds(268, 87, 237, 24);
		phone_textField.setColumns(10);
		
		JLabel phone_Label = new JLabel("\u8D26\u53F7\uFF1A");
		phone_Label.setBounds(140, 87, 75, 22);
		phone_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		pwd_passwordField = new JPasswordField();
		pwd_passwordField.setBounds(268, 129, 237, 24);
		
		confpwd_passwordField = new JPasswordField();
		confpwd_passwordField.setBounds(268, 171, 237, 24);
		
		JLabel pwd_Label = new JLabel("\u5BC6\u7801\uFF1A");
		pwd_Label.setBounds(140, 129, 75, 22);
		pwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel confpwd_Label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		confpwd_Label.setBounds(140, 171, 86, 22);
		confpwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		name_textField = new JTextField();
		name_textField.setBounds(268, 213, 237, 24);
		name_textField.setColumns(10);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setBounds(140, 213, 86, 22);
		name_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		id_textField = new JTextField();
		id_textField.setBounds(268, 255, 237, 24);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		id_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		id_Label.setBounds(140, 255, 118, 22);
		panel.setLayout(null);
		panel.add(name_textField);
		panel.add(name_Label);
		panel.add(id_textField);
		panel.add(id_Label);
		panel.add(confpwd_Label);
		panel.add(pwd_Label);
		panel.add(phone_Label);
		panel.add(phone_textField);
		panel.add(pwd_passwordField);
		panel.add(confpwd_passwordField);
		
		JButton register_Button = new JButton("\u6CE8\u518C");
		register_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				register();
			}
		});
		register_Button.setBounds(263, 475, 113, 39);
		contentPane.add(register_Button);
		
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
	
	private void register()
	{
		User usertemp = new User();
		
		// �˺��ж�
		String phone = this.phone_textField.getText();
		if(StringUtil.isNotEmpty(phone))
		{
			usertemp.setUser_phone(phone);
		}
		else
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "�������˺ţ�");
			return;
		}
		
		// �����ж�
		String pwd = new String(this.pwd_passwordField.getPassword());
		String confpwd = new String(this.confpwd_passwordField.getPassword());
		// ȷ�������ж�
		if(StringUtil.isNotEmpty(pwd))   // �ж�������Ƿ�Ϊ��
		{
			if(pwd.length() >= 6)   // �ж����볤���Ƿ����6
			{
				if(confpwd.equals(pwd))   // �ж�ȷ�������Ƿ���������ͬ
				{
					usertemp.setUser_pwd(pwd);
				}
				else
				{
					// ���ð�ť��ʾЧ��
					UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
					// �����ı���ʾЧ��
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
					// ��Ϣ�Ի���
					JOptionPane.showMessageDialog(null, "��ȷ�����룡");
					return;
				}
			}
			else
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "���볤�Ȳ���С��6��");
				return;
			}
		}
		else
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "���������룡");
			return;
		}
		
		// �����ж�
		String name = this.name_textField.getText();
		if(StringUtil.isNotEmpty(name))
		{
			usertemp.setUser_name(name);
		}
		else
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "������������");
			return;
		}
		
		// ���֤���ж�
		String id = this.id_textField.getText();
		if(StringUtil.isNotEmpty(id))
		{
			usertemp.setId(id);
		}
		else
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "���������֤�ţ�");
			return;
		}
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = userDao.addUser(con, usertemp);
			if(modifyNum == 1)
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "ע��ɹ���");
				this.dispose();
				new LoginFrame().setVisible(true);
				return;
			}
			else if(modifyNum == -1)
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "���˺��ѱ�ע�ᣡ");
				return;
			}
			else
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
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
