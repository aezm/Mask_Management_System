package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {

	private JPanel adminPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		setTitle("\u6B22\u8FCE\u60A8\uFF01");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminFrame.class.getResource("/images/mask2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 633);
		this.setLocationRelativeTo(null);   // ´°Ìå¾ÓÖÐ
		adminPane = new JPanel();
		adminPane.setBackground(Color.WHITE);
		adminPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(adminPane);
		adminPane.setLayout(null);
		
		JButton mask_Button = new JButton("\u53E3\u7F69\u7BA1\u7406");
		mask_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new MaskManageFrame().setVisible(true);
			}
		});
		mask_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		mask_Button.setBounds(167, 370, 179, 45);
		adminPane.add(mask_Button);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/mask2.png")));
		iconLabel.setBounds(178, 58, 196, 168);
		adminPane.add(iconLabel);
		
		JLabel title_Label = new JLabel("\u53E3\u7F69\u9884\u7EA6\u7CFB\u7EDF");
		title_Label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 33));
		title_Label.setBounds(405, 117, 204, 44);
		adminPane.add(title_Label);
		
		JButton return_Button = new JButton("\u9000\u51FA\u767B\u5F55");
		return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		return_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		return_Button.setBounds(470, 430, 179, 45);
		adminPane.add(return_Button);
		
		JButton user_Button = new JButton("\u7528\u6237\u7BA1\u7406");
		user_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new UserMamageFrame().setVisible(true);
			}
		});
		user_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		user_Button.setBounds(167, 430, 179, 45);
		adminPane.add(user_Button);
		
		JButton admin_Button = new JButton("\u7BA1\u7406\u5458\u6DFB\u52A0");
		admin_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new AdminAddFrame().setVisible(true);
			}
		});
		admin_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		admin_Button.setBounds(470, 310, 179, 45);
		adminPane.add(admin_Button);
		
		JButton shop_Button = new JButton("\u9886\u53D6\u70B9\u7BA1\u7406");
		shop_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new ShopManageFrame().setVisible(true);
			}
		});
		shop_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		shop_Button.setBounds(167, 310, 179, 45);
		adminPane.add(shop_Button);
		
		JButton staff_Button = new JButton("\u804C\u5DE5\u6DFB\u52A0");
		staff_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				dispose();
				new StaffAddFrame().setVisible(true);
			}
		});
		staff_Button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		staff_Button.setBounds(470, 370, 179, 45);
		adminPane.add(staff_Button);
	}

}
