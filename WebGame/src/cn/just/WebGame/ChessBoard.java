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
 * 棋盘类
 * @author Shinelon
 *
 */
public class ChessBoard extends JPanel implements MouseListener{
	public static final int MARGIN=30;       //边距
	public static final int GRID_SPAN=35;    //网格间距
	public static final int ROWS=15;        //棋盘行数
	public static final int CLOS=15;           //棋盘的列数
	Point[] chessList=new Point[(ROWS+1)*(CLOS+1)];     //初始化每一个数组元素null
	boolean isBlack=true;   //默认开始是黑棋先下
	boolean gameOver=false;     //游戏是否结束
	int chessCount;    //当前棋盘的棋子的个数
	int xindex,yindex;    //当前刚下棋子的索引
public ChessBoard(){
		this.setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				//将鼠标点击的坐标位置转换成网格索引
				int x1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;  
				int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				//游戏已经结束，不能下
				//落在棋盘外面，不能下
				//x,y位置已经有棋子存在，不能下
				if(x1<0||x1>ROWS||y1<0||y1>CLOS||gameOver||findChess(x1,y1)){
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));       //设置成默认形装
				}else{
					setCursor(new Cursor(Cursor.HAND_CURSOR));       //设置成手型
				}
			}	
		});
	}
	//绘制
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//画棋盘
		for(int i=0;i<ROWS;i++){      //画横线
			g.drawLine(MARGIN, MARGIN+i*GRID_SPAN,MARGIN+CLOS*GRID_SPAN, MARGIN+i*GRID_SPAN);
		}
		for(int i=0;i<CLOS;i++){        //画竖线
			g.drawLine(MARGIN+i*GRID_SPAN, MARGIN,MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
		}
		//画棋子
		for(int i=0;i<chessCount;i++){
			int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;     //网格交叉点x坐标
			int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;      //网格交叉点的y坐标
			g.setColor(chessList[i].getColor());       //设置颜色
			g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER,Point.DIAMETER);
			//标记最后一个棋子的红矩形框
			if(i==chessCount-1){		//最后一个棋子
				g.setColor(Color.RED);
				g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER);
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {   //鼠标按键在组件上单击（按下并释放）时调用
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//游戏结束，不能下
		if(gameOver){
			return;
		}
		String colorName=isBlack?"黑棋":"白棋";
		xindex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;    //将鼠标点击的坐标位置转换成网格索引
		yindex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
		//落在棋盘外，不能下
		if(xindex<0||xindex>ROWS||yindex<0||yindex>CLOS){
			return;
		}
		//x,y位置已经有棋子存在，不能下
		if(findChess(xindex,yindex))
			return;
		Point ch=new Point(xindex,yindex,isBlack?Color.black:Color.white);
		chessList[chessCount++]=ch;
		repaint();        //通知系统重新绘制
		if(isWin()){
			//给出胜利信息，不能再继续下棋
			String msg=String.format("恭喜，%s赢了", colorName);
			JOptionPane.showMessageDialog(this, msg);
			gameOver=true;
		}
		isBlack=!isBlack;
	}

	@Override
	public void mouseReleased(MouseEvent e) {     //鼠标在组件上释放时调用
	}

	@Override
	public void mouseEntered(MouseEvent e) {    //鼠标进入组件上时调用
	}

	@Override
	public void mouseExited(MouseEvent e) {      //鼠标离开组件时调用
	}
	//在棋子数组中查找是否有索引为x,y的棋子存在
	private boolean findChess(int x,int y){
		for(Point c:chessList){
			if(c!=null&&c.getX()==x&&c.getY()==y)
				return true;
		}
		return false;
	}
	private boolean isWin(){    //判断哪一方赢
		int continueCount=1;   //连续棋子的个数
		//横向向西寻找
		for(int x=xindex-1;x>=0;x--){
			Color c=isBlack?Color.BLACK:Color.white;
			if(getChess(x,yindex,c)!=null){
				continueCount++;
			}else{
				break;
			}
		}
			//横向向东寻找
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
			//纵向向上寻找
			for(int y=yindex-1;y>=0;y--){
				Color c=isBlack?Color.black:Color.white;
				if(getChess(xindex,y,c)!=null){
					continueCount++;
				}else
					break;
			}
			//纵向向下寻找
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
			//东北寻找
			for(int x=xindex+1,y=yindex-1;y>=0&&x<=CLOS;x++,y--){
				Color c=isBlack?Color.BLACK:Color.WHITE;
				if(getChess(x,y,c)!=null){
					continueCount++;
				}else{
					break;
				}
			}
			//西南寻找
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
			//西北寻找
			for(int x=xindex-1,y=yindex-1;y>=0&&x>=0;x--,y--){
				Color c=isBlack?Color.BLACK:Color.WHITE;
				if(getChess(x,y,c)!=null){
					continueCount++;
				}else{
					break;
				}
			}
			//西南寻找
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
		//清除棋子
		for(int i=0;i<chessCount;i++){
			chessList[i]=null;
			//恢复游戏相关的变量值
			isBlack=true;
			gameOver=false;     //游戏是否结束
			chessCount=0;       //当前棋子的个数
			repaint();
		}
	}
		//悔棋
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
		//矩形
	/*	public Dimension getPreferredSize(){
			return new Dimension(MARGIN*2+GRID_SPAN*CLOS,MARGIN*2+GRID_SPAN*ROWS);
		}
		*/
	}
	
	


