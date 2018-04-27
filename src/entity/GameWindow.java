package entity;

import javax.swing.*;
import java.awt.*;

/**
 * 表示每一个框框的位置和大小
 * 比如分数框、等级框、开始、暂停以及左边大块的游戏界面
 */
public class GameWindow extends JLabel{

	

	private static final int SIZE = 7;
	private static final Image WINDOW_IMG=new ImageIcon("Graphics/window/newWindow.png").getImage();
	private static final int WINDOW_IMGW = WINDOW_IMG.getWidth(null);
	private static final int WINDOW_IMGH = WINDOW_IMG.getHeight(null);
	
	public int x;
	public int y;
	public int h;
	public int w;
	
	/**
	 * 只有窗体没有内容的调用这个构造函数
	 */
	public GameWindow(int x, int y, int w, int h){
		this.x=x-5;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	/**
	 * 里面有字的比如分数、等级的，调用这个函数，因为要绘图
	 */
	public GameWindow(int x, int y, int w, int h,
			String strUrl){
		this(x,y,w,h);
        
        // 设置按钮背景图
        ImageIcon icon=new ImageIcon(strUrl);
        icon.setImage(icon.getImage().getScaledInstance((int)(w*0.4),(int)(h*0.6),Image.SCALE_DEFAULT)); 
        setIcon(icon);
        
        // 设置焦点控制
        setFocusable(true);
	}
	/**
	 * 创建窗体
	 */
	public void creatwindow(Graphics g){
		//左上
		g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//中上
		g.drawImage(WINDOW_IMG, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0, WINDOW_IMGW-SIZE, SIZE, null);
		//右上
		g.drawImage(WINDOW_IMG, x+w-SIZE, y, x+w, y+SIZE, WINDOW_IMGW-SIZE, 0, WINDOW_IMGW, SIZE, null);
		//左中
		g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, WINDOW_IMGH-SIZE, null);	
		//中
		g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, WINDOW_IMGW-SIZE, WINDOW_IMGH-SIZE, null);			
		//右中
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, WINDOW_IMGW-SIZE, SIZE, WINDOW_IMGW,WINDOW_IMGH-SIZE, null);
		//左下
		g.drawImage(WINDOW_IMG, x, y+h-SIZE, x+SIZE, y+h, 0, WINDOW_IMGH-SIZE, SIZE, WINDOW_IMGH, null);
		//中下
		g.drawImage(WINDOW_IMG, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, WINDOW_IMGH-SIZE, WINDOW_IMGW-SIZE, WINDOW_IMGH, null);
		//右下
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, WINDOW_IMGW-SIZE, WINDOW_IMGH-SIZE, WINDOW_IMGW, WINDOW_IMGH, null);
		
	}
	
	
}
