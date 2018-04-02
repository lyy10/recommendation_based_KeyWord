package second_work;
/*
 * 2018-04-02 @Lyy
 * 程序基于movilens的item，采用给定电影的类别经行基于内容的评分推荐 
*/
import java.util.*;
public class Start {
	private final static int user_id = 405;		//用户ID
	private final static int movies_id = 288;	//电影ID
	public static void main(String[] srg)
	{
		Start.initMassage();		//初始化数据库，电影总数
		
		//*********初始化目标关键字集合与电影ID集合
		List<String> target_words = ConnectMySql.getItemString(movies_id);
		KeyWords.initIDF(target_words.size());
		List<Integer> num_movies = ConnectMySql.getMoviesId(user_id);
		
		//**********分别计算每个关键字的IDF值
		for(int i=0;i<target_words.size();i++)
		{
			KeyWords.computIDF(user_id, target_words.get(i), i);
		}
		
		//**********初始化推荐目标的电影类
		Movies target_movies = new Movies(target_words);
		target_movies.setMoviesId(movies_id);
		target_movies.initKeyWords();
		
		//***********针对每一个用户看过的电影，计算其关键字单位TF*IDF值，并和目标电影点积，存储最大相关电影
		int numM = Movies.getNumMovies();
		int MID=0;
		double MID_SIM=-1;
		double sim;
		for(int i=0;i<numM;i++)
		{
			Movies Temp = new Movies(target_words);
			Temp.setMoviesId(num_movies.get(i));
			Temp.initKeyWords();
			sim=0;
			for(int j=0;j<target_words.size();j++)
				sim += target_movies.getTFIDF(j)*Temp.getTFIDF(j);
			if(MID_SIM < sim)
			{
				MID_SIM = sim;
				MID = num_movies.get(i);
			}
		}
		
		//***********打印推荐分数
		double score;
		score = ConnectMySql.getScore(user_id, MID);
		System.out.println(score);
		
		
		
		
	}
	private static void initMassage()
	{
		ConnectMySql.initConnect();
		Movies.initNumMovies(user_id);
		
	}
}
