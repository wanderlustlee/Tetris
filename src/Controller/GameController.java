package Controller;

import MySocket.ExchangeThread;
import dao.UserDaoImplements;
import entity.Rect;
import model.GameDao;
import user.UserInfo;
import view.LoginUI;
import view.OfflinePanel;
import view.OnlinePanel;

import javax.swing.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ��Ϸ���������������������ƶ�����ͣ��Ϸ��
 * 
 */
public class GameController {
	public UserInfo user= LoginUI.user;
	public static GameController localController;

	// ����
	private JPanel panel;
	// ʱ�������������GameTask��ÿ��һ��ʱ�䣬����ͱ仯һ��
	private Timer timer;

	private boolean isRunning =false;

	public GameDao getGamedao() {
		return gamedao;
	}

	public Rect getCurRect() {
		return curRect;
	}

	public Rect getNextRect() {
		return nextRect;
	}

	// ��Ϸ���̿�������������ײ���֮���
	private GameDao gamedao;

	// ��ǰͼ������һ��ͼ��
	private Rect curRect;
	private Rect nextRect;

	// Զ��ͨ���õ��߳�
	private ExchangeThread exchangeThread;

	private class GameTask extends TimerTask {
		private int speed = 5;
        public void run() {

			if(!isRunning){
				return ;
			}

			// speed������ʱ��������
        	if(speed <= 0){
				if(gamedao.isput(curRect)){
					if(gamedao.gameover()) {
						System.out.println("��Ϸ������");
						// ����ͣ��Ϸ
						isRunning = false;
						//��սģʽ
						if(exchangeThread!=null){
							exchangeThread.sendMessage("gameover");
							int myScore = gamedao.score;
							int remoteScore = RemoteController.remoteController.getGameDao().score;

							String str = Integer.toString(myScore) + "��" + Integer.toString(remoteScore) + ",";
							if (myScore > remoteScore) {
								// WIN
								JOptionPane.showMessageDialog(panel, str + "��Ӯ��");
							} else if (myScore < remoteScore) {
								// LOSE
								JOptionPane.showMessageDialog(panel, str + "������");
							} else {
								// pingju
								JOptionPane.showMessageDialog(panel, str + "����һ��ƽ��");
							}
						}else{
							//����ģʽ
							int myScore = gamedao.score;
							System.out.println("����1");
							user.setScore(myScore);
							new UserDaoImplements().writeScore(user);
							System.out.println("����2");
							JOptionPane.showMessageDialog(panel, "��Ϸ����."+
							"��ĵ÷�Ϊ:"+Integer.toString(myScore));
						}
						return;
					}
					Random random = new Random();
					// �Ѿ��������˵���˼
					if(exchangeThread!=null){
						exchangeThread.sendMessage("isput");
					}
					curRect.setColor(0);
					int temp=random.nextInt(7)+1;
					curRect = new Rect(nextRect.color);
					nextRect = new Rect(temp);
					if(exchangeThread!=null){
						// ����rect������,ֻ������һ����
						exchangeThread.sendMessage(Integer.toString(temp));
					}
					if(gamedao.ispop()){
						// ��ȥһ��
						if(exchangeThread!=null){
							exchangeThread.sendMessage("ispop");
						}
					}
                }else{
					curRect.down();
					if(exchangeThread!=null)
						exchangeThread.sendMessage("down");
				}
				// ���û�з���������down
                panel.repaint();
                speed=10-gamedao.level;
        	}
        	else{
				speed--;
			}
        }
    }
	public GameController(JPanel panel){
		this.panel=(OfflinePanel)panel;
	}

	public GameController(ExchangeThread thread,OnlinePanel panel) {
		this.exchangeThread=thread;
		this.panel=(OnlinePanel)panel;


//		Random random = new Random();
//		// �����������
//		this.curRect = new Rect(random.nextInt(7)+1);
//		this.nextRect = new Rect(random.nextInt(7)+1);
//
//
//		if(exchangeThread!=null){
//			exchangeThread.sendMessage(Integer.toString(curRect.color));
//			exchangeThread.sendMessage(Integer.toString(nextRect.color));
//		}
	}

	/**
	 * ������Ϸ
	 */
	public void gameStart(){

		gamedao = new GameDao();


		// �����������
//		this.curRect = new Rect(random.nextInt(7)+1);
//		this.nextRect = new Rect(random.nextInt(7)+1);

		this.curRect = new Rect(1);
		this.nextRect = new Rect(2);

		isRunning =true;
//		if(exchangeThread!=null){
//			exchangeThread.sendMessage(Integer.toString(curRect.color));
//			exchangeThread.sendMessage(Integer.toString(nextRect.color));
//		}

		timer = new Timer();
		timer.schedule(new GameTask(), 100,30);
	}

	public void keyUp() {
		if(!isRunning) return;
		if(!gamedao.ifcanChange(curRect)) return;

		curRect.change();
		if(exchangeThread!=null)
			exchangeThread.sendMessage("up");
		panel.repaint();
		
	}

	public void keyDown() {
		if(!isRunning) return;
		if(gamedao.isput(curRect)) return;
		
		curRect.down();
		if(exchangeThread!=null)
			exchangeThread.sendMessage("down");
    	panel.repaint();

	}

	public void keyLeft() {
		if(!isRunning) return;
		if(gamedao.isleftside(curRect))	return;
		
		curRect.left();
		if(exchangeThread!=null)
			exchangeThread.sendMessage("left");
		panel.repaint();
				
	}

	public void keyRight() {
		if(!isRunning) return;
		if(gamedao.isrightside(curRect))	return;
		
		curRect.right();
		if(exchangeThread!=null)
			exchangeThread.sendMessage("right");
		panel.repaint();
				
	}

	/**
	 * ������ͣ����ʱ��Զ�̷�����ͣ����
	 */
	public void keyPause()  {
		isRunning =false;
		if(exchangeThread!=null)
			exchangeThread.sendMessage("keyPause");
	}

	/**
	 * �����ָ�����ʱ��Զ�̷��ͻָ�����
	 */
	public void keyResume() {
		isRunning =true;
		if(exchangeThread!=null)
			exchangeThread.sendMessage("keyResume");
	}

	/**
	 * ����Զ�̵���ָͣ��
	 */
	public void pause()  {
		isRunning =false;
	}

	/**
	 * ����Զ�̵Ļָ�ָ��
	 */
	public void resume() {
		isRunning =true;
	}

	/**
	 * ֻ��ͨ��remote������
	 * ������һ���Ѿ�gameover�ˣ����ҲҪͣһ��
	 */
	public int gameover(){
		System.out.print("gameover");
		isRunning=false;
		return gamedao.score;
	}

	// �Ƿ��������У��������еĻ�����true
	public boolean isRunning() {
		return isRunning;
	}
	
	
}
