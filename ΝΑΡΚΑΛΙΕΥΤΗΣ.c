#include <stdio.h>
#include <stdlib.h>


int vobves(char **p,int Y,int X,int b,int n);

void print(char **p, int b, int n);

int main (int argc,char *argv)
{
	int a,b,n,i,y,x,j;
	char **pin;
	system ("chcp 1253");
	printf ("пкгйтяокоцисте паяайакы та ояиа тоу пимайа \n");
	scanf ("%d %d",&b,&n);
	printf ("йатавыяисте том аяихло тым болбым\n");
	scanf ("%d",&a);
	pin=malloc(b*sizeof(char*));
	for (i=0;i<b;i++)
	{
	
		pin[i]=malloc(n*sizeof(char));
	}
	
    for (i=0; i<b; i++)
    {
	
     for (j=0; j<n; j++)
     {
	 
     {
      pin[i][j]=' ';
	srand(time(NULL));
     }
   }
   }  
	for(i=0;i<a;i++)
	{
		do
		{
			y=rand()%b;
			x=rand()%n;
		}
		while(pin[y][x]=='*');
		pin[y][x]='*';
	}
	for (i=0;i<b;i++)
	{
	
	 for (j=0;j<n;j++)
	 {
	 
	    if (i>=0 && i<b && j>=0&&j<n)
	    {
		
	     if (pin[i][j]!='*')
	     {
		 
	      pin[i][j]=vomves(pin,i,j,b,n)+ 48;
	     }
	  }
     }
   }
	print (pin,b,n);
	return 0;
	free (**pin);
}




void print(char **pin, int b, int n)
{
	int i,j;
	for (i=0;i<b;i++)
	{
		for(j=0;j<n;j++)
		printf("%c",pin[i][j]);
	printf("\n");
	}
		
}



int vomves(char **p,int Y,int X,int b,int n)
{
	int i,j,c;
	c=0;
	for (i=Y-1;i<=Y+1;i++)
	{
	
	  	for (j=X-1;j<=X+1;j++)
	  	{
		
	  	  	if (i>=0 && i<b && j>=0 && j<n)
	  	  	{
				
	  	  		if (p[i][j]=='*')
	  	  		{
				c++;
                }
            }
        }
    }
return c;

}

