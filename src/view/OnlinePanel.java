package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import user.UserInfo;
import Controller.GameController;
import model.MusicPlayer;
import Controller.RemoteController;
import entity.GameWindow;
import entity.LabelWithBG;
import entity.MyButton;

/**
 * 双人模式下的界面
 * @author Meng
 *
 */
public class OnlinePanel extends JPanel {
	public UserInfo user= LoginUI.user;
	private GameController localController;
	private RemoteController remoteController;
	
	private LabelWithBG newbackground;
	
	private GameWindow mainWindow;
	private JLabel lblScroe,lblLevel;
	
	private GameWindow mainWindow2;
	private JLabel lblScroe2,lblLevel2;
	
	private MyButton btnMusic;
	private MyButton btnResume;
	private MyButton btnPause;
	private JLabel lblNewLabel;
	
	public void setLocalController(GameController gameController){
		this.localController=gameController;
	}
	public void setRemoteController(RemoteController remoteController){
		this.remoteController=remoteController;
	}
	/**
	 * Create the panel.
	 */
	public OnlinePanel() {
		setLayout(null);
		
		int width=155;
		int heiht=53;
		
		newbackground = new LabelWithBG(508, 460,"Graphics/background/background.jpg");
		
		// -------------1------------------------------
		mainWindow = new GameWindow(10, 10, 233, 405);
		mainWindow.setBounds(10, 10, 233, 405);
		add(mainWindow);
		
		JLabel lblLabei= new JLabel("\u97F3\u4E50");
		lblLabei.setForeground(new Color(255,69,0));
		lblLabei.setFont(new Font("黑体", Font.BOLD, 16));
		lblLabei.setBounds(283, 37, 54, 15);
		add(lblLabei);
		
		JLabel lblLabei_1 = new JLabel("\u7B49\u7EA7");
		lblLabei_1.setForeground(new Color(255,69,0));
		lblLabei_1.setFont(new Font("黑体", Font.BOLD, 16));
		lblLabei_1.setBounds(283, 77, 54, 15);
		add(lblLabei_1);
		
		lblLevel = new JLabel("0");
		lblLevel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblLevel.setForeground(new Color(255,69,0));
		lblLevel.setBounds(293, 102, 54, 15);
		add(lblLevel);
		
		JLabel label_1 = new JLabel("\u5206\u6570");
		label_1.setForeground(new Color(255,69,0));
		label_1.setFont(new Font("黑体", Font.BOLD, 16));
		label_1.setBounds(285, 151, 54, 15);
		add(label_1);
		
		lblScroe = new JLabel("0");
		lblScroe.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblScroe.setForeground(new Color(255,69,0));
		lblScroe.setBounds(293, 176, 54, 15);
		add(lblScroe);
		
		JLabel label_3 = new JLabel("\u4E0B\u4E00\u4E2A\u5F62\u72B6");
		label_3.setForeground(new Color(255,69,0));
		label_3.setFont(new Font("黑体", Font.BOLD, 16));
		label_3.setBounds(268, 219, 86, 15);
		add(label_3);
		
		// --------------------2
		mainWindow2 = new GameWindow(505, 10, 233, 405);
		mainWindow2.setBounds(505, 10, 233, 405);
		add(mainWindow2);
		
		JLabel label = new JLabel("\u7B49\u7EA7");
		label.setForeground(new Color(255,69,0));
		label.setFont(new Font("黑体", Font.BOLD, 16));
		label.setBounds(420, 77, 54, 15);
		add(label);
		
		lblLevel2 = new JLabel("0");
		lblLevel2.setForeground(new Color(255,69,0));
		lblLevel2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblLevel2.setBounds(430, 102, 54, 15);
		add(lblLevel2);
		
		JLabel label_4 = new JLabel("\u5206\u6570");
		label_4.setForeground(new Color(255,69,0));
		label_4.setFont(new Font("黑体", Font.BOLD, 16));
		label_4.setBounds(422, 151, 54, 15);
		add(label_4);
		
		lblScroe2 = new JLabel("0");
		lblScroe2.setForeground(new Color(255,69,0));
		lblScroe2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblScroe2.setBounds(430, 176, 54, 15);
		add(lblScroe2);
		
		JLabel label_6 = new JLabel("\u4E0B\u4E00\u4E2A\u5F62\u72B6");
		label_6.setForeground(new Color(255,69,0));
		label_6.setFont(new Font("黑体", Font.BOLD, 16));
		label_6.setBounds(405, 219, 86, 15);
		add(label_6);
				
		// 开始和暂停键
		btnResume = new MyButton("Graphics/window/null.png","开始",86,52);
		btnResume.setForeground(new Color(255,69,0));
		btnResume.setFont(new Font("黑体", Font.BOLD, 20));
		btnResume.setBounds(271, 354, 86, 23);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localController.keyResume();
				if(!MusicPlayer.isRunning()){
					MusicPlayer.bgmPlay();
				}
			}
		});
		add(btnResume);
		
		btnPause = new MyButton("Graphics/window/null.png","暂停",86,52);
		btnPause.setFont(new Font("黑体", Font.BOLD, 20));
		btnPause.setForeground(new Color(255,69,0));
		btnPause.setBounds(398, 354, 86, 23);
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
				30, 25);
		btnMusic.setBounds(388, 30, 30, 25);
		btnMusic.setBorderPainted(false);
		btnMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MusicPlayer.isturnOn()){
					MusicPlayer.setturnOn(false);
					MusicPlayer.bgmStop();
					// 换图片
					btnMusic.setNewImage("Graphics/button/musicOff.png");
				}else{
					MusicPlayer.setturnOn(true);
					MusicPlayer.bgmPlay();
					// 换图片
					btnMusic.setNewImage("Graphics/button/musicOn.png");
				}
			}
		});
		add(btnMusic);
		
		mainWindow.setFocusable(false);
		btnResume.setFocusable(false);
		btnPause.setFocusable(false);
		btnMusic.setFocusable(false);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(376, 57, 2, 259);
		ImageIcon icon1=new ImageIcon("Graphics/window/white.png");
        icon1.setImage(icon1.getImage().getScaledInstance((int)(2*0.9),(int)(259*0.9),Image.SCALE_DEFAULT)); 
        lblNewLabel.setIcon(icon1);
		add(lblNewLabel);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		newbackground.draw(g);
		// 1-------------------------------
		mainWindow.creatwindow(g);
		
		// 2-------------------------------
		mainWindow2.creatwindow(g);
		try{
			lblLevel.setText(Integer.toString(localController.getGamedao().level));
			lblScroe.setText(Integer.toString(localController.getGamedao().score));
			
			lblLevel2.setText(Integer.toString(remoteController.getGameDao().level));
			lblScroe2.setText(Integer.toString(remoteController.getGameDao().score));
			
			// 在这里控制下落跟下一个形状的位置
			localController.getCurRect().draw(g, 12, 32);
			localController.getNextRect().draw(g, 200, 265);
			localController.getGamedao().drawmap(g,0);
			
			// 在这里控制下落跟下一个形状的位置
			remoteController.getCurRect().draw(g, 506, 32);
			remoteController.getNextRect().draw(g, 335, 265);
			remoteController.getGameDao().drawmap(g,1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
