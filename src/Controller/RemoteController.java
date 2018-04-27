package Controller;

import entity.Rect;
import model.GameDao;
import view.OnlinePanel;

import javax.swing.*;

/**
 * 响应对战方操作，显示对战方情况
*/
public class RemoteController  {

    public static RemoteController remoteController;

    // 生成界面
    private OnlinePanel panel;
    private GameDao gameDao=new GameDao();

    public GameDao getGameDao() {
        return gameDao;
    }

    // 当前图形与下一个图形
    private Rect curRect;
    private Rect nextRect;

    public void setcurRect(int color){
        curRect =new Rect(color);
    }

    public void setNextRect(int color){
        nextRect =new Rect(color);
    }
    public Rect getCurRect() {
        return curRect;
    }

    public Rect getNextRect() {
        return nextRect;
    }

    public RemoteController(OnlinePanel panel) {
        this.panel=panel;
        curRect =new Rect(1);
        nextRect =new Rect(2);
    }

    // 图形控制
    public void rectUp() {
        curRect.change();
        panel.repaint();
    }
    public void rectDown() {
        curRect.down();
        panel.repaint();
    }

    public void rectLeft() {
        curRect.left();
        panel.repaint();

    }

    public void rectRight() {
        curRect.right();
        panel.repaint();
    }

    // 图形停止、消去
    public void isPut(){
        gameDao.isput(curRect);
        curRect.setColor(0);
    }
    public void ispop(){
        gameDao.ispop();
    }
    public void gameover(){
        // 接到另一方的命令，首先暂停另一个controller，然后比较分数
        int remoteScore= GameController.localController.gameover();
        int myScore=gameDao.score;
        String str=Integer.toString(myScore)+"比"+Integer.toString(remoteScore)+",";
        // 这里比分的判断与GameController中的相反
        if(myScore<remoteScore){
            // WIN
            JOptionPane.showMessageDialog(panel, str + "你赢了");
        }else if(myScore>remoteScore){
            // LOSE
            JOptionPane.showMessageDialog(panel,str+"你输了");
        }else{
            // pingju
            JOptionPane.showMessageDialog(panel,str+"这是一场平局");
        }
    }


}
