package cn.just.WebGame;

import java.awt.Canvas;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 画棋盘
 * @author Shinelon
 *
 */
public class OmokBoard extends Canvas{
   public OmokBoard(){
	   
    	JFrame jf=new JFrame();
    	jf.setTitle("五子棋游戏");
    	jf.setSize(500, 500);
    	//jf.setLocation(200, 200);
    	jf.setVisible(true);
    	jf.setResizable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	int width=Toolkit.getDefaultToolkit().getScreenSize().width;     //得到你屏幕的大小
    	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
    
    	jf.setLocation(width/2-jf.getWidth()/2, height/2-jf.getHeight()/2);
   }
}
