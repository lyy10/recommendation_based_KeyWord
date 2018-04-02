package second_work;
/*
 *对数据库操作的相关函数与驱动 
 */
import java.sql.*;
import java.util.*;
public class ConnectMySql {
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1010";
    private static Connection conn = null;
    private static Statement stmt = null;
    //*****初始化连接
    public static void initConnect() 
    {
    	 try {
    	        // 注册 JDBC 驱动
    	        Class.forName(JDBC_DRIVER);
    	        // 打开链接
//    	        System.out.println("连接数据库...");
    	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
//    	        if(!conn.isClosed()) 
//    	            System.out.println("Succeeded connecting to the Database!");
//    	        // 执行查询
//    	        System.out.println(" 实例化Statement对象...");
    	        stmt = conn.createStatement();
    	 		}catch(ClassNotFoundException e) {
    	 			System.out.println("Sorry,can`t find the Driver!"); 
    	 			e.printStackTrace();}
         		catch(SQLException e) {
         			e.printStackTrace();
         		} catch(Exception e) {
         			e.printStackTrace();
         		}
    }
    //*****计算关键字频率不为0的电影有多少个，用于计算IDF的n(i)
    public static int getKeysMoviesNum(int user_id,String key)
    {
    	try {
    		String sql = "select count(lyy.movies.MID) from lyy.u1,lyy.movies where lyy.u1.ID="+user_id+" and (lyy.u1.MID=lyy.movies.MID and lyy.movies."+key+"=1)";
    		ResultSet n = stmt.executeQuery(sql);
    		n.next();
    		int Number = n.getInt("count(lyy.movies.MID)");
    		n.close();
    		return Number;
    	}catch(SQLException e) {
 			e.printStackTrace();
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
    	return -1;
    }
    //计算对应用户看来多少个电影，用于初始化电影数目
    public static int getNumMovies(int user_id)
    {
    	try {
    		String sql = "select count(lyy.movies.MID) from lyy.u1,lyy.movies where lyy.u1.ID="+user_id+" and lyy.u1.MID=lyy.movies.MID";
    		ResultSet n = stmt.executeQuery(sql);
    		n.next();
    		int Number = n.getInt("count(lyy.movies.MID)");
    		n.close();
    		return Number;
    	}catch(SQLException e) {
 			e.printStackTrace();
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
    	return -1;
    }
    //根据单个电影的词频率不为0生成目标关键字序列
    public static List<String> getItemString(int movies_id)
    {
    	String sql = "select Action,Adventure,Animation,Childrens,Comedy,Crime"
    			+ ",Documentary,Drama,Fantasy,FilmNoir,Horror,Musical,Mystery,Romance,SciFi,"+
    			"Thriller,War,Western from lyy.movies where MID="+movies_id;
    	String[] s = {"Action","Adventure","Animation","Childrens","Comedy","Crime"
    			,"Documentary","Drama","Fantasy","FilmNoir","Horror","Musical","Mystery","Romance","SciFi",
    			"Thriller","War","Western"};
    	try {
    		ResultSet n = stmt.executeQuery(sql);
    		int i=0;
    		int j;
    		List<String> re = new ArrayList<String>();
    		n.next();
    		while(i<s.length)
    		{
    			j = n.getInt(s[i]);
    			if(j == 1)
    				re.add(s[i]);
    			i++;
    		}
    		n.close();
    		return re;
    	}catch(SQLException e) {
          e.printStackTrace();
         } catch(Exception e) {
          e.printStackTrace();
      }
    	return null;
    }
    // 输出电影的单个关键字的词频，用与TF计算
    public static int getKeyWordsS(int movies_id,String key)
    {
    	try {
    		String sql = "select "+key+" from lyy.movies where MID="+movies_id;
    		ResultSet n = stmt.executeQuery(sql);
    		n.next();
    		int x = n.getInt(key);
    		n.close();
    		return x;
    	}catch(SQLException e) {
            e.printStackTrace();
            System.out.println(key+movies_id);
           } catch(Exception e) {
            e.printStackTrace();
            System.out.println(key+movies_id);
        }
    	return -1;
    }
    //生成电影集合
    public static List<Integer> getMoviesId(int user_id)
    {
    	try {
    		String sql = "select MID from lyy.u1 where ID="+user_id;
    		ResultSet n = stmt.executeQuery(sql);
    		List<Integer> id = new ArrayList<Integer>();
    		while(n.next())
    		{
    			id.add(n.getInt("MID"));
    		}
    		n.close();
    		return id;
    	}catch(SQLException e) {
            e.printStackTrace();
           } catch(Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
    //推荐分数获取
    public static double getScore(int user_id,int movies_id)
    {
    	try {
    		String sql = "select Score from lyy.u1 where ID="+user_id+" and MID="+movies_id;
    		ResultSet n = stmt.executeQuery(sql);
    		n.next();
    		double x = n.getDouble("Score");
    		n.close();
    		return x;
    	}catch(SQLException e) {
            e.printStackTrace();
           } catch(Exception e) {
            e.printStackTrace();
        }
    	return -1;
    }
    //为了TF*IDF的单位化，计算平方和
    public static double getItemPow(int movies_id)
    {
    	String sql = "select Action,Adventure,Animation,Childrens,Comedy,Crime"
    			+ ",Documentary,Drama,Fantasy,FilmNoir,Horror,Musical,Mystery,Romance,SciFi,"+
    			"Thriller,War,Western from lyy.movies where MID="+movies_id;
    	String[] s = {"Action","Adventure","Animation","Childrens","Comedy","Crime"
    			,"Documentary","Drama","Fantasy","FilmNoir","Horror","Musical","Mystery","Romance","SciFi",
    			"Thriller","War","Western"};
    	try {
    		ResultSet n = stmt.executeQuery(sql);
    		int i=0;
    		double j=0;
    		n.next();
    		while(i<s.length)
    		{
    			j += Math.pow(n.getDouble(s[i]), 2);
    			i++;
    		}
    		n.close();
    		return j;
    	}catch(SQLException e) {
            e.printStackTrace();
           } catch(Exception e) {
            e.printStackTrace();
        } 
    	return -1;
    }
    //关闭连接
    public void shutConnection()
    {
    	try {
    		stmt.close(); 
    		if(!conn.isClosed())
    			conn.close();
    	}catch(SQLException e) {
            e.printStackTrace();
           } catch(Exception e) {
            e.printStackTrace();
        } 
    }
}
