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
        // ���ð�ť����ͼ
        ImageIcon icon1=new ImageIcon(url);
        icon1.setImage(icon1.getImage().getScaledInstance((int)(width*0.9),(int)(height*0.9),Image.SCALE_DEFAULT)); 
        setIcon(icon1);
        
        // �����ƽ���
        setFocusPainted(false);

        // ������������
        setContentAreaFilled(false);

        setText(text);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
        // ���ý������
        setFocusable(true);

        // ���ð�ť�߿���߿�����֮���������
        setMargin(new Insets(0, 0, 0, 0));
	}
	public void setNewImage(String url){
		ImageIcon icon1=new ImageIcon(url);
        icon1.setImage(icon1.getImage().getScaledInstance((int)(width*0.9),(int)(height*0.9),Image.SCALE_DEFAULT)); 
        setIcon(icon1);
	}
	
}
