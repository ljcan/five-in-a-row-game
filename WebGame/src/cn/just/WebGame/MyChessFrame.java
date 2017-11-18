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
	private ChessBoard chessBoard;     //��ս���
	private JPanel toolbar;       //���������
	public JButton startButton;      //���¿�ʼ��ť�����尴ť���˳���ť
	public JButton backButton;
	public JButton exitButton;
	private JMenuBar menuBar;        //�˵���
	private JMenu sysMenu;        //ϵͳ�˵�
	public JMenuItem startMenuItem;     //���¿�ʼ���˳����ͻ���˵���
	public JMenuItem exitMenuItem;
	public JMenuItem bcakMenuItem;
public MyChessFrame(){
	this.setTitle("��������Ϸ");
	chessBoard=new ChessBoard();        //��ʼ��������
	//��������Ӳ˵�
	menuBar=new JMenuBar();    //��ʼ���˵���
	sysMenu=new JMenu("ϵͳ");    
	startMenuItem=new JMenuItem("���¿�ʼ");
	exitMenuItem=new JMenuItem("�˳�");
	bcakMenuItem=new JMenuItem("����");
	//�������˵���ӵ�ϵͳ�˵�����
	sysMenu.add(startMenuItem);
	sysMenu.add(exitMenuItem);
	sysMenu.add(bcakMenuItem);
	this.startMenuItem.addActionListener(this);       //�������˵���ע�ᵽ�¼���������
	this.exitMenuItem.addActionListener(this);
	this.bcakMenuItem.addActionListener(this);
	menuBar.add(sysMenu);       //��ϵͳ�˵���ӵ��˵�����
	this.setJMenuBar(menuBar);        //��menuBar����Ϊ�˵���
	toolbar=new JPanel();
	startButton=new JButton("���¿�ʼ");
	backButton=new JButton("����");
	exitButton=new JButton("�˳�");
	toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));      //�����߰�ť��FlowLayout����
	toolbar.add(backButton);          //��������ť��ӵ����������
	toolbar.add(startButton);
	toolbar.add(exitButton);
	startButton.addActionListener(this);      //��������ťע������¼�
	backButton.addActionListener(this);
	exitButton.addActionListener(this);
	this.add(toolbar,BorderLayout.SOUTH);     //���������ŵ����������
	this.add(chessBoard);              //��������ŵ�������
	this.setSize(500, 500);
	this.setVisible(true);
	this.setResizable(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	int width=Toolkit.getDefaultToolkit().getScreenSize().width;     //�õ�����Ļ�Ĵ�С
	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	this.setLocation(width/2-this.getWidth()/2, height/2-this.getHeight()/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getActionCommand();      //ȡ���¼�Դ
		if(obj.equals("���¿�ʼ")){
			System.out.println("���¿�ʼ...");
			chessBoard.restartGame();         //���¿�ʼ��Ϸ
		}else if(obj.equals("�˳�")){
			System.exit(0);           //�˳���Ϸ
		}else if(obj.equals("����")){
			System.out.println("����");
			chessBoard.goback();      
		}
	}   
}

