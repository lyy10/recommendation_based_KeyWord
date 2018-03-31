package second_work;

public class Movies {
	
	private static int Num_Movies;
	private KeyWords[] keywords;
	private int moviesid;
	Movies(int N)
	{
		for(int i=0;i<N;i++)
			keywords[i] = new KeyWords();
		
	}
	public static int getNumMovies()
	{
		return Num_Movies;
	}
	public static void initNumMovies(int user_id)
	{
		Num_Movies = ConnectMySql.getNumMovies(user_id);
	}
	
	
}
