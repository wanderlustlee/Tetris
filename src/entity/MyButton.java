package entity;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Button
 */
public class MyButton extends JButton{

	int width,height;
	

	public MyButton(String url,String text,int width,int height){
		this.width=width;
		this.height=height;
        // 设置按钮背景图
        ImageIcon icon1=new ImageIcon(url);
        icon1.setImage(icon1.getImage().getScaledInstance((int)(width*0.9),(int)(height*0.9),Image.SCALE_DEFAULT)); 
        setIcon(icon1);
        
        // 不绘制焦点
        setFocusPainted(false);

        // 不绘制内容区
        setContentAreaFilled(false);

        setText(text);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
        // 设置焦点控制
        setFocusable(true);

        // 设置按钮边框与边框内容之间的像素数
        setMargin(new Insets(0, 0, 0, 0));
	}
	public void setNewImage(String url){
		ImageIcon icon1=new ImageIcon(url);
        icon1.setImage(icon1.getImage().getScaledInstance((int)(width*0.9),(int)(height*0.9),Image.SCALE_DEFAULT)); 
        setIcon(icon1);
	}
	
}
