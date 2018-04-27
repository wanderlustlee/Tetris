package entity;

import javax.swing.*;
import java.awt.*;

/**
 * ��ʾÿһ������λ�úʹ�С
 * ��������򡢵ȼ��򡢿�ʼ����ͣ�Լ���ߴ�����Ϸ����
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
	 * ֻ�д���û�����ݵĵ���������캯��
	 */
	public GameWindow(int x, int y, int w, int h){
		this.x=x-5;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	/**
	 * �������ֵı���������ȼ��ģ����������������ΪҪ��ͼ
	 */
	public GameWindow(int x, int y, int w, int h,
			String strUrl){
		this(x,y,w,h);
        
        // ���ð�ť����ͼ
        ImageIcon icon=new ImageIcon(strUrl);
        icon.setImage(icon.getImage().getScaledInstance((int)(w*0.4),(int)(h*0.6),Image.SCALE_DEFAULT)); 
        setIcon(icon);
        
        // ���ý������
        setFocusable(true);
	}
	/**
	 * ��������
	 */
	public void creatwindow(Graphics g){
		//����
		g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0, WINDOW_IMGW-SIZE, SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x+w-SIZE, y, x+w, y+SIZE, WINDOW_IMGW-SIZE, 0, WINDOW_IMGW, SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, WINDOW_IMGH-SIZE, null);	
		//��
		g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, WINDOW_IMGW-SIZE, WINDOW_IMGH-SIZE, null);			
		//����
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, WINDOW_IMGW-SIZE, SIZE, WINDOW_IMGW,WINDOW_IMGH-SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x, y+h-SIZE, x+SIZE, y+h, 0, WINDOW_IMGH-SIZE, SIZE, WINDOW_IMGH, null);
		//����
		g.drawImage(WINDOW_IMG, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, WINDOW_IMGH-SIZE, WINDOW_IMGW-SIZE, WINDOW_IMGH, null);
		//����
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, WINDOW_IMGW-SIZE, WINDOW_IMGH-SIZE, WINDOW_IMGW, WINDOW_IMGH, null);
		
	}
	
	
}
