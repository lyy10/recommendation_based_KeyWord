package second_work;
import java.sql.*;
import java.util.*;

public class ConnectMySql {
	// JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306";
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "1010";
    private static Connection conn = null;
    private static Statement stmt = null;
    public static void initConnect() 
    {
    	 try {
    	        // ע�� JDBC ����
    	        Class.forName(JDBC_DRIVER);
    	        // ������
//    	        System.out.println("�������ݿ�...");
    	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
//    	        if(!conn.isClosed()) 
//    	            System.out.println("Succeeded connecting to the Database!");
//    	        // ִ�в�ѯ
//    	        System.out.println(" ʵ����Statement����...");
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

    
    public void shutConnection()
    {
    	try {
    		if(!conn.isClosed())
    			conn.close();
    	}catch(SQLException e) {
            e.printStackTrace();
           } catch(Exception e) {
            e.printStackTrace();
        } 
    }
    /*
    sql = "SELECT id, name, url FROM websites";
    ResultSet rs = stmt.executeQuery(sql);
    
     	//չ����������ݿ�
    while(rs.next()){
     //  ͨ���ֶμ���
       int id  = rs.getInt("id");
       String name = rs.getString("name");
       String url = rs.getString("url");

       // �������
       System.out.print("ID: " + id);
       System.out.print(", վ������: " + name);
       System.out.print(", վ�� URL: " + url);
       System.out.print("\n");
    }
    // ��ɺ�ر�
    rs.close();
    stmt.close(); 
    */
}
