package crawlers;

import crawlers_impl.UserCrawler;;

public class ScreenName
{
	public static void main(String args[]) throws Exception
	{
		CrawlerParam param=new CrawlerParam(args);
		if (param.help) return;
		UserCrawler crawler=new UserCrawler(param.cKey, param.cSecret, param.aToken, param.aSecret);
		if (param.directory)
		{
			crawler.crawlUserNameByIDDir(param.input, param.output);
		}
		else
		{
			crawler.crawlUserNameByIDFile(param.input, param.output);
		}
	}
}
