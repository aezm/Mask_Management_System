package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Staff;

import java.awt.Color;
import java.awt.Toolkit;

public class StaffFrame extends JFrame {

	private JPanel staffPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StaffFrame frame = new StaffFrame();
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
	public StaffFrame(Staff staff) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StaffFrame.class.getResource("/images/mask2.png")));
		setTitle("\u6B22\u8FCE\u60A8\uFF01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // ´°Ìå¾ÓÖÐ
		staffPane = new JPanel();
		staffPane.setBackground(Color.WHITE);
		staffPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(staffPane);
		staffPane.setLayout(null);
		
		JButton store_Button = new JButton("\u5E93\u5B58\u7BA1\u7406");
		store_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				new StoreManageFrame(staff).setVisible(true);
			}
		});
		store_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		store_Button.setBounds(321, 305, 179, 52);
		staffPane.add(store_Button);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/mask2.png")));
		iconLabel.setBounds(178, 28, 196, 168);
		staffPane.add(iconLabel);
		
		JLabel title_Label = new JLabel("\u53E3\u7F69\u9884\u7EA6\u7CFB\u7EDF");
		title_Label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 33));
		title_Label.setBounds(405, 85, 204, 44);
		staffPane.add(title_Label);
		
		JButton return_Button = new JButton("\u9000\u51FA\u767B\u5F55");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		return_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		return_Button.setBounds(321, 500, 179, 52);
		staffPane.add(return_Button);
		
		JButton shop_Button = new JButton("\u9886\u53D6\u70B9\u4FE1\u606F\u4FEE\u6539");
		shop_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new ShopMessageFrame(staff).setVisible(true);
			}
		});
		shop_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		shop_Button.setBounds(321, 370, 179, 52);
		staffPane.add(shop_Button);
		
		JButton message_Button = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		message_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new StaffMessageFrame(staff).setVisible(true);
			}
		});
		message_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		message_Button.setBounds(321, 435, 179, 52);
		staffPane.add(message_Button);
		
		JButton finish_Button = new JButton("\u8BA2\u5355\u9886\u53D6");
		finish_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new FinishListFrame(staff).setVisible(true);
			}
		});
		finish_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		finish_Button.setBounds(321, 240, 179, 52);
		staffPane.add(finish_Button);
	}

}
