package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import dao.StaffDao;
import dao.UserDao;
import model.Staff;
import model.User;
import util.DBUtil;
import util.StringUtil;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class StaffMessageFrame extends JFrame {

	private StaffDao staffDao = new StaffDao();
	private DBUtil dbUtil = new DBUtil();
	
	private JPanel smf_Pane;
	private JTextField id_textField;
	private JPasswordField pwd_passwordField;
	private JPasswordField oldpwd_passwordField;
	private JPasswordField newpwd_passwordField;
	private JTextField name_textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StaffMessageFrame frame = new StaffMessageFrame();
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
	public StaffMessageFrame(Staff staff) {
		setTitle("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StaffMessageFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // �������
		smf_Pane = new JPanel();
		smf_Pane.setBackground(Color.WHITE);
		smf_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(smf_Pane);
		smf_Pane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u804C\u5DE5\u4FE1\u606F", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(79, 46, 659, 367);
		smf_Pane.add(panel);
		
		id_textField = new JTextField();
		id_textField.setBounds(268, 70, 193, 24);
		id_textField.setEditable(false);
		id_textField.setColumns(10);
		
		JLabel id_Label = new JLabel("\u8D26\u53F7\uFF1A");
		id_Label.setBounds(189, 69, 59, 24);
		id_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		panel.setLayout(null);
		
		pwd_passwordField = new JPasswordField();
		pwd_passwordField.setEditable(false);
		pwd_passwordField.setBounds(268, 123, 193, 24);
		panel.add(pwd_passwordField);
		panel.add(id_Label);
		panel.add(id_textField);
		
		oldpwd_passwordField = new JPasswordField();
		oldpwd_passwordField.setBounds(268, 176, 193, 24);
		panel.add(oldpwd_passwordField);
		
		newpwd_passwordField = new JPasswordField();
		newpwd_passwordField.setBounds(268, 229, 193, 24);
		panel.add(newpwd_passwordField);
		
		name_textField = new JTextField();
		name_textField.setBounds(268, 282, 193, 24);
		panel.add(name_textField);
		name_textField.setColumns(10);
		
		JLabel name_Label = new JLabel("\u59D3\u540D\uFF1A");
		name_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		name_Label.setBounds(189, 282, 59, 24);
		panel.add(name_Label);
		
		JLabel pwd_Label = new JLabel("\u5BC6\u7801\uFF1A");
		pwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		pwd_Label.setBounds(189, 123, 59, 24);
		panel.add(pwd_Label);
		
		JLabel oldpwd_Label = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
		oldpwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		oldpwd_Label.setBounds(189, 176, 73, 24);
		panel.add(oldpwd_Label);
		
		JLabel newpwd_Label = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		newpwd_Label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		newpwd_Label.setBounds(189, 229, 73, 24);
		panel.add(newpwd_Label);
		
		JButton change_Button = new JButton("\u4FEE\u6539");
		change_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				changeMessage(staff);
			}
		});
		change_Button.setBounds(263, 475, 113, 39);
		smf_Pane.add(change_Button);
		
		JButton return_Button = new JButton("\u8FD4\u56DE");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new StaffFrame(staff).setVisible(true);
			}
		});
		return_Button.setBounds(440, 475, 113, 39);
		smf_Pane.add(return_Button);
		
		fillblanks(staff);
	}
	
	private void fillblanks(Staff staff)
	{
		this.id_textField.setText("" + staff.getStaff_id());   // IntתString
		this.pwd_passwordField.setText(staff.getStaff_pwd());
		this.name_textField.setText(staff.getStaff_name());
	}
	
	private void changeMessage(Staff staff)
	{
		Staff stafftemp = new Staff();
		
		// ��������ж�
		String pwd = new String(this.pwd_passwordField.getPassword());
		String oldpwd = new String(this.oldpwd_passwordField.getPassword());
		String newpwd = new String(this.newpwd_passwordField.getPassword());
		if(StringUtil.isNotEmpty(oldpwd))   // �жϾ�������Ƿ�Ϊ��
		{
			if(oldpwd.equals(pwd))   // �жϾ������Ƿ��ԭ������ͬ 
			{
				if(StringUtil.isNotEmpty(newpwd))   // �ж���������Ƿ�Ϊ��
				{
					if(newpwd.length() >= 6)   // �ж������볤���Ƿ����6
					{
						stafftemp.setStaff_pwd(newpwd);
					}
					else
					{
						// ���ð�ť��ʾЧ��
						UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
						// �����ı���ʾЧ��
						UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
						// ��Ϣ�Ի���
						JOptionPane.showMessageDialog(null, "���볤�Ȳ�������6λ��");
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
					JOptionPane.showMessageDialog(null, "�����������룡");
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
				JOptionPane.showMessageDialog(null, "���벻��ȷ��");
				return;
			}
		}
		else
		{
			stafftemp.setStaff_pwd(new String(this.pwd_passwordField.getPassword()));
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
			
		stafftemp.setStaff_id(Integer.parseInt(this.id_textField.getText()));
		
		Connection con = null;
		try
		{
			con = dbUtil.getCon();
			int modifyNum = staffDao.updateMessage(con, stafftemp);
			if(modifyNum == 1)
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				staff.setStaff_name(stafftemp.getStaff_name());
				staff.setStaff_pwd(stafftemp.getStaff_pwd());
				this.dispose();
				new StaffFrame(staff).setVisible(true);
				return;
			}
			else
			{
				// ���ð�ť��ʾЧ��
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// �����ı���ʾЧ��
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("΢���ź�", Font.PLAIN, 15)));
				// ��Ϣ�Ի���
				JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
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
