package crawlers;

import crawlers_impl.TweetCrawler;

public class TweetsByUserID
{
	public static void main(String args[]) throws Exception
	{
		CrawlerParam param=new CrawlerParam(args);
		if (param.help) return;
		TweetCrawler crawler=new TweetCrawler(param.cKey, param.cSecret, param.aToken, param.aSecret);
		if (param.directory)
		{
			crawler.crawlTweetByUserIDDir(param.input, param.output, param.numPages, param.numTweets);
		}
		else
		{
			crawler.crawlTweetByUserIDFile(param.input, param.output, param.numPages, param.numTweets);
		}
	}
}
