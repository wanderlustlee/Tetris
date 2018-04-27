package model;

import dao.UserDaoImplements;
import entity.Rect;

import java.awt.*;

import user.UserInfo;
import view.LoginUI;
import view.OfflinePanel;

/**
 * ��Ϸ�����е���ײ��⡢���������
*/
public class GameDao {

	private boolean[][] gamemap;
	public int cancelline=0;
	public int score =0;
	public int level=0;
	
	public GameDao(){
		// 11X19�ķ��񣬶��ҳ�ʼ��Ϊδռ��
		this.gamemap = new boolean[11][19];
		for(int x=0;x<11;x++){
			for(int y=0;y<19;y++){
				gamemap[x][y]=false;
			}
		}
		
	}
	
	public boolean isleftside(Rect nowrect){
		
		for(int i=0;i<4;i++){
			//�����ǽ���߷��鶼����
			//System.out.println(nowrect.x[i]-1+"  "+nowrect.y[i]);
			if(nowrect.x[i]==0 || gamemap[nowrect.x[i]-1][nowrect.y[i]+1]){		
				return true;
			}
		}
		return false;
	}
	
	public boolean isrightside(Rect nowrect){
		
		for(int i=0;i<4;i++){
			//�ұ���ǽ���߷��鶼����
			//System.out.println(nowrect.x[i]-1+"   "+nowrect.y[i]);
			if(nowrect.x[i]==10  || gamemap[nowrect.x[i]+1][nowrect.y[i]+1])
				return true;
		}
		return false;
	}
	
	public boolean isput(Rect nowrect){
		
		boolean isput = false;

		for(int i=0;i<4;i++){
			//������ǽ���߷���
			//System.out.println(nowrect.x[i]-1+"  "+nowrect.y[i]);
			if( nowrect.y[i]>=18 || gamemap[nowrect.x[i]][nowrect.y[i]+1] ){
				isput = true;
				break;
			}
		}
		if(isput)
			//���isput���Ž���ͼ
			for(int j=0;j<4;j++){
				if (nowrect.y[j]==-1) {
					nowrect.y[j] = 0;
				}
				//System.out.println(nowrect.x[j]+"  "+nowrect.y[j]);
				gamemap[nowrect.x[j]][nowrect.y[j]]=true;
			}
		return isput;
	}

	public boolean ifcanChange(Rect nowrect){
		
		for(int i=0;i<4;i++){
			int change_x = nowrect.y[i]-nowrect.y[0]+nowrect.x[0] ;
			int change_y = nowrect.x[0]-nowrect.x[i]+nowrect.y[0] ;
			if(  change_x<0 || change_y<0 || change_x>10 || gamemap[change_x][change_y])
				return false;
		}
		return true;
	}
	
	public boolean gameover(){
		
		//boolean game=false;
		for(int x=0;x<11;x++){
			if(gamemap[x][0] == true)
				return true;
		}
		return false;
		
	}

	/**
	 * �Ƿ�ɹ���ȥһ��
	 * @return
	 */
	public boolean ispop(){
		
		boolean iscancel=false;
		int tmpline=0;						//�������
		for(int y=18;y>=0;y--)
			for(int x=0;x<11;x++){
				if(gamemap[x][y] != true)
					break;

				if(x==10){
					tmpline++;
					cancelline++;
					popLine(y);
					y++;
					iscancel=true;
				}
			}
		
		if(tmpline != 0){
			//�����͵ȼ�����
			score +=tmpline*tmpline;
			if(score > (2+2*level*level))
				level++;
		}
		return iscancel;
	}
	
	public void popLine(int line){
		for(int y=line;y>0;y--)
			for(int x=0;x<11;x++){
				gamemap[x][y] = gamemap[x][y-1];
			}	
		
	}

	/**
	 * ���Ƶ׶��Ѿ����µķ���
	 * @param g
	 * @param mode
	 */
	public void drawmap(Graphics g,int mode){
		Rect rect = new Rect(0);
		for(int x=0;x<11;x++)
			for(int y=0;y<19;y++)
				if(gamemap[x][y]==true){
					if(mode==1){
						rect.drawone(g, 506, 32, x, y);
					}else {
						rect.drawone(g, 12, 32, x, y);
					}
				}

	}
}
