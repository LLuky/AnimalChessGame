package animal_game_group1;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


//放接口的
public class AGControl implements ActionListener,MouseListener,Runnable{
    
    //Thread of controlling the chessman's flicker控制棋子闪烁的线程
    Thread tmain;

    
    private AGModel model;
    private AGView view;
    UpdateInfo uInfo;
//    private ActionListener actionListener;
    public AGControl(AGView theView, AGModel theModel){
        this.view = theView;
	this.model = theModel;
        view.setAController(this);
        view.setMController(this);
        
        model.FileVar=new Vector();
        model.Var=new Vector();
       
        /*
        //Listener for CloseWindow 关闭窗口监听
        this.addWindowListener(new WindowAdapter()
	{
            @Override
            public void windowClosing(WindowEvent we)
            {
		System.exit(0);
            }
	});
        */

    }
    
    @Override
    /*Use Thread to control the chessman's flicker线程方法控制棋子闪烁*/
    public void run() {
        while (true)
	{
            //Start flickering at first click of chessman 单击棋子第一下开始闪烁
            if (uInfo.chessManClick)
            {
		view.play[uInfo.Man].setVisible(false);

		//Time control 时间控制
		try
                {
                    tmain.sleep(500);
		}
		catch(Exception e){}

		view.play[uInfo.Man].setVisible(true);
            }

            //Flash current hint to take user's attention 闪烁当前提示信息 以免用户看不见
            else
            {
                view.text.setVisible(false);

                //Time control 时间控制
		try
		{
                    tmain.sleep(500);
		}
		catch(Exception e){}

		view.text.setVisible(true);
            }

            try
            {
		tmain.sleep(500);
            }
            catch (Exception e){}
	}
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        //System.out.println("Mouse");

	//Current position 当前坐标
	int Ex=0,Ey=0;
	String temp;
	char[] temp2;

	//Start Thread 启动线程
	if (tmain == null)
	{
            tmain = new Thread(this);
            tmain.start();
	}

	//Click chessBoard (move chessMan) 单击棋盘(移动棋子)
	if (me.getSource().equals(view.image))
	{
            
            //When the Blue moves 该蓝棋走棋的时候
            if (uInfo.chessPlayClick == 2 && view.play[uInfo.Man].getName().charAt(0) == '2')
            {
		Ex = view.play[uInfo.Man].getX();
		Ey = view.play[uInfo.Man].getY();

                //System.out.println("This is Man"+Man);
		//Move 移动
		if (uInfo.Man > 13 || uInfo.Man < 10)
		{
                    if (uInfo.Man == 1)
			model.normalRule(uInfo.Man,view.play[uInfo.Man],me);
                    else
                        model.normal2Rule(uInfo.Man, view.play[uInfo.Man], me);
                    //System.out.println("Move a chessMan successfully");
		}

		//Move Lion & Tiger移动狮、虎
		else
		{
                    model.specialRule(uInfo.Man, view.play[uInfo.Man], me);
		}

		if ((view.play[uInfo.Man].getX()+22 > 120 && view.play[uInfo.Man].getX()+22 < 240 && view.play[uInfo.Man].getY() > 2 
                        && view.play[uInfo.Man].getY() < 15 ) || (view.play[uInfo.Man].getX()+22 > 170 && view.play[uInfo.Man].getX()+22 < 190 
                        && view.play[uInfo.Man].getY() > 40 && view.play[uInfo.Man].getY() < 70))
		{
                    temp=view.play[uInfo.Man].getName();
                    temp2=temp.toCharArray();
                    temp2[1]='b';
                    temp=String.valueOf(temp2);
                    view.play[uInfo.Man].setName(temp);
                    if (view.play[uInfo.Man].getX()+22 > 170 && view.play[uInfo.Man].getX()+22 < 190 
                            && view.play[uInfo.Man].getY() > 2 && view.play[uInfo.Man].getY() < 15)
                    {
			JOptionPane.showConfirmDialog(this,"Blue Win","Player1 Win",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
			uInfo.chessPlayClick=3;
			view.text.setText("Blue Win");
                    }
		}
		//Is it a wrong movement(stay still)是否走棋错误(是否在原地没有动)
                if (Ex == view.play[uInfo.Man].getX() && Ey == view.play[uInfo.Man].getY())
		{
                    view.text.setText("  Move a Blue one");
                    view.jmain.setBackground(Color.BLUE);
                    uInfo.chessPlayClick=2;
		}
		else
		{
                    view.text.setText("  Move a Red one");
                    view.jmain.setBackground(Color.RED);
                    uInfo.chessPlayClick=1;
		}

            }//if

            //When the Red moves该红棋走棋的时候
            else if (uInfo.chessPlayClick == 1 && view.play[uInfo.Man].getName().charAt(0) == '1')
            {
		Ex = view.play[uInfo.Man].getX();
		Ey = view.play[uInfo.Man].getY();

		//Move 移动
		if (uInfo.Man > 13 || uInfo.Man < 10)
		{
                    if (uInfo.Man == 0)
			model.normalRule(uInfo.Man,view.play[uInfo.Man],me);
                    else
			model.normal2Rule(uInfo.Man, view.play[uInfo.Man], me);
		}

		//Move Lion & Tiger移动狮、虎
		else
		{
                    model.specialRule(uInfo.Man, view.play[uInfo.Man], me);
		}

		if ((view.play[uInfo.Man].getX()+22 > 120 && view.play[uInfo.Man].getX()+22 < 240 && view.play[uInfo.Man].getY() > 392 
                        && view.play[uInfo.Man].getY() < 405 ) || (view.play[uInfo.Man].getX()+22 > 170 && view.play[uInfo.Man].getX()+22 < 190
                        && view.play[uInfo.Man].getY() > 340 && view.play[uInfo.Man].getY() < 360))
		{
                    temp=view.play[uInfo.Man].getName();
                    temp2=temp.toCharArray();
                    temp2[1]='b';
                    temp=String.valueOf(temp2);
                    view.play[uInfo.Man].setName(temp);
                    if (view.play[uInfo.Man].getX()+22 > 170 && view.play[uInfo.Man].getX()+22 < 190 
                            && view.play[uInfo.Man].getY() > 392 && view.play[uInfo.Man].getY() < 405)
			{
                            JOptionPane.showConfirmDialog(this,"Red Win","Player2 Win",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
                            uInfo.chessPlayClick=3;
                            view.text.setText("Red Win");
			}
                }

		//Is it a wrong movement(stay still)是否走棋错误(是否在原地没有动)
		if (Ex == view.play[uInfo.Man].getX() && Ey == view.play[uInfo.Man].getY())
		{
                    view.text.setText("  Move a Red one");
                    view.jmain.setBackground(Color.RED);
                    uInfo.chessPlayClick=1;
		}
		else
		{
                    view.text.setText("  Move a Blue one");
                    view.jmain.setBackground(Color.BLUE);
                    uInfo.chessPlayClick=2;
		}

            }//else if

            //With no operation currently (Stop flickering)当前没有操作(停止闪烁)
		uInfo.chessManClick=false;

	}//if

	//Click a chessman 单击棋子
	else
	{
            //Start flickering at first click of chessman第一次单击棋子(闪烁棋子)
            if (!uInfo.chessManClick)
            {
		for (int c=0;c<16;c++)
		{
                    //The one is clicked 被单击的棋子
                    if (me.getSource().equals(view.play[c]))
                    {
			//Tell Thread to make this chessman flicker告诉线程让该棋子闪烁
			uInfo.Man=c;
			//Start flickering 开始闪烁
			uInfo.chessManClick=true;
			break;
                    }
		}//for
            }//if

            //Make a second click of chessman (Eat chessman) 第二次单击棋子(吃棋子)
            else if (uInfo.chessManClick)
            {
		//With no operation currently (Stop flickering)当前没有操作(停止闪烁)
                uInfo.chessManClick=false;

		for (uInfo.i=0;uInfo.i<16;uInfo.i++)
		{
                    //Find the captured one找到被吃的棋子
                    if (me.getSource().equals(view.play[uInfo.i]))
                    {
			//When the Blue is Capturing 该蓝棋吃棋的时候
			if (uInfo.chessPlayClick == 2 && view.play[uInfo.Man].getName().charAt(0) == '2')
			{
                            Ex = view.play[uInfo.Man].getX();
                            Ey = view.play[uInfo.Man].getY();

                            //Capturing 吃子
                            if ( (uInfo.Man > uInfo.i && !(uInfo.Man == 15 && uInfo.i == 0) ) 
                                    || view.play[uInfo.Man].getName().charAt(3) == view.play[uInfo.i].getName().charAt(3) 
                                    || view.play[uInfo.i].getName().charAt(1) == 'b' || (uInfo.i == 14 && uInfo.Man == 1))
                            {
				if (uInfo.Man > 13 || uInfo.Man < 10)
                                {
                                    if (uInfo.Man == 1)
					model.normalRule(view.play[uInfo.Man],view.play[uInfo.i]);
                                    else
					model.normal2Rule(view.play[uInfo.Man],view.play[uInfo.i]);
				}

				//When Lion or Tiger is capturing狮、虎吃子
				else
				{
                                    model.specialRule(view.play[uInfo.Man],view.play[uInfo.i]);
				}
                            }

                            //Is it a wrong movement(stay still)是否走棋错误(是否在原地没有动)
                            if (Ex == view.play[uInfo.Man].getX() && Ey == view.play[uInfo.Man].getY())
                            {
				view.text.setText("  Move a Blue one");
                                view.jmain.setBackground(Color.BLUE);
				uInfo.chessPlayClick=2;
				break;
                            }
                            else
                            {
				view.text.setText("  Move a Red one");
                                view.jmain.setBackground(Color.RED);
				uInfo.chessPlayClick=1;
				break;
                            }

			}//if

			//When the Red is Capturing 该红棋吃棋的时候
			else if (uInfo.chessPlayClick == 1 && view.play[uInfo.Man].getName().charAt(0) == '1')
			{
                            Ex = view.play[uInfo.Man].getX();
                            Ey = view.play[uInfo.Man].getY();

                            //Capturing 吃子
                            if ( (uInfo.Man > uInfo.i && !(uInfo.Man == 14 && uInfo.i == 1) ) 
                                    || view.play[uInfo.Man].getName().charAt(3) == view.play[uInfo.i].getName().charAt(3) 
                                    || view.play[uInfo.i].getName().charAt(1) == 'b' || (uInfo.i == 15 && uInfo.Man == 0))
                            {
				if (uInfo.Man > 13 || uInfo.Man < 10)
				{
                                    if (uInfo.Man == 0)
					model.normalRule(view.play[uInfo.Man],view.play[uInfo.i]);
                                    else
					model.normal2Rule(view.play[uInfo.Man],view.play[uInfo.i]);
				}

				//When Lion or Tiger is capturing狮、虎吃子
				else
				{
                                    model.specialRule(view.play[uInfo.Man],view.play[uInfo.i]);
				}
                            }

                            //Is it a wrong movement(stay still)是否走棋错误(是否在原地没有动)
                            if (Ex == view.play[uInfo.Man].getX() && Ey == view.play[uInfo.Man].getY())
                            {
				view.text.setText("  Move a Red one");
                                view.jmain.setBackground(Color.RED);
				uInfo.chessPlayClick=1;
				break;
                            }

                            else
                            {
				view.text.setText("  Move a Blue one");
                                view.jmain.setBackground(Color.BLUE);
				uInfo.chessPlayClick=2;
				break;
                            }

			}//else if

                    }//if

		}//for

		//是否胜利

            }//else

	}//else
        
    }
    
    
    
    
    
    
    
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        try{    
            String eStr=e.getActionCommand();
            System.out.println(eStr);
            if(eStr.compareTo("Log in")==0){
                String un=view.unInput.getText();
                String pw=view.pwInput.getText();
                model.checkid(un, pw);
                System.out.println(model.uInfo.currentscore);
            }   
        }
        catch(NumberFormatException ex){
            System.out.println(ex);
	
        }
    }
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        
            //Resume a game重新开始
        if (ae.getSource().equals(view.anew))
	{
            int j=JOptionPane.showConfirmDialog(this,"Want to start a new game?","New Game",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (j == JOptionPane.YES_OPTION)
            {
                //Reorder every chessman's position 重新排列每个棋子的位置
                    
                //Lion狮
                view.play[12].setBounds(10,8,45,45);
                view.play[12].setName("1a_Lion");

                view.play[13].setBounds(305,398,45,45);
                view.play[13].setName("2a_Lion");


                //Tiger虎
                view.play[10].setBounds(305,8,45,45);
                view.play[10].setName("1a_Tiger");

                view.play[11].setBounds(10,398,45,45);
                view.play[11].setName("2a_Tiger");


                //Dog狗
                view.play[6].setBounds(60,58,45,45);
                view.play[6].setName("1a_Dog");

                view.play[7].setBounds(255,349,45,45);
                view.play[7].setName("2a_Dog");


                //Cat猫
                view.play[2].setBounds(256,57,45,45);
                view.play[2].setName("1a_Cat");

                view.play[3].setBounds(60,348,45,45);
                view.play[3].setName("2a_Cat");


                //Elephant象
                view.play[14].setBounds(305,106,45,45);
                view.play[14].setName("1a_Elephant");

                view.play[15].setBounds(10,300,45,45);
                view.play[15].setName("2a_Elephant");


                //Rat鼠
                view.play[0].setBounds(10,106,45,45);
                view.play[0].setName("1a_Rat");

                view.play[1].setBounds(304,301,45,45);
                view.play[1].setName("2a_Rat");


                //Leopard豹
                view.play[8].setBounds(109,105,45,45);
                view.play[8].setName("1a_Red_Leopard");

                view.play[9].setBounds(206,300,45,45);
                view.play[9].setName("2a_Blue_Leopard");


                //Wolf狼
                view.play[4].setBounds(206,106,45,45);
                view.play[4].setName("1a_Wolf");

                view.play[5].setBounds(109,300,45,45);
                view.play[5].setName("2a_Wolf");

                uInfo.chessPlayClick = 2;
                view.text.setText("  Move a Blue one");
                view.jmain.setBackground(Color.BLUE);
                    
                for (uInfo.i=0;uInfo.i<16;uInfo.i++)
                {
                    view.play[i].setVisible(true);
                }

                
                for(uInfo.i=0;uInfo.i<model.Var.size();uInfo.i++)
                {
                    model.Var.remove(uInfo.i);
                }
            }
            
            //Repent悔棋
            else if (ae.getSource().equals(view.repent))
            {
                try
                {
                    //Get & delete index获得索引
                    int M = Integer.parseInt((String)model.Var.get(model.Var.size()-1));
                    //Get & delete y获得Y坐标
                    int y = Integer.parseInt((String)model.Var.get(model.Var.size()-2));
                    //Get & delete x 获得X坐标
                    int x = Integer.parseInt((String)model.Var.get(model.Var.size()-3));
                    //Get & delete the value of setVisible 属性值
                    String S = (String)model.Var.get(model.Var.size()-4);
                    
                    
                    //Assign chessman 赋给棋子
                    view.play[M].setVisible(true);
                    view.play[M].setBounds(x,y,40,40);

                    if (view.play[M].getName().charAt(0) == '1')
                    {
                        view.text.setText("  Move a Red one");
                        view.jmain.setBackground(Color.RED);
                        uInfo.chessPlayClick = 1;
                    }
                    else
                    {
                    view.text.setText("  Move a Blue one");
                    view.jmain.setBackground(Color.BLUE);
                    uInfo.chessPlayClick = 2;
                    }
                    
                    //Remove the old location  删除用过的坐标
                    model.Var.remove(model.Var.size()-4);
                    model.Var.remove(model.Var.size()-3);
                    model.Var.remove(model.Var.size()-2);
                    model.Var.remove(model.Var.size()-1);         
                    
                    
                    
                    //Stop flickering 停止棋子闪烁
                    uInfo.chessManClick=false;
                }
                catch(Exception ex){}
            }
        }
            
            //Open 打开棋局
            else if (ae.getSource().equals(view.showOpen))
            {
                try
                {
                    //Opening dialog box打开对话框
                    JFileChooser jfcOpen = new JFileChooser("Open a game");
                    int v=jfcOpen.showOpenDialog(this);

                    if (v != JFileChooser.CANCEL_OPTION)
                    {
                        //Delete all infromation of ArrayList删除集合所有信息
                        model.Var.removeAllElements();
			model.FileVar.removeAllElements();

                        ObjectInputStream objIn;
                        //Open a file and gain all data of it打开文件获得所有数据
                        FileInputStream fileIn = new FileInputStream(jfcOpen.getSelectedFile());
                        objIn = new ObjectInputStream(fileIn);
                        model.FileVar = (Stack)objIn.readObject();
                        fileIn.close();
			objIn.close();

			//The contents of the ArrayList match with the coordinates of chessman 集合内容对应棋子坐标
			int k=0;
			for (uInfo.i=0;uInfo.i<16;uInfo.i++)
			{
                            view.play[uInfo.i].setBounds(((Integer)model.FileVar.get(k)), ((Integer)model.FileVar.get(k+1)),40,40);
                            //Caputured chesses are not shown被吃掉的棋子不显示
                            if (!((Boolean)model.FileVar.get(k+2)))
                            {
                                view.play[uInfo.i].setVisible(false);
                            }
                            k+=3;
                        }
			//Which color move first当前该哪方棋子走棋
			if (((String)model.FileVar.lastElement()).equals("  Move a Blue one"))
			{
                            view.text.setText(((String)model.FileVar.lastElement()));
                            uInfo.chessPlayClick = 2;
			}
			else if (((String)model.FileVar.get(model.FileVar.size()-1)).equals("  Move a Red one"))
			{
                            view.text.setText(((String)model.FileVar.lastElement()));
                            uInfo.chessPlayClick = 1;
			}
			else if ((((String)model.FileVar.lastElement())).substring(5).equals("Win"))
			{
                            view.text.setText(((String)model.FileVar.lastElement()));
                            uInfo.chessPlayClick = 3;
			}

                    }
		}

		catch(HeadlessException | IOException | ClassNotFoundException e)
		{
                    System.out.println("ERROR ShowOpen");
		}
            }
            
            //Save 保存棋局
            else if (ae.getSource().equals(view.showSave))
            {
                try
                {
                    //Saving dialog box保存对话框
                    JFileChooser jfcSave = new JFileChooser("Save current game");
                    int v=jfcSave.showSaveDialog(this);

                    if (v != JFileChooser.CANCEL_OPTION)
                    {
                        model.FileVar.removeAllElements();

                        //Save all Chessman's coordinates and visibility 保存所有棋子的坐标和是否可见
                        for (int p=0;p<16;p++)
                        {
                            model.FileVar.addElement(view.play[p].getX());//Integer
                            model.FileVar.addElement(view.play[p].getY());//Integer
                            model.FileVar.addElement(view.play[p].isVisible());//Boolean
                        }
                        //Save which color can capture currently 保存当前该哪方吃棋
                        model.FileVar.add(view.text.getText());

                        //Save into file 保存到文件
                        FileOutputStream fileOut = new FileOutputStream(jfcSave.getSelectedFile());
                        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
                        //We save the information of chess in Object
                        objOut.writeObject(model.FileVar);
                        
                        objOut.close();
                        fileOut.close();
                    }
                }
                catch(HeadlessException | IOException e)
                {
                    System.out.println("ERROR ShowSave");
                }
            }
            
            
            //Exit 退出
            else if (ae.getSource().equals(view.exit))
            {
                //Check jdk & Overwrite YES_NO_OPTION
                int j=JOptionPane.showConfirmDialog(this,"Are you sure to exit?","Exit",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (j == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
            
        }
    
    @Override
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    
    
}
