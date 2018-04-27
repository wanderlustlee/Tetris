package Controller;

import entity.Rect;
import model.GameDao;
import view.OnlinePanel;

import javax.swing.*;

/**
 * ��Ӧ��ս����������ʾ��ս�����
*/
public class RemoteController  {

    public static RemoteController remoteController;

    // ���ɽ���
    private OnlinePanel panel;
    private GameDao gameDao=new GameDao();

    public GameDao getGameDao() {
        return gameDao;
    }

    // ��ǰͼ������һ��ͼ��
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

    // ͼ�ο���
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

    // ͼ��ֹͣ����ȥ
    public void isPut(){
        gameDao.isput(curRect);
        curRect.setColor(0);
    }
    public void ispop(){
        gameDao.ispop();
    }
    public void gameover(){
        // �ӵ���һ�������������ͣ��һ��controller��Ȼ��ȽϷ���
        int remoteScore= GameController.localController.gameover();
        int myScore=gameDao.score;
        String str=Integer.toString(myScore)+"��"+Integer.toString(remoteScore)+",";
        // ����ȷֵ��ж���GameController�е��෴
        if(myScore<remoteScore){
            // WIN
            JOptionPane.showMessageDialog(panel, str + "��Ӯ��");
        }else if(myScore>remoteScore){
            // LOSE
            JOptionPane.showMessageDialog(panel,str+"������");
        }else{
            // pingju
            JOptionPane.showMessageDialog(panel,str+"����һ��ƽ��");
        }
    }


}
