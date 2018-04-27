package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.MusicPlayer;
/**
 * 按键控制器，响应按键操作
*/
public class KeyController extends KeyAdapter{

	private GameController gameController;
	public KeyController(GameController gameController){
		this.gameController = gameController;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(MusicPlayer.isturnOn())
			MusicPlayer.actionPlay();
		
		if(e.getKeyCode()==KeyEvent.VK_A) {
			this.gameController.keyResume();
			if(!MusicPlayer.isRunning()){
				MusicPlayer.bgmPlay();
			}
			return;
		}

		if(gameController.isRunning()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					this.gameController.keyUp();
					break;
				case KeyEvent.VK_DOWN:
					this.gameController.keyDown();
					break;
				case KeyEvent.VK_LEFT:
					this.gameController.keyLeft();
					break;
				case KeyEvent.VK_RIGHT:
					this.gameController.keyRight();
					break;
				case KeyEvent.VK_SPACE:
					this.gameController.keyPause();
					if(MusicPlayer.isRunning()){
						MusicPlayer.bgmStop();
					}
					break;
				default:
					break;
			}
		}



	}
	
}
