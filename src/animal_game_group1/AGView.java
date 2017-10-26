package animal_game_group1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

//GUI部分
public class AGView extends JFrame implements Observer{
    
    Container con;

    
    JLabel play[] = new JLabel[16];
    JLabel image;
    JToolBar jmain;

    JButton anew;
    JButton repent;//Back to your last step 悔棋
    JButton showOpen;//Open an old game 打开
    JButton showSave;//Save current game 保存
    JButton exit;//Exit the program 退出
	
    JLabel text;//Show current information 当前信息
    
    
 
    
    private JPanel userPanel=new JPanel();
    private JPanel calcPanel=new JPanel();
    private JLabel message=new JLabel("");
    private JLabel uName=new JLabel("Username: ");
    private JLabel pWord=new JLabel("Password: ");
    public JTextField unInput = new JTextField(10);
    public JTextField pwInput = new JTextField(10);
    private JLabel wrongName=new JLabel("Wrong username or passwork!");

    //private JLabel firstNumber=new JLabel();
    //private JLabel secondNumber=new JLabel();
    //private JLabel additionLabel = new JLabel("+");
    //private JTextField secondNumber = new JTextField(10);
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");
    public boolean started=false;
    UpdateInfo uInfo;
    

    //public JTextField userSolution = new JTextField(10); 
    
    AGView(String Title){  
        
        con = this.getContentPane();
	con.setLayout(null);

        jmain = new JToolBar();
	text = new JLabel("  Move a Blue one");
	text.setToolTipText("Hints");
        text.setForeground(Color.WHITE);
        jmain.setBackground(Color.BLUE);
        
	anew = new JButton("New Game");
	anew.setToolTipText("Start a new game");
	exit = new JButton("Exit");
	exit.setToolTipText("Exit this Programme");
	repent = new JButton("Repent");
	repent.setToolTipText("Retract a false move");
	showOpen = new JButton("Open");
	showOpen.setToolTipText("Open an old game");
	showSave = new JButton("Save");
	showSave.setToolTipText("Save current game");
        
        jmain.setLayout(new GridLayout(2,3));
	jmain.add(anew);
	jmain.add(repent);
        jmain.add(text);
	jmain.add(showOpen);
	jmain.add(showSave);
	jmain.add(exit);
        jmain.setBounds(0,450,360,60);
	con.add(jmain);
                
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(600, 200);
        userPanel.add(uName);
        userPanel.add(unInput);
        userPanel.add(pWord);
        userPanel.add(pwInput);
        userPanel.add(loginButton);
        userPanel.add(message);
        this.add(userPanel);
        
        //Add Labels of chessMan添加棋子标签
	drawChessMan();   
        
        con.add(image = new JLabel(new ImageIcon("chess.png")));
	image.setBounds(0,0,360,450);
        
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = this.getSize();

	if (frameSize.height > screenSize.height)
	{
		frameSize.height = screenSize.height;
	}
	if (frameSize.width > screenSize.width)
	{
		frameSize.width = screenSize.width;
	}

	this.setLocation((screenSize.width - frameSize.width) / 2 - 200 ,(screenSize.height - frameSize.height ) / 2 - 290);

        this.setIconImage(new ImageIcon("blue_tiger2.png").getImage());
	this.setResizable(false);
	this.setTitle(Title);
	this.setSize(365,545);
        
	this.show();        
        
        
    }
    

    

    
   public void setAController(ActionListener cntrl){
       
        /*Register Listeners注册监听者*/

	//Listeners for Button
	anew.addActionListener(cntrl);
	repent.addActionListener(cntrl);
	exit.addActionListener(cntrl);
	showOpen.addActionListener(cntrl);
	showSave.addActionListener(cntrl);


       
        loginButton.addActionListener(cntrl);
        quitButton.addActionListener(cntrl);
           
           
           
    }
   
   public void setMController(MouseListener cntrl){
       
        //MouseListener for chessMan注册棋子移动监听
	for (int i=0;i<16;i++)
	{
            con.add(play[i]);
            play[i].addMouseListener(cntrl);
	}       
        image.addMouseListener(cntrl);
        
   }
       
   
   
   
   
   
   
   
   
   
   
   
   //拿来放界面的，分别是我们的数据库界面，棋盘界面，游戏说明界面（未改！！！）
   public void update(Observable obs, Object obj) {
       
       
       UpdateInfo update=(UpdateInfo)obj;
       if(!update.loginflag)
       {    
           message.setText("Wrong password or username");
           System.out.println("Wrong password");
           pwInput.setText("");
           userPanel.repaint();
       }
       else if(!started){
           //setNewQuestion(update.num1,update.num2);
           //startQuiz();
           started=true;
       }
       else if(update.quitflag)
       {
           JPanel quitPanel=new JPanel();
           JLabel scoreLabel=new JLabel("Your score: "+update.currentscore);
           quitPanel.add(scoreLabel);
           this.getContentPane().removeAll();
           this.add(quitPanel);
           this.revalidate();
           this.repaint();
       }
       else{
           //setNewQuestion(update.num1,update.num2);
           //calcPanel.repaint();
       }
   }
   
   
    private void drawChessMan() {
        
	Icon in;

	//Lion狮
	in = new ImageIcon("red_lion1.png");

	play[12] = new JLabel(in);
	play[12].setBounds(10,8,45,45);
	play[12].setName("1a_Lion");

	in = new ImageIcon("blue_lion2.png");

	play[13] = new JLabel(in);
	play[13].setBounds(305,398,45,45);
	play[13].setName("2a_Lion");


	//Tiger虎
	in = new ImageIcon("red_tiger1.png");

	play[10] = new JLabel(in);
	play[10].setBounds(305,8,45,45);
	play[10].setName("1a_Tiger");

	in = new ImageIcon("blue_tiger2.png");

	play[11] = new JLabel(in);
	play[11].setBounds(10,398,45,45);
	play[11].setName("2a_Tiger");


	//Dog狗
	in = new ImageIcon("red_dog1.png");

	play[6] = new JLabel(in);
	play[6].setBounds(60,58,45,45);
	play[6].setName("1a_Dog");

	in = new ImageIcon("blue_dog2.png");

	play[7] = new JLabel(in);
	play[7].setBounds(255,349,45,45);
	play[7].setName("2a_Dog");


	//Cat猫
	in = new ImageIcon("red_cat1.png");

	play[2] = new JLabel(in);
	play[2].setBounds(256,57,45,45);
	play[2].setName("1a_Cat");

	in = new ImageIcon("blue_cat2.png");

	play[3] = new JLabel(in);
	play[3].setBounds(60,348,45,45);
	play[3].setName("2a_Cat");


	//Elephant象
	in = new ImageIcon("red_elephant1.png");

	play[14] = new JLabel(in);
	play[14].setBounds(305,106,45,45);
	play[14].setName("1a_Elephant");

	in = new ImageIcon("blue_elephant2.png");

	play[15] = new JLabel(in);
	play[15].setBounds(10,300,45,45);
	play[15].setName("2a_Elephant");


	//Rat鼠
	in = new ImageIcon("red_rat1.png");

	play[0] = new JLabel(in);
	play[0].setBounds(10,106,45,45);
	play[0].setName("1a_Rat");

	in = new ImageIcon("blue_rat2.png");

	play[1] = new JLabel(in);
	play[1].setBounds(304,301,45,45);
	play[1].setName("2a_Rat");


	//Leopard豹
	in = new ImageIcon("red_leopard1.png");

	play[8] = new JLabel(in);
	play[8].setBounds(109,105,45,45);
	play[8].setName("1a_Leopard");

	in = new ImageIcon("blue_leopard2.png");

	play[9] = new JLabel(in);
	play[9].setBounds(206,300,45,45);
	play[9].setName("2a_Leopard");


	//Wolf狼
	in = new ImageIcon("red_wolf1.png");

	play[4] = new JLabel(in);
	play[4].setBounds(206,106,45,45);
	play[4].setName("1a_Wolf");

	in = new ImageIcon("blue_wolf2.png");

	play[5] = new JLabel(in);
	play[5].setBounds(109,300,45,45);
	play[5].setName("2a_Wolf");
    }    
       
   
   
}

