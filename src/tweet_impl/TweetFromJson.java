package tweet_impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import cfg.Cfg;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

public class TweetFromJson
{
	public void convertTweetFile(String tweetFileName, String textFileName) throws Exception
	{
		BufferedReader br=new BufferedReader(new FileReader(tweetFileName));
		BufferedWriter bw=new BufferedWriter(new FileWriter(textFileName));
		String line;
		Status status;
		while ((line=br.readLine())!=null)
		{
			status=TwitterObjectFactory.createStatus(line);
			bw.write(status.getId()+"\t"+status.getText());
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public void convertTweetDir(String tweetDirName, String textDirName) throws Exception
	{
		File tweetFiles[]=new File(tweetDirName).listFiles();
		for (int i=0; i<tweetFiles.length; i++)
		{
			convertTweetFile(tweetDirName+tweetFiles[i].getName(), textDirName+tweetFiles[i].getName());
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		TweetFromJson tfj=new TweetFromJson();
		tfj.convertTweetDir(Cfg.dataPath+"TweetJson"+File.separator+"input"+File.separator,
				Cfg.dataPath+"TweetJson"+File.separator+"output"+File.separator);
	}
}
