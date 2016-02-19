package tweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import cfg.Cfg;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

public class TweetFromJson
{
	public ArrayList<Status> statuses;
	
	public void readTweets(String tweetFileName) throws Exception
	{
		BufferedReader br=new BufferedReader(new FileReader(tweetFileName));
		String line;
		while ((line=br.readLine())!=null)
		{
			statuses.add(TwitterObjectFactory.createStatus(line));
		}
		br.close();
	}
	
	public void writeText(String textFileName) throws Exception
	{
		BufferedWriter bw=new BufferedWriter(new FileWriter(textFileName));
		for (Status status : statuses)
		{
			bw.write(status.getId()+"\t"+status.getText());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
	
	public TweetFromJson()
	{
		statuses=new ArrayList<Status>();
	}
	
	public static void main(String args[]) throws Exception
	{
		TweetFromJson tfj=new TweetFromJson();
		tfj.readTweets(Cfg.dataPath+"Tweet"+File.separator+"example.txt");
		tfj.writeText(Cfg.dataPath+"Tweet"+File.separator+"text.txt");
	}
}
