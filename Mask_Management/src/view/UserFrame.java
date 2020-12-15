package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {

	private JPanel userPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserFrame frame = new UserFrame();
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
	public UserFrame(User user) {
		setTitle("\u6B22\u8FCE\u60A8\uFF01");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // ´°Ìå¾ÓÖÐ
		userPane = new JPanel();
		userPane.setBackground(Color.WHITE);
		userPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(userPane);
		userPane.setLayout(null);
		
		JButton message_Button = new JButton("\u4FE1\u606F\u4FEE\u6539");
		message_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				new UserMessageFrame(user).setVisible(true);
			}
		});
		message_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		message_Button.setBounds(470, 294, 179, 52);
		userPane.add(message_Button);
		
		JButton order_Button = new JButton("\u53E3\u7F69\u9884\u7EA6");
		order_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				new MaskOrderFrame(user).setVisible(true);
			}
		});
		order_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		order_Button.setBounds(167, 294, 179, 52);
		userPane.add(order_Button);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/mask2.png")));
		iconLabel.setBounds(178, 28, 196, 168);
		userPane.add(iconLabel);
		
		JLabel title_Label = new JLabel("\u53E3\u7F69\u9884\u7EA6\u7CFB\u7EDF");
		title_Label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 33));
		title_Label.setBounds(405, 85, 204, 44);
		userPane.add(title_Label);
		
		JButton return_Button = new JButton("\u9000\u51FA\u767B\u5F55");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		return_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		return_Button.setBounds(470, 379, 179, 52);
		userPane.add(return_Button);
		
		JButton list_Button = new JButton("\u8BA2\u5355\u67E5\u8BE2");
		list_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new UserListFrame(user).setVisible(true);
			}
		});
		list_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		list_Button.setBounds(167, 379, 179, 52);
		userPane.add(list_Button);
	}
}
