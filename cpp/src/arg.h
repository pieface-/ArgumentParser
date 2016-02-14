#ifndef ARG_H
#define ARG_H

class Arg
{

private:
	char* flags[];
	char* flagsParam[];
	char* args[];
	bool valid;

public:
	Arg(char* args[]);
	Arg(char* args[], &ArgFormat);
	char* toString();
	bool isAFlag(char*);
	char* getFlagParams(char*);
	bool isValid() { return valid };
	int getArgCount();
	int getFlagCount();
	char* getArg(int);
	//void trim();

};

#endif
