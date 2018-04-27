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
 * 游戏整体控制器，比如命令方块移动，暂停游戏等
 * 
 */
public class GameController {
	public UserInfo user= LoginUI.user;
	public static GameController localController;

	// 界面
	private JPanel panel;
	// 时间控制器，加载GameTask，每过一段时间，界面就变化一次
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

	// 游戏进程控制器，比如碰撞检测之类的
	private GameDao gamedao;

	// 当前图形与下一个图形
	private Rect curRect;
	private Rect nextRect;

	// 远程通信用的线程
	private ExchangeThread exchangeThread;

	private class GameTask extends TimerTask {
		private int speed = 5;
        public void run() {

			if(!isRunning){
				return ;
			}

			// speed来控制时间间隔。。
        	if(speed <= 0){
				if(gamedao.isput(curRect)){
					if(gamedao.gameover()) {
						System.out.println("游戏结束！");
						// 先暂停游戏
						isRunning = false;
						//对战模式
						if(exchangeThread!=null){
							exchangeThread.sendMessage("gameover");
							int myScore = gamedao.score;
							int remoteScore = RemoteController.remoteController.getGameDao().score;

							String str = Integer.toString(myScore) + "比" + Integer.toString(remoteScore) + ",";
							if (myScore > remoteScore) {
								// WIN
								JOptionPane.showMessageDialog(panel, str + "你赢了");
							} else if (myScore < remoteScore) {
								// LOSE
								JOptionPane.showMessageDialog(panel, str + "你输了");
							} else {
								// pingju
								JOptionPane.showMessageDialog(panel, str + "这是一场平局");
							}
						}else{
							//单机模式
							int myScore = gamedao.score;
							System.out.println("结束1");
							user.setScore(myScore);
							new UserDaoImplements().writeScore(user);
							System.out.println("结束2");
							JOptionPane.showMessageDialog(panel, "游戏结束."+
							"你的得分为:"+Integer.toString(myScore));
						}
						return;
					}
					Random random = new Random();
					// 已经放下来了的意思
					if(exchangeThread!=null){
						exchangeThread.sendMessage("isput");
					}
					curRect.setColor(0);
					int temp=random.nextInt(7)+1;
					curRect = new Rect(nextRect.color);
					nextRect = new Rect(temp);
					if(exchangeThread!=null){
						// 更新rect的命令,只发送下一个的
						exchangeThread.sendMessage(Integer.toString(temp));
					}
					if(gamedao.ispop()){
						// 消去一行
						if(exchangeThread!=null){
							exchangeThread.sendMessage("ispop");
						}
					}
                }else{
					curRect.down();
					if(exchangeThread!=null)
						exchangeThread.sendMessage("down");
				}
				// 如果没有放下来，就down
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
//		// 随机产生方块
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
	 * 启动游戏
	 */
	public void gameStart(){

		gamedao = new GameDao();


		// 随机产生方块
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
	 * 按键暂停，此时向远程发送暂停命令
	 */
	public void keyPause()  {
		isRunning =false;
		if(exchangeThread!=null)
			exchangeThread.sendMessage("keyPause");
	}

	/**
	 * 按键恢复，此时向远程发送恢复命令
	 */
	public void keyResume() {
		isRunning =true;
		if(exchangeThread!=null)
			exchangeThread.sendMessage("keyResume");
	}

	/**
	 * 接受远程的暂停指令
	 */
	public void pause()  {
		isRunning =false;
	}

	/**
	 * 接受远程的恢复指令
	 */
	public void resume() {
		isRunning =true;
	}

	/**
	 * 只能通过remote来调用
	 * 表明另一边已经gameover了，这边也要停一下
	 */
	public int gameover(){
		System.out.print("gameover");
		isRunning=false;
		return gamedao.score;
	}

	// 是否正在运行，正在运行的话返回true
	public boolean isRunning() {
		return isRunning;
	}
	
	
}
