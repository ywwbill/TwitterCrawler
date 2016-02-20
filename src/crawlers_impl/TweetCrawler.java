package crawlers_impl;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import cfg.Cfg;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

public class TweetCrawler extends BaseCrawler
{
	public void crawlTweetByTweetIDFile(String idFileName, String tweetFileName) throws Exception
	{
		ArrayList<String> ids=new ArrayList<String>();
		BufferedReader br=new BufferedReader(new FileReader(idFileName));
		String line;
		while ((line=br.readLine())!=null)
		{
			ids.add(line);
		}
		br.close();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(tweetFileName));
		line="";
		String seg[];
		long crawlIDs[];
		for (int i=0; i<ids.size(); i++)
		{
			if (line.length()==0)
			{
				line+=ids.get(i);
			}
			else
			{
				line+=","+ids.get(i);
			}
			
			if ((i+1)%100==0 || i==ids.size()-1)
			{
				seg=line.split(",");
				line="";
				crawlIDs=new long[seg.length];
				for (int j=0; j<seg.length; j++)
				{
					crawlIDs[j]=Long.valueOf(seg[j]);
				}
				
				try
				{
					ResponseList<Status> response=twitter.lookup(crawlIDs);
					for (Status status : response)
					{
						bw.write(TwitterObjectFactory.getRawJSON(status));
						bw.newLine();
					}
					bw.flush();
					System.out.println(idFileName+"\t"+(i+1)+"/"+ids.size()+"\t"+response.size()+"/"+seg.length);
				}
				catch (TwitterException e)
				{
					System.out.println(idFileName+"\t"+(i+1)+"/"+ids.size()+"\tFailed");
				}
				
				Thread.sleep(5300);
			}
		}
		bw.close();
	}
	
	public void crawlTweetByTweetIDDir(String idDirName, String tweetDirName) throws Exception
	{
		File idFiles[]=new File(idDirName).listFiles();
		for (int i=0; i<idFiles.length; i++)
		{
			crawlTweetByTweetIDFile(idDirName+idFiles[i].getName(), tweetDirName+idFiles[i].getName());
		}
	}
	
	public void crawlTweetByUserIDFile(String idFileName, String tweetFileName, int numPages, int numTweets) throws Exception
	{
		ArrayList<Long> ids=new ArrayList<Long>();
		BufferedReader br=new BufferedReader(new FileReader(idFileName));
		String line;
		while ((line=br.readLine())!=null)
		{
			ids.add(Long.valueOf(line));
		}
		br.close();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(tweetFileName));
		for (int i=0; i<ids.size(); i++)
		{
			for (int page=1; page<=numPages; page++)
			{
				try
				{
					ResponseList<Status> response=twitter.getUserTimeline(ids.get(i), new Paging(page, numTweets));
					for (Status status : response)
					{
						bw.write(TwitterObjectFactory.getRawJSON(status));
						bw.newLine();
					}
					bw.flush();
					System.out.println(idFileName+"\t"+(i+1)+"/"+ids.size()+"\tPage "+page+"/"+numPages+"\t"+response.size());
				}
				catch (TwitterException te)
				{
					System.out.println(idFileName+"\t"+(i+1)+"/"+ids.size()+"\tPage "+page+"/"+numPages+"\tFailed");
				}
				Thread.sleep(5300);
			}
		}
		bw.close();
	}
	
	public void crawlTweetByUserIDDir(String idDirName, String tweetDirName, int numPages, int numTweets) throws Exception
	{
		File idFiles[]=new File(idDirName).listFiles();
		for (int i=0; i<idFiles.length; i++)
		{
			crawlTweetByUserIDFile(idDirName+idFiles[i].getName(), tweetDirName+idFiles[i].getName(), numPages, numTweets);
		}
	}
	
	public TweetCrawler(String keyFileName) throws Exception
	{
		super(keyFileName);
	}
	
	public TweetCrawler(String cKey, String cSecret, String aToken, String aSecret) throws Exception
	{
		super(cKey, cSecret, aToken, aSecret);
	}
	
	public static void main(String args[]) throws Exception
	{
		TweetCrawler crawler=new TweetCrawler(Cfg.dataPath+"keys.txt");
		crawler.crawlTweetByTweetIDDir(Cfg.dataPath+"TweetByID"+File.separator+"ids"+File.separator,
				Cfg.dataPath+"TweetByID"+File.separator+"tweets"+File.separator);
		crawler.crawlTweetByUserIDDir(Cfg.dataPath+"TweetByUser"+File.separator+"ids"+File.separator,
				Cfg.dataPath+"TweetByUser"+File.separator+"tweets"+File.separator, 2, 200);
	}
}
