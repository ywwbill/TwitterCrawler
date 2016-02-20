package tweet;

import tweet_impl.TweetFromJson;

public class TextExtractor
{
	public static void main(String args[]) throws Exception
	{
		ExtractorParam param=new ExtractorParam(args);
		if (param.help) return;
		TweetFromJson tfj=new TweetFromJson();
		if (param.directory)
		{
			tfj.convertTweetDir(param.input, param.output);
		}
		else
		{
			tfj.convertTweetFile(param.input, param.output);
		}
	}
}
