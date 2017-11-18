package cn.just.WebGame;

import javax.swing.JOptionPane;

public class TestApp {
   public static void main(String[] args){
	   MyChessFrame mf=new MyChessFrame();
	  /*  JOptionPane.showMessageDialog(mf, "我的信息");       //用这个类的静态方法来创建对话框
	    int result=JOptionPane.showConfirmDialog(mf, "确定开始游戏吗？");
	   // System.out.println("你选择的结果是："+result);
	   String username= JOptionPane.showInputDialog("请输入姓名：");
	   JOptionPane.showMessageDialog(mf, "你输入的姓名为："+username);  */
	   mf.setVisible(true);
   }
}
