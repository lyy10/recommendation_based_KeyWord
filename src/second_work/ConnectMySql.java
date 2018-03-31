package second_work;
import java.sql.*;

public class ConnectMySql {
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1010";
    private static Connection conn = null;
    private static Statement stmt = null;
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
    public static void getKeysMoviesNum(int user_id,String key)
    {
    	try {
    		
    	}catch(ClassNotFoundException e) {
 			System.out.println("Sorry,can`t find the Driver!"); 
 			e.printStackTrace();}
 		catch(SQLException e) {
 			e.printStackTrace();
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
    }
//    public void getUser(User x,int item){
//        
//        try {
//        String sql;
//        
//        sql = "select count(ID) from lyy.ratings where ID="+x.getId();
//        ResultSet n = stmt.executeQuery(sql);
//        n.next();
//        int Number = n.getInt("count(ID)");
//        n.close();
//        if(Number == 0)
//        	return;
//        
//        x.setMoviesMany(Number);
//        sql = "select MID,Score from lyy.ratings ";
//        sql = sql + "where ID=" + x.getId() + " order by MID";
//        n = stmt.executeQuery(sql);
//        int i=0;
//        while(n.next())
//        {
//        	int Mid = n.getInt("MID");
//        	double S = n.getDouble("Score");
//        	x.setMoviesValue(i++, Mid, S);
//        }
//        
//        n.close();
//        if(item != -1)
//        {
//        	sql = "select ID from lyy.ratings where ID="+x.getId()+" and MID="+item;
//        	ResultSet se = stmt.executeQuery(sql);
//        	if(!se.next())
//        	{
//        		x.setId(-1);
//        		se.close();
//        		return;
//        	}
//        	se.close();
//        }
//        //conn.close();
//        }catch(SQLException e) {
//            e.printStackTrace();
//           } catch(Exception e) {
//            e.printStackTrace();
//        } 
//        
//        //System.out.println("Goodbye!");
//    }
    
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
    
     	//展开结果集数据库
    while(rs.next()){
     //  通过字段检索
       int id  = rs.getInt("id");
       String name = rs.getString("name");
       String url = rs.getString("url");

       // 输出数据
       System.out.print("ID: " + id);
       System.out.print(", 站点名称: " + name);
       System.out.print(", 站点 URL: " + url);
       System.out.print("\n");
    }
    // 完成后关闭
    rs.close();
    stmt.close(); 
    */
}
