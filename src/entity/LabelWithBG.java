package entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 带背景图片的label
 * 不用Background是因为这里可以拉伸图片，而那边不行
 */
public class LabelWithBG  extends JLabel{
	
	// 要让图片拉伸
	private Image background;
	
	public LabelWithBG(int width,int height,String address){
		// 设置按钮背景图
        ImageIcon icon=new ImageIcon(address);
        background=icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
	}
	public void draw(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
}
