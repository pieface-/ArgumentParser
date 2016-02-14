#ifndef ARGFORMAT_H
#define ARGFORMAT_H

class ArgFormat
{

private:
	char* flags[];
	int numOfFlagParams[];
	bool valid;
	bool restrictive;

public:
	ArgFormat(int);
	ArgFormat(char*[]);
	bool isValid():
	bool isAFlag(char*);
	int numOfParams(char*);
	char* toString();

};

#endif
