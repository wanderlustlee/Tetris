package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.UserInfo;
import Controller.GameController;
import Controller.KeyController;
import Controller.RemoteController;
import MySocket.Client;
import MySocket.ExchangeThread;
import MySocket.Server;
import model.MusicPlayer;
/**
 * ������Ϸ�Ŀ��
 * 
 *
 */
public class MainFrame extends JFrame {

	private String[] title={"����˹���鵥����","����˹����������ս��"};
	private int[] size={415,760};
	public UserInfo user= LoginUI.user;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MusicPlayer.bgmPlay();
		
		//���ñ���
		this.setTitle("����˹����");
		//���ô����С
		this.setSize(470,390);
		//����
		this.setLocationRelativeTo(null);
		//�ر��¼�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����Ĭ��Panel
		this.setContentPane(new LauncherJPanel(this));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 21);
		this.getContentPane().add(menuBar);
		
		JMenu menu = new JMenu("\u5E2E\u52A9");
		menu.setBackground(new Color(0, 102, 153));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u6E38\u620F\u8BF4\u660E");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "���Ҽ����Ʒ����¼����٣��ϼ����Σ��ո����ͣ�Ϳ�ʼ","��Ϸ˵����",JOptionPane.INFORMATION_MESSAGE);

			}
		});
		
		menu.add(menuItem);
		menuBar.setBackground(new Color(255, 153, 51));
		
	}
	
	public void chooseMode(int mode){
		System.out.println("mode"+mode);
		int matchPort = 0;
		switch (mode){
		case 0:
			OfflinePanel offlinePanel=new OfflinePanel();
			GameController.localController=new GameController(offlinePanel);
			offlinePanel.setLocalController(GameController.localController);
			this.setContentPane(offlinePanel);
			this.addKeyListener(new KeyController(GameController.localController));
			// ����ˢ��һ�½���ſ���,
			this.setTitle(title[0]);
			this.setSize(size[0],470);
			GameController.localController.gameStart();
			break;
		case 1:
			String port=JOptionPane.showInputDialog("�����뷿���:");
			while (!ExchangeThread.isNum(port)) {
				JOptionPane.showMessageDialog(null, "���������֣�");
				System.out.println("���������֣�");
				port=JOptionPane.showInputDialog("�����뷿���:");
			}
				matchPort = Integer.parseInt(port);
				Server.Init(Integer.parseInt(port));
				System.out.println("���ӳɹ�");
				
				OnlinePanel onlinePanel=new OnlinePanel();
				GameController.localController=new GameController(Server.getExchangeThread(),onlinePanel);
				RemoteController.remoteController=new RemoteController(onlinePanel);
				onlinePanel.setLocalController(GameController.localController);
				onlinePanel.setRemoteController(RemoteController.remoteController);
				this.setContentPane(onlinePanel);
				this.addKeyListener(new KeyController(GameController.localController));
				// ����ˢ��һ�½���ſ���,
				this.setTitle(title[1]);
				this.setSize(size[1],470);
				GameController.localController.gameStart();
				break;
			
		case 2:
			String port2=JOptionPane.showInputDialog("�����뷿���:");
			while (!ExchangeThread.isNum(port2)) {
				JOptionPane.showMessageDialog(null, "���������֣�");
				System.out.println("���������֣�");
				port2=JOptionPane.showInputDialog("�����뷿���:");
			}
			
			Client.Init(Integer.parseInt(port2));
			System.out.println("���ӳɹ�");

			OnlinePanel onlinePanel2=new OnlinePanel();
			GameController.localController=new GameController(Client.getExchangeThread(),onlinePanel2);
			RemoteController.remoteController=new RemoteController(onlinePanel2);
			onlinePanel2.setLocalController(GameController.localController);
			onlinePanel2.setRemoteController(RemoteController.remoteController);
			this.setContentPane(onlinePanel2);
			this.addKeyListener(new KeyController(GameController.localController));
			// ����ˢ��һ�½���ſ���,
			this.setTitle(title[1]);
			this.setSize(size[1],470);
			GameController.localController.gameStart();
			break;
		}
		requestFocus();
	}

}
