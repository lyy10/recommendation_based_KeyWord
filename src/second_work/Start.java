package second_work;

import java.util.*;

public class Start {
	private final static int user_id = 405;
	private final static int movies_id = 288;
	public static void main(String[] srg)
	{
		ConnectMySql.initConnect();
		Movies.initNumMovies(user_id);
		
	}
}
