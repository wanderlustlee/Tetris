package view;

import javax.swing.JPanel;

import Controller.GameController;
import model.MusicPlayer;
import dao.UserDaoImplements;
import entity.GameWindow;
import entity.LabelWithBG;
import entity.MyButton;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import user.UserInfo;

import java.awt.Color;

/**
 * ����ģʽ�µĽ���
 *
 */
public class OfflinePanel extends JPanel {
	public static int score;
	private GameController localController;
	
	private LabelWithBG newbackground;
	
	private GameWindow mainWindow;
	private MyButton btnMusic;
	private MyButton btnResume;
	private MyButton btnPause;
	private JLabel lblScroe,lblLevel;
	
	
	public void setLocalController(GameController gameController){
		this.localController=gameController;
	}
	
	/**
	 * Create the panel.
	 */
	public OfflinePanel() {
		setLayout(null);
		
		newbackground = new LabelWithBG(508, 460,"Graphics/background/background.jpg");
		
		mainWindow = new GameWindow(10, 10, 236, 405);
		mainWindow.setBounds(25, 10, 236, 405);
		add(mainWindow);
		
		JLabel lblLabei= new JLabel("\u97F3\u4E50");//����
		lblLabei.setForeground(new Color(255,69,0));
		lblLabei.setFont(new Font("����", Font.BOLD, 17));
		lblLabei.setBounds(271, 26, 54, 15);
		add(lblLabei);
		
		JLabel lblLabei_1 = new JLabel("\u7B49\u7EA7");//�ȼ�
		lblLabei_1.setForeground(new Color(255,69,0));
		lblLabei_1.setFont(new Font("����", Font.BOLD, 17));
		lblLabei_1.setBounds(283, 77, 54, 15);
		add(lblLabei_1);
		
		lblLevel = new JLabel("0");
		lblLevel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblLevel.setForeground(new Color(255,69,0));
		lblLevel.setBounds(293, 102, 54, 15);
		add(lblLevel);
		
		JLabel label_1 = new JLabel("\u5206\u6570");//����
		label_1.setForeground(new Color(255,69,0));
		label_1.setFont(new Font("����", Font.BOLD, 17));
		label_1.setBounds(285, 151, 54, 15);
		add(label_1);
		
		lblScroe = new JLabel("0");
		lblScroe.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblScroe.setForeground(new Color(255,69,0));
		lblScroe.setBounds(293, 176, 54, 15);
		add(lblScroe);
		
		JLabel label_3 = new JLabel("\u4E0B\u4E00\u4E2A\u5F62\u72B6");//��һ����״
		label_3.setForeground(new Color(255,69,0));
		label_3.setFont(new Font("����", Font.BOLD, 17));
		label_3.setBounds(268, 219, 100, 15);
		add(label_3);
		
		// ��ʼ����ͣ��
		btnResume = new MyButton("","��ʼ",86,52);
		btnResume.setForeground(new Color(255,69,0));
		btnResume.setFont(new Font("����", Font.BOLD, 20));
		btnResume.setBounds(271, 325, 86, 23);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyResume();
				if(!MusicPlayer.isRunning()){
					MusicPlayer.bgmPlay();
				}
			}
		});
		add(btnResume);
		
		btnPause = new MyButton("Graphics/window/null.png","��ͣ",86,52);
		btnPause.setFont(new Font("����", Font.BOLD, 20));
		btnPause.setForeground(new Color(255,69,0));
		btnPause.setBounds(271, 363, 86, 28);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyPause();
				if(MusicPlayer.isRunning()){
					MusicPlayer.bgmStop();
				}
			}
		});
		add(btnPause);
				
		btnMusic = new MyButton("Graphics/button/musicOff.png","",
				40, 30);
		btnMusic.setBounds(323, 23, 40, 25);
		btnMusic.setBorderPainted(false);
		btnMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MusicPlayer.isturnOn()){
					MusicPlayer.setturnOn(false);
					MusicPlayer.bgmStop();
					// ��ͼƬ
					btnMusic.setNewImage("Graphics/button/musicOff.png");
				}else{
					MusicPlayer.setturnOn(true);
					MusicPlayer.bgmPlay();
					// ��ͼƬ
					btnMusic.setNewImage("Graphics/button/musicOn.png");
				}
			}
		});
		add(btnMusic);
		
		mainWindow.setFocusable(false);
		btnResume.setFocusable(false);
		btnPause.setFocusable(false);
		btnMusic.setFocusable(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		newbackground.draw(g);
		
		mainWindow.creatwindow(g);
		try{
			lblLevel.setText(Integer.toString(localController.getGamedao().level));
			lblScroe.setText(Integer.toString(localController.getGamedao().score));
			score = localController.getGamedao().score;
			// ����������������һ����״��λ��
			localController.getCurRect().draw(g, 12, 32);
			localController.getNextRect().draw(g, 200, 265);
			localController.getGamedao().drawmap(g,0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
