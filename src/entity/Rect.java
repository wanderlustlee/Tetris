package entity;

import javax.swing.*;
import java.awt.*;

/**
 * 表示各种图形，比如长条、正方形等
*/
public class Rect {

	protected static final int RECTW = 20;
	
	protected static final int RECTH = 20;
		
	public int[] x = new int[4];
	
	public int[] y = new int[4];
	
	public int color;
	
	public Image RECT_IMG = new ImageIcon("Graphics/game/newmyrect.png").getImage();
		
	public Rect(int color){
		this.color = color;
		switch(color){
			case 1: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=7 ;	//长条形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=-1; 
				break;
			case 2: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=5 ;	//土字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=0; 
				break;
			case 3: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=5 ;this.x[3]=4 ;	//田字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=0 ;this.y[3]=0 ; 
				break;
			case 4: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=6 ;	//右L字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=0; 
				break;
			case 5: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=6 ;this.x[3]=4;	//左L字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=-1;this.y[3]=0; 
				break;
			case 6: 
				this.x[0]=5 ;this.x[1]=6 ;this.x[2]=5 ;this.x[3]=4;	//Z字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=0;this.y[3]=0; 
				break;
			case 7: 
				this.x[0]=5 ;this.x[1]=4 ;this.x[2]=5 ;this.x[3]=6;	//反Z字形
				this.y[0]=-1;this.y[1]=-1;this.y[2]=0;this.y[3]=0; 
				break;
		}
	}
	
	public void draw(Graphics g,int pos_x,int pos_y){
		
		for(int i=0;i<4;i++){
			drawone(g,pos_x,pos_y,x[i],y[i]);
		}
	}


	public void drawone(Graphics g,int pos_x,int pos_y,int x,int y){

			g.drawImage(RECT_IMG,
					pos_x+x*RECTW , pos_y+y*RECTH, pos_x+(x+1)*RECTW , pos_y+(y+1)*RECTH,
					color*RECTW, 0 , (color+1)*RECTW, RECTH, null);
			
	}
	
	public void change(){
			int tmpx=0;
			if(color != 3 && tmpx!=-1)
				for(int i=0;i<4;i++){
					tmpx=this.x[i];
					this.x[i]=this.y[i]-this.y[0]+this.x[0];
					this.y[i]=this.x[0]-tmpx+this.y[0];
				}		
		}	
	
	public void down(){
			for(int i=0;i<4;i++)
				this.y[i] = this.y[i] + 1;

	}
	
	public void left(){
		
		for(int i=0;i<4;i++)
			this.x[i] = this.x[i] - 1;

	}
	
	public void right(){

		for(int i=0;i<4;i++)
			this.x[i] = this.x[i] + 1;

	}


	public void setColor(int color) {
		this.color = color;
	}
}
