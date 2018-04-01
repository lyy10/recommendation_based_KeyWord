package second_work;

import java.util.*;

//关键字对应着电影ID
public class KeyWords {
	private static double[] IDF;
	private String Words;
	private int TF; 
	private double TF_IDF;
	public static void initIDF(int N)
	{
//		for(int i=0;i<N;i++)
			IDF = new double[N];
	}
	public void setWords(String w)
	{
		this.Words = w;
	}
	public void setTF(int n)
	{
		this.TF = n;
	}
	public String getWords()
	{
		return this.Words;
	}
	public int getTF()
	{
		return this.TF;
	}
	public void setTFIDF(double tfidf)
	{
		this.TF_IDF = tfidf;
	}
	public double getTFIDF()
	{
		return this.TF_IDF;
	}
	public static void computIDF(int user_id,String s,int n)
	{
		int Num_movies = Movies.getNumMovies();//电影总数量
		int num_key_movies = ConnectMySql.getKeysMoviesNum(user_id, s);
		double idf = (double)num_key_movies/(double)Num_movies;
		IDF[n] = idf;
	}
	public static double getIDF(int n)
	{
		return IDF[n];
	}
}