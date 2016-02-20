package crawlers;

import crawlers_impl.RateLimitCrawler;

public class RateLimit
{
	public static void main(String args[]) throws Exception
	{
		RateLimitParam param=new RateLimitParam(args);
		if (param.help) return;
		RateLimitCrawler crawler=new RateLimitCrawler(param.cKey, param.cSecret, param.aToken, param.aSecret);
		if (param.limit==null)
		{
			crawler.getAllRateLimit();
		}
		else
		{
			crawler.getRateLimit(param.limit);
		}
	}
}
