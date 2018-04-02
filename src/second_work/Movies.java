package second_work;
//电影类
import java.util.List;

public class Movies {
	
	private static int Num_Movies;
	private KeyWords[] keywords;
	private int moviesid;
	Movies(List<String> N)
	{
		keywords = new KeyWords[N.size()];
		for(int i=0;i<N.size();i++)
		{
			keywords[i] = new KeyWords();
			keywords[i].setWords(N.get(i));
			
		}	
	}
	public static int getNumMovies()
	{
		return Num_Movies;
	}
	public static void initNumMovies(int user_id)
	{
		Num_Movies = ConnectMySql.getNumMovies(user_id);
	}
	public void setMoviesId(int id)
	{
		this.moviesid = id;
	}
	public double getTFIDF(int n)
	{
		return this.keywords[n].getTFIDF();
	}
	//计算对应用户的TF*IDF
	public void initKeyWords()
	{
		for(int i=0;i<this.keywords.length;i++)
		{
			keywords[i].setTF(ConnectMySql.getKeyWordsS(this.moviesid, keywords[i].getWords()));
			keywords[i].setTFIDF(keywords[i].getTF()*KeyWords.getIDF(i));
		}
		double e = ConnectMySql.getItemPow(this.moviesid);//计算IDF的平方和
//		for(int i=0;i<this.keywords.length;i++)
//			e = e + Math.pow(keywords[i].getTFIDF(), 2);
		e = Math.sqrt(e);
		for(int i=0;i<this.keywords.length;i++) //单位化
			keywords[i].setTFIDF(keywords[i].getTFIDF()/e);
		
	}
	
	
}
