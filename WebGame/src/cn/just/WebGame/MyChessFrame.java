package cn.just.WebGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyChessFrame extends JFrame implements ActionListener{
	private ChessBoard chessBoard;     //对战面板
	private JPanel toolbar;       //工具条面板
	public JButton startButton;      //重新开始按钮，悔棋按钮，退出按钮
	public JButton backButton;
	public JButton exitButton;
	private JMenuBar menuBar;        //菜单栏
	private JMenu sysMenu;        //系统菜单
	public JMenuItem startMenuItem;     //重新开始，退出，和悔棋菜单项
	public JMenuItem exitMenuItem;
	public JMenuItem bcakMenuItem;
public MyChessFrame(){
	this.setTitle("五子棋游戏");
	chessBoard=new ChessBoard();        //初始化面板对象
	//创建和添加菜单
	menuBar=new JMenuBar();    //初始化菜单栏
	sysMenu=new JMenu("系统");    
	startMenuItem=new JMenuItem("重新开始");
	exitMenuItem=new JMenuItem("退出");
	bcakMenuItem=new JMenuItem("悔棋");
	//将三个菜单项加到系统菜单项上
	sysMenu.add(startMenuItem);
	sysMenu.add(exitMenuItem);
	sysMenu.add(bcakMenuItem);
	this.startMenuItem.addActionListener(this);       //将三个菜单项注册到事件监听器上
	this.exitMenuItem.addActionListener(this);
	this.bcakMenuItem.addActionListener(this);
	menuBar.add(sysMenu);       //将系统菜单添加到菜单栏上
	this.setJMenuBar(menuBar);        //将menuBar设置为菜单栏
	toolbar=new JPanel();
	startButton=new JButton("重新开始");
	backButton=new JButton("悔棋");
	exitButton=new JButton("退出");
	toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));      //将工具按钮用FlowLayout布局
	toolbar.add(backButton);          //将三个按钮添加到工具面板上
	toolbar.add(startButton);
	toolbar.add(exitButton);
	startButton.addActionListener(this);      //将三个按钮注册监听事件
	backButton.addActionListener(this);
	exitButton.addActionListener(this);
	this.add(toolbar,BorderLayout.SOUTH);     //将工具面板放到界面的下面
	this.add(chessBoard);              //将面板对象放到窗体上
	this.setSize(500, 500);
	this.setVisible(true);
	this.setResizable(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	int width=Toolkit.getDefaultToolkit().getScreenSize().width;     //得到你屏幕的大小
	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	this.setLocation(width/2-this.getWidth()/2, height/2-this.getHeight()/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getActionCommand();      //取得事件源
		if(obj.equals("重新开始")){
			System.out.println("重新开始...");
			chessBoard.restartGame();         //重新开始游戏
		}else if(obj.equals("退出")){
			System.exit(0);           //退出游戏
		}else if(obj.equals("悔棋")){
			System.out.println("悔棋");
			chessBoard.goback();      
		}
	}   
}

