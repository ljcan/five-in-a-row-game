package cn.just.WebGame;

import java.awt.Canvas;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ������
 * @author Shinelon
 *
 */
public class OmokBoard extends Canvas{
   public OmokBoard(){
	   
    	JFrame jf=new JFrame();
    	jf.setTitle("��������Ϸ");
    	jf.setSize(500, 500);
    	//jf.setLocation(200, 200);
    	jf.setVisible(true);
    	jf.setResizable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	int width=Toolkit.getDefaultToolkit().getScreenSize().width;     //�õ�����Ļ�Ĵ�С
    	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
    
    	jf.setLocation(width/2-jf.getWidth()/2, height/2-jf.getHeight()/2);
   }
}
