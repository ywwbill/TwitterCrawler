package crawlers_impl;

import java.util.Map;

import cfg.Cfg;
import twitter4j.RateLimitStatus;

public class RateLimitCrawler extends BaseCrawler
{
	public Map<String, RateLimitStatus> rateLimitStatus;
	
	public void crawlRateLimit() throws Exception
	{
		rateLimitStatus=twitter.getRateLimitStatus();
	}
	
	public void getRateLimit(String item) throws Exception
	{
		if (rateLimitStatus==null)
		{
			crawlRateLimit();
		}
		if (!rateLimitStatus.containsKey(item))
		{
			System.out.println("No such item.");
			return;
		}
		RateLimitStatus status=rateLimitStatus.get(item);
		System.out.println("Item: "+item);
		System.out.println("\tLimit: " + status.getLimit());
		System.out.println("\tRemaining: " + status.getRemaining());
		System.out.println("\tReset Time In Seconds: " + status.getResetTimeInSeconds());
		System.out.println("\tSeconds Until Reset: " + status.getSecondsUntilReset());
	}
	
	public void getAllRateLimit() throws Exception
	{
		if (rateLimitStatus==null)
		{
			crawlRateLimit();
		}
		for (String item : rateLimitStatus.keySet())
		{
			getRateLimit(item);
		}
	}
	
	public RateLimitCrawler(String keyFileName) throws Exception
	{
		super(keyFileName);
	}
	
	public RateLimitCrawler(String cKey, String cSecret, String aToken, String aSecret) throws Exception
	{
		super(cKey, cSecret, aToken, aSecret);
	}
	
	public static void main(String args[]) throws Exception
	{
		RateLimitCrawler crawler=new RateLimitCrawler(Cfg.dataPath+"keys.txt");
		crawler.getAllRateLimit();
	}
}
