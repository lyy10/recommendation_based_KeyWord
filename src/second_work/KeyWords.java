package second_work;

import java.util.*;

//关键字对应着电影ID
public class KeyWords {
	private static double[] IDF;
	private String Words;
	private int TF; 
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
	public static void computIDF(int user_id,String s,int n)
	{
		int Num_movies = Movies.getNumMovies();
		
	}
	
}