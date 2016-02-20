package crawlers_impl;

import java.io.BufferedReader;
import java.io.FileReader;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class BaseCrawler
{
	public Twitter twitter;
	
	public BaseCrawler(String keyFileName) throws Exception
	{
		ConfigurationBuilder cb=new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setJSONStoreEnabled(true);
		
		BufferedReader br=new BufferedReader(new FileReader(keyFileName));
		cb.setOAuthConsumerKey(br.readLine());
		cb.setOAuthConsumerSecret(br.readLine());
		cb.setOAuthAccessToken(br.readLine());
		cb.setOAuthAccessTokenSecret(br.readLine());
		br.close();
		
		twitter=new TwitterFactory(cb.build()).getInstance();
	}
	
	public BaseCrawler(String cKey, String cSecret, String aToken, String aSecret) throws Exception
	{
		ConfigurationBuilder cb=new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setJSONStoreEnabled(true);
		cb.setOAuthConsumerKey(cKey);
		cb.setOAuthConsumerSecret(cSecret);
		cb.setOAuthAccessToken(aToken);
		cb.setOAuthAccessTokenSecret(aSecret);
		twitter=new TwitterFactory(cb.build()).getInstance();
	}
}
