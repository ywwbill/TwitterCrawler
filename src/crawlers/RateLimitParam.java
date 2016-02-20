package crawlers;

import java.io.BufferedReader;
import java.io.FileReader;

public class RateLimitParam
{
	public String tokenFileName,cKey,cSecret,aToken,aSecret,limit;
	public boolean help;
	
	public RateLimitParam(String args[]) throws Exception
	{
		for (int i=0; i<args.length; i++)
		{
			if (args[i].equals("-tf") && i+1<args.length) tokenFileName=args[i+1];
			if (args[i].equals("-ck") && i+1<args.length) cKey=args[i+1];
			if (args[i].equals("-cs") && i+1<args.length) cSecret=args[i+1];
			if (args[i].equals("-at") && i+1<args.length) aToken=args[i+1];
			if (args[i].equals("-as") && i+1<args.length) aSecret=args[i+1];
			if (args[i].equals("-l") && i+1<args.length) limit=args[i+1];
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
	}
	
	public void outputHelp()
	{
		System.out.println("Parameters for crawlers:");
		System.out.println("\t-tf: Token file name, necessary if -ck, -cs, -at or -as is not given");
		System.out.println("\t-ck: Consumer key, necessary if -tf is not given");
		System.out.println("\t-cs: Consumer secret, necessary if -tf is not given");
		System.out.println("\t-at: Access token, necessary if -tf is not given");
		System.out.println("\t-as: Access token secret, necessary if -tf is not given");
		System.out.println("\t-l: Limit type, default all");
		System.out.println("\t-h: Show help information");
	}
	
	public String toString()
	{
		String s="Consumer Key: "+cKey+"\n";
		s+="Consumer Secret: "+cSecret+"\n";
		s+="Access Token: "+aToken+"\n";
		s+="Access Secret: "+aSecret+"\n";
		return s;
	}
}
