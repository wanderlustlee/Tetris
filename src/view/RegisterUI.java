package view;

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

import Controller.Register;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class RegisterUI {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI window = new RegisterUI();
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
	public RegisterUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u7528\u6237\u6CE8\u518C");
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	    int w = screensize.width;
	    int h = screensize.height;
		frame.setBounds(w/3, h/4, 508, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setBounds(87, 50, 64, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBackground(new Color(102, 255, 255));
		textField.setColumns(10);
		textField.setBounds(143, 47, 102, 21);
		frame.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("宋体", Font.BOLD, 14));
		label_1.setBounds(256, 50, 54, 15);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("注册");
		button.setBackground(new Color(255, 153, 51));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag = new Register().register(textField.getText(), textField_1.getText());
				if (flag == 1) {
					JOptionPane.showMessageDialog(null,"注册成功！", "用户注册",JOptionPane.ERROR_MESSAGE);
					frame.setVisible(false);
					new LoginUI().frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"注册失败！", "用户注册",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		button.setBounds(368, 216, 70, 23);
		frame.getContentPane().add(button);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(102, 255, 255));
		textField_1.setBounds(297, 47, 102, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		ImageIcon img = new ImageIcon("Graphics/background/background2.jpg");
		JLabel lblNewLabel = new JLabel(img);
		lblNewLabel.setBounds(0, 0, 508, 331);
		frame.getContentPane().add(lblNewLabel);
		
	}
}
