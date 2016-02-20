package crawlers;

import crawlers_impl.TweetCrawler;

public class TweetsByTweetID
{
	public static void main(String args[]) throws Exception
	{
		CrawlerParam param=new CrawlerParam(args);
		if (param.help) return;
		TweetCrawler crawler=new TweetCrawler(param.cKey, param.cSecret, param.aToken, param.aSecret);
		if (param.directory)
		{
			crawler.crawlTweetByTweetIDDir(param.input, param.output);
		}
		else
		{
			crawler.crawlTweetByTweetIDFile(param.input, param.output);
		}
	}
}
