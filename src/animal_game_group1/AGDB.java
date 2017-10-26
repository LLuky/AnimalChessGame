package animal_game_group1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;


//老师math的JDBC，在另外三个java文件里没有把这个的数据库连接部分拿出
public class AGDB {
    Connection conn=null;
    private String url="jdbc:derby:PlayerDB;create=true";  //url of the DB host
    private String dbusername="pdc";  //your DB username
    private String dbpassword="pdc";   //your DB password
    private String userid=null;
    
        
    public void dbsetup()
    {
        try {
            conn=DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";

           if(!checkTableExisting(tableName))
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(12), password VARCHAR(12), score INT)");
            //statement.executeUpdate("INSERT INTO " + tableName + " VALUES('Fiction',0),('Non-fiction',10),('Textbook',20)");
            statement.close();
            
        } catch (Throwable e) {
            System.out.println("error");
            
        }
    }
        
        
               
    private boolean checkTableExisting(String newTableName)
    {
        boolean flag=false;
        try {
            System.out.println("check existing tables.... ");
            String[] types={"TABLE"};
            DatabaseMetaData dbmd=conn.getMetaData();
            ResultSet rsDBMeta=dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while(rsDBMeta.next())
            {
                    String tableName=rsDBMeta.getString("TABLE_NAME");
                    if(tableName.compareToIgnoreCase(newTableName)==0)
                    {
                        System.out.println(tableName+"  is there");
                        flag=true;
                    }
                }
                if(rsDBMeta!=null)
                    rsDBMeta.close();
            }catch (SQLException ex) {
            }
        return flag; 
    }
    public int checkName(String username, String password){
        int score=-99;
        try {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("SELECT userid, password, score FROM UserInfo "
                    + "WHERE userid = '"+username+"'");
            if(rs.next()) {
                String pass = rs.getString("password");
                System.out.println("***"+pass);
                System.out.println("found user");
                if(password.compareTo(pass)==0)
                {
                    score=rs.getInt("score");
                    this.userid=username;
                    
                }
            } 
            else{
                System.out.println("no such user");
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('"+username+"', '"+password+"', 0)");
                score=0;
                this.userid=username;
                
            }            
  
        } catch (SQLException ex) {
            Logger.getLogger(AGDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return score;
    
    }
    void updateDBScore(int score){
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE UserInfo SET score="+score+" WHERE userid='"+this.userid+"'");
            System.out.println(userid+score);
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AGDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
