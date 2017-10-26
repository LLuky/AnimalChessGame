
package animal_game_group1;

//数据封装
public class UpdateInfo {

    
    /*Click a chessman*******************************/
    /*chessManClick = true 闪烁棋子 并给线程响应
      Blink the chessman & Give a response to Thread*/
    /*chessManClick = false 吃棋子 停止闪烁  并给线程响应
      Capturing chessman & Stop blinking & Give a response to Thread*/
    boolean chessManClick;

    /*Control the movement of player******************/
    /*chessPlayClick=1 Red can move 红棋走棋*/
    /*chessPlayClick=2 Blue can move (Default)蓝棋走棋 默认红棋*/
    /*chessPlayClick=3 Both can not move the chessman*/	
    int chessPlayClick=2;
    

    //Give the response to Thread at the first clike把第一次的单击棋子给线程响应
    int Man,i;
    

    boolean loginflag=false;
    //boolean quitflag=false;
    //int currentscore=0;
    //int num1=0;
    //int num2=0;
    
}
