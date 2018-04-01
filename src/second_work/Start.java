package second_work;

import java.util.*;

public class Start {
	private final static int user_id = 405;
	private final static int movies_id = 288;
	public static void main(String[] srg)
	{
		Start.initMassage();		
		List<String> target_words = ConnectMySql.getItemString(movies_id);
		KeyWords.initIDF(target_words.size());
		List<Integer> num_movies = ConnectMySql.getMoviesId(user_id);
		
		for(int i=0;i<target_words.size();i++)
		{
			KeyWords.computIDF(user_id, target_words.get(i), i);
		}
		Movies target_movies = new Movies(target_words);
		target_movies.setMoviesId(movies_id);
		target_movies.initKeyWords();
		
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
