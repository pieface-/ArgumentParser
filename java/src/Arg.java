package pieface.argumentparser;

public class Arg
{
	private String flags[];
	private String flagsParam[];
	private String argument[];
	private boolean valid;
	//assumes default parse, not invalid based on nonexistent flags
	public Arg(String args[])
	{
		this(args, new ArgumentFormat());
	}

	//will allow invalid to be determined based on user choice
	public Arg(String args[], ArgumentFormat format)
	{
		try {

			this.flags = new String[args.length];
			this.flagsParam = new String[args.length];
			this.argument = new String[args.length];
			int flagsIndex = 0, flagsParamIndex = 0, argumentIndex = 0;
			for (int i = 0; i < args.length; i++)
			{
				if(format.isAFlag(args[i]))
				{
					flags[flagsIndex] = args[i];
					this.flagsParam[flagsParamIndex] = "";
					int x = format.numOfParams(args[i]);
					for (int j = 0; j < x; j++, i++)
					{
						this.flagsParam[flagsParamIndex] += args[i+1] + (j == x-1 ? "" : " ");
					}
					flagsParamIndex++;
					flagsIndex++;

				}
				else if(args[i].charAt(0) == '-')
				{
					this.valid = false;
					return;
				}
				else
				{
					this.argument[argumentIndex] = args[i];
					argumentIndex++;
				}

			}
			trim();
			valid = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace(System.out);
			valid = false;
		}

	}

	public Arg(String args)
	{
		this(args.split(" "));
	}

	public Arg(String args, ArgumentFormat format)
	{
		this(args.split(" "), format);
	}

	public String toString()
	{
		if(this.valid)
		{
			String out = "";
			for(int i = 0; i < this.flags.length; i++)
				out += "Flag" + (i+1) + ": " + this.flags[i] + " - " + this.flagsParam[i] + "\n";
			for(int i = 0; i < this.argument.length; i++)
				out += this.argument[i] + "\n";
			return out.substring(0,out.length()-1);
		}
		else
		{
			return "invalid";

		}
	}

	public boolean isAFlag(String flag)
	{
		for(int i = 0; i < this.flags.length; i++)
			if(this.flags[i].equals(flag)) return true;
		return false;
	}

	public String getFlagParams(String flag)
	{
		for(int i = 0; i < this.flags.length; i++)
			if(this.flags[i].equals(flag)) return this.flagsParam[i];
		return "";
	}

	public boolean isValid() 
	{ 
		return valid; 
	}
	
	public int getArgCount()
	{
		return this.argument.length;
	}
	
	public int getFlagCount()
	{
		return this.flags.length;
	}

	public String getArg(int i)
	{

		try
		{
			if(valid)
				return this.argument[i];
		}
		catch(Exception ex)
		{}
		return "";
	}

	private void trim()
	{
		for(int i = 0; i < this.flags.length; i++)
			if (this.flags[i] == null)
			{
				String s[] = this.flags;
				this.flags = new String[i];
				for(int j = 0; j<i; j++)
					this.flags[j] = s[j];
				break;
			}
		for(int i = 0; i < this.flagsParam.length; i++)
			if (this.flagsParam[i] == null)
			{
				String s[] = this.flagsParam;
				this.flagsParam = new String[i];
				for(int j = 0; j<i; j++)
					this.flagsParam[j] = s[j];
				break;
			}
		for(int i = 0; i < this.argument.length; i++)
			if (this.argument[i] == null)
			{
				String s[] = this.argument;
				this.argument = new String[i];
				for(int j = 0; j<i; j++)
					this.argument[j] = s[j];
				break;
			}
	}
}
