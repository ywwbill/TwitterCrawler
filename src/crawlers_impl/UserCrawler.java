package crawlers_impl;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import cfg.Cfg;
import twitter4j.User;
import twitter4j.IDs;
import twitter4j.TwitterException;

public class UserCrawler extends BaseCrawler
{	
	public void crawlUserNameByIDFile(String idFileName, String nameFileName) throws Exception
	{
		ArrayList<Long> ids=new ArrayList<Long>();
		BufferedReader br=new BufferedReader(new FileReader(idFileName));
		String line;
		while ((line=br.readLine())!=null)
		{
			ids.add(Long.valueOf(line));
		}
		br.close();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(nameFileName));
		for (int i=0; i<ids.size(); i++)
		{
			try
			{
				User user=twitter.showUser(ids.get(i));
				bw.write(ids.get(i)+"\t"+user.getName());
				bw.newLine();
				bw.flush();
				System.out.println(nameFileName+"\t"+(i+1)+"/"+ids.size()+"\t"+ids.get(i)+"\t"+user.getName());
			}
			catch (TwitterException te)
			{
				System.out.println(nameFileName+"\t"+(i+1)+"/"+ids.size()+"\t"+ids.get(i)+"\tFailed");
			}
			Thread.sleep(5300);
		}
		bw.close();
	}
	
	public void crawlUserNameByIDDir(String idDirName, String nameDirName) throws Exception
	{
		File idFiles[]=new File(idDirName).listFiles();
		for (int i=0; i<idFiles.length; i++)
		{
			crawlUserNameByIDFile(idDirName+idFiles[i].getName(), nameDirName+idFiles[i].getName());
		}
	}
	
	public void crawlFollowerByIDFile(String idFileName, String followerIDFileName) throws Exception
	{
		ArrayList<Long> ids=new ArrayList<Long>();
		BufferedReader br=new BufferedReader(new FileReader(idFileName));
		String line;
		while ((line=br.readLine())!=null)
		{
			ids.add(Long.valueOf(line));
		}
		br.close();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(followerIDFileName));
		long cursor=-1;
		for (int i=0; i<ids.size(); i++)
		{
			try
			{
				IDs followerIDs=twitter.getFollowersIDs(ids.get(i), cursor);
				for (int j=0; j<followerIDs.getIDs().length; j++)
				{
					bw.write(ids.get(i)+"\t"+followerIDs.getIDs()[j]);
					bw.newLine();
				}
				bw.flush();
				System.out.println(idFileName+"\t"+ids.get(i)+"\t"+followerIDs.getIDs().length);
			}
			catch (TwitterException te)
			{
				System.out.println(idFileName+"\t"+ids.get(i)+"\tFailed");
			}
			Thread.sleep(61000);
		}
		bw.close();
	}
	
	public void crawlFollowerByIDDir(String idDirName, String followerIDDirName) throws Exception
	{
		File idFiles[]=new File(idDirName).listFiles();
		for (int i=0; i<idFiles.length; i++)
		{
			crawlFollowerByIDFile(idDirName+idFiles[i].getName(), followerIDDirName+idFiles[i].getName());
		}
	}
	
	public void crawlFriendByIDFile(String idFileName, String friendIDFileName) throws Exception
	{
		ArrayList<Long> ids=new ArrayList<Long>();
		BufferedReader br=new BufferedReader(new FileReader(idFileName));
		String line;
		while ((line=br.readLine())!=null)
		{
			ids.add(Long.valueOf(line));
		}
		br.close();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(friendIDFileName));
		long cursor=-1;
		for (int i=0; i<ids.size(); i++)
		{
			try
			{
				IDs friendIDs=twitter.getFriendsIDs(ids.get(i), cursor);
				for (int j=0; j<friendIDs.getIDs().length; j++)
				{
					bw.write(ids.get(i)+"\t"+friendIDs.getIDs()[j]);
					bw.newLine();
				}
				bw.flush();
				System.out.println(idFileName+"\t"+ids.get(i)+"\t"+friendIDs.getIDs().length);
			}
			catch (TwitterException te)
			{
				System.out.println(idFileName+"\t"+ids.get(i)+"\tFailed");
			}
			Thread.sleep(61000);
		}
		bw.close();
	}
	
	public void crawlFriendByIDDir(String idDirName, String friendIDDirName) throws Exception
	{
		File idFiles[]=new File(idDirName).listFiles();
		for (int i=0; i<idFiles.length; i++)
		{
			crawlFriendByIDFile(idDirName+idFiles[i].getName(), friendIDDirName+idFiles[i].getName());
		}
	}
	
	public UserCrawler(String keyFileName) throws Exception
	{
		super(keyFileName);
	}
	
	public UserCrawler(String cKey, String cSecret, String aToken, String aSecret) throws Exception
	{
		super(cKey, cSecret, aToken, aSecret);
	}
	
	public static void main(String args[]) throws Exception
	{
		UserCrawler crawler=new UserCrawler(Cfg.dataPath+"keys.txt");
		crawler.crawlUserNameByIDDir(Cfg.dataPath+"UserNameByID"+File.separator+"ids"+File.separator,
				Cfg.dataPath+"UserNameByID"+File.separator+"names"+File.separator);
		crawler.crawlFollowerByIDDir(Cfg.dataPath+"FollowerIDByID"+File.separator+"ids"+File.separator,
				Cfg.dataPath+"FollowerIDByID"+File.separator+"followers"+File.separator);
		crawler.crawlFriendByIDDir(Cfg.dataPath+"FriendIDByID"+File.separator+"ids"+File.separator,
				Cfg.dataPath+"FriendIDByID"+File.separator+"friends"+File.separator);
	}
}
