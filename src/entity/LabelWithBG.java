package entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ������ͼƬ��label
 * ����Background����Ϊ�����������ͼƬ�����Ǳ߲���
 */
public class LabelWithBG  extends JLabel{
	
	// Ҫ��ͼƬ����
	private Image background;
	
	public LabelWithBG(int width,int height,String address){
		// ���ð�ť����ͼ
        ImageIcon icon=new ImageIcon(address);
        background=icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
	}
	public void draw(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
}
