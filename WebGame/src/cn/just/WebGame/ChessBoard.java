package cn.just.WebGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * ������
 * @author Shinelon
 *
 */
public class ChessBoard extends JPanel implements MouseListener{
	public static final int MARGIN=30;       //�߾�
	public static final int GRID_SPAN=35;    //������
	public static final int ROWS=15;        //��������
	public static final int CLOS=15;           //���̵�����
	Point[] chessList=new Point[(ROWS+1)*(CLOS+1)];     //��ʼ��ÿһ������Ԫ��null
	boolean isBlack=true;   //Ĭ�Ͽ�ʼ�Ǻ�������
	boolean gameOver=false;     //��Ϸ�Ƿ����
	int chessCount;    //��ǰ���̵����ӵĸ���
	int xindex,yindex;    //��ǰ�������ӵ�����
public ChessBoard(){
		this.setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				//�������������λ��ת������������
				int x1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;  
				int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				//��Ϸ�Ѿ�������������
				//�����������棬������
				//x,yλ���Ѿ������Ӵ��ڣ�������
				if(x1<0||x1>ROWS||y1<0||y1>CLOS||gameOver||findChess(x1,y1)){
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));       //���ó�Ĭ����װ
				}else{
					setCursor(new Cursor(Cursor.HAND_CURSOR));       //���ó�����
				}
			}	
		});
	}
	//����
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//������
		for(int i=0;i<ROWS;i++){      //������
			g.drawLine(MARGIN, MARGIN+i*GRID_SPAN,MARGIN+CLOS*GRID_SPAN, MARGIN+i*GRID_SPAN);
		}
		for(int i=0;i<CLOS;i++){        //������
			g.drawLine(MARGIN+i*GRID_SPAN, MARGIN,MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
		}
		//������
		for(int i=0;i<chessCount;i++){
			int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;     //���񽻲��x����
			int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;      //���񽻲���y����
			g.setColor(chessList[i].getColor());       //������ɫ
			g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER,Point.DIAMETER);
			//������һ�����ӵĺ���ο�
			if(i==chessCount-1){		//���һ������
				g.setColor(Color.RED);
				g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER);
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {   //��갴��������ϵ��������²��ͷţ�ʱ����
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//��Ϸ������������
		if(gameOver){
			return;
		}
		String colorName=isBlack?"����":"����";
		xindex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;    //�������������λ��ת������������
		yindex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
		//���������⣬������
		if(xindex<0||xindex>ROWS||yindex<0||yindex>CLOS){
			return;
		}
		//x,yλ���Ѿ������Ӵ��ڣ�������
		if(findChess(xindex,yindex))
			return;
		Point ch=new Point(xindex,yindex,isBlack?Color.black:Color.white);
		chessList[chessCount++]=ch;
		repaint();        //֪ͨϵͳ���»���
		if(isWin()){
			//����ʤ����Ϣ�������ټ�������
			String msg=String.format("��ϲ��%sӮ��", colorName);
			JOptionPane.showMessageDialog(this, msg);
			gameOver=true;
		}
		isBlack=!isBlack;
	}

	@Override
	public void mouseReleased(MouseEvent e) {     //�����������ͷ�ʱ����
	}

	@Override
	public void mouseEntered(MouseEvent e) {    //�����������ʱ����
	}

	@Override
	public void mouseExited(MouseEvent e) {      //����뿪���ʱ����
	}
	//�����������в����Ƿ�������Ϊx,y�����Ӵ���
	private boolean findChess(int x,int y){
		for(Point c:chessList){
			if(c!=null&&c.getX()==x&&c.getY()==y)
				return true;
		}
		return false;
	}
	private boolean isWin(){    //�ж���һ��Ӯ
		int continueCount=1;   //�������ӵĸ���
		//��������Ѱ��
		for(int x=xindex-1;x>=0;x--){
			Color c=isBlack?Color.BLACK:Color.white;
			if(getChess(x,yindex,c)!=null){
				continueCount++;
			}else{
				break;
			}
		}
			//������Ѱ��
			for(int x=xindex+1;x<=ROWS;x++){
				Color c=isBlack?Color.black:Color.white;
				if(getChess(x,yindex,c)!=null){
					continueCount++;
				}else
					break;
			}
			if(continueCount>5){
				return true;
			}else{
				continueCount=1;
			}
			//��������Ѱ��
			for(int y=yindex-1;y>=0;y--){
				Color c=isBlack?Color.black:Color.white;
				if(getChess(xindex,y,c)!=null){
					continueCount++;
				}else
					break;
			}
			//��������Ѱ��
			for(int y=yindex+1;y<=ROWS;y++){
				Color c=isBlack?Color.black:Color.white;
				if(getChess(xindex,y,c)!=null){
					continueCount++;
				}else
					break;
			}
			if(continueCount>=5){
				return true;
			}else{
				continueCount=1;
			}
			//����Ѱ��
			for(int x=xindex+1,y=yindex-1;y>=0&&x<=CLOS;x++,y--){
				Color c=isBlack?Color.BLACK:Color.WHITE;
				if(getChess(x,y,c)!=null){
					continueCount++;
				}else{
					break;
				}
			}
			//����Ѱ��
			for(int x=xindex-1,y=yindex+1;y<=ROWS&&x>=0;x--,y++){
				Color c=isBlack?Color.BLACK:Color.WHITE;
				if(getChess(x,y,c)!=null){
					continueCount++;
				}else{
					break;
				}
			}
			if(continueCount>=5){
				return true;
			}else{
				continueCount=1;
			}
			//����Ѱ��
			for(int x=xindex-1,y=yindex-1;y>=0&&x>=0;x--,y--){
				Color c=isBlack?Color.BLACK:Color.WHITE;
				if(getChess(x,y,c)!=null){
					continueCount++;
				}else{
					break;
				}
			}
			//����Ѱ��
			for(int x=xindex+1,y=yindex+1;y<=ROWS&&x<=CLOS;x++,y++){
				Color c=isBlack?Color.BLACK:Color.WHITE;
				if(getChess(x,y,c)!=null){
					continueCount++;
				}else{
					break;
				}
			}
			if(continueCount>=5){
				return true;
			}else
				continueCount=1;
				return false;
		}
	private Point getChess(int xindex,int yindex,Color color){
		for(Point c:chessList){
			if(c!=null&&c.getX()==xindex&&c.getY()==yindex&&c.getColor()==color)
				return c;
		}
		return null;
	}
	public void restartGame(){
		//�������
		for(int i=0;i<chessCount;i++){
			chessList[i]=null;
			//�ָ���Ϸ��صı���ֵ
			isBlack=true;
			gameOver=false;     //��Ϸ�Ƿ����
			chessCount=0;       //��ǰ���ӵĸ���
			repaint();
		}
	}
		//����
	public void goback(){
			if(chessCount==0)
				return;
				chessList[chessCount-1]=null;
				chessCount--;
				if(chessCount>0){
					xindex=chessList[chessCount-1].getX();
					yindex=chessList[chessCount-1].getY();
				}
			isBlack=!isBlack;
			repaint();
		}
		//����
	/*	public Dimension getPreferredSize(){
			return new Dimension(MARGIN*2+GRID_SPAN*CLOS,MARGIN*2+GRID_SPAN*ROWS);
		}
		*/
	}
	
	


