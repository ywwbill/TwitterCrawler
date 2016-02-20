package tweet;

public class ExtractorParam
{
	public String input,output;
	public boolean directory,help;
	
	public ExtractorParam(String args[]) throws Exception
	{
		for (int i=0; i<args.length; i++)
		{
			if (args[i].equals("-i") && i+1<args.length) input=args[i+1];
			if (args[i].equals("-o") && i+1<args.length) output=args[i+1];
			if (args[i].equals("-d")) directory=true;
			if (args[i].equals("-h"))
			{
				help=true;
				outputHelp();
				return;
			}
		}
		
		if (input==null)
		{
			throw new Exception("Input file/directory is not given. Use -h for help.");
		}
		
		if (output==null)
		{
			throw new Exception("Output file/directory is not given. Use -h for help.");
		}
	}
	
	public void outputHelp()
	{
		System.out.println("Parameters for crawlers:");
		System.out.println("\t-d: Input and output are directories, default false");
		System.out.println("\t-i: Input file/directory name, necessary");
		System.out.println("\t-o: Output file/directory name, necessary");
		System.out.println("\t-h: Show help information");
	}
	
	public String toString()
	{
		String s="Input: "+input+"\n";
		s+="Output: "+output+"\n";
		s+="Directory: "+directory;
		return s;
	}
}
