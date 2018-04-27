package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import user.UserInfo;
import Controller.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class LoginUI {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public static UserInfo user = new UserInfo();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u4FC4\u7F57\u65AF\u65B9\u5757");
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	    int w = screensize.width;
	    int h = screensize.height;
		frame.setBounds(w/3, h/4, 508, 331);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setBounds(83, 56, 64, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBackground(new Color(51, 255, 255));
		textField.setBounds(144, 53, 102, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 14));
		label_1.setBounds(271, 56, 63, 15);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(102, 255, 255));
		passwordField.setBounds(314, 53, 102, 21);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setBackground(new Color(255, 153, 51));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag = (new Login()).login(textField.getText(),passwordField.getText());
				if (flag == 1) {
					user.setUserName(textField.getText());
					//System.out.println(user.getUserName());
					frame.repaint();
					System.out.println("密码正确");
					frame.setVisible(false);
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
				else {
					frame.repaint();
					System.out.println("密码错误");
					JOptionPane.showMessageDialog(null,"密码错误！请重新输入", "用户登录错误",JOptionPane.ERROR_MESSAGE);


				}
			}
		});
		button.setBounds(327, 211, 70, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.setBackground(new Color(255, 153, 51));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new RegisterUI().frame.setVisible(true);
			}
		});
		button_1.setBounds(103, 211, 70, 23);
		frame.getContentPane().add(button_1);
		
		
		
		ImageIcon img = new ImageIcon("Graphics/background/background2.jpg");
		JLabel lblNewLabel = new JLabel(img);
		lblNewLabel.setBounds(0, 0, 508, 331);
		frame.getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu menu = new JMenu("\u5E2E\u52A9");
		menu.setBackground(new Color(0, 102, 153));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6E38\u620F\u8BF4\u660E");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "左右键控制方向，下键加速，上键变形，空格键暂停和开始","游戏说明：",JOptionPane.INFORMATION_MESSAGE);

			}
		});
		
		menu.add(menuItem);
		menuBar.setBackground(new Color(255, 153, 51));
		JMenuItem menuItem_1 = new JMenuItem("\u6587\u4EF6\u5BFC\u51FA");
		menu.add(menuItem_1);
	}

	
}
