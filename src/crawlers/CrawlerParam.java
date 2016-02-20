package crawlers;

import java.io.BufferedReader;
import java.io.FileReader;

public class CrawlerParam
{
	public String tokenFileName,cKey,cSecret,aToken,aSecret;
	public String input,output;
	public boolean directory,help;
	public int numPages,numTweets;
	
	public CrawlerParam(String args[]) throws Exception
	{
		numPages=1;
		numTweets=200;
		for (int i=0; i<args.length; i++)
		{
			if (args[i].equals("-tf") && i+1<args.length) tokenFileName=args[i+1];
			if (args[i].equals("-ck") && i+1<args.length) cKey=args[i+1];
			if (args[i].equals("-cs") && i+1<args.length) cSecret=args[i+1];
			if (args[i].equals("-at") && i+1<args.length) aToken=args[i+1];
			if (args[i].equals("-as") && i+1<args.length) aSecret=args[i+1];
			if (args[i].equals("-i") && i+1<args.length) input=args[i+1];
			if (args[i].equals("-o") && i+1<args.length) output=args[i+1];
			if (args[i].equals("-d")) directory=true;
			if (args[i].equals("-p") && i+1<args.length) numPages=Integer.valueOf(args[i+1]);
			if (args[i].equals("-t") && i+1<args.length) numTweets=Integer.valueOf(args[i+1]);
			if (args[i].equals("-h"))
			{
				help=true;
				outputHelp();
				return;
			}
		}
		
		if (tokenFileName!=null)
		{
			BufferedReader br=new BufferedReader(new FileReader(tokenFileName));
			cKey=br.readLine();
			cSecret=br.readLine();
			aToken=br.readLine();
			aSecret=br.readLine();
			br.close();
		}
		
		if (cKey==null || cSecret==null || aToken==null || aSecret==null)
		{
			throw new Exception("Application key, secret and/or token are not given. Use -h for help.");
		}
		
		if (input==null)
		{
			throw new Exception("Input file/directory is not given. Use -h for help.");
		}
		
		if (output==null)
		{
			throw new Exception("Output file/directory is not given. Use -h for help.");
		}
		
		if (numPages>16) numPages=16;
		if (numTweets>200) numTweets=200;
	}
	
	public void outputHelp()
	{
		System.out.println("Parameters for crawlers:");
		System.out.println("\t-tf: Token file name, necessary if -ck, -cs, -at or -as is not given");
		System.out.println("\t-ck: Consumer key, necessary if -tf is not given");
		System.out.println("\t-cs: Consumer secret, necessary if -tf is not given");
		System.out.println("\t-at: Access token, necessary if -tf is not given");
		System.out.println("\t-as: Access token secret, necessary if -tf is not given");
		System.out.println("\t-d: Input and output are directories, default false");
		System.out.println("\t-i: Input file/directory name, necessary");
		System.out.println("\t-o: Output file/directory name, necessary");
		System.out.println("\t-p: #Requests per user when crawling his tweets, default 1, max 200");
		System.out.println("\t-t: #Tweets per request when crawling a user's tweets, default 200, max 200");
		System.out.println("\t-h: Show help information");
	}
	
	public String toString()
	{
		String s="Consumer Key: "+cKey+"\n";
		s+="Consumer Secret: "+cSecret+"\n";
		s+="Access Token: "+aToken+"\n";
		s+="Access Secret: "+aSecret+"\n";
		s+="Input: "+input+"\n";
		s+="Output: "+output+"\n";
		s+="Directory: "+directory;
		return s;
	}
}
