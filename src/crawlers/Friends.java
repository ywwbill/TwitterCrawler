package crawlers;

import crawlers_impl.UserCrawler;

public class Friends
{
	public static void main(String args[]) throws Exception
	{
		CrawlerParam param=new CrawlerParam(args);
		if (param.help) return;
		UserCrawler crawler=new UserCrawler(param.cKey, param.cSecret, param.aToken, param.aSecret);
		if (param.directory)
		{
			crawler.crawlFriendByIDDir(param.input, param.output);
		}
		else
		{
			crawler.crawlFriendByIDFile(param.input, param.output);
		}
	}
}
