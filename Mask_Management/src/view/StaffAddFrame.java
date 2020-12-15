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
import dao.StaffDao;
import model.Admin;
import model.Shop;
import model.Staff;
import util.DBUtil;
import util.StringUtil;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Color;

public class StaffAddFrame extends JFrame {

	private DBUtil dbUtil = new DBUtil();
	private StaffDao staffDao = new StaffDao();
	
	private JPanel saf_Pane;
	private JPasswordField pwd_passwordField;
	private JPasswordField confpwd_passwordField;
	private JTextField name_textField;
	private JTextField shop_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffAddFrame frame = new StaffAddFrame();
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
	public StaffAddFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StaffAddFrame.class.getResource("/images/mask2.png")));
		setTitle("\u804C\u5DE5\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // �������
		saf_Pane = new JPanel();
		saf_Pane.setBackground(Color.WHITE);
		saf_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(saf_Pane);
		saf_Pane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u804C\u5DE5\u4FE1\u606F", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 45, 659, 368);
		saf_Pane.add(panel);
		panel.setLayout(null);
		
		pwd_passwordField = new JPasswordField();
		pwd_passwordField.setBounds(278, 103, 216, 24);
		panel.add(pwd_passwordField);
		
		confpwd_passwordField = new JPasswordField();
		confpwd_passwordField.setBounds(278, 156, 216, 24);
		panel.add(confpwd_passwordField);
		
		JLabel pwd_Label = new JLabel("\u5BC6\u7801\uFF1A");
		pwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		pwd_Label.setBounds(158, 102, 90, 24);
		panel.add(pwd_Label);
		
		JLabel confpwd_Label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		confpwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		confpwd_Label.setBounds(158, 155, 114, 24);
		panel.add(confpwd_Label);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		name_Label.setBounds(158, 208, 104, 24);
		panel.add(name_Label);
		
		name_textField = new JTextField();
		name_textField.setBounds(278, 209, 216, 24);
		panel.add(name_textField);
		name_textField.setColumns(10);
		
		shop_textField = new JTextField();
		shop_textField.setColumns(10);
		shop_textField.setBounds(278, 262, 216, 24);
		panel.add(shop_textField);
		
		JLabel shop_Label = new JLabel("\u9886\u53D6\u70B9\u7F16\u53F7\uFF1A");
		shop_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		shop_Label.setBounds(158, 261, 104, 24);
		panel.add(shop_Label);
		
		JButton change_Button = new JButton("\u6DFB\u52A0");
		change_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				addAction();
			}
		});
		change_Button.setBounds(263, 475, 113, 39);
		saf_Pane.add(change_Button);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new AdminFrame().setVisible(true);
			}
		});
		return_Button.setBounds(440, 475, 113, 39);
		saf_Pane.add(return_Button);
	}

	private void reset()
	{
		this.pwd_passwordField.setText("");
		this.confpwd_passwordField.setText("");
		this.name_textField.setText("");
		this.shop_textField.setText("");
	}
	
	/**
	 * ���ְ��
	 */
	private void addAction()
	{
		Staff stafftemp = new Staff();
		String pwd = new String(this.pwd_passwordField.getPassword());
		String confpwd = new String(this.confpwd_passwordField.getPassword());
		
		// ȷ�������ж�
		if(StringUtil.isNotEmpty(pwd))   // �ж�������Ƿ�Ϊ��
		{
			if(pwd.length() >= 6)   // �ж����볤���Ƿ����6
			{
				if(confpwd.equals(pwd))   // �ж�ȷ�������Ƿ���������ͬ
				{
					stafftemp.setStaff_pwd(pwd);
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
		
		
		// ���������ж�
		String name = this.name_textField.getText();
		if(StringUtil.isNotEmpty(name))
		{
			stafftemp.setStaff_name(name);
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
		
		Shop shoptemp = new Shop();
		String shop_id = this.shop_textField.getText();
		if(StringUtil.isNotEmpty(shop_id))
		{
			shoptemp.setShop_id(Integer.parseInt(shop_id));;
		}
		else
		{
			// ���ð�ť��ʾЧ��
			UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// �����ı���ʾЧ��
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
			// ��Ϣ�Ի���
			JOptionPane.showMessageDialog(null, "��������ȡ���ţ�");
			return;
		}
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int id = staffDao.add(con, stafftemp, shoptemp);

			if(id != -1)
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "��ӳɹ���\n����ְ���˺�Ϊ"+ id + '��');
				reset();
				return;
			}
			else
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
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
