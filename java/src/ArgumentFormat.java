package pieface.argumentparser;

public class ArgumentFormat
{
	private String flags[];
	private int numOfFlagParams[];
	private boolean valid;
	private boolean restrictive;

	//default format allows no flag parameters
	public ArgumentFormat()
	{
		this(0);
	}

	//can be non-restrictive on flags, sets const amount of params for all flags
	public ArgumentFormat(int numOfFlagParams)
	{
		this.numOfFlagParams = new int[1];
		this.numOfFlagParams[0] = numOfFlagParams;
		this.restrictive = false;
		this.valid = true;
	}

	public ArgumentFormat(String... flags)
	{
		this.valid = true;
		this.restrictive = true;
		this.flags = new String[flags.length];
		for(int i = 0; i < this.flags.length; i++) this.flags[i] = "";
		this.numOfFlagParams = new int[flags.length];
		for(int i = 0; i < flags.length; i++)
		{
			int index = -1;
			for(int j = 0; j < flags.length; j++)
			{
				if(Character.isDigit(flags[i].charAt(j)))
				{
					index++;
				}
				else
				{
					break;
				}	
			}

			if(index>=0)
			{
				try
				{
					this.numOfFlagParams[i] = Integer.parseInt(flags[i].substring(0, index+1));
				}
				catch(Exception ex)
				{
					this.numOfFlagParams[i] = -1;
				}
			}
			else
			{
				this.numOfFlagParams[i] = 0;
			}

			if(!this.isAFlag(flags[i].substring(index+1)))
			{
				this.flags[i] = flags[i].substring(index+1);
			}
			else
			{
				this.numOfFlagParams[i] = -1;
			}	

			if(!(this.numOfFlagParams[i] >= 0 && this.flags[i].charAt(0) == '-'))
			{
				this.valid = false;
				return;
			}
		}
		
	}

	public boolean isValid() 
	{
		return valid;
	}

	public boolean isAFlag(String flag)
	{
		if(this.restrictive)
		{
			for(int i = 0; i < this.flags.length; i++)
				if(this.flags[i].equals(flag)) return this.valid;
			return false;
		}
		else
		{
			return flag.charAt(0) == '-';
		}
	}

	public int numOfParams(String flag)
	{
		if (this.restrictive)
		{
			for(int i = 0; i < this.flags.length; i++)
				if(this.flags[i].equals(flag)) return this.numOfFlagParams[i];
			return -1;
		}
		else
		{
			return this.numOfFlagParams[0];
		}
	}

	public String toString()
	{
		String out = "Flags and num of parameters: ";
		if (this.restrictive)
		{
			for(int i = 0; i < this.flags.length; i++)
				out+= this.flags[i] + "," + this.numOfFlagParams[i] + ", ";
		}
		else
		{
			out += "<any flag>," + this.numOfFlagParams[0];
		}	

		return out;
	}
}
