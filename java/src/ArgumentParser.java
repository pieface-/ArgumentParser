package pieface.argumentparser;
//HOW TO USE:
//ArgumentFormat object defines flags
//flag is "-<flag>" or "<num>-<flag>"
//num is how many parameters the flag takes
//if you use default constructor, the value of <flag> does not matter, no flag options available
//if you use 1 int constructor, value of <flag> does not matter, all flags use same num of options
//like printf, you can define as many flags as you like
//
//Arg takes args[] from main method (or a string) and an ArgumentFormat
//it seperates args[] into user accessible arrays
//.isValid() - returns true if parse according to format was successful (usually only false if user enters nonexistent flags or puts a flag with options on the end)
//.isAFlag(String) - returns if a given flag was entered
//.getFlagParams(String) - returns the paramaters of the given flag, if applicable. else is ""
//.getArg(int) - returns the given argument (not a flag)
//.getFlagCount() - returns number of flags
//.getArgCount() - returns number of args

public class ArgumentParser
{
	public static void main(String args[])
	{
		ArgumentFormat f = new ArgumentFormat("-flag1","2-flag2");
		Arg a = new Arg("t -flag2 t c",f);
		System.out.println(f);
		System.out.println(a);
		if(a.isAFlag("-flag2")) System.out.println(a.getFlagParams("-flag2"));
	}
}
