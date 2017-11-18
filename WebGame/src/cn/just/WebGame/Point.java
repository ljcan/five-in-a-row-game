package cn.just.WebGame;

import java.awt.Color;

/**
 * 五子棋设计
 * @author Shinelon
 *
 */
public class Point {
	private int x;
	private int y;
	private Color color;     //颜色
	public static final int DIAMETER=30;		//直径
	public Point(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
