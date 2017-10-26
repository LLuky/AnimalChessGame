package animal_game_group1;

import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Vector;
import javax.swing.JLabel;

//放rule的
public class AGModel extends Observable{
    
    
    //to save current operation保存当前操作
    Vector FileVar;
    Vector Var;    
    
    
    int answer=0;
    String username=null;
    String password=null;
    AGDB dbconnection;
    UpdateInfo uInfo;
    
    AGModel(){

        dbconnection=new AGDB();
        dbconnection.dbsetup();
        uInfo=new UpdateInfo();

        
    }
    
    
    
    
    
    
    //这是原本mathquiz跟JDBC互动的地方
    /*
    public void checkid(String un, String pw){
        this.username=un;
        this.password=pw;
        if(username!=null&&password!=null){
            uInfo.currentscore=dbconnection.checkName(username, password);
            if(uInfo.currentscore==-99)
                uInfo.loginflag=false;
            else
            {
                uInfo.loginflag=true;
                //uInfo.num1=this.getNumber();
                //uInfo.num2=this.getNumber();
            }
            setChanged();
            notifyObservers(uInfo);
        }
    
    }

    */
    
    
        //Design for rats 0,1    
        public void normalRule(int Man,JLabel play,MouseEvent me)
        {
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));
            if ((me.getX()-play.getX())>50 && (me.getX()-play.getX())<90 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42 && play.getX()<300)
            {
                play.setBounds(play.getX()+49,play.getY(),45,45);
            }
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>50 && (me.getY()-play.getY())<90 && play.getY()<390)
            {
                play.setBounds(play.getX(),play.getY()+49,45,45);
            }
            if ((me.getX()-play.getX())>-45 && (me.getX()-play.getX())<-5 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42 && play.getX()>15)
            {
                play.setBounds(play.getX()-49,play.getY(),45,45);
            }
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>-45 && (me.getY()-play.getY())<-5 && play.getY()>15 )
            {
                play.setBounds(play.getX(),play.getY()-49,45,45);
            }

        }

        public void normalRule(JLabel play,JLabel play2)
        {
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(uInfo.Man));

            //Add current record into Set(Repent) 当前记录添加到集合(用于悔棋)
            Var.add(String.valueOf(play2.isVisible()));
            Var.add(String.valueOf(play2.getX()));
            Var.add(String.valueOf(play2.getY()));
            Var.add(String.valueOf(uInfo.i));
            if ((play2.getX()+22-play.getX())>50 && (play2.getX()+22-play.getX())<90 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42 && play.getX()<300)
            {
                play2.setVisible(false);
                play.setBounds(play.getX()+49,play.getY(),45,45);
            }
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>50 && (play2.getY()+22-play.getY())<90 && play.getY()<390)
            {
                play2.setVisible(false);
                play.setBounds(play.getX(),play.getY()+49,45,45);
            }
            if ((play2.getX()+22-play.getX())>-45 && (play2.getX()+22-play.getX())<-5 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42 && play.getX()>15)
            {
                play2.setVisible(false);
                play.setBounds(play.getX()-49,play.getY(),45,45);
            }
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>-45 && (play2.getY()+22-play.getY())<-5 && play.getY()>15 )
            {
                play2.setVisible(false);
                play.setBounds(play.getX(),play.getY()-49,45,45);
            }

        }

        public void normal2Rule(int Man,JLabel play,MouseEvent me)
        {
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));
            //Go right
            if ((me.getX()-play.getX())>50 && (me.getX()-play.getX())<90 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42 && play.getX()<300)
            {
                if ((play.getX()>5 && play.getX()<30 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play.setBounds(play.getX()+49,play.getY(),45,45);
            }
            //Go down
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>50 && (me.getY()-play.getY())<90 && play.getY()<390)
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>90 && play.getY()<120) || (play.getX()>200 && play.getX()<275 && play.getY()>90 && play.getY()<120))
                    ;
                else
                    play.setBounds(play.getX(),play.getY()+49,45,45);
            }
            //Go right
            if ((me.getX()-play.getX())>-45 && (me.getX()-play.getX())<-5 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42 && play.getX()>15)
            {
                if ((play.getX()>290 && play.getX()<320 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play.setBounds(play.getX()-49,play.getY(),45,45);
            }
            //Go up
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>-45 && (me.getY()-play.getY())<-5 && play.getY()>15 )
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>290 && play.getY()<320) || (play.getX()>200 && play.getX()<275 && play.getY()>290 && play.getY()<320))
                    ;
                else
                    play.setBounds(play.getX(),play.getY()-49,45,45);
            }

        }

        public void normal2Rule(JLabel play,JLabel play2)
        {
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(uInfo.Man));

            //Add current record into Set(Repent) 当前记录添加到集合(用于悔棋)
            Var.add(String.valueOf(play2.isVisible()));
            Var.add(String.valueOf(play2.getX()));
            Var.add(String.valueOf(play2.getY()));
            Var.add(String.valueOf(uInfo.i));
            //Go right
            if ((play2.getX()+22-play.getX())>50 && (play2.getX()+22-play.getX())<90 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42 && play.getX()<300)
            {
                if ((play.getX()>5 && play.getX()<30 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX()+49,play.getY(),45,45);
            }
            //Go down
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>50 && (play2.getY()+22-play.getY())<90 && play.getY()<390)
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>90 && play.getY()<120) || (play.getX()>200 && play.getX()<275 && play.getY()>90 && play.getY()<120))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX(),play.getY()+49,45,45);
            }
            //Go left
            if ((play2.getX()+22-play.getX())>-45 && (play2.getX()+22-play.getX())<-5 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42 && play.getX()>15)
            {
                if ((play.getX()>290 && play.getX()<320 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX()-49,play.getY(),45,45);
            }
            //Go up
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>-45 && (play2.getY()+22-play.getY())<-5 && play.getY()>15 )
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>290 && play.getY()<320) || (play.getX()>200 && play.getX()<275 && play.getY()>290 && play.getY()<320))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX(),play.getY()-49,45,45);
            }

        }

        //Design for Lion12,13 & Tiger10,11
        public void specialRule(int Man,JLabel play,MouseEvent me)
        {
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(Man));
            //Go right
            if ((me.getX()-play.getX())>50 && (me.getX()-play.getX())<90 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42 && play.getX()<300)
            {
                if ((play.getX()>5 && play.getX()<30 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play.setBounds(play.getX()+49,play.getY(),45,45);
            }
            //Go down
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>50 && (me.getY()-play.getY())<90 && play.getY()<390)
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>90 && play.getY()<120) || (play.getX()>200 && play.getX()<275 && play.getY()>90 && play.getY()<120))
                    ;
                else
                    play.setBounds(play.getX(),play.getY()+49,45,45);
            }
            //Go left
            if ((me.getX()-play.getX())>-45 && (me.getX()-play.getX())<-5 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42 && play.getX()>15)
            {
                if ((play.getX()>290 && play.getX()<320 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play.setBounds(play.getX()-49,play.getY(),45,45);
            }
            //Go up
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>-45 && (me.getY()-play.getY())<-5 && play.getY()>15 )
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>290 && play.getY()<320) || (play.getX()>200 && play.getX()<275 && play.getY()>290 && play.getY()<320))
                    ;
                else
                    play.setBounds(play.getX(),play.getY()-49,45,45);
            }
            if ((me.getX()-play.getX())>146 && (me.getX()-play.getX())<190 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42)
            {
                if (( play.getX()>5 && play.getX()<30 && play.getY()>150 && play.getY()<275 ) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275)  )
                {
                    play.setBounds(play.getX()+148, play.getY(), 45, 45);
                }
            }
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>198 && (me.getY()-play.getY())<240)
            {
                if ( (play.getX()>50 && play.getX()<120 && play.getY()>90 && play.getY()<120) || (play.getX()>200 && play.getX()<275 && play.getY()>90 && play.getY()<120))
                {
                    play.setBounds(play.getX(),play.getY()+194,45,45);
                }
            }
            if ((me.getX()-play.getX())>-145 && (me.getX()-play.getX())<-105 && (me.getY()-play.getY())>2 && (me.getY()-play.getY())<42)
            {
                if ((play.getX()>290 && play.getX()<320 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                {
                   play.setBounds(play.getX()-148,play.getY(),45,45);
		}
            }
            if ((me.getX()-play.getX())>2 && (me.getX()-play.getX())<42 && (me.getY()-play.getY())>-194 && (me.getY()-play.getY())<-152)
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>290 && play.getY()<320) || (play.getX()>200 && play.getX()<275 && play.getY()>290 && play.getY()<320))
                {
                    play.setBounds(play.getX(),play.getY()-194,45,45);
                }
            }
        }

        public void specialRule(JLabel play,JLabel play2)
        {
            Var.add(String.valueOf(play.isVisible()));
            Var.add(String.valueOf(play.getX()));
            Var.add(String.valueOf(play.getY()));
            Var.add(String.valueOf(uInfo.Man));

            //Add current record into Vector(Repent)当前记录添加到集合(用于悔棋)
            Var.add(String.valueOf(play2.isVisible()));
            Var.add(String.valueOf(play2.getX()));
            Var.add(String.valueOf(play2.getY()));
            Var.add(String.valueOf(uInfo.i));
            //Go right
            if ((play2.getX()+22-play.getX())>50 && (play2.getX()+22-play.getX())<90 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42 && play.getX()<300)
            {
                if ((play.getX()>5 && play.getX()<30 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX()+49,play.getY(),45,45);
            }
            //Go down
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>50 && (play2.getY()+22-play.getY())<90 && play.getY()<390)
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>90 && play.getY()<120) || (play.getX()>200 && play.getX()<275 && play.getY()>90 && play.getY()<120))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX(),play.getY()+49,45,45);
            }
            //Go left
            if ((play2.getX()+22-play.getX())>-45 && (play2.getX()+22-play.getX())<-5 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42 && play.getX()>15)
            {
                if ((play.getX()>290 && play.getX()<320 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX()-49,play.getY(),45,45);
            }
            //Go up
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>-45 && (play2.getY()+22-play.getY())<-5 && play.getY()>15 )
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>290 && play.getY()<320) || (play.getX()>200 && play.getX()<275 && play.getY()>290 && play.getY()<320))
                    ;
                else
                    play2.setVisible(false);
                play.setBounds(play.getX(),play.getY()-49,45,45);
            }
            if ((play2.getX()+22-play.getX())>146 && (play2.getX()+22-play.getX())<190 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42)
            {
                if (( play.getX()>5 && play.getX()<30 && play.getY()>150 && play.getY()<275 ) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275)  )
                    {
                        play2.setVisible(false);
                        play.setBounds(play.getX()+148, play.getY(), 45, 45);
                    }
            }
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>198 && (play2.getY()+22-play.getY())<240)
            {
                if ( (play.getX()>50 && play.getX()<120 && play.getY()>90 && play.getY()<120) || (play.getX()>200 && play.getX()<275 && play.getY()>90 && play.getY()<120))
                    {
                        play2.setVisible(false);
                        play.setBounds(play.getX(),play.getY()+194,45,45);
                    }
            }
            if ((play2.getX()+22-play.getX())>-145 && (play2.getX()+22-play.getX())<-105 && (play2.getY()+22-play.getY())>2 && (play2.getY()+22-play.getY())<42)
            {
                if ((play.getX()>290 && play.getX()<320 && play.getY()>150 && play.getY()<275) || (play.getX()>150 && play.getX()<175 && play.getY()>150 && play.getY()<275))
                    {
                        play2.setVisible(false);
                        play.setBounds(play.getX()-148,play.getY(),45,45);
                    }
            }
            if ((play2.getX()+22-play.getX())>2 && (play2.getX()+22-play.getX())<42 && (play2.getY()+22-play.getY())>-194 && (play2.getY()+22-play.getY())<-152)
            {
                if ((play.getX()>50 && play.getX()<120 && play.getY()>290 && play.getY()<320) || (play.getX()>200 && play.getX()<275 && play.getY()>290 && play.getY()<320))
                    {
                        play2.setVisible(false);
                        play.setBounds(play.getX(),play.getY()-194,45,45);
                    }
            }
        }
        
      
    
    
}
